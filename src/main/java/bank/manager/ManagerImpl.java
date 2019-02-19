
    package bank.manager;

    import bank.dao.BankDaoInterface;
    import bank.model.LoanBean;
    import bank.model.LoanInfo;
    import bank.model.RecordBean;
    import bank.model.UserBean;
    import bank.util.*;
    import org.hibernate.HibernateException;
    import org.hibernate.LockMode;
    import org.hibernate.Session;
    import org.hibernate.query.Query;
    import org.springframework.orm.hibernate5.HibernateCallback;
    import org.springframework.orm.hibernate5.HibernateTemplate;
    import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

    import java.sql.Timestamp;
    import java.util.Date;
    import java.util.Iterator;
    import java.util.List;
    import java.util.Map;
    import java.util.concurrent.ConcurrentHashMap;

    /**
     * 实现Manager接口
     * @author 22222jh
     * */
    public class ManagerImpl extends HibernateDaoSupport implements Manager {
      /*
      *使用volatile关键字，保证当线程A创建单例对象之后，线程B可以及时看到
      */
        private volatile static ManagerImpl manager;
        private BankDaoInterface getDao;

        /*
        * 使用线程安全的ConcurrentHashMap作为缓存存储每个用户的用户名密码用于登录
        * */
        private Map userinfo = new ConcurrentHashMap();
        private ManagerImpl() throws Exception {
//            udf = UserDaoFactory.getUserDaoFactory();
//            getDao = udf.getBankDao();
        }
        public static ManagerImpl getManager() throws Exception {
            if (manager==null) {
                synchronized (ManagerImpl.class) {
                    if (manager == null) {
                        manager = new ManagerImpl();
                    }
                }
            }
            return manager;
        }

        public void setGetDao(BankDaoInterface getDao) {
            this.getDao = getDao;
        }

        /**
         * 查询方法
         * */
        @Override
        public double inquiry(int id){
            UserBean user = this.getHibernateTemplate().load(UserBean.class,id);
            user.setEnable(true);
            this.getHibernateTemplate().update(user);
            return user.getMoney();
        }

        /**
         * 取款方法
         * */
        @Override
        public void withdrawals(int id,double money) throws AccountOverDrawnException, InvalidDepositException {
            if (money<0){
                throw new InvalidDepositException();
            }
            else {
                HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
                UserBean user = hibernateTemplate.load(UserBean.class,id);
                if (user.getMoney() < money) {
                    throw new AccountOverDrawnException();
                }
                else {
                    user.setMoney(standardMoney(user.getMoney()-money));
                    hibernateTemplate.update(user);
                }
            }
        }

        /**
         * 存款方法
         * */
        @Override
        public void deposit(int id,double money) throws InvalidDepositException {
            if (money<0) {
                throw new InvalidDepositException();
            }
            else {
                HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
                UserBean user;
                user = hibernateTemplate.load(UserBean.class,id);
                user.setMoney(standardMoney(user.getMoney()+money));
                hibernateTemplate.update(user);
            }
        }

        /**
         * 注册方法
         * */
        @Override
        public boolean register(String username,String password,String email) throws UsernameExistException {
            HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
            UserBean user = null;
            user = new UserBean();
            user.setUser(username);
            user.setPassword(password);
            user.setEmail(email);
            if (isUserExist(username,email)!=-1){
                throw new UsernameExistException(username);
            }
            hibernateTemplate.save(user);
            return getDao.register(user);
        }

        /**
         * 登录方法
         * */
        @Override
        public UserBean login(String username,String password) throws UserNotExistException,WrongPasswordException {
            HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
            int id = isUserExist(username,username);
            if (id==-1){
                throw new UserNotExistException(username);
            }
            if (userinfo.containsKey(username)){
                boolean flag = ((UserBean)userinfo.get(username)).getPassword().equals(password);
                if (flag==false){
                    throw new WrongPasswordException();
                }
                return (UserBean)userinfo.get(username);
            }
            UserBean userBean = hibernateTemplate.load(UserBean.class,id);
            userBean.setPassword(password);
            boolean flag = getDao.login(userBean.getUser(),password);
            if (flag){
                if (userinfo.get(username)==null){
                    userinfo.put(username,userBean);
                }
            }
            return userBean;
        }

        /**
         * 转账方法
         * */
        @Override
        public void transfer(int id,double money,String clientName) throws UserNotExistException,AccountOverDrawnException,IllegalOperateException{
            MD5 md5 = new MD5();
            String che = md5.getMD5("che");
            String ck = md5.getMD5("ck");
            if (!clientName.contains(che)||!clientName.contains(ck)){
                throw new IllegalOperateException();
            }
            clientName = clientName.substring(clientName.indexOf(che)+che.length(),clientName.lastIndexOf(ck));
            HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
            UserBean user;
            user = hibernateTemplate.load(UserBean.class,id);
            if (money<0){
                throw new InvalidDepositException();
            }
            else {
                if (user.getMoney() < money) {
                    throw new AccountOverDrawnException();
                }
            }
            int clientId = isUserExist(clientName,clientName);
            if (clientId==-1){
                throw new UserNotExistException(clientName);
            }
            else {
                UserBean client = hibernateTemplate.load(UserBean.class, clientId);
                user.setMoney(standardMoney(user.getMoney()-money));
                client.setMoney(standardMoney(client.getMoney() + money));
                hibernateTemplate.update(client);
                hibernateTemplate.update(user);
            }
        }

        /**
         * 用于获取指定页面的信息
         * */
        @Override
        public List getInfo(int id,int page){
            int maxPages = getMaxPages(id);
            if (maxPages == 0){
                throw new NoTrinfoException();
            }
            if (page>maxPages){
                page = maxPages;
            }
            HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
            int finalPage = (page-1)*8;
            List list = hibernateTemplate.execute(new HibernateCallback<List>() {
                @Override
                public List doInHibernate(Session session) throws HibernateException {
                    Query query = session.createQuery("from RecordBean r where r.userBean.id = :id");
                    query.setParameter("id",id);
                    query.setMaxResults(8);
                    query.setFirstResult(finalPage);
                    return query.list();
                }
            });
            return list;
        }

        /**
         * 用于获得最大页数
         * */
        @Override
        public int getMaxPages(int id){
            int maxPages = 0;
            HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
            Long count = (Long) hibernateTemplate.find("select count(*) from RecordBean as r where r.userBean.id = ?0",id).iterator().next();
            int records = count.intValue();
            maxPages = (records + 7) / 8;
            return maxPages;
        }


        /**
         * 将存入金额保留两位小数
         * */
        private double standardMoney(double money){
            String str = String.format("%.2f",money);
            return Double.parseDouble(str);
        }

        /**
         * 判断用户是否存在
         * 并返回用户的id
         * */
        public int isUserExist(String name,String email){
            HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
            Iterator iterator = hibernateTemplate.find("select u.id from UserBean as u where u.user = ?0 or u.email = ?1",
                    new Object[]{name,email}).iterator();
            int id;
            if (iterator.hasNext()){
                id = (Integer)iterator.next();
            }
            else {
                id = -1;
            }
            return id;
        }

        /**
         * 获得贷款信息
         * */
        public LoanInfo getLoanInfo(int id){
            HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
            LoanBean loanBean = null;
            LoanInfo loanInfo = new LoanInfo();
            loanBean = hibernateTemplate.load(LoanBean.class,id);
            loanInfo.setCanloan(loanBean.getLeftAmount());
            loanInfo.setLeftduration(loanBean.getDuration()-loanBean.getPeriod());
//            得到当前日期与贷款日期的日期差
            long time = new Date().getTime()-loanBean.getLoantime().getTime();
            Long days = time/(1000*60*60*24);
            loanInfo.setLoanduration(loanBean.getDuration()==0?0:days.intValue()-loanBean.getPeriod());
            int terms = loanInfo.getLoanduration()>0?loanInfo.getLoanduration():0;
            terms = terms>loanInfo.getLeftduration()?loanInfo.getLeftduration():terms;
            loanInfo.setPayback(standardMoney(loanBean.getPrincipal()*(1+loanBean.getInterest()/100)*terms));
            loanInfo.setAllLoan(standardMoney(loanBean.getPrincipal()*(1+loanBean.getInterest()/100)*loanInfo.getLeftduration()));
            return loanInfo;
        }

        /**
         * 贷款方法
         * */
        public void loan(int id,double money,int time){
            HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
            UserBean userBean = hibernateTemplate.load(UserBean.class,id);
            LoanBean loanBean = hibernateTemplate.load(LoanBean.class,id);
            userBean.setMoney(standardMoney(userBean.getMoney()+money));
            loanBean.setLeftAmount(loanBean.getLeftAmount()-money);
            loanBean.setDuration(time);
            loanBean.setInterest(getInterest(time));
            loanBean.setPrincipal(standardMoney(money/time));
            loanBean.setLoantime(new Timestamp(System.currentTimeMillis()));
            hibernateTemplate.update(userBean);
            hibernateTemplate.update(loanBean);
        }

        /**
         * 偿还贷款方法
         * */
        public void repayment(int id,int terms) throws AccountOverDrawnException{
            HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
            UserBean userBean = hibernateTemplate.load(UserBean.class,id);
            LoanBean loanBean = hibernateTemplate.load(LoanBean.class,id);
            double payMoney = standardMoney(loanBean.getPrincipal()*(1+loanBean.getInterest()/100)*terms);
            if (userBean.getMoney()<payMoney){
                throw new AccountOverDrawnException();
            }
            userBean.setMoney(standardMoney(userBean.getMoney()-payMoney));
            loanBean.setPeriod(loanBean.getPeriod()+terms);
            loanBean.setPaytime(new Timestamp(System.currentTimeMillis()));
            loanBean.setLeftAmount(loanBean.getLeftAmount()+loanBean.getPrincipal()*terms);
            if (loanBean.getDuration()==loanBean.getPeriod()){
                loanBean.setDuration(0);
                loanBean.setPeriod(0);
                loanBean.setPrincipal(0);
                loanBean.setInterest(0);
            }
            hibernateTemplate.update(userBean);
            hibernateTemplate.update(loanBean);
        }

        private double getInterest(int time){
            double interest;
            switch (time){
                case 1:
                    interest = 4.35;
                    break;
                case 2:
                    interest = 4.35;
                    break;
                case 3:
                    interest = 4.75;
                    break;
                case 4:
                    interest = 4.75;
                    break;
                case 5:
                    interest = 4.75;
                    break;
                 default:
                        interest = 4.90;
                        break;
            }
            return interest;
        }

    }

package bank.manager;

import bank.model.LoanBean;
import bank.model.RecordBean;
import bank.model.UserBean;
import bank.util.MD5;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

@Aspect
public class SaveTrInfoImpl extends HibernateDaoSupport implements SaveTrInfo {

    @Override
    @AfterReturning("execution(void bank.manager.ManagerImpl.* (int,..))&&args(id,..)")
    public void saveRecord(JoinPoint jp,int id){
        RecordBean[] rb = getRecordBean(jp, id);
        if (rb==null){
            return;
        }
        for (RecordBean rbean:rb){
            this.getHibernateTemplate().save(rbean);
        }
    }

    public RecordBean[] getRecordBean(JoinPoint jp,int id){
        String methodName = jp.getSignature().getName();
        UserBean userBean = this.getHibernateTemplate().load(UserBean.class,id);
        String mes = null;
        switch (methodName){
            case "withdrawals":
                mes = "取款";
                break;
            case "deposit":
                mes = "存款";
                break;
            case "transfer":
                MD5 md5 = new MD5();
                String che = md5.getMD5("che");
                String ck = md5.getMD5("ck");
                Object[] argstr = jp.getArgs();
                double moneytr = standardMoney((Double) argstr[1]);
                String clientName = (String)argstr[2];
                clientName = clientName.substring(clientName.indexOf(che)+che.length(),clientName.lastIndexOf(ck));
                UserBean userBean1 = (UserBean) this.getHibernateTemplate().find("from UserBean as u where u.user = ?0 or u.email = ?1",
                        new Object[]{clientName,clientName}).iterator().next();
                String mes1 = "转账给"+userBean1.getUser();
                RecordBean rb1 = new RecordBean();
                rb1.setMes(mes1);
                rb1.setMoney(moneytr);
                rb1.setUserBean(userBean);
                RecordBean rb2 = new RecordBean();
                String mes2 = "收到来自"+userBean.getUser()+"的转账";
                rb2.setUserBean(userBean1);
                rb2.setMoney(moneytr);
                rb2.setMes(mes2);
                return new RecordBean[]{rb1,rb2};
            case "loan":
                mes = "贷款";
                break;
            case "repayment":
                Object[] argsrepay = jp.getArgs();
                int terms = (Integer)argsrepay[1];
                mes = "偿还贷款";
                LoanBean loanBean = this.getHibernateTemplate().load(LoanBean.class,id);
                double payMoney = standardMoney(loanBean.getPrincipal()*(1+loanBean.getInterest()/100)*terms);
                RecordBean rbrepay = new RecordBean();
                rbrepay.setMes(mes);
                rbrepay.setMoney(payMoney);
                rbrepay.setUserBean(userBean);
                return new RecordBean[]{rbrepay};
            default:
                return null;
        }
        RecordBean rb = new RecordBean();
        Object[] args = jp.getArgs();
        double money = standardMoney((Double) args[1]);
        rb.setUserBean(userBean);
        rb.setMes(mes);
        rb.setMoney(money);
        return new RecordBean[]{rb};
    }

    private double standardMoney(double money){
        String str = String.format("%.2f",money);
        return Double.parseDouble(str);
    }
}


    package bank.manager;

    import bank.model.LoanBean;
    import bank.model.LoanInfo;
    import bank.model.UserBean;
    import bank.util.*;

    import java.util.List;

    /**
     * 业务层接口
     *
     * @author 22222jh
     * */
    public interface Manager{
        /**
         * 查询方法
         * @return double 返回查询到的余额
         * */
        double inquiry(int id);

        /**
         * 取款方法
         * @param outmoney 取出的金额
         * @exception InvalidDepositException,AccountOverDrawnException 取款金额不足，取款金额为负
         * */
        void withdrawals(int id, double outmoney)throws InvalidDepositException,AccountOverDrawnException;

        /**
         * 存款方法
         * @param inmoney 存入的金额
         * @exception InvalidDepositException 存入金额为负
         * */
        void deposit(int id, double inmoney)throws InvalidDepositException;

        /**
         * 退出方法，用于关闭连接池之类，关闭缓存之类的操作
         * */
//        void exit();

        /**
         * 注册功能
         *
         * @param username 用户名
         * @param password 密码
         * @return boolean 注册成功与否
         * @exception UsernameExistException 用户名已存在
         * */
        boolean register(String username, String password, String email) throws UsernameExistException;

        /**
         * 登录功能
         * @param username 用户名
         * @param password 密码
         * @return UserBean 返回登录成功的用户信息
         * */
        UserBean login(String username, String password);

        /**
         * 转账功能
         *
         * @param clientName 转账用户
         * @param money 转账金额
         * @return boolean 转账成功与否
         * @exception UserNotExistException 转账用户不存在
         * */
        void transfer(int id, double money, String clientName) throws UserNotExistException,AccountOverDrawnException,IllegalOperateException;

        List getInfo(int id, int page);

        int getMaxPages(int id);

        int isUserExist(String name, String email);

        LoanInfo getLoanInfo(int id);

        void loan(int id, double money, int time);

        void repayment(int id, int terms) throws AccountOverDrawnException;
    }

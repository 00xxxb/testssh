package bank.dao;

import bank.model.UserBean;
import bank.util.UsernameExistException;

/**
 * @author 22222jh
 * */
public interface BankDaoInterface {

    /**
     * 注册功能
     *
     * @return boolean 返回注册的成功与否
     * */
    boolean register(UserBean userBean) throws UsernameExistException;

    /**
     * 登录功能
     *
     * @return boolean 登录的成功与否
     * @param username 用户名
     * @param password 密码
     * */
    boolean login(String username, String password);

    /**
     * 存储功能
     * */
    /*void saveMoney(double money);*/

    /**
     * 查询功能
     *
     * @return double
     * */
    /*double getBalance();*/

    /**
     * 转账功能
     * */
    /*boolean transfer(String username, double money) throws UsernameExistException;*/

    /**
     * 获得UserBean
     *
     * @return UserBean UserBean对象
     * */
//    public UserBean getUserBean();

    /**
     * 设置UserBean
     *
     * @param username 登录时传来的用户名
     * @param password 登录时传来的密码
     * */
//    public void setUserBean(String username, String password);

    /**
     * 修改路径信息
     *
     * @param path 针对web项目，传递当前项目的路径
     * */
    void setPath(String path);

}

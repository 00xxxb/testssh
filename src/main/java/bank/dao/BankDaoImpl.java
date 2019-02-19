package bank.dao;

import bank.model.UserBean;
import bank.util.MD5;
import bank.util.UserNotExistException;
import bank.util.UsernameExistException;
import bank.util.WrongPasswordException;

import java.io.*;
import java.util.Properties;

/**
 * 使用文件存储用户名和密码
 * 仅仅实现登录验证和注册功能
 *
 * @author 22222jh
 * */
public class BankDaoImpl implements BankDaoInterface {

    private String path;

    public void setPath(String path) {
        this.path = System.getProperty("rootPath");
    }

    public BankDaoImpl(){

    }
    /**
     * 创建Properties对象
     * */
    Properties pro;
    {
        pro = new Properties();
    }

    private MD5 md5 = new MD5();


    /**
     *实现接口的注册方法
     * */
    @Override
    public boolean register(UserBean userBean) throws UsernameExistException {
        if(userBean.getUser()==null||userBean.getUser().length()==0){
            return false;
        }
        if (userBean.getPassword()==null||userBean.getPassword().length()==0){
            return false;
        }
        File file = new File(path+userBean.getUser()+".properties");

        if (file.exists()) {
            throw new UsernameExistException(userBean.getUser());
        }
        synchronized (pro) {
            try {
                OutputStream os = new FileOutputStream(file);
                pro.setProperty("username", userBean.getUser());
                //注册时使用MD5加密
                pro.setProperty("password", md5.getMD5(userBean.getPassword()));
                pro.store(os, "注册信息");
                os.close();
            } catch (IOException e) {
                return false;
            }
            return true;
        }
    }


   /**
    *  实现接口的登录方法
    *  */
    @Override
    public boolean login(String username,String password) throws UserNotExistException,WrongPasswordException{
        File file  = new File(path+username+".properties");
        String name;
        String word;
        if (!file.exists()) {
            throw new UserNotExistException(username);
        }
        synchronized (pro) {
            try {
                InputStream is = new BufferedInputStream(new FileInputStream(file));
                pro.load(is);
                name = pro.getProperty("username");
                word = pro.getProperty("password");
                is.close();
            } catch (IOException e) {
                return false;
            }
            //登录时，需对登录密码进行加密后进行比较
            if (name.equals(username) && word.equals(password)) {
                return true;
            } else {
                throw new WrongPasswordException();
            }
        }
    }

}

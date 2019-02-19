package bank.util;


/**
 * 在登录时可能产生的异常
 * 用户不存在
 * @author 22222jh
 * */
public class UserNotExistException extends RuntimeException {
    public UserNotExistException(){
        super();
    }

    public UserNotExistException(String mes){
        super(mes);
    }
}

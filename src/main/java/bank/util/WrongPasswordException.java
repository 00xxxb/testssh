package bank.util;

/**
 *密码错误异常
 * @author 22222jh
 * */
public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(){
        super();
    }

    public WrongPasswordException(String mes){
        super(mes);
    }
}

package bank.util;

import java.io.IOException;

/**
 * 在注册时产生的异常
 * 用户名已存在
 * @author 22222jh
 * */
public class UsernameExistException extends IOException {
    public UsernameExistException(){
        super();
    }
    public UsernameExistException(String str){
        super(str);
    }

    @Override
    public String toString() {
        return "该用户名已存在！";
    }
}

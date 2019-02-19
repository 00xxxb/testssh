package bank.util;

public class NoTrinfoException extends RuntimeException {
    public NoTrinfoException(){
        super();
    }

    public NoTrinfoException(String mes){
        super(mes);
    }
}

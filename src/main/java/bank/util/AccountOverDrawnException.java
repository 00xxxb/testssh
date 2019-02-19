
    package bank.util;


    /**
     * 判断取款金额是否小于余额的异常
     * @author 22222jh
     * */
    public class AccountOverDrawnException extends ArithmeticException{
        public AccountOverDrawnException(){
            super();
        }
        public AccountOverDrawnException(String msg){
            super(msg);
        }

        @Override
        public String toString() {
            return "取款金额超出余额！";
        }
    }

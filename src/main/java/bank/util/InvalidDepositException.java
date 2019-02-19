
    package bank.util;

    /**
     * 判断输入金额是否为负的异常
     * @author 22222jh
     * */
    public class InvalidDepositException extends ArithmeticException {
        public InvalidDepositException(){
            super();
        }
        public InvalidDepositException(String msg){
            super(msg);
        }

        @Override
        public String toString() {
            return "输入金额不能为负";
        }
    }

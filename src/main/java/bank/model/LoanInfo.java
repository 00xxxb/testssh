package bank.model;

public class LoanInfo {
//    当前应还金额
    private double payback;
//    剩余额度
    private double canloan;
//    剩余期数
    private int leftduration;
//    这次该还期数
    private int loanduration;
//    所有的贷款金额
    private double allLoan;

    public double getAllLoan() {
        return allLoan;
    }

    public void setAllLoan(double allLoan) {
        this.allLoan = allLoan;
    }

    public double getPayback() {
        return payback;
    }

    public void setPayback(double payback) {
        this.payback = payback;
    }

    public double getCanloan() {
        return canloan;
    }

    public void setCanloan(double canloan) {
        this.canloan = canloan;
    }

    public int getLeftduration() {
        return leftduration;
    }

    public void setLeftduration(int leftduration) {
        this.leftduration = leftduration;
    }

    public int getLoanduration() {
        return loanduration;
    }

    public void setLoanduration(int loanduration) {
        this.loanduration = loanduration;
    }

    @Override
    public String toString() {
        return "LoanInfo{" +
                "payback=" + payback +
                ", canloan=" + canloan +
                ", leftduration=" + leftduration +
                ", loanduration=" + loanduration +
                '}';
    }
}

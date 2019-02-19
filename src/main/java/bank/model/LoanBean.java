package bank.model;

import java.sql.Timestamp;

public class LoanBean {
    private int UserId;
//    剩余额度
    private double leftAmount;
//    贷款期数
    private int duration;
//    贷款时间
    private Timestamp loantime;
//    每期需还款金额
    private double  principal;
//    利息
    private double interest;
    private UserBean userBean;
//    已经还清的期数
    private int period;
//    还款日期
    private Timestamp paytime;

    public Timestamp getPaytime() {
        return paytime;
    }

    public void setPaytime(Timestamp paytime) {
        this.paytime = paytime;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getPrincipal() {
        return principal;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public double getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(double leftAmount) {
        this.leftAmount = leftAmount;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Timestamp getLoantime() {
        return loantime;
    }

    public void setLoantime(Timestamp loantime) {
        this.loantime = loantime;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }
}

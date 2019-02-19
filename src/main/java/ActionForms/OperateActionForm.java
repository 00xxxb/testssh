package ActionForms;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;


/**
 * 收集操作信息的表单
 * @author 22222jh
 * */
public class OperateActionForm extends ActionForm {
    private String deposit;
    private String withdrawals;
    private String transuser;
    private String transmoney;
    private String reuser;
    private String repassword;
    private String louser;
    private String lopassword;
    private String email;
    private double loanmoney;
    private int pasttime;

    public double getLoanmoney() {
        return loanmoney;
    }

    public void setLoanmoney(double loanmoney) {
        this.loanmoney = loanmoney;
    }

    public int getPasttime() {
        return pasttime;
    }

    public void setPasttime(int pasttime) {
        this.pasttime = pasttime;
    }

    public String getDeposit() {
        return deposit;
    }

    public String getTransuser() {
        return transuser;
    }

    public String getTransmoney() {
        return transmoney;
    }

    public String getWithdrawals() {
        return withdrawals;
    }

    public void setWithdrawals(String withdrawals) {
        this.withdrawals = withdrawals;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public void setTransmoney(String transmoney) {
        this.transmoney = transmoney;
    }

    public void setTransuser(String transuser) {
        this.transuser = transuser;
    }

    public String getLopassword() {
        return lopassword;
    }

    public String getLouser() {
        return louser;
    }

    public void setLopassword(String lopassword) {
        this.lopassword = lopassword;
    }

    public void setLouser(String louser) {
        this.louser = louser;
    }

    public String getReuser() {
        return reuser;
    }

    public void setReuser(String reuser) {
        this.reuser = reuser;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        deposit = null;
        transmoney = null;
        transuser = null;
        withdrawals = null;
        reuser=null;
        repassword=null;
        lopassword=null;
        louser=null;
        email = null;
    }
}

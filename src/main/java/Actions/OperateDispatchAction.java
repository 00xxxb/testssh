package Actions;

import ActionForms.OperateActionForm;
import bank.manager.Manager;
import bank.manager.ManagerImpl;
import bank.model.LoanBean;
import bank.model.LoanInfo;
import bank.model.UserBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 为了减少配置量，采用DispatchAction，对应银行主界面的各个操作
 * @author 22222jh
 * */
public class OperateDispatchAction extends DispatchAction {
    /**
    * 使用DispatchAction来替代filter进行验证拦截
    * */
    private Manager manager;

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String flag = "user";
        if (session.getAttribute(flag)==null){
            return mapping.findForward("back");
        }
        return super.execute(mapping, form, request, response);
    }

    /**
     * 对应查询方法
     * */
    public ActionForward inquiry(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        int id = (Integer) session.getAttribute("user");
        double money = manager.inquiry(id);
        request.setAttribute("money",money);
        return mapping.findForward("main");
    }

    /**
     * 对应存款功能
     * */
    public ActionForward deposit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        OperateActionForm oaf = (OperateActionForm)form;
        int id = (Integer)session.getAttribute("user");
        String deposit = oaf.getDeposit();
        double money = Double.parseDouble(deposit);
        manager.deposit(id,money);
        request.setAttribute("money",manager.inquiry(id));
        return mapping.findForward("main");
    }

    /**
     * 对应取款功能
     * */
    public ActionForward withdrawals(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        OperateActionForm oaf = (OperateActionForm)form;
        String withdrawals = oaf.getWithdrawals();
        double money = Double.parseDouble(withdrawals);
        int id = (Integer) session.getAttribute("user");
        manager.withdrawals(id,money);
        request.setAttribute("money",manager.inquiry(id));
        return mapping.findForward("main");
    }

    /**
     * 对应转账功能
     * */
    public ActionForward transfer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        OperateActionForm oaf = (OperateActionForm)form;
        String transuser = oaf.getTransuser();
        String transmoney = oaf.getTransmoney();
        int id = (Integer) session.getAttribute("user");
        double money = Double.parseDouble(transmoney);
        manager.transfer(id,money,transuser);
        request.setAttribute("money",manager.inquiry(id));
        return mapping.findForward("main");
    }

    public ActionForward loan(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        int id = (Integer)session.getAttribute("user");
        LoanInfo loanInfo = manager.getLoanInfo(id);
        request.setAttribute("loanInfo",loanInfo);
        return mapping.findForward("loan");
    }

    /**
     * 对应退出功能
     * */
    public ActionForward exit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        session.invalidate();
        return mapping.findForward("back");
    }
}

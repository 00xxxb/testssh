package Actions;

import ActionForms.OperateActionForm;
import bank.manager.Manager;
import bank.manager.ManagerImpl;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoanAction extends DispatchAction {
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

    public ActionForward loan(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        OperateActionForm oaf = (OperateActionForm)form;
        int time = oaf.getPasttime();
        double loanmoney = oaf.getLoanmoney();
        int id = (int)session.getAttribute("user");
        manager.loan(id,loanmoney,time);
        return mapping.findForward("loanInfo");
    }

    public ActionForward payback(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        int id = (int)session.getAttribute("user");
        int terms = Integer.parseInt(request.getParameter("terms"));
        manager.repayment(id,terms);
        return mapping.findForward("loanInfo");
    }
}

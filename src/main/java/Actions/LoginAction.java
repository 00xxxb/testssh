package Actions;

import ActionForms.OperateActionForm;
import bank.manager.Manager;
import bank.manager.ManagerImpl;
import bank.model.UserBean;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 控制登录的Action
 * @author 22222jh
 * */
public class LoginAction extends Action {
    private Manager manager;

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        OperateActionForm taf = (OperateActionForm) form;
        String louser = taf.getLouser();
        String lopassword = taf.getLopassword();
        UserBean userBean = manager.login(louser,lopassword);
        HttpSession session1 = request.getSession();
        session1.invalidate();
        HttpSession session = request.getSession();
        session.setAttribute("level",userBean.getLevel());
        session.setAttribute("user",userBean.getId());
        return mapping.findForward("main");
    }
}

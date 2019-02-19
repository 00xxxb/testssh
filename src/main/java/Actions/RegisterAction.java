package Actions;

import ActionForms.OperateActionForm;
import bank.manager.Manager;
import bank.manager.ManagerImpl;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 控制注册的Action
 * @author 22222jh
 * */
public class RegisterAction extends Action {
    private Manager manager;

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        OperateActionForm raf = (OperateActionForm)form;
        String username = raf.getReuser();
        String password = raf.getRepassword();
        String email = raf.getEmail();
        manager.register(username,password,email);
        return mapping.findForward("reSuccess");
    }
}

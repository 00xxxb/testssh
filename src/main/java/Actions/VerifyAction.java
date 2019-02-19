package Actions;

import MailSupport.SendMail;
import bank.manager.Manager;
import bank.manager.ManagerImpl;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class VerifyAction extends DispatchAction {
    private Manager manager;

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public ActionForward verify(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        //这几行代码是用于设置浏览器不进行ajax页面的缓存
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
//        **********************************
        String name = request.getParameter("name");
        if (name==""){
            out.write("NO");
        }
        else {

            if ( manager.isUserExist(name,name)==-1){
                out.write("OK");
            }
            else {
                out.write("NO");
            }
        }
        return new Action().execute(mapping,form,request,response);
    }

    public ActionForward sendMail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        //这几行代码是用于设置浏览器不进行ajax页面的缓存
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
//        **********************************
        String name = request.getParameter("name");
        if (name==""){
            out.write("NO");
        }
        else {
            if ( manager.isUserExist(name,name)==-1){
                SendMail sendMail = new SendMail();
                sendMail.setRecipientAddress(name);
                String code = sendMail.getCode();
                session.setAttribute("code",code);
                out.write("OK");
            }
            else {
                out.write("NO");
            }
        }
        return new Action().execute(mapping,form,request,response);
    }

    public ActionForward check(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        //这几行代码是用于设置浏览器不进行ajax页面的缓存
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
//        **********************************
        String name = request.getParameter("name");
        if (name==""){
            out.write("NO");
        }
        else {
            String code = (String)session.getAttribute("code");
            if (name.equals(code)) {
                out.write("OK");
            } else {
                out.write("NO");
            }
        }
        return new Action().execute(mapping,form,request,response);
    }
}

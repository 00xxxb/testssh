package Actions;

import bank.manager.Manager;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.xpath.operations.Bool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowInfoAction extends Action {
    private Manager manager;

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = request.getParameter("page");
        HttpSession session = request.getSession();
        if (session.getAttribute("user")==null){
            return mapping.findForward("back");
        }
        int id = (Integer) session.getAttribute("user");
        if (session.getAttribute("pageInfo")==null) {
            session.setAttribute("pageInfo", new HashMap());
        }
        /*
        * 对每一个用户，根据不同的使用程度，开启线程，按照不同时间刷新缓存
        * 如果等级为最低级的用户，则不需要刷新缓存（甚至可以不使用缓存），因为使用频率低
        * */
        if (session.getAttribute("online")==null||!(Boolean) session.getAttribute("online")){
            char level = (Character) session.getAttribute("level");
            session.setAttribute("online",true);
        }
        Map map = (Map) session.getAttribute("pageInfo");
        int pageId;
        if (page == null) {
            pageId = 1;
        } else {
            pageId = Integer.parseInt(page);
        }
        int maxPages;
        List showList;
        if (map.get("maxPages") == null) {
            map.put("maxPages", manager.getMaxPages(id));
        }
        maxPages = (Integer) map.get("maxPages");
        if (map.get(pageId)==null){
            map.put(pageId,manager.getInfo(id,pageId));
        }
        showList = (List)map.get(pageId);
        session.setAttribute("maxPages", maxPages);
        session.setAttribute("page", pageId > maxPages ? maxPages : pageId);
        session.setAttribute("showList", showList);
        return mapping.findForward("showInfo");
    }
}



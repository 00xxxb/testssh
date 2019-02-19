package Actions;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 切换Locale
 * 迎合国际化
 * @author 22222jh
 * */
public class ChangeLanguageAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String lan = request.getParameter("lan");
        Locale locale = Locale.getDefault();
        String englishLocale = "en";
        String chineseLocale = "zh";
        if (englishLocale.equals(lan)){
            locale = new Locale("en","US");
        }
        else if (chineseLocale.equals(lan)){
            locale = new Locale("zh","CN");
        }
        this.setLocale(request,locale);
        return mapping.findForward("index");
    }
}

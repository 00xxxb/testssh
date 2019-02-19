package filter;

import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 编码格式拦截器
 * @author 22222jh
 * */
public class EncodingFilter extends UrlRewriteFilter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        super.doFilter(req,resp,chain);
    }
}

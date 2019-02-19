package filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 编码格式拦截器
 * @author 22222jh
 * */
public class EncodingFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException  {
        req.setCharacterEncoding("UTF-8");
        chain.doFilter(req,resp);
    }

    public void destroy() {

    }
}

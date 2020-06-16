package Filter;


import domain.User;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录验证的过滤器
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = "/*")
public class
LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //获取资源请求路径
        String uri = request.getRequestURI();
        //System.out.println(uri + "-------");
        //判断是否包含登录相关资源路径，排除css/js/图片/验证码等资源
        if (uri.contains("/login.jsp") || uri.contains("/loginServlet") ||  uri.contains("/css/") ||
                uri.contains("/bootStrap/") || uri.contains("/images/") || uri.contains("/js/") || uri.contains("/fonts/")){
            //包含，用户就是想登陆，放行
            chain.doFilter(req, resp);
        }else {
            User user = (User) request.getSession().getAttribute("user");
            if (user != null){
                chain.doFilter(req, resp);
            }else {
                request.getSession().setAttribute("login_msg","您尚未登录，请登录");
                response.sendRedirect("/login.jsp");
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

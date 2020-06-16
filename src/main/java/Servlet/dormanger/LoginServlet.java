package Servlet.dormanger;

import Dao.LoginDao;
import domain.Admin;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    public boolean check(String name, String pwd) {
        if (name == null || "".equals(name) || pwd == null || "".equals(pwd)) {
            return false;
        }
        return true;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("uesrName");
        String password = request.getParameter("pwd");
        String userType = request.getParameter("userType");
        if (request.getParameter("remember") != null) {
            Cookie cookie = new Cookie("admin", userName);
            Cookie cookie2 = new Cookie("pwd", password);
            cookie.setMaxAge(1000*60*60);
            cookie2.setMaxAge(1000*60*60);
            response.addCookie(cookie);
            response.addCookie(cookie2);
        } else {
            Cookie[] Cookies = request.getCookies();
            if (Cookies != null) {
                for (int i = 0; i < Cookies.length; i++) {
                    if(Cookies[i].getName().equals("admin")||Cookies[i].getName().equals("pwd")){
                        Cookies[i].setMaxAge(0);//删除cookie，没有条件限制会把session也一起删掉
                        response.addCookie(Cookies[i]);
                    }
                }
            }
        }

        if (check(userName, password) == false) {
            request.getSession().setAttribute("errormessage", "用户名或密码为空！");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            int result = -1;
            //管理员登入
            if (userType.equals("admin")) {
                try {
                    LoginDao lg = new LoginDao();
                    result = lg.login(userName, password, userType);
                    if (result <= 0) {
                        request.getSession().setAttribute("errormessage", "用户名或密码错误！");
                        response.sendRedirect("login.jsp");
                    } else {
                        User user = new User(userType,userName,password);
                        request.getSession().setAttribute("user",user);
                        Admin admin=lg.adminInformation(userName,password);
                        request.getSession().setAttribute("Admin",admin);
                        request.getRequestDispatcher("navPg.jsp").forward(request, response);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    LoginDao lg = new LoginDao();
                    result = lg.login(userName, password, userType);
                    if (result <= 0) {
                        request.getSession().setAttribute("errormessage", "用户名或密码错误！");
                        response.sendRedirect("login.jsp");
                    } else {
                        //宿管登入成功进入某个界面
                        System.out.println("宿管登入成功！");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
package Servlet.student;

import Dao.StuDao;
import until.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/updateStuServlet")
public class updateStuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage = request.getParameter("currentPage");
        String _stuNum = request.getParameter("_stuNum");//原先学号
        String stuNum = request.getParameter("stuNum");//修改后的学号
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String sex = request.getParameter("sex");
        String dormBuildId = request.getParameter("dormBuildId");
        String dormName = request.getParameter("dormName");
        String tel = request.getParameter("tel");
        String information = null;
        if ((StringUtil.isEmpty(name)==false||StringUtil.isEmpty(password)==false)){
            String error = "用户名或密码不能为空！";
            request.getRequestDispatcher("dormM_index.jsp?error=" + error).forward(request,response);
        }else if(!repassword.equals(password)){
            String error = "密码不一致，请重新填写";
            request.getRequestDispatcher("dormM_index.jsp?error=" + error).forward(request,response);
        }else {
            StuDao sd = new StuDao();
            boolean flag = false;
            try {
                flag = sd.updateStuInfo(stuNum,name,password,sex,dormBuildId,dormName,tel,_stuNum);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (flag) {
                information = "修改成功";
                request.getRequestDispatcher("showStuServlet?judge=ad&information=" + information + "&currentPage=" + currentPage).forward(request,response);
            } else {
                String error = "修改学生信息失败";
                request.getRequestDispatcher("dormM_index.jsp?error=" + error).forward(request,response);
            }
        }
    }
}

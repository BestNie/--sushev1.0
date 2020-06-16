package Servlet.dormanger;

import Dao.DormMangDao;
import until.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/addDormManServlet")
public class addDormManServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage = request.getParameter("currentPage");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String sex = request.getParameter("sex");
        String dormBuildId = request.getParameter("dormBuildId");
        String dormBuildName = request.getParameter("dormBuildName");
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String information = null;
        String dormBuildNamee = null;
        if (StringUtil.isEmpty(dormBuildId) && StringUtil.isEmpty(dormBuildName)) {
            //在宿舍管理员加了一列宿舍楼名称，判断宿舍楼ID和宿舍楼名称是不是对的上
            dormBuildNamee = dormBuildName.replace("栋","");
        }
        if ((StringUtil.isEmpty(userName)==false||StringUtil.isEmpty(password)==false)){
            String error = "用户名或密码不能为空！";
            request.getRequestDispatcher("dormM_index.jsp?&error=" + error).forward(request,response);
        }else if(!repassword.equals(password)){
            String error = "密码不一致，请重新填写";
            request.getRequestDispatcher("dormM_index.jsp?&error=" + error).forward(request,response);
        }else if (!dormBuildId.equals(dormBuildNamee)){
                String error = "楼栋id和楼栋名称对不上，请重新填写";
//                request.getSession().setAttribute("mainPage","dormanger/addDormMan.jsp");
                request.getRequestDispatcher("dormM_index.jsp?&error=" + error).forward(request,response);
        }else {
            DormMangDao dm = new DormMangDao();
            boolean flag = false;
            try {
                flag = dm.addDromManager(userName,password,name,sex,tel,dormBuildId,dormBuildName);
            } catch (Exception e) {
//                String error = "添加失败";
//                request.getRequestDispatcher("dormM_index.jsp?&error=" + error).forward(request,response);
            }
            if (flag) {
                information = "添加宿管成功";
                request.getRequestDispatcher("showDroServlet?information=" + information + "&currentPage=" + currentPage).forward(request,response);
            }
            else {
                String error = "添加宿管失败";
                request.getRequestDispatcher("dormM_index.jsp?&error=" + error).forward(request,response);
            }
        }
    }
}

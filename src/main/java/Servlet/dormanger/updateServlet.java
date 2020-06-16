package Servlet.dormanger;

import Dao.DormMangDao;
import until.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/updateServlet")
public class updateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage = request.getParameter("currentPage");
        String dormManId = request.getParameter("dormManId");
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
            dormBuildNamee = dormBuildName.replace("栋", "");
        }
        if ((StringUtil.isEmpty(userName) == false || StringUtil.isEmpty(password) == false)) {
            String error = "用户名或密码不能为空！";
            request.getRequestDispatcher("dormM_index.jsp?&error=" + error).forward(request, response);
        }else if (!dormBuildId.equals(dormBuildNamee)) {
            String error = "楼栋id和楼栋名称对不上，请重新填写";
            request.getRequestDispatcher("dormM_index.jsp?&error=" + error).forward(request, response);
        }else if (!repassword.equals(password)) {
            String error = "密码不一致，请重新填写";
            request.getRequestDispatcher("dormM_index.jsp?&error=" + error).forward(request, response);
        } else{
            DormMangDao sd = new DormMangDao();
            boolean flag = false;
            try {
                flag = sd.updateDromMInfo(dormManId, userName, password, sex, name, tel, dormBuildId, dormBuildName);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (flag) {
                information = "修改宿管信息成功";
                request.getRequestDispatcher("showDroServlet?information=" + information + "&currentPage=" + currentPage).forward(request, response);
            } else {
                String error = "修改宿管信息失败";
                request.getRequestDispatcher("dormM_index.jsp?&error=" + error).forward(request, response);
            }
        }
    }
}

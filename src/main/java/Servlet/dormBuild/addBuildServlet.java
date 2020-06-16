package Servlet.dormBuild;

import Dao.DormBuildDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addBuildServlet")
public class addBuildServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dormBuildName = request.getParameter("dormBuildName");
        String dormBuildDetail = request.getParameter("dormBuildDetail");
        DormBuildDao dao=new DormBuildDao();
        Boolean flag=false;
        try {
            flag=dao.addBuild(dormBuildName,dormBuildDetail);
        } catch (SQLException e) {
            e.printStackTrace();
            request.getSession().setAttribute("tsxx","<script language=\"javascript\">alert('插入成功！')</script>");
            request.getSession().setAttribute("mainPage", "dormBuild/showAllDorBuild.jsp");
            request.getRequestDispatcher("dormM_index.jsp").forward(request, response);
        }
        if (flag){
            request.getSession().setAttribute("tsxx","<script language=\"javascript\">alert('插入成功！')</script>");
        }
        request.getSession().setAttribute("mainPage", "dormBuild/addBuild.jsp");
        request.getRequestDispatcher("dormM_index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

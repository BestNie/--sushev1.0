package Servlet.dormBuild;

import Dao.DormBuildDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteBuildServlet")
public class deleteBuildServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage = request.getParameter("currentPage");
        String dormBuildId = request.getParameter("dormBuildId");
        String information = null;
        DormBuildDao db = new DormBuildDao();
        boolean flag = db.deleteBuild(dormBuildId);
        if (flag) {
            request.getRequestDispatcher("showBuildServlet?currentPage=" + currentPage).forward(request,response);
        } else {
            information = "删除失败";
            request.getRequestDispatcher("showBuildServlet?information=" + information + "&currentPage=" + currentPage).forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

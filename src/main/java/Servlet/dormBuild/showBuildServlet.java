package Servlet.dormBuild;

import domain.DormBuild;
import domain.Page;
import service.DormBuildService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/showBuildServlet")
public class showBuildServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage = request.getParameter("currentPage");//当前页面
        String rows = request.getParameter("rows");//每页显示的条数
        String searchName = request.getParameter("searchName");//关键字
        String action = request.getParameter("action");
        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }
        if (rows == null || "".equals(rows)) {
            rows = "10";
        }
        DormBuildService service = new DormBuildService();
        Page<DormBuild> db = null;
        try {
            db = service.findDorBuildByPage(searchName, currentPage, rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("pb", db);
        if ("showDormB".equals(action)) request.getSession().setAttribute("mainPage", "dormBuild/showAllDorBuild.jsp");
        if ("updateBuild".equals(action)) request.getSession().setAttribute("mainPage", "dormBuild/updateBuild.jsp");
        if ("addBuild".equals(action)) request.getSession().setAttribute("mainPage", "dormBuild/addBuild.jsp");
        request.getRequestDispatcher("dormM_index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

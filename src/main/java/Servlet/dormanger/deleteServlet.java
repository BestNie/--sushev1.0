package Servlet.dormanger;

import Dao.DormMangDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteServlet")
public class deleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage = request.getParameter("currentPage");
        String dormManId = request.getParameter("dormManId");
        String information = null;
        DormMangDao dm = new DormMangDao();
        boolean flag = dm.deleteDormManger(dormManId);
        if (flag) {
            request.getRequestDispatcher("showDroServlet?currentPage=" + currentPage).forward(request,response);
        } else {
            information = "删除失败";
            request.getRequestDispatcher("showDroServlet?information=" + information + "&currentPage=" + currentPage).forward(request,response);
        }
    }
}

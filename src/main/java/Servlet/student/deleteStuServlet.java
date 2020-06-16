package Servlet.student;

import Dao.StuDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteStuServlet")
public class deleteStuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage = request.getParameter("currentPage");
        String stuNum = request.getParameter("stuNum");
        String information = null;
        StuDao sd = new StuDao();
        boolean flag = sd.deleteStu(stuNum);
        if (flag) {
            request.getRequestDispatcher("showStuServlet?currentPage=" + currentPage).forward(request,response);
        } else {
            information = "删除失败";
            request.getRequestDispatcher("showStuServlet?information=" + information + "&currentPage=" + currentPage).forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

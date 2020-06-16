package Servlet.student;

import Dao.StuDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteRecordServlet")
public class deleteRecordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage = request.getParameter("currentPage");
        String recordId = request.getParameter("recordId");
        String information = null;
        StuDao sd = new StuDao();
        boolean flag = sd.deleteRecord(recordId);
        if (flag) {
            request.getRequestDispatcher("recordServlet?action=record&judge=dm&currentPage=" + currentPage).forward(request,response);
        } else {
            information = "删除失败";
            request.getRequestDispatcher("recordServlet?action=record&judge=dm&information=" + information + "&currentPage=" + currentPage).forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

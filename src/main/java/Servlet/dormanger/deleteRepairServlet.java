package Servlet.dormanger;

import Dao.RepairDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteRepairServlet")
public class deleteRepairServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage = request.getParameter("currentPage");
        String repairId = request.getParameter("repairId");
        String information = null;
        RepairDao repairDao = new RepairDao();
        boolean flag = repairDao.deleteRepair(repairId);
        if (flag) {
            request.getRequestDispatcher("dormBuildRepairServlet?judge=dm&currentPage=" + currentPage).forward(request,response);
        } else {
            information = "删除失败";
            request.getRequestDispatcher("dormBuildRepairServlet?judge=dm&information=" + information + "&currentPage=" + currentPage).forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

package Servlet.dormanger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateStatusServlet")
public class updateStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String status = request.getParameter("status");
        String currentPage = request.getParameter("currentPage");
        String repairId = request.getParameter("repairId");
        if ("已受理".equals(status)){
            request.getSession().setAttribute("tsxx","<script language=\"javascript\">alert('该单号已受理，请勿重复操作！')</script>");
            request.getRequestDispatcher("dormM_index.jsp?judge=ad").forward(request,response);
        }else {
            request.getRequestDispatcher("dormBuildRepairServlet?repairId=" + repairId + "&currentPage=" + currentPage).forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

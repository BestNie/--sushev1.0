package Servlet.remark;

import Dao.RemarkDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/remarkDeleteServlet")
public class deleteRemarkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String datail=request.getParameter("remark");
        String userName=request.getParameter("admin");
        RemarkDao rm=new RemarkDao();
        boolean flag = false;
        try {
            flag = rm.deleteRemark(datail);
            if (flag){
                List<String> list=rm.getRemarks(userName);
                request.getSession().setAttribute("remarkList",list);
                response.sendRedirect("dormM_index.jsp");
            }else {
                request.getSession().setAttribute("txxx","<script language=\"javascript\">alert('操作失败！')</script>");
                request.getRequestDispatcher("dormM_index.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

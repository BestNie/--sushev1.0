package Servlet.remark;

import Dao.RemarkDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/remarkServlet")
public class remarkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        RemarkDao rd=new RemarkDao();
        try {
            List<String> list=rd.getRemarks(userName);
            request.getSession().setAttribute("remarkList",list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String action = request.getParameter("action");
        if (("addrmk").equals(action))request.getSession().setAttribute("mainPage","addRemark.jsp");
        if (("bwl").equals(action))request.getSession().setAttribute("mainPage","remark.jsp");
        request.getRequestDispatcher("dormM_index.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

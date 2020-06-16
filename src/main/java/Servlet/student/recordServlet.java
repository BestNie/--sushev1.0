package Servlet.student;

import domain.DormManager;
import domain.Page;
import domain.Record;
import service.RecordService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/recordServlet")
public class recordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage = request.getParameter("currentPage");//当前页面
        String rows = request.getParameter("rows");//每页显示的条数
        String searchName=request.getParameter("searchName");//关键字
        String judge = request.getParameter("judge");
        String stuNumber = request.getParameter("stuNum");
        int dormBuildId = 0;//管理员登录，dormBuildId设置为0
        DormManager dm = (DormManager) request.getSession().getAttribute("DormManger");
        if (dm != null) {
            dormBuildId = dm.getDormBuildID();
        }
        if(currentPage == null || "".equals(currentPage)){
            currentPage = "1";
        }
        if(rows == null || "".equals(rows)){
            rows = "4";
        }

        //调用service查询
        RecordService service=new RecordService();
        Page<Record> pb = null;
        try {
            pb=service.findRecordStu(searchName,currentPage,rows,dormBuildId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //将pageBean存入request
        request.getSession().setAttribute("pb",pb);
        String action = request.getParameter("action");
        if ("record".equals(action))
            request.getSession().setAttribute("mainPage","student/record.jsp");
        if ("addabsenceInfo".equals(action)){
            request.getSession().setAttribute("mainPage","student/addAbsenceInfo.jsp");
        }

        if ("dm".equals(judge)){
            if (stuNumber == null) {
                request.getRequestDispatcher("dormHostel_index.jsp").forward(request, response);
            }
            else{
                request.getRequestDispatcher("dormHostel_index.jsp?stuNum=" + stuNumber).forward(request,response);
            }
        }else if ("ad".equals(judge)){
            request.getRequestDispatcher("dormM_index.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

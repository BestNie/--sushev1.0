package Servlet.student;

import domain.DormManager;
import domain.Page;
import domain.Student;
import service.DormService;
import service.StuService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/showStuServlet")
public class showStuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage = request.getParameter("currentPage");//当前页面
        String rows = request.getParameter("rows");//每页显示的条数
        String searchName=request.getParameter("searchName");//关键字
        String judge = request.getParameter("judge");
        int dormBuildId = 0;//表示系统管理员登录
        DormManager dm = (DormManager) request.getSession().getAttribute("DormManger");
        if(currentPage == null || "".equals(currentPage)){
            currentPage = "1";
        }
        if(rows == null || "".equals(rows)){
            rows = "10";
        }

        //调用service查询
        StuService service=new StuService();
        Page<Student> pb = null;
        try {
            if (dm != null){
                pb=service.findStuByPage(searchName,currentPage,rows,dm.getDormBuildID());
            }else{
                pb = service.findStuByPage(searchName,currentPage,rows,dormBuildId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //将pageBean存入request
        request.getSession().setAttribute("pb",pb);
        String path = "showAllStu.jsp";
        String action = request.getParameter("action");
        if ("addsu".equals(action)) path = "addStu.jsp";
        if ("showStu".equals(action)) path = "showAllStu.jsp";
        if ("update".equals(action)) path = "updateStu.jsp";
        //转发到stu_index.jsp宿管管理员管理
        request.getSession().setAttribute("mainPage","student/" + path);
        if ("sy".equals(action))
        request.getSession().setAttribute("mainPage",null);
        if("dm".equals(judge)) {
            request.getRequestDispatcher("dormHostel_index.jsp?judge=" + judge).forward(request,response);
        }else {
            request.getRequestDispatcher("dormM_index.jsp?judge=" + judge).forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

package Servlet.dormanger;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import domain.DormManager;
import domain.Page;
import service.DormService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/showDroServlet")
public class showDroServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage = request.getParameter("currentPage");//当前页面
        String rows = request.getParameter("rows");//每页显示的条数
        String searchName=request.getParameter("searchName");//关键字
        if(currentPage == null || "".equals(currentPage)){
            currentPage = "1";
        }
        if(rows == null || "".equals(rows)){
            rows = "4";
        }

        //调用service查询
        DormService service=new DormService();
        Page<DormManager> pb = null;
        try {
            pb=service.findStuByPage(searchName,currentPage,rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //将pageBean存入request
        request.getSession().setAttribute("pb",pb);
        String path = "showAllDor.jsp";
        String action = request.getParameter("action");
        if("addsu".equals(action)) path = "addDormMan.jsp";
        if ("showDormm".equals(action)) path = "showAllDor.jsp";
        if ("xgxx".equals(action)) path = "adminUpdate.jsp";
        if ("update".equals(action)) path = "update.jsp";
        request.getSession().setAttribute("mainPage","dormanger/"+path);
        if ("sy".equals(action))
            request.getSession().setAttribute("mainPage",null);
        //转发到dorm_manage.jsp宿管管理员管理
       /*response.sendRedirect("dormM_index.jsp");*/
        request.getRequestDispatcher("dormM_index.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

}

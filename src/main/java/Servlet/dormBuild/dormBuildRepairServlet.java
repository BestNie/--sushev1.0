package Servlet.dormBuild;

import Dao.RepairDao;
import domain.DormManager;
import domain.Page;
import domain.Repair;
import service.RepairService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/dormBuildRepairServlet")
public class dormBuildRepairServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage = request.getParameter("currentPage");//当前页面
        String rows = request.getParameter("rows");//每页显示的条数
        String searchName=request.getParameter("searchName");//关键字
        String judge = request.getParameter("judge");
        String action=request.getParameter("action");
        String repairId = request.getParameter("repairId");
        if (repairId != null){
            try {
                boolean flag = new RepairDao().updateRepair(Integer.parseInt(repairId));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
        RepairService rs = new RepairService();
        Page<Repair> pb = null;
        try {
            pb=rs.findRepairDorm(searchName,currentPage,rows,dormBuildId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //将pageBean存入request
        request.getSession().setAttribute("pb",pb);
        if ("bxjl".equals(action) || "sl".equals(action))
        request.getSession().setAttribute("mainPage","dormanger/repair.jsp");
        if ("add".equals(action))
            request.getSession().setAttribute("mainPage","dormanger/addRepair.jsp");
        if ("dm".equals(judge)){
            request.getRequestDispatcher("dormHostel_index.jsp?judge=dm").forward(request, response);
        }else if ("ad".equals(judge)){
            request.getRequestDispatcher("dormM_index.jsp?judge=ad").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

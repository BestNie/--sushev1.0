package Servlet.dormBuild;

import Dao.RepairDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/addRepairServlet")
public class addRepairServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dormBuildId = request.getParameter("dormBuildId");
        String repairer = request.getParameter("repairer");
        String dormName = request.getParameter("dormName");
        String project = request.getParameter("project");
        String content = request.getParameter("content");
        if ("".equals(repairer) || "".equals(dormName) || "".equals(project) || "".equals(content)) {
            request.getSession().setAttribute("error", "报修信息必须填写完整！");
            request.getRequestDispatcher("dormHostel_index.jsp").forward(request, response);
        } else {
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(d);
            RepairDao rd = new RepairDao();
            boolean flag = false;
            try {
                flag = rd.addRepair(dormBuildId, dormName, repairer, content, project, date);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (flag) {
                request.getSession().setAttribute("error", "保修成功！");
                request.getRequestDispatcher("dormHostel_index.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("error", "保修成功！");
                request.getRequestDispatcher("dormHostel_index.jsp").forward(request, response);
            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

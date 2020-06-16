package Servlet.dormBuild;

import Dao.DormBuildDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateBuildServlet")
public class updateBuildServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dormBuildName=request.getParameter("dormBuildName");
        String dormBuildDetail=request.getParameter("dormBuildDetail");
        String dormBuildId=request.getParameter("dormBuildId");
        DormBuildDao db=new DormBuildDao();
        boolean flag = db.updateBuild(dormBuildId,dormBuildName, dormBuildDetail);
        String information;
        if (flag){
            request.getSession().setAttribute("tsxx", "<script language=\"javascript\">alert('修改成功！')</script>");
            request.getRequestDispatcher("showBuildServlet?action=showDormB").forward(request,response);
        }else {
            request.getSession().setAttribute("tsxx", "<script language=\"javascript\">alert('修改失败！')</script>");
            request.getRequestDispatcher("showBuildServlet?action=showDormB").forward(request,response);
        }
    }
}

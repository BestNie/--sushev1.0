package Servlet.student;

import Dao.DormBuildDao;
import Dao.StuDao;
import domain.DormManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/absenceServlet")
public class absenceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stuNum = request.getParameter("stuNum");
        String absenceDate = request.getParameter("adsenceDate");
        String detail = request.getParameter("detail");
        String information = null;
        DormManager dm = (DormManager) request.getSession().getAttribute("DormManger");
        String dormBuildName = new DormBuildDao().findDormBuildName(stuNum);
        if (dormBuildName == null||!dormBuildName.equals(dm.getDormBuildName())){
            String error = "您所管理的" + dm.getDormBuildName() +"没找到学号为" + stuNum + "的学生";
            request.getSession().setAttribute("error",error);
            request.getRequestDispatcher("dormHostel_index.jsp?error=" + error + "&stuNum=").forward(request,response);
        }
        else{
            StuDao sd = new StuDao();
            boolean flag = sd.addAbsenceInfo(stuNum,absenceDate,detail);
            if (flag) {
                information = "添加成功";
                request.getRequestDispatcher("recordServlet?action=record&judge=dm&information=" + information).forward(request,response);
            } else {
                information = "添加失败";
                request.getRequestDispatcher("recordServlet?action=record&judge=dm&information=" + information).forward(request,response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

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

@WebServlet("/addRemarkServlet")
public class addRemarkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String datail = request.getParameter("bwl");
        int num=datail.toCharArray().length;
        if (num>30){
            request.getSession().setAttribute("error","您的备忘录不能超过个20字符");
            request.getRequestDispatcher("dormM_index.jsp").forward(request,response);
        }else if (num<=0){
            request.getSession().setAttribute("error","您的备忘录不能为空！");
            request.getRequestDispatcher("dormM_index.jsp").forward(request,response);
        }
        else {
            RemarkDao rd=new RemarkDao();
            try {
                boolean b = rd.addRemark(user, datail);
                if (b){
                    List<String> list=rd.getRemarks(user);
                    request.getSession().setAttribute("remarkList",list);
                    request.getSession().setAttribute("error","添加成功！");
                    request.getRequestDispatcher("dormM_index.jsp").forward(request,response);
                }else {
                    request.getSession().setAttribute("error","添加失败！");
                    request.getRequestDispatcher("dormM_index.jsp").forward(request,response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

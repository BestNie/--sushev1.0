package Servlet.dormanger;

import Dao.LoginDao;
import domain.Admin;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet("/uploadAdminServlet")
public class uploadAdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int adminId=Integer.valueOf(request.getParameter("adminId"));
        String image=request.getParameter("image");
        //创建上传工厂
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload upload=new ServletFileUpload(factory);
//        //设置缓冲期大小为8kb
//        factory.setSizeThreshold(8*1024);
//        //创建上传组件
//        ServletFileUpload servletFileUpload=new ServletFileUpload(factory);
//        //设置上传总大小
//        servletFileUpload.setSizeMax(1024*1024*80);
//        //单文件大小40MB
//        servletFileUpload.setFileSizeMax(1024*1024*40);
        //解析请求数据

        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            List<String> list=new ArrayList<>();
            for (FileItem item:fileItems
                 ) {
                if (item.isFormField()){            //如果是普通输入框
                    list.add(item.getString("UTF-8"));
                }else {
                    String name=item.getName();
                    if (!name.equals("")) {
                        int index = name.lastIndexOf(".");
                        String imageType = name.substring(index + 1);
                        if (("png").equals(imageType) || ("jpg").equals(imageType)) {
                            String dir = "D:\\测试工作室期末项目\\images\\";
                            File file1 = new File(dir);
                            if (!file1.exists()) file1.mkdirs();
                            String fileName = UUID.randomUUID().toString();//创建一个随机的名字
                            //保存导服务器上
                            image = fileName + name;
                            File file = new File(dir + image);//内存中
                            item.write(file);
                        }else {
                            request.getSession().setAttribute("tsxx","<script language=\"javascript\">alert('文件类型不符合要求！')</script>");
                            response.sendRedirect("dormM_index.jsp");
                        }
                    }
                }
            }
            LoginDao lg=new LoginDao();
            int count = lg.updateAdmin(adminId, list.get(0), list.get(2), list.get(1), list.get(3), list.get(4), image);
            if (count>0){
//                Admin admin=lg.adminInformation(list.get(0));
                Admin admin=lg.adminInformation(list.get(0), list.get(2));
                request.getSession().setAttribute("Admin",admin);
                response.sendRedirect("dormM_index.jsp");
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

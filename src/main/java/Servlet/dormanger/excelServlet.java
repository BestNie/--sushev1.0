package Servlet.dormanger;

import Dao.DormMangDao;
import domain.DormManager;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@WebServlet("/excelServlet")
public class excelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DormMangDao dao = new DormMangDao();
        List<DormManager> list = dao.findAllDor();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("宿管表");
        String[] title = {"编号", "姓名", "用户名", "性别", "管理楼栋", "电话"};
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < title.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(title[i]);
        }
        for (int i = 0; i < list.size(); i++) {
            HSSFRow row1 = sheet.createRow(i + 1);
            DormManager dormManager = list.get(i);
            HSSFCell cell = row1.createCell(0);
            cell.setCellValue(dormManager.getDormManId());
            HSSFCell cell2 = row1.createCell(1);
            cell2.setCellValue(dormManager.getName());
            HSSFCell cell3 = row1.createCell(2);
            cell3.setCellValue(dormManager.getUserName());
            HSSFCell cell4 = row1.createCell(3);
            cell4.setCellValue(dormManager.getSex());
            HSSFCell cell5 = row1.createCell(4);
            cell5.setCellValue(dormManager.getDormBuildID());
            HSSFCell cell6 = row1.createCell(5);
            cell6.setCellValue(dormManager.getTel());
        }
        File file = new File("D:\\person.xls");
        OutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);//把工作簿内容输出到person.xls
        outputStream.close();
        request.getSession().setAttribute("tsxx", "<script language=\"javascript\">alert('导出成功!文件已存入D://peroson.xls')</script>");
        response.sendRedirect("dormM_index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

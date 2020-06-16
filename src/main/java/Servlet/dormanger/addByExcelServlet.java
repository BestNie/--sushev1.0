package Servlet.dormanger;

import Dao.DormMangDao;
import domain.DormManager;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addByExcelServlet")
public class addByExcelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File file=new File("D:\\addPerson.xls");
        InputStream inputStream=new FileInputStream(file);
        HSSFWorkbook workbook=new HSSFWorkbook(inputStream);
        //获取工作表通过名字
        HSSFSheet sheet=workbook.getSheet("宿管表");
        int begin=1;
        int end=sheet.getLastRowNum();
        boolean falg=false;
        for (int i = begin; i <=end ; i++) {
            HSSFRow row=sheet.getRow(i);
            row.getCell(0).setCellType(CellType.STRING);
            String userName=row.getCell(0).getStringCellValue();
            row.getCell(1).setCellType(CellType.STRING);
            String name=row.getCell(1).getStringCellValue();
            row.getCell(2).setCellType(CellType.STRING);
            String sex=row.getCell(2).getStringCellValue();
            row.getCell(3).setCellType(CellType.STRING);
            String  password=row.getCell(3).getStringCellValue();
            row.getCell(4).setCellType(CellType.STRING);
            String  dorBuildId=row.getCell(4).getStringCellValue();
            row.getCell(5).setCellType(CellType.STRING);
            String tel=row.getCell(5).getStringCellValue();
            row.getCell(6).setCellType(CellType.STRING);
            String dormBuildName=row.getCell(5).getStringCellValue();
            DormMangDao dm = new DormMangDao();
            try {
                falg = dm.addDromManager(userName, password, name, sex, tel, dorBuildId, dormBuildName);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (falg=true)
        request.getSession().setAttribute("jsts","<script language=\"javascript\">alert('导入数据成功！')</script>");
        response.sendRedirect("dormM_index.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

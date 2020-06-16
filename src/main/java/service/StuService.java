package service;

import Dao.DormMangDao;
import Dao.StuDao;
import domain.DormManager;
import domain.Page;
import domain.Student;
import until.StringUtil;

import java.sql.SQLException;
import java.util.List;

public class StuService {
    public Page<Student> findStuByPage(String searchName , String _currentPage, String _rows,int dormBuildId) throws SQLException {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        if(currentPage <= 0){
            currentPage = 1;
        }

        Page<Student> page = new Page<Student>();
        page.setCurrentPage(currentPage);
        page.setRows(rows);
        page.setSearchName(searchName);
        StuDao sd = new StuDao();
        int totalCount=1;
        if (StringUtil.isEmpty(searchName)){
            //调用dao查询总记录数
            totalCount = sd.findTotalCount(searchName,dormBuildId);
            page.setTotalCount(totalCount);
        }else {
            totalCount = sd.findTotalCount(dormBuildId);
            page.setTotalCount(totalCount);
        }


        //计算总页码
        int totalPage = (totalCount % rows) == 0 ? totalCount / rows : (totalCount / rows) + 1;
        page.setTotalPage(totalPage);

        if(currentPage >= totalPage){
            currentPage = totalPage;
            page.setCurrentPage(currentPage);
        }

        //调用dao查询List集合
        //开始计算索引
        int start = 0;
        if (currentPage > 1){
            start = (currentPage - 1) * rows;
        }
        List<Student> list=null;
        list = sd.findStuWthName(searchName,start, rows,dormBuildId);
        page.setList(list);

        return page;
    }
}

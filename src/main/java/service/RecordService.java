package service;

import Dao.StuDao;
import domain.Page;
import domain.Record;
import domain.Student;
import until.StringUtil;

import java.sql.SQLException;
import java.util.List;

public class RecordService {
    public Page<Record> findRecordStu(String searchName , String _currentPage, String _rows,int dormBuildId) throws SQLException {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        if(currentPage <= 0){
            currentPage = 1;
        }

        Page<Record> page = new Page<Record>();
        page.setCurrentPage(currentPage);
        page.setRows(rows);
        page.setSearchName(searchName);
        StuDao sd = new StuDao();
        int totalCount = 1;
        if (StringUtil.isEmpty(searchName)){
            //调用dao查询总记录数
            totalCount = sd.findRecordTotalCount(searchName,dormBuildId);
            page.setTotalCount(totalCount);
        }else {
            totalCount = sd.findRecordTotalCount(dormBuildId);
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
        List<Record> list=null;
        list = sd.findStuRecordWthName(searchName,start, rows,dormBuildId);
        page.setList(list);

        return page;
    }
}

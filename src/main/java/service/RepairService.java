package service;

import Dao.RepairDao;
import domain.Page;
import domain.Repair;
import until.StringUtil;

import java.sql.SQLException;
import java.util.List;

public class RepairService {
    public Page<Repair> findRepairDorm(String searchName , String _currentPage, String _rows, int dormBuildId) throws SQLException {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        if(currentPage <= 0){
            currentPage = 1;
        }

        Page<Repair> page = new Page<Repair>();
        page.setCurrentPage(currentPage);
        page.setRows(rows);
        page.setSearchName(searchName);
        RepairDao rd = new RepairDao();
        int totalCount = 1;
        if (StringUtil.isEmpty(searchName)){
            //调用dao查询总记录数
            totalCount = rd.findRepairTotalCount(searchName,dormBuildId);
            page.setTotalCount(totalCount);
        }else {
            totalCount = rd.findRepairTotalCount(dormBuildId);
            page.setTotalCount(totalCount);
        }

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
        List<Repair> list=null;
        list = rd.findDormPepairWithName(searchName,start, rows,dormBuildId);
        page.setList(list);

        return page;
    }
}

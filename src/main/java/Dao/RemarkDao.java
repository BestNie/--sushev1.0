package Dao;

import until.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RemarkDao {
    public List<String> getRemarks(String usereName) throws SQLException {
        Connection con= DBUtils.getConnection();
        List<String> remark=new ArrayList<>();
        String sql="select * from t_remark where userName=? and remarks!=''";
        PreparedStatement pstm=con.prepareStatement(sql);
        pstm.setString(1,usereName);
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            String remarks=resultSet.getString("remarks");
            remark.add(remarks);
        }
        return remark;
    }

    public boolean deleteRemark(String remarkDatai) throws SQLException {
        Connection con= DBUtils.getConnection();
        String sql="update  t_remark set remarks=? where remarks=?";
        PreparedStatement pstm=con.prepareStatement(sql);
        pstm.setString(1,null);
        pstm.setString(2,remarkDatai);
        int i = pstm.executeUpdate();
        return i>0?true:false;
    }

    public boolean addRemark(String user,String datail) throws SQLException {
        Connection con= DBUtils.getConnection();
        String sql="insert into t_remark values(null,?,?)";
        PreparedStatement pstm=con.prepareStatement(sql);
        pstm.setString(1,user);
        pstm.setString(2,datail);
        int i = pstm.executeUpdate();
        return i>0?true:false;
    }

}

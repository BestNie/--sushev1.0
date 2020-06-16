package Dao;

import domain.Admin;
import domain.DormManager;
import until.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
    public int login(String name, String Password, String uerType) throws SQLException {
        Connection connection = DBUtils.getConnection();
        String sql;
        if ("admin".equals(uerType)) {
            sql = "select count(*) from t_admin where userName=? and password=?";
        } else {
            sql = "select count(*) from t_dormmanager where userName=? and password=?";
        }
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, name);
        pstm.setString(2, Password);
        ResultSet rs = pstm.executeQuery();
        int count = -1;
        while (rs.next()) {
            count = rs.getInt(1);
        }
        return count;
    }
    public Admin  adminInformation(String name, String password) throws SQLException {
        Connection connection = DBUtils.getConnection();
        String sql="select * from t_admin where userName = ? and password = ?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1,name);
        pstm.setString(2,password);
        ResultSet rs=pstm.executeQuery();
        Admin admin=new Admin();
        while (rs.next()){
            admin.setUserName(rs.getString("userName"));
            admin.setSex(rs.getString("sex"));
            admin.setName(rs.getString("name"));
            admin.setPassWord(rs.getString("password"));
            admin.setTel(rs.getString("tel"));
            admin.setImage(rs.getString("image"));
            admin.setAdminId(rs.getInt("adminId"));
        }
        return admin;
    }

    public DormManager dormManagerInfo(String name, String password) throws SQLException {
        Connection connection = DBUtils.getConnection();
        String sql="select * from t_dormmanager where userName = ? and password = ?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1,name);
        pstm.setString(2,password);
        ResultSet rs=pstm.executeQuery();
        DormManager dm = new DormManager();
        //找第一个就行，小概率用户名和密码相同
//        while (rs.next()){
        if (rs.next()){
            dm.setDormManId(rs.getInt("dormManId"));
            dm.setUserName(rs.getString("userName"));
            dm.setPassword(rs.getString("password"));
            dm.setDormBuildID(rs.getInt("dormBuildId"));
            dm.setDormBuildName(rs.getString("dormBuildName"));
            dm.setName(rs.getString("name"));
            dm.setSex(rs.getString("sex"));
            dm.setTel(rs.getString("tel"));
            dm.setImage(rs.getString("image"));
        }
        return dm;
    }
    public int  updateAdmin(int adminId,String userName,String password,String name,String sex,String tel,String image) throws SQLException {
        Connection connection = DBUtils.getConnection();
        String sql="update t_admin set userName=?,password=?,name=?,sex=?,tel=?,image=? where adminId=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1,userName);
        pstm.setString(2,password);
        pstm.setString(3,name);
        pstm.setString(4,sex);
        pstm.setString(5,tel);
        pstm.setString(6,image);
        pstm.setInt(7,adminId);
        int count=pstm.executeUpdate();
        return count;
    }

}

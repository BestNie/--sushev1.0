package Dao;

import domain.DormBuild;
import until.DBUtils;
import until.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DormBuildDao {
    Connection conn = null;
    PreparedStatement prst = null;
    ResultSet rs = null;
    public List<DormBuild> getAllBuild() throws SQLException {
        List<DormBuild> List=new ArrayList<>();
        conn= DBUtils.getConnection();
        String sql="select * from t_dormbuild";
        PreparedStatement pstm=conn.prepareStatement(sql);
        ResultSet rs=pstm.executeQuery();
        while (rs.next()){
            DormBuild db=new DormBuild();
            db.setDormBuildId(rs.getInt(1));
            db.setDormBuildName(rs.getString(2));
            db.setDormBuildDetail(rs.getString(3));
            List.add(db);
        }
        return List;
    }
    public int findTotalCount(String searchName) {
        int total = 0;
        try {
            conn = DBUtils.getConnection();
            String sql = "select count(*) from t_dormbuild where dormBuildName like ? ";
            prst = conn.prepareStatement(sql);
            prst.setString(1,"%"+searchName+"%");
            rs = prst.executeQuery();
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.close(rs, prst, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return total;
    }
    public int findTotalCount() {
        int total = 0;
        try {
            conn = DBUtils.getConnection();
            String sql = "select count(*) from t_dormbuild";
            prst = conn.prepareStatement(sql);
            rs = prst.executeQuery();
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.close(rs, prst, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return total;
    }
    public List<DormBuild> findDroWthName(String searchName, int start, int rows) throws SQLException {
        StringBuffer sb = new StringBuffer("select * FROM t_dormbuild ");
        List<DormBuild> list = new ArrayList<>();
        conn = DBUtils.getConnection();
        ResultSet rs = null;
        if (StringUtil.isEmpty(searchName)) {
            sb.append(" where dormBuildName like ? order by dormBuildId  LIMIT ?,? ");
            PreparedStatement pstm = conn.prepareStatement(sb.toString());
            pstm.setString(1, "%" + searchName + "%");
            pstm.setInt(2, start);
            pstm.setInt(3, rows);
            rs = pstm.executeQuery();
        } else {
            sb.append(" order by dormBuildId  LIMIT ?,? ");
            PreparedStatement pstm = conn.prepareStatement(sb.toString());
            pstm.setInt(1, start);
            pstm.setInt(2, rows);
            rs = pstm.executeQuery();
        }
        while (rs.next()) {
            DormBuild db=new DormBuild();
            db.setDormBuildId(rs.getInt(1));
            db.setDormBuildName(rs.getString(2));
            db.setDormBuildDetail(rs.getString(3));
            list.add(db);
        }
        return list;
    }
    public boolean updateBuild(String dormBuildId,String dormBuildName,String dormBuildDetail) {
        try {
            conn = DBUtils.getConnection();
            String sql = "update t_dormbuild set dormBuildDetail=?,dormBuildName = ? where dormBuildId=?";
            prst = conn.prepareStatement(sql);
            prst.setString(1,dormBuildDetail);
            prst.setString(2,dormBuildName);
            prst.setString(3,dormBuildId);
            int i = prst.executeUpdate();
            if (i > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.close(prst, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean deleteBuild(String dormBuildId) {
        int dormManId = Integer.parseInt(dormBuildId);
        try {
            conn = DBUtils.getConnection();
            String sql = "delete from t_dormbuild where dormBuildId = ?";
            prst = conn.prepareStatement(sql);
            prst.setInt(1, dormManId);
            int i = prst.executeUpdate();
            if (i > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBUtils.close(prst, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public Boolean addBuild(String dormBuildName, String dormBuildDetail) throws SQLException {
        conn=DBUtils.getConnection();
        String sql="insert into t_dormbuild values(null,?,?)";
        prst=conn.prepareStatement(sql);
        prst.setString(1,dormBuildName);
        prst.setString(2,dormBuildDetail);
        int count = prst.executeUpdate();
        return  count>0?true:false;
    }

    public String findDormBuildName(int dormBuild) throws SQLException {
        String dormBuildName = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "select dormBuildName FROM t_dormbuild where dormBuildId = ?";
            prst = conn.prepareStatement(sql);
            prst.setInt(1,dormBuild);
            rs = prst.executeQuery();
            while(rs.next()){
                dormBuildName = rs.getString("dormBuildName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn.close();
        return dormBuildName;
    }

    public String findDormBuildName(String _stuNum){
        int stuNum = Integer.parseInt(_stuNum);
        int dormBuildId;
        String dormBuildName = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "select dormBuildId FROM t_student where stuNum = ?";
            prst = conn.prepareStatement(sql);
            prst.setInt(1,stuNum);
            rs = prst.executeQuery();
            while(rs.next()){
                dormBuildId = rs.getInt("dormBuildId");
                dormBuildName = findDormBuildName(dormBuildId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dormBuildName;
    }
}

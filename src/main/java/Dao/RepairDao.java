package Dao;

import domain.Repair;
import until.DBUtils;
import until.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepairDao {
    Connection conn = null;
    PreparedStatement prst = null;
    ResultSet rs;

    public int findRepairTotalCount(String searchName,int dormBuildId) {
        int total = 0;
        try {
            conn = DBUtils.getConnection();
            if(dormBuildId != 0){
                String sql = "select count(*) from t_repair where dormBuildId = ? and dormName like ?";
                prst = conn.prepareStatement(sql);
                prst.setInt(1,dormBuildId);
                prst.setString(2,"%"+searchName+"%");
            }else {
                String sql = "select count(*) from t_repair where dormName like ?";
                prst = conn.prepareStatement(sql);
                prst.setString(1,"%"+searchName+"%");
            }
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

    public int findRepairTotalCount(int dormBuildId) {
        int total = 0;
        String sql;
        try {
            conn = DBUtils.getConnection();
            if (dormBuildId != 0){
                sql = "select count(*) from t_repair where dormBuildId = ?";
                prst = conn.prepareStatement(sql);
                prst.setInt(1,dormBuildId);
            }else{
                sql = "select count(*) from t_repair ";
                prst = conn.prepareStatement(sql);
            }
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

    public List<Repair> findDormPepairWithName(String searchName, int start, int rows, int dormBuildId) throws SQLException {
        //有个小问题，当searchName和dormBuildId同时成立
        String _dormBuildId = dormBuildId + "";
        StringBuffer sb = new StringBuffer("select * FROM t_repair ");
        List<Repair> list = new ArrayList<>();
        Connection con = DBUtils.getConnection();
        if (StringUtil.isEmpty(searchName)&&StringUtil.isEmpty(_dormBuildId) && dormBuildId != 0) {
            sb.append(" where dormBuildId = ? and dormName like ? order by repairId LIMIT ?,?");
            PreparedStatement pstm = con.prepareStatement(sb.toString());
            pstm.setInt(1, dormBuildId);
            pstm.setString(2, '%'+searchName+'%');
            pstm.setInt(3, start);
            pstm.setInt(4, rows);
            rs = pstm.executeQuery();
            System.out.println(rs.toString());
        }else if (StringUtil.isEmpty(searchName)) {
            sb.append(" where dormName like ? order by repairId  LIMIT ?,? ");
            PreparedStatement pstm = con.prepareStatement(sb.toString());
            pstm.setString(1, "%" + searchName + "%");
            pstm.setInt(2, start);
            pstm.setInt(3, rows);
            rs = pstm.executeQuery();
        }else if (StringUtil.isEmpty(_dormBuildId) && dormBuildId != 0){
            sb.append(" where dormBuildId = ? order by repairId LIMIT ?,?");
            PreparedStatement pstm = con.prepareStatement(sb.toString());
            pstm.setInt(1, dormBuildId);
            pstm.setInt(2, start);
            pstm.setInt(3, rows);
            rs = pstm.executeQuery();
        } else{
            sb.append(" order by repairId  LIMIT ?,? ");
            PreparedStatement pstm = con.prepareStatement(sb.toString());
            pstm.setInt(1, start);
            pstm.setInt(2, rows);
            rs = pstm.executeQuery();
        }
        while (rs.next()){
            Repair rp=new Repair();
            rp.setRepairId(rs.getInt("repairId"));
            rp.setDate(rs.getString("date"));
            rp.setDormBuildId(rs.getInt("dormBuildId"));
            int id=rs.getInt("dormBuildId");
            rp.setDormBuildName(StuDao.getDorBuildName(id));
            rp.setDormName(rs.getString("dormName"));
            rp.setStatus(rs.getString("status"));
            rp.setProject(rs.getString("project"));
            rp.setContent(rs.getString("content"));
            rp.setRepairer(rs.getString("repairer"));
            list.add(rp);
        }
        return list;
    }

    public boolean deleteRepair(String _repairId) {
        int repairId = Integer.parseInt(_repairId);
        try {
            conn = DBUtils.getConnection();
            String sql = "delete from t_repair where repairId = ?";
            prst = conn.prepareStatement(sql);
            prst.setInt(1, repairId);
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

    public boolean addRepair(String dormBuildId, String dormName, String repairer, String content, String project, String date) throws SQLException {
        conn=DBUtils.getConnection();
        String sql="insert into t_repair values(null,?,?,?,?,?,?,'未受理')";
        prst=conn.prepareStatement(sql);
        prst.setInt(1,Integer.valueOf(dormBuildId));
        prst.setString(2,dormName);
        prst.setString(3,repairer);
        prst.setString(4,content);
        prst.setString(5,project);
        prst.setString(6,date);
        int i = prst.executeUpdate();
        DBUtils.close(prst, conn);
        return i>0?true:false;
    }

    public boolean updateRepair(int repairId) throws SQLException {
        conn = DBUtils.getConnection();
        String sql = "update t_repair set status = ? where repairId = ?";
        prst = conn.prepareStatement(sql);
        prst.setString(1,"已受理");
        prst.setInt(2,repairId);
        int i = prst.executeUpdate();
        DBUtils.close(prst, conn);
        return i>0?true:false;
    }
}

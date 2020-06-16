package Dao;

import domain.DormManager;
import until.DBUtils;
import until.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DormMangDao {
    Connection conn = null;
    PreparedStatement prst = null;
    ResultSet rs = null;

    //查验管理员登录
    public boolean findAdmir(String username, String password) {
        try {
            conn = DBUtils.getConnection();
            String sql = "select * from admin where username = ? and password = ?";
            prst = conn.prepareStatement(sql);
            prst.setString(1, username);
            prst.setString(2, password);
            rs = prst.executeQuery();
            if (rs.next()) {
                return true;
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
        return false;
    }

    //查询总记录数
    public int findTotalCount() {
        int total = 0;
        try {
            conn = DBUtils.getConnection();
            String sql = "select count(*) from t_dormmanager";
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
    //查询总记录数
    public int findTotalCount(String searchName) {
        int total = 0;
        try {
            conn = DBUtils.getConnection();
            String sql = "select count(*) from t_dormmanager where userName like ? ";
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

    public List<DormManager> findDroWthName(String searchName, int start, int rows) throws SQLException {
        StringBuffer sb = new StringBuffer("select * FROM t_dormmanager");
        List<DormManager> list = new ArrayList<DormManager>();
        Connection con = DBUtils.getConnection();
        ResultSet rs = null;
            if (StringUtil.isEmpty(searchName)) {
            sb.append(" where userName like ? order by dormManId  LIMIT ?,? ");
            PreparedStatement pstm = con.prepareStatement(sb.toString());
            pstm.setString(1, "%" + searchName + "%");
            pstm.setInt(2, start);
            pstm.setInt(3, rows);
            rs = pstm.executeQuery();
        } else {
            sb.append(" order by dormManId  LIMIT ?,? ");
            PreparedStatement pstm = con.prepareStatement(sb.toString());
            pstm.setInt(1, start);
            pstm.setInt(2, rows);
            rs = pstm.executeQuery();
        }
        while (rs.next()) {
            DormManager dm = new DormManager();
            dm.setDormManId(rs.getInt(1));
            dm.setUserName(rs.getString(2));
            dm.setPassword(rs.getString(3));
            dm.setDormBuildID(rs.getInt(4));
            dm.setDormBuildName(new DormBuildDao().findDormBuildName(dm.getDormBuildID()));
            dm.setName(rs.getString(5));
            dm.setSex(rs.getString(6));
            dm.setTel(rs.getString(7));
            list.add(dm);
        }
        return list;
    }

    //查询当前页的记录

    /**
     * @param start 当前页记录开始
     * @param rows  每页显示的记录数
     * @return
     */
    public List<DormManager> findByPage(int start, int rows) {
        List<DormManager> list = new ArrayList<DormManager>();
        try {
            conn = DBUtils.getConnection();
            String sql = "select * from t_dormmanager order by dormManId asc limit ? , ?";
            prst = conn.prepareStatement(sql);
            prst.setInt(1, start);
            prst.setInt(2, rows);
            rs = prst.executeQuery();
            DormManager dm = null;
            while (rs.next()) {
                dm = new DormManager();
                dm.setDormManId(rs.getInt(1));
                dm.setUserName(rs.getString(2));
                dm.setPassword(rs.getString(3));
                dm.setDormBuildID(rs.getInt(4));
                dm.setDormBuildName(new DormBuildDao().findDormBuildName(dm.getDormBuildID()));
                dm.setName(rs.getString(5));
                dm.setSex(rs.getString(6));
                dm.setTel(rs.getString(7));
                list.add(dm);
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
        return list;
    }
    //读取全部宿管
    public List<DormManager> findAllDor() {
        List<DormManager> list = new ArrayList<DormManager>();
        try {
            conn = DBUtils.getConnection();
            String sql = "select * from t_dormmanager order by dormManId asc ";
            prst = conn.prepareStatement(sql);
            rs = prst.executeQuery();
            DormManager dm = null;
            while (rs.next()) {
                dm = new DormManager();
                dm.setDormManId(rs.getInt(1));
                dm.setUserName(rs.getString(2));
                dm.setPassword(rs.getString(3));
                dm.setDormBuildID(rs.getInt(4));
                dm.setDormBuildName(new DormBuildDao().findDormBuildName(dm.getDormBuildID()));
                dm.setName(rs.getString(5));
                dm.setSex(rs.getString(6));
                dm.setTel(rs.getString(7));
                list.add(dm);
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
        return list;
    }

    //修改信息操作

    /**
     * @param _id      原先的id
     * @param userName 修改后的用户名
     * @param password 修改后的密码
     * @param sex      修改后的性别
     * @param name     名字
     * @param tel      电话
     * @return
     */
    public boolean updateDromMInfo(String _id, String userName, String password, String sex, String name, String tel,String _dormBuildId,String dormBuildName) throws SQLException {
        int id = Integer.parseInt(_id);
        int dormBuildId = Integer.parseInt(_dormBuildId);
        conn = DBUtils.getConnection();
        String sql = "update t_dormmanager set userName = ?, password = ?, sex = ?, name = ?, tel = ?, dormBuildId= ?, dormBuildName = ? where dormManId = ?";
        prst = conn.prepareStatement(sql);
        prst.setString(1, userName);
        prst.setString(2, password);
        prst.setString(3, sex);
        prst.setString(4, name);
        prst.setString(5, tel);
        prst.setInt(6,dormBuildId);
        prst.setString(7,dormBuildName);
        prst.setInt(8, id);
        int i = prst.executeUpdate();
        DBUtils.close(prst, conn);
        return i > 0 ? true : false;



    }

    //删除学生

    public boolean deleteDormManger(String _dormManId) {
        int dormManId = Integer.parseInt(_dormManId);
        try {
            conn = DBUtils.getConnection();
            String sql = "delete from t_dormmanager where dormManId = ?";
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

    //添加学生
    public boolean addDromManager(String userName, String password, String name, String sex, String tel,String _dormBuildId,String dormBuildName) throws SQLException {
        int dormBuildId = Integer.parseInt(_dormBuildId);
            conn = DBUtils.getConnection();
            String sql = "insert into t_dormmanager(userName,password,dormBuildId,name,sex,tel,dormBuildName) values(?,?,?,?,?,?,?)";
            prst = conn.prepareStatement(sql);
            prst.setString(1, userName);
            prst.setString(2, password);
            prst.setInt(3, dormBuildId);
            prst.setString(4, name);
            prst.setString(5, sex);
            prst.setString(6,tel);
            prst.setString(7,dormBuildName);
            int i = prst.executeUpdate();
            DBUtils.close(prst, conn);
            return i>0?true:false;


    }
}

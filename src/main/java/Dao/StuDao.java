package Dao;

import domain.DormManager;
import domain.Record;
import domain.Student;
import until.DBUtils;
import until.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StuDao {
    DormBuildDao dbd = new DormBuildDao();
    Connection conn = null;
    PreparedStatement prst = null;
    ResultSet rs = null;
    //查询总记录数
    public int findTotalCount(int dormBuildId) throws SQLException {
        int total = 0;
        String sql;
        try {
            conn = DBUtils.getConnection();
            if (dormBuildId != 0){
                sql = "select count(*) from t_student where dormBuildId = ?";
                prst = conn.prepareStatement(sql);
                prst.setInt(1,dormBuildId);
            }else{
                sql = "select count(*) from t_student";
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
        conn.close();
        return total;
    }

    //查询总记录数
    public int findTotalCount(String searchName,int dormBuildId) {
        int total = 0;
        String sql="";
        try {
            conn = DBUtils.getConnection();
            if (dormBuildId != 0){
                sql = "select count(*) from t_student where name like ? and dormBuildId = ?";
                prst = conn.prepareStatement(sql);
                prst.setString(1,"%"+searchName+"%");
                prst.setInt(2,dormBuildId);
            }else{
                sql = "select count(*) from t_student where name like ?";
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

    /**
     * 搜索
     * @param searchName 学生名
     * @param start 页面开始
     * @param rows 每页显示条数
     * @return
     * @throws SQLException
     */
    public List<Student> findStuWthName(String searchName, int start, int rows, int dormBuildId) throws SQLException {
        String _dormBuildId = dormBuildId + "";
        StringBuffer sb = new StringBuffer("select * FROM t_student ");
        List<Student> list = new ArrayList<>();
        Connection con = DBUtils.getConnection();
        ResultSet rs = null;
        if (StringUtil.isEmpty(searchName)) {
            sb.append(" where name like ? order by stuNum  LIMIT ?,? ");
            PreparedStatement pstm = con.prepareStatement(sb.toString());
            pstm.setString(1, "%" + searchName + "%");
            pstm.setInt(2, start);
            pstm.setInt(3, rows);
            rs = pstm.executeQuery();
        }else if (StringUtil.isEmpty(_dormBuildId) && dormBuildId != 0){
            sb.append(" where dormBuildId = ? order by stuNum LIMIT ?,?");
            PreparedStatement pstm = con.prepareStatement(sb.toString());
            pstm.setInt(1, dormBuildId);
            pstm.setInt(2, start);
            pstm.setInt(3, rows);
            rs = pstm.executeQuery();
        }
        else {
            sb.append(" order by stuNum  LIMIT ?,? ");
            PreparedStatement pstm = con.prepareStatement(sb.toString());
            pstm.setInt(1, start);
            pstm.setInt(2, rows);
            rs = pstm.executeQuery();
        }
        while (rs.next()) {
            Student stu = new Student();
            stu.setStuNum(rs.getString(1));
            stu.setPassword(rs.getString(2));
            stu.setName(rs.getString(3));
            stu.setDormBuildId(rs.getInt(4));
            if (dormBuildId != 0){
                stu.setDormBuildName(dbd.findDormBuildName(dormBuildId));
            }
            else{
                stu.setDormBuildName(dbd.findDormBuildName(stu.getDormBuildId()));
            }
            stu.setDormName(rs.getString(5));
            stu.setSex(rs.getString(6));
            stu.setTel(rs.getString(7));
            list.add(stu);
        }
        con.close();
        return list;
    }

    //查询当前页的记录

    /**
     * @param start 当前页记录开始
     * @param rows  每页显示的记录数
     * @return
     */
    public List<Student> findByPage(int start, int rows) {
        List<Student> list = new ArrayList<Student>();
        try {
            conn = DBUtils.getConnection();
            String sql = "select * from t_student order by stuNum asc limit ? , ?";
            prst = conn.prepareStatement(sql);
            prst.setInt(1, start);
            prst.setInt(2, rows);
            rs = prst.executeQuery();
            Student stu = null;
            while (rs.next()) {
                stu = new Student();
                stu.setStuNum(rs.getString(1));
                stu.setPassword(rs.getString(2));
                stu.setName(rs.getString(3));
                stu.setDormBuildId(rs.getInt(4));
                stu.setDormBuildName(dbd.findDormBuildName(stu.getDormBuildId()));
                stu.setDormName(rs.getString(5));
                stu.setSex(rs.getString(6));
                stu.setTel(rs.getString(7));
                list.add(stu);
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
    public List<Student> findAllStu() {
        List<Student> list = new ArrayList<Student>();
        try {
            conn = DBUtils.getConnection();
            String sql = "select * from t_student order by stuNum asc ";
            prst = conn.prepareStatement(sql);
            rs = prst.executeQuery();
            Student stu = null;
            while (rs.next()) {
                stu = new Student();
                stu.setStuNum(rs.getString(1));
                stu.setPassword(rs.getString(2));
                stu.setName(rs.getString(3));
                stu.setDormBuildId(rs.getInt(4));
                stu.setDormBuildName(dbd.findDormBuildName(stu.getDormBuildId()));
                stu.setDormName(rs.getString(5));
                stu.setSex(rs.getString(6));
                stu.setTel(rs.getString(7));
                list.add(stu);
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

    //按学号删除学生
    public boolean deleteStu(String _stuNum) {
        int stuNum = Integer.parseInt(_stuNum);
        try {
            conn = DBUtils.getConnection();
            String sql = "delete from t_student where stuNum = ?";
            prst = conn.prepareStatement(sql);
            prst.setInt(1, stuNum);
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

    //修改信息操作

    /**
     *
     * @param stuNum  修改后的学号
     * @param name 修改后的名字
     * @param password 修改后的密码
     * @param sex
     * @param _dormBuildId
     * @param dormName
     * @param tel
     * @param _stuNum 原先的学号
     * @return
     */
    public boolean updateStuInfo(String stuNum, String name, String password, String sex, String _dormBuildId, String dormName, String tel, String _stuNum) throws SQLException {
        int dormBuildId = Integer.parseInt(_dormBuildId);
        conn = DBUtils.getConnection();
        String sql = "update t_student set stuNum = ?, name = ?, password = ?, sex = ?, dormBuildId = ?, dormName = ?, tel = ? where stuNum = ?";
        prst = conn.prepareStatement(sql);
        prst.setString(1, stuNum);
        prst.setString(2, name);
        prst.setString(3, password);
        prst.setString(4, sex);
        prst.setInt(5, dormBuildId);
        prst.setString(6, dormName);
        prst.setString(7, tel);
        prst.setString(8,_stuNum);
        int i = prst.executeUpdate();
        DBUtils.close(prst, conn);
        return i > 0 ? true : false;
    }

    //添加学生
    public boolean addStu(String _stuNum, String password, String name, String sex, String _dormBuildId, String dormName, String tel) throws SQLException {
        int stuNum = Integer.parseInt(_stuNum);
        int dormBuildId = Integer.parseInt(_dormBuildId);
        conn = DBUtils.getConnection();
        String sql = "insert into t_student(stuNum,password,name,sex,dormBuildId,dormName,tel) values(?,?,?,?,?,?,?)";
        prst = conn.prepareStatement(sql);
        prst.setInt(1, stuNum);
        prst.setString(2, password);
        prst.setString(3, name);
        prst.setString(4, sex);
        prst.setInt(5, dormBuildId);
        prst.setString(6,dormName);
        prst.setString(7,tel);
        int i = prst.executeUpdate();
        DBUtils.close(prst, conn);
        return i > 0 ? true : false;

    }

    public int findRecordTotalCount(String searchName,int dormBuildId) {
        int total = 0;
        try {
            conn = DBUtils.getConnection();
            if(dormBuildId != 0){
                String sql = "select count(*) from t_record where dormBuildId = ? and studentNumber like ?";
                prst = conn.prepareStatement(sql);
                prst.setInt(1,dormBuildId);
                prst.setString(2,"%"+searchName+"%");
            }else {
                String sql = "select count(*) from t_record where studentNumber like ?";
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
    public int findRecordTotalCount(int dormBuildId) {
        int total = 0;
        String sql;
        try {
            conn = DBUtils.getConnection();
            if (dormBuildId != 0){
                sql = "select count(*) from t_record where dormBuildId = ?";
                prst = conn.prepareStatement(sql);
                prst.setInt(1,dormBuildId);
            }else{
                sql = "select count(*) from t_record ";
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
    public List<Record> findStuRecordWthName(String searchName, int start, int rows,int dormBuildId) throws SQLException {
        //有个小问题，当searchName和dormBuildId同时成立
        String _dormBuildId = dormBuildId + "";
        StringBuffer sb = new StringBuffer("select * FROM t_record ");
        List<Record> list = new ArrayList<>();
        Connection con = DBUtils.getConnection();
        ResultSet rs = null;
        if (StringUtil.isEmpty(searchName)&&StringUtil.isEmpty(_dormBuildId) && dormBuildId != 0) {
            sb.append(" where dormBuildId = ? and studentNumber like ? order by studentNumber LIMIT ?,?");
            PreparedStatement pstm = con.prepareStatement(sb.toString());
            pstm.setInt(1, dormBuildId);
            pstm.setInt(2, Integer.valueOf(searchName));
            pstm.setInt(3, start);
            pstm.setInt(4, rows);
            rs = pstm.executeQuery();
        }
        else if (StringUtil.isEmpty(searchName)) {
            sb.append(" where studentNumber like ? order by studentNumber  LIMIT ?,? ");
            PreparedStatement pstm = con.prepareStatement(sb.toString());
            pstm.setString(1, "%" + searchName + "%");
            pstm.setInt(2, start);
            pstm.setInt(3, rows);
            rs = pstm.executeQuery();
        }else if (StringUtil.isEmpty(_dormBuildId) && dormBuildId != 0){
            sb.append(" where dormBuildId = ? order by studentNumber LIMIT ?,?");
            PreparedStatement pstm = con.prepareStatement(sb.toString());
            pstm.setInt(1, dormBuildId);
            pstm.setInt(2, start);
            pstm.setInt(3, rows);
            rs = pstm.executeQuery();
        }
        else {
            sb.append(" order by studentNumber  LIMIT ?,? ");
            PreparedStatement pstm = con.prepareStatement(sb.toString());
            pstm.setInt(1, start);
            pstm.setInt(2, rows);
            rs = pstm.executeQuery();
        }
        while (rs.next()) {
            Record record=new Record();
            record.setRecordId(rs.getInt("recordId"));
            record.setDate(rs.getString("date"));
            record.setStudentNumber(rs.getInt("studentNumber")+"");
            record.setStudentName(rs.getString("studentName"));
            int id=rs.getInt("dormBuildId");
            record.setDormBuildName(StuDao.getDorBuildName(rs.getInt("dormBuildId")));
            record.setDormName(rs.getString("dormName"));
            record.setDetail(rs.getString("detail"));
            list.add(record);
        }
        return list;
    }

    public  static String getDorBuildName(int dormBuildId) throws SQLException {
        Connection conn = DBUtils.getConnection();
        String sql="select * from t_dormBuild where dormBuildId=? ";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1,dormBuildId);
        ResultSet resultSet=pstm.executeQuery();
        String dorBuildName=null;
        while (resultSet.next()){
            dorBuildName=resultSet.getString("dormBuildName");
        }
        return dorBuildName;
    }

    public boolean deleteRecord(String recordId) {
        int stuNum = Integer.parseInt(recordId);
        try {
            conn = DBUtils.getConnection();
            String sql = "delete from t_record where recordId = ?";
            prst = conn.prepareStatement(sql);
            prst.setInt(1, stuNum);
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

    //按学号查学生信息
    public Student findStu(int stuNum){
        Student stu = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "select * from t_student where stuNum = ?";
            prst = conn.prepareStatement(sql);
            prst.setInt(1,stuNum);
            rs = prst.executeQuery();
            while (rs.next()) {
                stu = new Student();
                stu.setStuNum(rs.getString(1));
                stu.setPassword(rs.getString(2));
                stu.setName(rs.getString(3));
                stu.setDormBuildId(rs.getInt(4));
                stu.setDormBuildName(dbd.findDormBuildName(stu.getDormBuildId()));
                stu.setDormName(rs.getString(5));
                stu.setSex(rs.getString(6));
                stu.setTel(rs.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stu;
    }

    //宿管添加缺勤记录
    public boolean addAbsenceInfo(String _stuNum, String date, String detail){
        int stuNum = Integer.parseInt(_stuNum);
        Student stu = new Student();
        stu = findStu(stuNum);
        try {
            conn = DBUtils.getConnection();
            String sql = "insert into t_record(studentNumber,studentName,dormBuildId,dormName,date,detail) values(?,?,?,?,?,?)";
            prst = conn.prepareStatement(sql);
            prst.setInt(1,stuNum);
            prst.setString(2,stu.getName());
            prst.setInt(3,stu.getDormBuildId());
            prst.setString(4,stu.getDormName());
            prst.setString(5,date);
            prst.setString(6,detail);
            int i = prst.executeUpdate();
            if (i > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

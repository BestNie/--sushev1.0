package domain;

public class Student {
    private int studentId;//编号
    private String stuNum;//学号
    private String password;//密码
    private String name;//姓名
    private int dormBuildId;//宿舍楼栋
    private String dormBuildName;//宿舍楼名称
    private String dormName;//寝室号
    private String sex;//性别
    private String tel;//电话号码

    public Student() {
    }

    public Student(int studentId, String stuNum, String password, String name, int dormBuildId, String dormBuildName, String dormName, String sex, String tel) {
        this.studentId = studentId;
        this.stuNum = stuNum;
        this.password = password;
        this.name = name;
        this.dormBuildId = dormBuildId;
        this.dormBuildName = dormBuildName;
        this.dormName = dormName;
        this.sex = sex;
        this.tel = tel;
    }

    public String getDormBuildName() {
        return dormBuildName;
    }

    public void setDormBuildName(String dormBuildName) {
        this.dormBuildName = dormBuildName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDormBuildId() {
        return dormBuildId;
    }

    public void setDormBuildId(int dormBuildId) {
        this.dormBuildId = dormBuildId;
    }

    public String getDormName() {
        return dormName;
    }

    public void setDormName(String dormName) {
        this.dormName = dormName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", stuNum='" + stuNum + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", dormBuildId=" + dormBuildId +
                ", dormBuildName='" + dormBuildName + '\'' +
                ", dormName='" + dormName + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}

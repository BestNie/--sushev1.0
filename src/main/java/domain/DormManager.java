package domain;

public class DormManager {
    private int dormManId;
    private String userName;
    private String password;
    private int dormBuildId;
    private String dormBuildName;
    private String name;
    private String sex;
    private String tel;
    private String image;

    public DormManager() {
    }

    public String getDormBuildName() {
        return dormBuildName;
    }

    public void setDormBuildName(String dormBuildName) {
        this.dormBuildName = dormBuildName;
    }

    public String getImage() {
        return image;
    }

    public int getDormBuildId() {
        return dormBuildId;
    }

    public void setDormBuildId(int dormBuildId) {
        this.dormBuildId = dormBuildId;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getDormManId() {
        return dormManId;
    }

    public void setDormManId(int dormManId) {
        this.dormManId = dormManId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDormBuildID() {
        return dormBuildId;
    }

    public void setDormBuildID(int dormBuildId) {
        this.dormBuildId = dormBuildId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "DormManager{" +
                "dormManId=" + dormManId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", dormBuildId=" + dormBuildId +
                ", dormBuildName='" + dormBuildName + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}

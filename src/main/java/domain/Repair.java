package domain;

import java.util.Date;

public class Repair {
    private int repairId;
    private String dormName;
    private String repairer;
    private int dormBuildId;
    private String dormBuildName;
    private String content;
    private String project;
    private String date;
    private String status;

    public Repair() {
    }

    public Repair(int repairId, String dormName, String repairer, int dormBuildId, String dormBuildName, String content, String project, String date, String status) {
        this.repairId = repairId;
        this.dormName = dormName;
        this.repairer = repairer;
        this.dormBuildId = dormBuildId;
        this.dormBuildName = dormBuildName;
        this.content = content;
        this.project = project;
        this.date = date;
        this.status = status;
    }

    public int getRepairId() {
        return repairId;
    }

    public void setRepairId(int repairId) {
        this.repairId = repairId;
    }

    public String getDormName() {
        return dormName;
    }

    public void setDormName(String dormName) {
        this.dormName = dormName;
    }

    public String getRepairer() {
        return repairer;
    }

    public void setRepairer(String repairer) {
        this.repairer = repairer;
    }

    public int getDormBuildId() {
        return dormBuildId;
    }

    public void setDormBuildId(int dormBuildId) {
        this.dormBuildId = dormBuildId;
    }

    public String getDormBuildName() {
        return dormBuildName;
    }

    public void setDormBuildName(String dormBuildName) {
        this.dormBuildName = dormBuildName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Repair{" +
                "repairId=" + repairId +
                ", dormName='" + dormName + '\'' +
                ", repairer='" + repairer + '\'' +
                ", dormBuildId=" + dormBuildId +
                ", dormBuildName='" + dormBuildName + '\'' +
                ", content='" + content + '\'' +
                ", project='" + project + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }
}

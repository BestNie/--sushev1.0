
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form action="addRepairServlet?dormBuildId=${DormManger.dormBuildId}" method="post">
    <div class="layui-table-box content" align="center">
        <div align="center" style="margin-top: 80px;width: 700px;height: 600px">
            <table cellspacing="0" cellpadding="0" border="0" class="layui-table">
                <tr data-index="0">
                    <td style="width:50px;">
                        <div class="layui-table-cell "><label>*宿舍楼栋名：</label></div>
                    </td>
                    <td style="width:50px;">
                        <div class="layui-table-cell "><input type="text" style="height: 30px" name="dormBuildName" value="${DormManger.dormBuildName}" readonly="true"
                            ">
                        </div>
                    </td>
                </tr>
                <tr data-index="0">
                    <td style="width:50px;">
                        <div class="layui-table-cell "><label>*报修人：</label></div>
                    </td>
                    <td>
                        <div class="layui-table-cell "><input type="type" name="repairer" style="height: 30px"></div>
                    </td>
                </tr>
                <tr data-index="0">
                    <td style="width:50px;">
                        <div class="layui-table-cell "><label>*寝室号：</label></div>
                    </td>
                    <td>
                        <div class="layui-table-cell "><input type="type" name="dormName" style="height: 30px"></div>
                    </td>
                </tr>
                <tr data-index="0">
                    <td style="width:50px;">
                        <div class="layui-table-cell "><label>*保修类型：</label></div>
                    </td>
                    <td style="width:50px;">
                        <div class="layui-table-cell ">
                            <select name="project" style="width: 180px;height: 30px">
                                <option value="水">水</option>
                                <option value="电">电</option>
                                <option value="木">木</option>
                            </select>
                        </div>
                    </td>
                </tr>

                <tr data-index="0">
                    <td style="width:50px;">
                        <div class="layui-table-cell "><label>*具体内容：</label></div>
                    </td>
                    <td style="width:50px;">
                        <div class="layui-table-cell "><input type="text" style="height: 30px" name="content"
                            ">
                        </div>
                    </td>
                </tr>
                <tr data-index="0">
                    <td colspan="2" align="center" style="width:50px;">
                        <span style="color: red">${error}${error=null}</span><br>
                        <button type="submit" class="btn btn-primary" value="保存">保存</button>&nbsp;&nbsp;<button
                            class="btn btn-success" type="reset" value="重置">重置
                    </button>&nbsp;&nbsp;<a href="dormBuildRepairServlet?action=bxjl&judge=dm">
                        <button type="button" class="btn btn-info" value="返回">返回</button>
                    </a>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</form>
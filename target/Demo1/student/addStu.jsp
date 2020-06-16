<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<form action="addStuServlet?currentPage=${param.currentPage}&menuitem=xsgl" method="post">
    <div class="layui-table-box content" align="center">
        <div align="center" style="margin-top: 80px;width: 700px;height: 600px">
            <table cellspacing="0" cellpadding="0" border="0" class="layui-table">
                <tr data-index="0">
                    <td style="width:50px;">
                        <div class="layui-table-cell "><label>*学号：</label></div>
                    </td>
                    <td style="width:50px;">
                        <div class="layui-table-cell "><input type="text" name="stuNum" style="height: 30px"
                            ">
                        </div>
                    </td>
                </tr>
                <tr data-index="0">
                    <td style="width:50px;">
                        <div class="layui-table-cell "><label>*姓名：</label></div>
                    </td>
                    <td style="width:50px;">
                        <div class="layui-table-cell "><input type="type" name="name" style="height: 30px"
                            ">
                        </div>
                    </td>
                </tr>
                <tr data-index="0">
                    <td style="width:50px;">
                        <div class="layui-table-cell "><label>*密码：</label></div>
                    </td>
                    <td style="width:50px;">
                        <div class="layui-table-cell "><input type="password" name="password" style="height: 30px"
                            ">
                        </div>
                    </td>
                </tr>
                <tr data-index="0">
                    <td style="width:50px;">
                        <div class="layui-table-cell "><label>*重复密码：</label></div>
                    </td>
                    <td style="width:50px;">
                        <div class="layui-table-cell "><input type="password" style="height: 30px" name="repassword"
                            ">
                        </div>
                    </td>
                </tr>
                <tr data-index="0">
                    <td style="width:50px;">
                        <div class="layui-table-cell "><label>*性别：</label></div>
                    </td>
                    <td style="width:50px;">
                        <div class="layui-table-cell ">
                            <select name="sex" style="width: 180px;height: 30px">
                                <option value="男">男</option>
                                <option value="女">女</option>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr data-index="0">
                    <td style="width:50px;">
                        <div class="layui-table-cell "><label>*宿舍楼栋：</label></div>
                    </td>
                    <td style="width:50px;">
                        <div class="layui-table-cell ">
                            <input class="browsers" list="browsers" name="dormBuildId"
                            ">
                            <datalist id="browsers">
                                <option value="1">
                                <option value="2">
                                <option value="3">
                                <option value="4">
                            </datalist>
                        </div>
                    </td>
                </tr>
                <tr data-index="0">
                    <td style="width:50px;">
                        <div class="layui-table-cell "><label>*寝室号：</label></div>
                    </td>
                    <td style="width:50px;">
                        <div class="layui-table-cell "><input type="text" style="height: 30px" name="dormName"
                            ">
                        </div>
                    </td>
                </tr>
                <tr data-index="0">
                    <td style="width:50px;">
                        <div class="layui-table-cell "><label>*联系电话：</label></div>
                    </td>
                    <td style="width:50px;">
                        <div class="layui-table-cell "><input type="text" style="height: 30px" name="tel"
                            ">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <span style="color: red">${param.error}</span><br>
                        <button type="submit" class="btn btn-primary" value="保存">提交</button>&nbsp;<button
                            class="btn btn-success" type="reset" value="重置">重置
                    </button>&nbsp;&nbsp;<a
                            href="showStuServlet?action=showStu&judge=ad&menuitem=xsgl">
                        <button type="button" class="btn btn-info" value="返回">返回</button>
                    </a>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><a href="#">导入</a></td>
                </tr>
            </table>
        </div>
    </div>
</form>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<form action="updateServlet?dormManId=${param.dormManId}&currentPage=${param.currentPage}&menuitem=sggl" method="post">
    <div class="layui-table-box content" align="center">
        <div align="center" style="margin-top: 80px;width: 700px;height: 600px">
            <table cellspacing="0" cellpadding="0" border="0" class="layui-table">
                <tr data-index="0">
                    <td style="width:50px;">
                        <div class="layui-table-cell "><label>*用户名：</label></div>
                    </td>
                    <td>
                        <div class="layui-table-cell "><input type="text" name="userName" style="height: 30px" value="${pb.list[param.listID].userName}"></div>
                    </td>
                </tr>
                <tr data-index="0">
                    <td style="width:50px;">
                        <div class="layui-table-cell "><label>*密码：</label></div>
                    </td>
                    <td>
                        <div class="layui-table-cell "><input type="password" name="password" style="height: 30px" value="${pb.list[param.listID].password}">
                        </div>
                    </td>
                </tr>
                <tr data-index="0">
                    <td style="width:50px;">
                        <div class="layui-table-cell "><label>*重复密码：</label></div>
                    </td>
                    <td style="width:50px;">
                        <div class="layui-table-cell "><input type="password" name="repassword" style="height: 30px" value="${pb.list[param.listID].password}">
                        </div>
                    </td>
                </tr>
                <tr data-index="0">
                    <td style="width:50px;">
                        <div class="layui-table-cell "><label>*姓名：</label></div>
                    </td>
                    <td style="width:50px;">
                        <div class="layui-table-cell "><input type="type" name="name" style="height: 30px" value="${pb.list[param.listID].name}"></div>
                    </td>
                </tr>
                <tr data-index="0">
                    <td style="width:50px;">
                        <div class="layui-table-cell "><label>*性别：</label></div>
                    </td>
                    <td style="width:50px;">
                        <div class="layui-table-cell ">
                            <select name="sex" style="width: 180px;height: 30px" value="${pb.list[param.listID].sex}">
                                <option value="男">男</option>
                                <option value="女">女</option>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr data-index="0" class="">
                    <td style="width:50px;">
                        <div class="layui-table-cell "><label>*宿舍楼栋Id：</label></div>
                    </td>
                    <td style="width:50px;">
                        <div class="layui-table-cell ">
                            <input class="browsers" list="browsers" name="dormBuildId" value="${pb.list[param.listID].dormBuildID}"
                            >
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
                        <div class="layui-table-cell "><label>*宿舍楼栋名：</label></div>
                    </td>
                    <td style="width:50px;">
                        <div class="layui-table-cell "><input type="text" style="height: 30px" name="dormBuildName" value="${pb.list[param.listID].dormBuildName}"
                            >
                        </div>
                    </td>
                </tr>
                <tr data-index="0">
                    <td style="width:50px;">
                        <div class="layui-table-cell "><label>*联系电话：</label></div>
                    </td>
                    <td style="width:50px;">
                        <div class="layui-table-cell "><input type="text" style="height: 30px" name="tel" value="${pb.list[param.listID].tel}"
                            >
                        </div>
                    </td>
                </tr>
                <tr data-index="0">
                    <td colspan="2" align="center" style="width:50px;">
                        <span style="color: red">${param.error}</span><br>
                        <button type="submit" class="btn btn-primary" value="保存">提交</button>&nbsp;<button
                            class="btn btn-success" type="reset" value="重置">重置
                    </button>&nbsp;&nbsp;<a
                            href="/showDroServlet?currentPage=${param.currentPage}&menuitem=sggl">
                        <button type="button" class="btn btn-info" value="返回">返回</button>
                    </a>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</form>

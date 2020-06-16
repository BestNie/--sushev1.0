
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form action="addRemarkServlet?user=${Admin.userName}" method="post">
    <div class="layui-table-box content" align="center">
        <div align="center" style="margin-top: 80px;width: 700px;height: 600px">
            <table cellspacing="0" cellpadding="0" border="0" class="layui-table">
                <tr data-index="0">
                    <td style="width:50px;">
                        <div class="layui-table-cell "><label>*添加您的备忘录：</label></div>
                    </td>
                    <td style="width:50px;">
                        <div class="layui-table-cell "><input type="type" name="bwl" style="height: 30px;width: 250px"
                            ">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <span style="color: red">${error}</span><br>
                        <button type="submit" class="btn btn-primary" value="保存">提交</button>&nbsp;<button
                            class="btn btn-success" type="reset" value="重置">重置
                    </button>&nbsp;&nbsp;<a
                            href="">
                        <a href="remarkServlet?action=bwl&userName=${Admin.userName}"><button type="button" class="btn btn-info" value="返回">返回</button></a>
                    </a>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">备忘录</td>
                </tr>
            </table>
        </div>
    </div>
</form>
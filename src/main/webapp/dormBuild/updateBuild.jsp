<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    <form action="updateBuildServlet?dormBuildId=${pb.list[param.listID].dormBuildId}&menuitem=ldgl" method="post">
        <div class="layui-upload-list" align="center">
        </div>
        <div class="layui-progress">
            <div class="layui-progress-bar layui-bg-red" lay-percent="100%"></div>
        </div>
        <br>
        <div  style="width: 650px;height: 500px;margin-left: 350px; margin-top: 50px">
            <div class="layui-form-item">
                <label class="layui-form-label">楼栋：</label>

                <div class="layui-input-block">
                    <input type="text" name="dormBuildName" readonly="true" class="layui-input" value="${pb.list[param.listID].dormBuildName}" >
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">详情信息：</label>
                <div class="layui-input-block">
                    <input type="text" name="dormBuildDetail"  class="layui-input" value="${pb.list[param.listID].dormBuildDetail}" >
                </div>
            </div>
            <div class="layui-form-item" align="center">
                <span style="color: red">${param.error}</span><br>
                <button type="submit" class="btn btn-primary"  value="保存">提交</button>&nbsp;<button class="btn btn-success" type="reset" value="重置">重置</button>&nbsp;&nbsp;<a
                    href="showBuildServlet?action=showDormB&currentPage=${param.currentPage}&menuitem=ldgl"><button type="button"class="btn btn-info"  value="返回">返回</button></a>
            </div>
        </div>
    </form>
</div>


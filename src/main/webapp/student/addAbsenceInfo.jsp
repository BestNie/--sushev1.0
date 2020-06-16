<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="/absenceServlet" method="post">
    <div class="layui-table-box content" align="center">
        <div align="center" style="margin-top: 80px;width: 700px;height: 600px">
            <table cellspacing="0" cellpadding="0" border="0" class="layui-table">
                <tr data-index="0" class="">
                    <td style="width:50px;">
                        <div class="layui-table-cell "><label>*学号：</label></div>
                    </td>
                    <td style="width:50px;">
                        <div class="layui-table-cell ">
                            <c:if test="${not empty param.stuNum}">
                                <input type="text" name="stuNum" style="height: 30px" value="${param.stuNum}" readonly>
                            </c:if>
                            <c:if test="${empty param.stuNum}">
                                <input type="text" name="stuNum" style="height: 30px">
                            </c:if>
                        </div>
                    </td>
                </tr>
                <tr data-index="0" class="">
                    <td style="width:50px;">
                        <div class="layui-table-cell "><label>*日期：</label></div>
                    </td>
                    <td style="width:50px;">
                        <div class="layui-table-cell "><input type="text" name="adsenceDate" style="height: 30px" value="${date}" readonly></div>
                    </td>
                </tr>
                <tr data-index="0" class="">
                    <td style="width:50px;">
                        <div class="layui-table-cell "><label>*备注：</label></div>
                    </td>
                    <td style="width:50px;">
                        <div class="layui-table-cell "><input type="text" name="detail" style="height: 30px"></div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <span style="color: red">${error}</span><br>${error=null}
                        <button type="submit" class="btn btn-primary" value="保存">提交</button>&nbsp;<button
                            class="btn btn-success" type="reset" value="重置">重置
                    </button>
                        <c:if test="${not empty param.stuNum}">
                        &nbsp;<a href="showStuServlet?action=showStu&judge=dm">
                            <button type="button" class="btn btn-info" value="返回">返回</button>
                        </a>
                        </c:if>
                        <c:if test="${empty param.stuNum}">
                            &nbsp;<a href="recordServlet?action=record&judge=dm">
                            <button type="button" class="btn btn-info" value="返回">返回</button>
                            </a>
                        </c:if>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</form>

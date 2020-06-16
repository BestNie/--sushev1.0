
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" type="text/css" href="css/style3.css" />
<%--<script src="js/js.js"></script>--%>

<h1 align="center">我的代办事项</h1>
<div style="width: 100%">
    <ul id="ul1">
        <c:forEach var="list" items="${remarkList}"  varStatus="status">
            <li>
                <h2>我的第${status.index+1}条便签</h2>
                <p>${list}<a href="remarkDeleteServlet?remark=${list}&admin=${Admin.userName}">我以完成</a></p>
            </li>
        </c:forEach>
        ${txss}${txss=null}
    </ul>
</div>
<div style="clear: both" align="center">
    <a href="remarkServlet?action=addrmk">添加备忘录</a>
</div>


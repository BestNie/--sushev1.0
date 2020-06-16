<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>宿舍管理系统</title>
<%--    <base href="<%=request.getContextPath()+(request.getContextPath().equals("/")?"":"/") %>"/>--%>
    <link rel="stylesheet" href="css\style.css">

</head>
<body>
<%!
    String admin;
    String password;
%>
<%
    Cookie[] cookies = request.getCookies();
    String admin = "";
    String password = "";
    if (cookies != null)
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("admin"))
                admin = cookie.getValue();
            else if (cookie.getName().equals("pwd"))
                password = cookie.getValue();
        }
%>
<div class="container">
    <img src="images/bc.jpg" alt="">
    <div class="panel">
        <div class="content login">
            <div class="switch">
                <span id='login' class='active'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Weclome!</span>
            </div>
            <form action="loginServlet" method="post">
                <div class="input" placeholder=''><input type="text" name="uesrName" value="<%=admin%>"
                                                         placeholder="userName"></div>
                <div class="input" placeholder=''><input type="password" name="pwd" value="<%=password%>"
                                                         placeholder="password"></div>
                <span>${errormessage}</span><br>
                <span><input type="radio" name="userType" value="admin" checked/> 系统管理员 <input type="radio"
                                                                                               name="userType"
                                                                                               value="dorm_manager"/> 宿舍管理员</span>
                <br><span><input id="remember" name="remember" type="checkbox" checked="checked"}>记住我 </span>
                <button type="submit">LOGIN</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>

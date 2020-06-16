<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <title>学生宿舍管理系统</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <base href="<%=request.getContextPath()+(request.getContextPath().equals("/")?"":"/") %>"/>
    <link rel="stylesheet" href="css/layui.css">
    <link rel="stylesheet" href="../bootStrap/css/bootstrap.min.css">
    <script type="text/javascript">
        function delcfm() {
            if (!confirm("确认要删除？")) {
                window.event.returnValue = false;
            }
        }

        var information = '<%=request.getParameter("information")%>';
        window.onload = function () {
            if (information != null && information != 'null') {
                alert(information);
            }
        }
    </script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">宿舍管理系统</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="showStuServlet?action=sy&judge=dm">主菜单</a></li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="/imagepath/${DormManger.image}" class="layui-nav-img">
                    ${DormManger.name}
                </a>
            </li>
            <li class="layui-nav-item"><a href="login.jsp">退出系统</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="showStuServlet?action=sy&judge=dm">首页</a>
                </li>
                <li class="layui-nav-item"><a href="showStuServlet?action=showStu&judge=dm">学生管理</a></li>
                <li class="layui-nav-item"><a href="recordServlet?action=record&judge=dm">缺勤记录</a></li>
                <li class="layui-nav-item"><a href="recordServlet?action=addabsenceInfo&judge=dm">添加缺勤记录</a></li>
                <li class="layui-nav-item"><a href="dormBuildRepairServlet?action=bxjl&judge=dm">报修记录</a></li>
                <li class="layui-nav-item"><a href="dormBuildRepairServlet?action=add&judge=dm">楼栋报修</a></li>
                <li class="layui-nav-item"><a href="removeServlet">退出登录</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <jsp:include page="${mainPage==null?'/welcome.jsp':mainPage}"></jsp:include>
    </div>


    <div class="layui-footer" align="center">
        <!-- 底部固定区域 -->
        <footer>© madeby-3522游击队</footer>
    </div>
    <script src="js/layui.js"></script>
    <script>
        //JavaScript代码区域
        layui.use('element', function(){
            var element = layui.element;
            element.init();
        });
    </script>
</div>

</body>
</html>

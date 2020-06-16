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
    <script src="js/layui.js"></script>
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

        function init() {
            //JavaScript代码区域
            layui.use('element', function () {
                var element = layui.element;
                element.init();
            });
            var url = window.location.href;
            var regex = /&menuitem=([^&]*)/;
            var results = regex.exec(url);
            var menuitem;
            if (!results) {
                menuitem = null;
            } else if (!results[1]) {
                menuitem = '';
            } else {
                menuitem = decodeURIComponent(results[1].replace(/\+/g, ' '));
            }
            // var node = document.getElementById(menuitem);
            // if(node){
            //     node.setAttribute("class", node.getAttribute("class") + " " + "layui-nav-itemed");
            // }
            var node = document.getElementById(menuitem);
            if ("sggl"===menuitem && node){
                var node1 = document.getElementById("xsgl").getAttribute("class");
                var node2 = document.getElementById("ldgl").getAttribute("class");
                node.setAttribute("class", node.getAttribute("class") + " " + "layui-nav-itemed");
                if (node1.includes("layui-nav-itemed") || node2.includes("layui-nav-itemed")){
                    node1 =  node1.replace(" layui-nav-itemed","");
                    node2 = node2.replace(" layui-nav-itemed","");
                    document.getElementById("xsgl").setAttribute("class",node1);
                    document.getElementById("ldgl").setAttribute("class",node2);
                }
            }
            if ("xsgl"===menuitem && node){
                var node1 = document.getElementById("sggl").getAttribute("class");
                var node2 = document.getElementById("ldgl").getAttribute("class");
                node.setAttribute("class", node.getAttribute("class") + " " + "layui-nav-itemed");
                if (node1.includes("layui-nav-itemed") || node2.includes("layui-nav-itemed")){
                    node1 =  node1.replace(" layui-nav-itemed","");
                    node2 = node2.replace(" layui-nav-itemed","");
                    document.getElementById("sggl").setAttribute("class",node1);
                    document.getElementById("ldgl").setAttribute("class",node2);
                }
            }
            if ("ldgl"===menuitem && node){
                var node1 = document.getElementById("sggl").getAttribute("class");
                var node2 = document.getElementById("xsgl").getAttribute("class");
                node.setAttribute("class", node.getAttribute("class") + " " + "layui-nav-itemed");
                if (node1.includes("layui-nav-itemed") || node2.includes("layui-nav-itemed")){
                    node1 =  node1.replace(" layui-nav-itemed","");
                    node2 = node2.replace(" layui-nav-itemed","");
                    document.getElementById("sggl").setAttribute("class",node1);
                    document.getElementById("xsgl").setAttribute("class",node2);
                }
            }
        }
    </script>
</head>
<body class="layui-layout-body" onload="init();">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">宿舍管理系统</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="/imagepath/${Admin.image}" class="layui-nav-img">
                    ${Admin.name}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="showDroServlet?action=xgxx">基本资料</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="removeServlet">退出系统</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item">
                    <a class="" href="showDroServlet?action=sy">首页</a>
                </li>
                <li class="layui-nav-item" id="sggl">
                    <a href="javascript:;">宿管管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="showDroServlet?action=showDormm&menuitem=sggl" >显示宿管</a></dd><!-- menuitem是开闭问题 -->
                        <dd><a href="showDroServlet?action=addsu&menuitem=sggl">增加宿管</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item" id="xsgl">
                    <a href="javascript:;">学生管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="showStuServlet?action=showStu&judge=ad&menuitem=xsgl">显示学生</a></dd>
                        <!-- 加个参数judge是因为避免重复创显示学生的jsp页面 -->
                        <dd><a href="showStuServlet?action=addsu&menuitem=xsgl">增加学生</a></dd>
                        <dd><a href="recordServlet?action=record&menuitem=xsgl&judge=ad">考勤管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item" id="ldgl">
                    <a href="javascript:;">楼栋管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="showBuildServlet?action=showDormB&menuitem=ldgl">显示楼栋</a></dd>
                        <dd><a href="showBuildServlet?action=addBuild&menuitem=ldgl">增加楼栋</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="remarkServlet?action=bwl&userName=${Admin.userName}">备忘录</a></li>
                <li class="layui-nav-item"><a href="dormBuildRepairServlet?action=bxjl&judge=ad">报修记录</a></li>
                <li class="layui-nav-item"><a href="showDroServlet?action=xgxx">修改个人信息</a></li>
                <li class="layui-nav-item"><a href="removeServlet">退出登录</a></li>
            </ul>
        </div>
    </div>
    <div class="layui-body">
<%--        <iframe src="${mainPage==null?'/welcome.jsp':mainPage}" style="margin: 0px;padding:0px;border: 0px;width:100%;height: 650px"></iframe>--%>
    <jsp:include page="${mainPage==null?'/welcome.jsp':mainPage}"></jsp:include>
    </div>
    <div class="layui-footer" align="center">
        <!-- 底部固定区域 -->
        <footer>© madeby-3522游击队</footer>
    </div>
</div>

</body>
</html>
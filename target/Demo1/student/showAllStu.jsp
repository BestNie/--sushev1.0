<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<form action="showStuServlet?judge=${param.judge}&menuitem=xsgl" class="navbar-form navbar-left" method="post" style="width: 100%">
    <div style="float: right">
        <label>姓名：</label>
        <input type="text" class="" name="searchName" value="${pb.searchName}">
        <button type="submit" class="layui-btn layui-btn-xs">查询</button>
    </div>
</form>
<div class="layui-table-box" style="clear: both">
    <div class="layui-table-header">
        <c:choose>
            <c:when test="${empty pb.list}">
                <div class="header"><h1>没有学生信息</h1></div>
            </c:when>
            <c:otherwise>
                <table cellspacing="0" cellpadding="0" border="0" class="layui-table">
                    <thead>
                    <tr>
                        <th style="width:50px;">
                            <div class="layui-table-cell"><span>学号</span></div>
                        </th>
                        <th style="width:50px;">
                            <div class="layui-table-cell "><span>姓名</span></div>
                        </th>
                        <th>
                            <div class="layui-table-cell "><span>性别</span></div>
                        </th>
                        <th>
                            <div class="layui-table-cell" align="center"><span>宿舍楼</span></div>
                        </th>
                        <th>
                            <div class="layui-table-cell" align="center"><span>寝室</span></div>
                        </th>
                        <th>
                            <div class="layui-table-cell" align="center"><span>电话</span></div>
                        </th>
                        <th>
                            <div class="layui-table-cell " align="center"><span>操作</span></div>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="stu" items="${pb.list}" varStatus="status">
                        <tr data-index="0" class="">
                            <td style="width:50px;">
                                <div class="layui-table-cell ">${stu.stuNum}</div>
                            </td>
                            <td style="width:50px;">
                                <div class="layui-table-cell ">${stu.name}</div>
                            </td>
                            <td style="width:50px;">
                                <div class="layui-table-cell">${stu.sex}</div>
                            </td>
                            <td data-field="username" class="">
                                <div class="layui-table-cell">${stu.dormBuildName}</div>
                            </td>
                            <td data-field="username" class="">
                                <div class="layui-table-cell">${stu.dormName}</div>
                            </td>
                            <td data-field="username" class="">
                                <div class="layui-table-cell">${stu.tel}</div>
                            </td>
                            <td align="center" data-off="true"
                                class="layui-table-col-special">
                                <div class="layui-table-cell ">
                                    <c:if test="${param.judge eq 'ad'}">
                                        <a class="layui-btn layui-btn-xs"
                                           href="showStuServlet?action=update&stuNum=${stu.stuNum}&currentPage=${pb.currentPage}&listID=${status.index}&menuitem=xsgl">
                                            编辑
                                        </a>&nbsp;&nbsp;
                                        <a
                                                class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"
                                                href="deleteStuServlet?stuNum=${stu.stuNum}&currentPage=${pb.currentPage}&menuitem=xsgl"
                                                onClick="delcfm()">删除</a>
                                    </c:if>
                                    <c:if test="${param.judge eq 'dm'}">
                                        <a class="layui-btn layui-btn-xs" href="recordServlet?action=addabsenceInfo&stuNum=${stu.stuNum}&judge=dm">添加缺勤记录</a>
                                    </c:if>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tr data-index="0" class="">
                        <td colspan="7" align="center">
                            <nav aria-label="Page navigation">
                                <ul class="pagination">
                                    <c:if test="${pb.currentPage == 1}">
                                    <li class="disabled">
                                        </c:if>
                                        <c:if test="${pb.currentPage != 1}">
                                    <li>
                                        </c:if>
                                        <a href="showStuServlet?judge=${param.judge}&currentPage=${pb.currentPage - 1}&searchName=${pb.searchName}&menuitem=xsgl"
                                           aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                    <c:if test="${pb.currentPage >= 7}">
                                        <c:forEach begin="${pb.currentPage - 5}"
                                                   end="${(pb.currentPage + 4) >= pb.totalPage ? pb.totalPage : (pb.currentPage + 4)}"
                                                   var="i">
                                            <c:if test="${pb.currentPage == i}">
                                                <li class="active"><a
                                                        href="showStuServlet?judge=${param.judge}&currentPage=${i}&searchName=${pb.searchName}&menuitem=xsgl">${i}</a>
                                                </li>
                                            </c:if>
                                            <c:if test="${pb.currentPage != i}">
                                                <li>
                                                    <a href="showStuServlet?judge=${param.judge}&currentPage=${i}&searchName=${pb.searchName}&menuitem=xsgl">${i}</a>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${pb.currentPage < 7}">
                                        <c:forEach begin="1" end="${pb.totalPage >= 10 ? 10 : pb.totalPage}"
                                                   var="i">
                                            <c:if test="${pb.currentPage == i}">
                                                <li class="active"><a
                                                        href="showStuServlet?judge=${param.judge}&currentPage=${i}&searchName=${pb.searchName}&menuitem=xsgl">${i}</a>
                                                </li>
                                            </c:if>
                                            <c:if test="${pb.currentPage != i}">
                                                <li>
                                                    <a href="showStuServlet?judge=${param.judge}&currentPage=${i}&searchName=${pb.searchName}&menuitem=xsgl">${i}</a>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${pb.currentPage == pb.totalPage}">
                                    <li class="disabled">
                                        </c:if>
                                        <c:if test="${pb.currentPage != pb.totalPage}">
                                    <li>
                                        </c:if>
                                        <a href="showStuServlet?judge=${param.judge}&currentPage=${pb.currentPage + 1}&searchName=${pb.searchName}&menuitem=xsgl"
                                           aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                            <span>共${pb.totalCount}条记录</span>
                            <span>共${pb.totalPage}页</span>
                        </td>
                    </tr>
                    <%--<tr>
                        <td colspan="7" align="center"><a
                                href="showDroServlet?action=addsu&currentPage=${pb.currentPage}">增加宿舍楼栋管理员</a></td>
                    </tr>
                    <tr>
                        <td colspan="7" align="center"><a href="/excelServlet">导出全部信息到电脑</a></td>
                            ${tsxx}
                            ${tsxx=null}
                    </tr>--%>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</div>

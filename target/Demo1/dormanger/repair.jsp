<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form action="dormBuildRepairServlet?action=bxjl&judge=${param.judge}" class="navbar-form navbar-left" method="post"
      style="width: 100%">
    <div style="float: right">
        <label>寝室号：</label>
        <input type="text" class="" name="searchName" value="${pb.searchName}">
        <button type="submit" class="layui-btn layui-btn-xs">查询</button>
    </div>
</form>
<div class="layui-table-box" style="clear: both">
    <div class="layui-table-header">
        <c:choose>
            <c:when test="${empty pb.list}">
                <div class="header"><h1>没有报修信息</h1></div>
            </c:when>
            <c:otherwise>
                <table cellspacing="0" cellpadding="0" border="0" class="layui-table">
                    <thead>
                    <tr>
                        <th style="width:50px;">
                            <div class="layui-table-cell"><span>报修单号</span></div>
                        </th>
                        <th style="width:50px;">
                            <div class="layui-table-cell "><span>楼栋名</span></div>
                        </th>
                        <th>
                            <div class="layui-table-cell "><span>寝室号</span></div>
                        </th>
                        <th style="width:50px;">
                            <div class="layui-table-cell"><span>报修类别</span></div>
                        </th>
                        <th>
                            <div class="layui-table-cell" align="center"><span>报修内容</span></div>
                        </th>
                        <th>
                            <div class="layui-table-cell" align="center"><span>报修人</span></div>
                        </th>
                        <th>
                            <div class="layui-table-cell" align="center"><span>报修时间</span></div>
                        </th>
                        <th>
                            <div class="layui-table-cell " align="center"><span>工单状态</span></div>
                        </th>
                        <th>
                            <div class="layui-table-cell " align="center"><span>操作</span></div>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="rp" items="${pb.list}" varStatus="statuss">
                        <tr data-index="0" class="">
                            <td style="width:50px;">
                                <div class="layui-table-cell ">${rp.repairId}</div>
                            </td>
                            <td style="width:50px;">
                                <div class="layui-table-cell ">${rp.dormBuildName}</div>
                            </td>
                            <td style="width:50px;">
                                <div class="layui-table-cell">${rp.dormName}</div>
                            </td>
                            <td style="width:50px;">
                                <div class="layui-table-cell ">${rp.project}</div>
                            </td>
                            <td data-field="username" class="">
                                <div class="layui-table-cell">${rp.content}</div>
                            </td>
                            <td data-field="username" class="">
                                <div class="layui-table-cell">${rp.repairer}</div>
                            </td>
                            <td data-field="username" class="">
                                <div class="layui-table-cell">${rp.date}</div>
                            </td>
                            <td data-field="username" class="">
                                <div class="layui-table-cell">${rp.status}</div>
                            </td>
                            <td align="center" data-off="true"
                                class="layui-table-col-special">
                                <div class="layui-table-cell ">
                                    <c:if test="${param.judge eq 'ad'}">
                                        <a class="layui-btn layui-btn-xs" href="updateStatusServlet?status=${rp.status}&repairId=${rp.repairId}&currentPage=${pb.currentPage}&judge=ad">受理</a>
                                    </c:if>
                                    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"
                                       href="deleteRepairServlet?repairId=${rp.repairId}&currentPage=${pb.currentPage}&judge=dm"
                                       onClick="delcfm()">删除</a>

                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tr data-index="0" class="">
                        <td colspan="9" align="center">
                            <nav aria-label="Page navigation">
                                <ul class="pagination">
                                    <c:if test="${pb.currentPage == 1}">
                                    <li class="disabled">
                                        </c:if>
                                        <c:if test="${pb.currentPage != 1}">
                                    <li>
                                        </c:if>
                                        <a href="dormBuildRepairServlet?currentPage=${pb.currentPage - 1}&searchName=${pb.searchName}&judge=${param.judge}"
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
                                                        href="dormBuildRepairServlet?currentPage=${i}&searchName=${pb.searchName}&judge=${param.judge}">${i}</a>
                                                </li>
                                            </c:if>
                                            <c:if test="${pb.currentPage != i}">
                                                <li>
                                                    <a href="dormBuildRepairServlet?currentPage=${i}&searchName=${pb.searchName}&judge=${param.judge}">${i}</a>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${pb.currentPage < 7}">
                                        <c:forEach begin="1" end="${pb.totalPage >= 10 ? 10 : pb.totalPage}"
                                                   var="i">
                                            <c:if test="${pb.currentPage == i}">
                                                <li class="active"><a
                                                        href="dormBuildRepairServlet?currentPage=${i}&searchName=${pb.searchName}&judge=${param.judge}">${i}</a>
                                                </li>
                                            </c:if>
                                            <c:if test="${pb.currentPage != i}">
                                                <li>
                                                    <a href="dormBuildRepairServlet?currentPage=${i}&searchName=${pb.searchName}&judge=${param.judge}">${i}</a>
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
                                        <a href="dormBuildRepairServlet?currentPage=${pb.currentPage + 1}&searchName=${pb.searchName}&judge=${param.judge}"
                                           aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                            <span>共${pb.totalCount}条记录</span>
                            <span>共${pb.totalPage}页</span>
                        </td>
                        ${tsxx}${tsxx=null}
                    </tr>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</div>


<%--
  Created by IntelliJ IDEA.
  User: Sergey Laposhko
  Date: 08.10.14
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<jsp:directive.page language="java"
                    contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"/>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:c="http://java.sun.com/jsp/jstl/core">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="css/pager.css" rel="stylesheet">
<body>
<table id="pager">
    <c:choose>
        <c:when test="${pager.pages < 6}">
            <c:forEach begin="1" end="${pager.pages}" var="i">
                <c:choose>
                    <c:when test="${pager.pageIndex eq i}">
                        <td class="current_page page">${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td class="page"><a href="#" onclick="setAttr('pageIndex', '${i}')">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${pager.pageIndex < 5}">
                    <c:forEach begin="1" end="5" var="i">
                        <c:choose>
                            <c:when test="${pager.pageIndex eq i}">
                                <td class="current_page page">${i}</td>
                            </c:when>
                            <c:otherwise>
                                <td class="page"><a href="#" onclick="setAttr('pageIndex', '${i}')">${i}</a></td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <td class="page">...</td>
                    <td class="page"><a href="#" onclick="setAttr('pageIndex', '${pager.pages}')">${pager.pages}</a></td>
                </c:when>
                <c:when test="${pager.pageIndex > pager.pages - 4 }">
                    <td class="page"><a href="#" onclick="setAttr('pageIndex', '1')">1</a></td>
                    <td class="page">...</td>
                    <c:forEach begin="${pager.pages - 4 }" end="${pager.pages}" var="i">
                        <c:choose>
                            <c:when test="${pager.pageIndex eq i}">
                                <td class="current_page page">${i}</td>
                            </c:when>
                            <c:otherwise>
                                <td class="page"><a href="#" onclick="setAttr('pageIndex', '${i}')">${i}</a></td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <td class="page"><a href="#"  onclick="setAttr('pageIndex', '1')">1</a></td>
                    <td class="page">...</td>
                    <c:forEach begin="${pager.pageIndex - 2 }" end="${pager.pageIndex + 2 }" var="i">
                        <c:choose>
                            <c:when test="${pager.pageIndex eq i}">
                                <td class="current_page page">${i}</td>
                            </c:when>
                            <c:otherwise>
                                <td class="page"><a href="#" onclick="setAttr('pageIndex', '${i}')">${i}</a></td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <td class="page">...</td>
                    <td class="page"><a href="#" onclick="setAttr('pageIndex', '${pager.pages}')">${pager.pages}</a></td>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
</table>
</body>
</html>


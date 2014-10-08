<%--
  Created by IntelliJ IDEA.
  User: Сергей
  Date: 08.10.14
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:directive.page language="java"
                    contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"/>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:c="http://java.sun.com/jsp/jstl/core">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
    <title>Insert title here</title>
</head>
<body>
<f:view>
    <table>
        <c:choose>
            <c:when test="${param.pager.pages < 6}">
                <c:forEach begin="1" end="${param.pager.pages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <td class="current_page">${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="${param.pager.setPageIndex(i)}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${param.pager.pageIndex < 5}">
                        <c:forEach begin="1" end="5" var="i">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                    <td class="current_page">${i}</td>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="${param.pager.setPageIndex(i)}">${i}</a></td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <td>...</td>
                        <td><a href="${param.pager.setPageIndex(param.pager.pages)}">${param.pager.pages}</a></td>
                    </c:when>
                    <c:when test="${param.pager.pageIndex > param.pager.pages - 4 }">
                        <td><a href="${param.pager.setPageIndex(1)}">1</a></td>
                        <td>...</td>
                        <c:forEach begin="${param.pager.pages - 4 }" end="${param.pager.pages}" var="i">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                    <td class="current_page">${i}</td>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="${param.pager.setPageIndex(i)}">${i}</a></td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>>
        </c:choose>
    </table>
</f:view>
</body>
</html>


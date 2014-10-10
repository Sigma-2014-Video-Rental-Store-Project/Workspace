<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:directive.page language="java"
                    contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"/>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:c="http://java.sun.com/jsp/jstl/core">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
    <title>Films</title>
</head>
<body>
${films}
${myfilm.title}
<f:view>
    <div id="films">
        <table>
            <thead>
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Copies left</th>
                    <th scope="col">Price per day</th>
                    <th scope="col">Add to cart</th>
                    <th scope="col">Edit</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${films.model}"  var="current">
                        <tr>
                            <td><c:out value="${current.title}" /></td>
                            <td><c:out value="${current.amount}" /></td>
                            <td><c:out value="${current.rentPrice}" /></td>
                            <td>smth&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <div id="pages">
        <c:set var="pager" value="${films.pager}" scope="request" />
        <c:set var="prefix" value="${films.pageNumPrefix}" scope="request"/>
        <jsp:include page="pager.jsp"/>
    </div>
</f:view>
</body>
</html>
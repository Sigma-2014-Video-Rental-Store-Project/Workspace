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
            <c:forEach var="film" items="${films.model}">
                <tbody>
                    <tr>
                        <td>${film.name}</td>
                        <td>${film.count}</td>
                        <td>${film.rentPrice}</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                </tbody>
            </c:forEach>
        </table>
    </div>

    <div id="pages">
        <jsp:include page="pager.jsp">
            <jsp:param name="pager" value="${films}"/>
        </jsp:include>
    </div>
</f:view>
</body>
</html>
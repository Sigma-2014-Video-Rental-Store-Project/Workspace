<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:directive.page language="java"
                    contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"/>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:c="http://java.sun.com/jsp/jstl/core">
<link href="css/linkBlock.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/film_list.css" rel="stylesheet">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="signin page for Rental Store">
    <meta name="author" content="Vlad Samotskiy">
    <link rel="icon" href="">
    <title>Films</title>
</head>
<body>
<f:view>
    <div id="header">
        <jsp:include page="../jspf/header.jspf"></jsp:include>
    </div>
    <div id="category">
        <c:forEach items="${categories.model}" var="current">
            <div style="background: #919191; padding: .3em 3px;">
                <a href="controller?command=fullFilmList&categories=${current.id}" class="categoryButton">
                    <c:out value="${current.name}"/>
                </a>
            </div>
            </br>
        </c:forEach>
    </div>
    <div id="content-body">
        <div id="films">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th class="name-column" scope="col">Name</th>
                    <th class="copies-column" scope="col">Copies left</th>
                    <th class="price-column" scope="col">Price per day</th>
                    <th class="add-column" scope="col">Add to cart</th>
                    <th class="edit-column" scope="col">Edit</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${films.model}" var="current">
                    <tr>
                        <td><c:out value="${current.title}"/></td>
                        <td><c:out value="${current.amount}"/></td>
                        <td><c:out value="${current.rentPrice}"/></td>
                        <td><a href="">add</a>&nbsp;</td>
                        <td><a href="">edit</a>&nbsp;</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div id="pages">
            <c:set var="pager" value="${films.pager}" scope="request"/>
            <c:set var="prefix" value="${films.pageNumPrefix}" scope="request"/>
            <jsp:include page="pager.jsp"/>
        </div>
    </div>
    <div id="footer">
        <jsp:include page="../jspf/footer.jspf"></jsp:include>
    </div>
</f:view>
</body>
</html>
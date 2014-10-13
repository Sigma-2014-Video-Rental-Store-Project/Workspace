<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:directive.page language="java"
                    contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"/>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:c="http://java.sun.com/jsp/jstl/core">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/film_list.css" rel="stylesheet">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Film list page.">
    <meta name="author" content="Sergey Laposhko">
    <link rel="icon" href="">
    <title>Films</title>
</head>
<body>
<f:view>
    <div id="header">
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
    </div>
    <div id="category">
        <ul class="nav nav-sidebar">
            <li>
                <a href="controller?command=fullFilmList&categories=0">
                    <c:out value="all categories"/>
                </a>
            </li>
            <c:forEach items="${categories.model}" var="current">
                <li>
                    <a href="controller?command=fullFilmList&categories=${current.id}">
                        <c:out value="${current.name}"/>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div id="content-body">
        <div id="buttons">
            <div id="filter-buttons">
                <form class="search form-inline" role="form" action="controller" method="post">
                    <%--<input type="hidden" name="command" value="TODO fullFilmList"/>--%>
                    <button class="btn btn-primary" type="submit">All</button>
                    <button class="btn btn-default" type="submit">Available</button>
                </form>
            </div>
            <div id="add-new-film-button">
                <form class="add-new-film" role="form" action="controller" method="post">
                    <%--<input type="hidden" name="command" value="TODO"/>--%>
                    <button class="btn btn-success" type="submit">Add new film</button>
                </form>
            </div>
            <div id="search-buttons">
                <form class="search" role="form" action="controller" method="post">
                    <input type="hidden" name="command" value="fullFilmList"/>
                    <input id="key-search" name="key" type="text" class="form-control" placeholder="Keywords" required="" autofocus="">
                    <button id="search-button"class="btn btn-primary" type="submit">Search</button>
                </form>
            </div>
        </div>

        <div id="films">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th class="name-column" scope="col">Name</th>
                    <th class="copies-column" scope="col">Amount</th>
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
        <jsp:include page="../jspf/footer.jspf"/>
    </div>
</f:view>
</body>
</html>
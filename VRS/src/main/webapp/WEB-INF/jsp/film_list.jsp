<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:directive.page language="java"
                    contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"/>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:c="http://java.sun.com/jsp/jstl/core">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/film_list.css" rel="stylesheet">
<link rel="stylesheet" href="css/font-awesome.min.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Film list page.">
    <meta name="author" content="Sergey Laposhko">
    <link rel="icon" href="">
    <title>Films</title>
	<fmt:setLocale value="en_US" />
</head>
<body>
<f:view>
    <div id="header">
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
    </div>
    <div id="category">
        <ul class="nav nav-sidebar">
            <li>
                <a href="#" onclick="setSeveralAttr(['categories', 'pageIndex'], ['0', '1'])">
                    <c:out value="all categories"/>
                </a>
            </li>
            <c:forEach items="${categories.model}" var="current">
                <li>
                    <a href="#" onclick="setSeveralAttr(['categories', 'pageIndex'], ['${current.id}', '1'])">
                        <c:out value="${current.name}"/>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div id="content-body">
        <div id="buttons">
            <div id="filter-buttons" class="btn-group" data-toggle="buttons">
                <label class="btn btn-primary active" id="all-filter-label">
                    <input type="radio" id="all-filter" checked> All
                </label>
                <label class="btn btn-primary" id="available-filter-label">
                    <input type="radio" id="available-filter"> Available
                </label>
            </div>
            <div id="add-new-film-button">
                    <button class="btn btn-success" onclick="controller?command=addNewFilm">Add new film</button>
            </div>
            <div id="search-buttons">
                <form class="search" role="form" action="controller" method="get">
                    <input type="hidden" name="command" value="fullFilmList"/>
                    <input id="product_search" style = "float: left; width: 88%;" name="key" type="text"class="form-control"
		                placeholder="Keywords" required="" autofocus="" autocomplete="off" data-provide="typeahead">
                    <button id="search-button" class="btn btn-primary" type="submit">Search</button>
                </form>
            </div>
        </div>

        <div id="films">
            <table id="films-table" class="table table-striped">
                <thead>
                <tr>
                    <th class="name-column" scope="col">Name
                        <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['name','up'])"><u><i
                                class="fa fa-arrow-up"></i></u></a>
                        <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['name','down'])"><u><i
                                class="fa fa-arrow-down"></i></u></a></th>
                    <th class="copies-column" scope="col">Copies left
                        <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['copies','up'])"><u><i
                                class="fa fa-arrow-up"></i></u></a>
                        <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['copies','down'])"><u><i
                                class="fa fa-arrow-down"></i></u></a>
                    </th>
                    <th class="price-column" scope="col">Price per day
                        <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['price','up'])"><u><i
                                class="fa fa-arrow-up"></i></u></a>
                        <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['price','down'])"><u><i
                                class="fa fa-arrow-down"></i></u></a></th>
                    <th class="add-column" scope="col">Add to cart</th>
                    <th class="edit-column" scope="col">Edit</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${films.model}" var="current">
                    <tr>
                        <td><a href="controller?command=filmDetails&filmId=${current.filmId}">${current.title}</a></td>
                        <td style="text-align:center;"><c:out value="${current.copiesLeft}"/></td>
                        <td style="text-align:right; padding-right:5%;"><fmt:formatNumber type="number" minFractionDigits="2" value="${current.rentPrice/100}"/></td>
                        <td style="text-align:center;"><a class="add-to-cart-link" href="#" onclick="sendGetRequest('${current.filmId}',this)">add</a>&nbsp;</td>
                        <td style="text-align:center;"><a href="controller?command=editFilm&filmId=${current.filmId}">edit</a>&nbsp;</td>
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
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/film_list.js"></script>
<script type="text/javascript" src="js/attribute_funcs.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="js/bootstrap-typeahead.js"></script>

<script>
$(document).ready(function($) {
// Workaround for bug in mouse item selection
$.fn.typeahead.Constructor.prototype.blur = function() {
var that = this;
setTimeout(function () { that.hide() }, 250);
};
 
$('#product_search').typeahead({
source: function(query, process) {
return [<c:forEach items="${allFilms.allFilms}" var="current">"<c:out value="${current.title}"/>",</c:forEach>];
}
});
})
</script>
</html>
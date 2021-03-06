<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:directive.page language="java"
                    contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"/>
<%@ taglib uri="http://ua.nure.sigma.store/functions" prefix="uf"%>
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
    <title><fmt:message key="filmlist.title"/></title>
</head>
<body>
<f:view>
    <div id="header">
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
    </div>
    <div id="category">
        <ul class="nav nav-sidebar">
            <li>
                <a href="#" onclick="setSeveralAttrForCategory(['categories', 'pageIndex'], ['0', '1'])">
                    <fmt:message key="filmlist.allCat"/>
                </a>
            </li>
            <c:forEach items="${categories.model}" var="current">
                <li>
                    <a href="#" onclick="setSeveralAttrForCategory(['categories', 'pageIndex'], ['${current.id}', '1'])">
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
                    <input type="radio" id="all-filter" checked><fmt:message key="filmlist.all"/>
                </label>
                <label class="btn btn-primary" id="available-filter-label">
                    <input type="radio" id="available-filter"><fmt:message key="filmlist.avail"/>
                </label>
            </div>
            <div id="add-new-film-button">
                <a href="controller?command=addNewFilm"><button class="btn btn-success"><fmt:message key="filmlist.addNewFilmbtn"/></button></a>
            </div>
            <div id="search-buttons">
                <form class="search" role="form" action="controller" method="get">
                    <input type="hidden" name="command" value="fullFilmList"/>
                    <input id="product_search" style = "float: left; width: 88%;" name="key" type="text"class="form-control"
                           placeholder='<fmt:message key="filmlist.searchPlaceholder"/>' required="" autofocus="" autocomplete="off" data-provide="typeahead">
                    <button id="search-button" class="btn btn-primary" type="submit"><fmt:message key="filmlist.searchbtn"/></button>
                </form>
            </div>
        </div>

        <div id="films">
            <table id="films-table" class="table table-striped">
                <thead>
                <tr>
                    <th class="name-column" scope="col"><fmt:message key="filmlist.colName"/>
                        <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['name','up'])"><u><i
                                class="fa fa-arrow-up"></i></u></a>
                        <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['name','down'])"><u><i
                                class="fa fa-arrow-down"></i></u></a></th>
                    <th class="copies-column" scope="col"><fmt:message key="filmlist.colCopies"/>
                        <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['copies','up'])"><u><i
                                class="fa fa-arrow-up"></i></u></a>
                        <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['copies','down'])"><u><i
                                class="fa fa-arrow-down"></i></u></a>
                    </th>
                    <th class="price-column" scope="col"><fmt:message key="filmlist.colPrice"/>
                        <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['price','up'])"><u><i
                                class="fa fa-arrow-up"></i></u></a>
                        <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['price','down'])"><u><i
                                class="fa fa-arrow-down"></i></u></a></th>
                    <th class="add-column" scope="col"><fmt:message key="filmlist.colAdd"/></th>
                    <th class="edit-column" scope="col"><fmt:message key="filmlist.colEdit"/></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${films.model}" var="current">
                    <tr>
                        <td><a href="controller?command=filmDetails&filmId=${current.filmId}">${current.title}</a></td>
                        <td style="text-align:center;"><c:out value="${current.copiesLeft}"/></td>
                        <td style="text-align:right; padding-right:5%;">\$<fmt:formatNumber type="number" minFractionDigits="2" value="${current.rentPrice/100}"/></td>
                        <td style="text-align:center;">
                            <c:choose>
                                <c:when test="${current.copiesLeft eq 0}">
                                    <span style="color: #b35a3f;"><fmt:message key="filmlist.absent"/></span>
                                </c:when>
                                <c:when test="${uf:isAdded(current.filmId,sessionScope.cart)}">
                                    <span style="color: #3CB371;"><fmt:message key="filmlist.added"/></span>
                                </c:when>
                                <c:otherwise>
                                    <form method="post" action="controller" style="display: inline;">
                                        <input type="hidden" name="command" value="cartAdd"/>
                                        <input type="hidden" name="returnTo" value="controller?command=fullFilmList"/>
                                        <input type="hidden" name="filmId" value="${current.filmId}"/>
                                        <input type="submit" style="color: #6495ED; background: none; border: none; cursor: pointer;" value='<fmt:message key="filmlist.addLink"/>'/>
                                    </form>
                                    <%--<a class="add-to-cart-link" href="controller?command=cartAdd&filmId=${current.filmId}">add</a>&nbsp;--%>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td style="text-align:center;"><a href="controller?command=editFilm&filmId=${current.filmId}"><fmt:message key="filmlist.editLink"/></a>&nbsp;</td>
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
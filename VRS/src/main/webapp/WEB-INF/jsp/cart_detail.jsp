<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:directive.page language="java"
                    contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"/>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:c="http://java.sun.com/jsp/jstl/core">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/film_list.css" rel="stylesheet">
<link rel="stylesheet" href="css/font-awesome.min.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Film list page.">
    <meta name="author" content="Maksim Sinkevich">
    <meta name="author" content="Denys Shevchenko">
    <link rel="icon" href="">
    <title>Films</title>
</head>
<body>
<f:view>
    <div id="header">
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
    </div>
    <%--<div id="bonuses">--%>
    <%--<c:out value="Total bonuses: ${totalBonuses}"/>--%>
    <%--</div>--%>

    <div style="padding: 0 10%;">
        <table id="films-table" class="table table-striped" style="border: 1px solid #CCCCCC;">
            <thead>
            <tr>
                <th class="name-column" scope="col">Name</th>
                <th class="copies-column" scope="col">Copies for rent</th>
                <th class="price-column" scope="col">Total price</th>
                <th class="add-column" scope="col">Days</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="entry" items="${sessionScope.cart.content}">
            <tr>
                <td style="padding-top: 15px;">${entry.value.title}</td>
                <td style="text-align:center;">
                    <input type="number" name="amount" class="form-control" style="max-width: 150px; margin-bottom:2%; margin-left:3%;"
                           min="0" max=${editFilmObject.copiesLeft} value="0">
                    <!--<c:out value="${current.copiesLeft}"/>-->
                </td>
                <td style="text-align:right; padding-right:2%; padding-top: 15px;">
                    4.20
                    <!--<fmt:formatNumber type="number" minFractionDigits="2" value="${current.rentPrice/100}"/>-->
                </td>
                <td style="text-align:center;">
                    <input type="number" name="amount" class="form-control" style="max-width: 150px; margin-bottom:2%; margin-left:3%;"
                           min="0" max=${editFilmObject.copiesLeft} value="0">
                </td>
                <td style="text-align:center; padding-top: 15px;"><a href="controller?command=editFilm&filmId=${current.filmId}">Remove</a>&nbsp;
                </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>


    <div style="height: 30px; padding: 0 15%" name="customer_select">
        <form class="search" role="form" action="controller" method="get">
            <input type="hidden" name="command" value="cartSearch"/>
            <input id="customer_search" style="float: left; width: 85%; padding-right: 10px;" name="customerFullName"
                   type="text"
                   class="form-control"
                   placeholder="Select customer" value="${customerFullName}" required="" autofocus="" autocomplete="off"
                   data-provide="typeahead">
            <button style="float: left; width: 12%; min-width: 100px; margin-left: 3%;" id="select_button"
                    class="btn btn-primary" type="submit">
                Select
            </button>
        </form>
    </div>

    <div style="height: 30px; padding: 0 15%" name="customer_select">
        <form class="search" role="form" action="controller" method="get">
            <button style="float: right; width: 12%; min-width: 100px; margin-left: 3%;" id="select_button"
                    class="btn btn-danger" type="submit">
                Cancel
            </button>
            <p style="float: right; font-weight: bold; padding-top: 8px;">Denys Shevchenko Olehovich</p>
            <p style="float: right; font-weight: bold; padding-top: 8px; margin-right: 15px;">Current customer:</p>
        </form>
    </div>

    <div style="width: 100%; float: left; padding: 0 15%; padding-top: 20px;">
        <div style="width: 376px; float: right; text-align: right;">
            <p style="clear: both; float: left; font-weight: bold;">Total cost without bonus: 100500$</p>
            <p style="clear: both; float: left; font-weight: bold;">Bonus for current rent: 10$</p>
            <p style="clear: both; float: left; font-weight: bold;">Bonus points available: 10$</p>
            <div style="width: 370px; float: left;">
                <label style="float: left; padding-top: 8px;" for="bonus-to-use">Bonus points to use:</label>
                <input  id="bonus-to-use" type="number" name="amount" class="form-control"
                       style="float: left; margin-bottom:2%; margin-left:3%; width: 100px;" min="0"
                       max=${editFilmObject.copiesLeft} value="0">
                <button style="float: left; margin-left: 15px;" id="use-bonus" class="btn btn-primary" type="submit">Use bonus</button>
            </div>
        </div>
    </div>

    <div style="margin-top: 10px; width: 100%; float: left; padding: 0 15%;">
        <div style="float: left;">
            <button id="clear-cart" class="btn btn-danger" type="submit">Clear cart</button>
        </div>
        <div style="float: right;">
            <button style="margin-right: 10px;" class="btn btn-success">Continue</button>
        </div>
    </div>

    <%--<div id="continueButton">--%>
    <%--<a href="controller?command=cartContinue">--%>
    <%--<button class="btn btn-success">Continue</button>--%>
    <%--</a>--%>
    <%--</div>--%>
    <div id="footer">
        <jsp:include page="../jspf/footer.jspf"/>
    </div>
</f:view>
</body>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="js/bootstrap-typeahead.js"></script>
<script>
    $(document).ready(function ($) {
        $.fn.typeahead.Constructor.prototype.blur = function () {
            var that = this;
            setTimeout(function () {
                that.hide()
            }, 250);
        };
        $('#customer_search').typeahead({
            source: function (query, process) {
                return [<c:forEach items="${customers.model}" var="current">"<c:out value="${current.firstName} ${current.lastName}"/>", </c:forEach>];
            }
        });
    })
</script>
</html>
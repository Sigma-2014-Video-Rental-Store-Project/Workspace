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
        <link rel="icon" href="">
        <title>Films</title>
    </head>
    <body>
        <f:view>
            <div id="header">
                    <%@ include file="/WEB-INF/jspf/header.jspf" %>
            </div>
            <div name="customer_select">
                <form class="search" role="form" action="controller" method="get">
                    <c:out value="${totalBonuses}"/>
                    <input type="hidden" name="command" value="cartSearch"/>
                    <input id="customer_search" style = "float: left; width: 88%;" name="customerFullName" type="text" class="form-control"
                        placeholder="Select customer" value="${customerFullName}" required="" autofocus="" autocomplete="off" data-provide="typeahead">
                    <button id="select_button" class="btn btn-primary" type="submit">Select</button>
                </form>
            </div>
            <div id="footer">
                <jsp:include page="../jspf/footer.jspf"/>
            </div>
        </f:view>
    </body>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
	<script src="js/bootstrap-typeahead.js"></script>
    <script>
        $(document).ready(function($) {
            $.fn.typeahead.Constructor.prototype.blur = function() {
                var that = this;
                setTimeout(function () { that.hide() }, 250);
            };
            $('#customer_search').typeahead({
                source: function(query, process) {
                    return [<c:forEach items="${customers.model}" var="current">"<c:out value="${current.firstName} ${current.lastName}"/>",</c:forEach>];
                }
            });
        })
    </script>
</html>
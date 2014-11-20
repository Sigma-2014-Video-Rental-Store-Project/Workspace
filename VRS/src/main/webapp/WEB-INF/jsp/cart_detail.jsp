<?xml version="1.0" encoding="UTF-8" ?>
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:c="http://java.sun.com/jsp/jstl/core">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/film_list.css" rel="stylesheet">
<link rel="stylesheet" href="css/font-awesome.min.css">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Film list page.">
    <meta name="author" content="Maksim Sinkevich">
    <meta name="author" content="Denys Shevchenko">
    <link rel="icon" href="">
    <title><fmt:message key="cart.title" /></title>
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
        <form action="controller" method="post">
            <%--<input type="hidden" name="command" value="cartUpdate"/>--%>
        <table id="films-table" class="table table-striped" style="border: 1px solid #CCCCCC;">
            <thead>
            <tr>
                <th class="name-column" scope="col" style="width:30%;"><fmt:message key="cart.name"/></th>
                <th class="copies-column" scope="col" style="width:20%;"><fmt:message key="cart.copies"/></th>
                <th class="price-column" scope="col" style="width:20%;"><fmt:message key="cart.price"/></th>
                <th class="add-column" scope="col" style="width:20%;"><fmt:message key="cart.days"/></th>
                <th scope="col"><button style="padding: 5px;" class="btn btn-primary" name="command" value="cartUpdate" type="submit"><fmt:message key="cart.update"/></button></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="entry" items="${sessionScope.cart.content}">
                <tr>
                    <td style="padding-top: 15px;">${entry.key.title}</td>
                    <td style="text-align:center;">
                        <input type="number" name="copies${entry.key.filmId}" class="form-control"
                               style="width: 30%; margin-bottom:2%; margin-left: auto; margin-right: auto;"
                               min="1" max=${entry.key.copiesLeft} value="${entry.value.copies}">
                    </td>
                    <td style="text-align:right; padding-right:2%; padding-top: 15px;">$
                        <fmt:formatNumber type="number" minFractionDigits="2"
                                          value="${entry.key.rentPrice/100*entry.value.copies*entry.value.days}"/>
                    </td>
                    <td style="text-align:center;">
                        <input type="number" name="days${entry.key.filmId}" class="form-control"
                               style="width:30%; margin-bottom:2%; margin-left: auto; margin-right: auto;"
                               min="1" value="${entry.value.days}">
                    </td>
                    <td style="text-align:center; padding-top: 15px;">
                        <form method="post" action="controller" style="display: inline;">
                            <input type="hidden" name="command" value="cartRemove"/>
                            <input type="hidden" name="filmId" value="${entry.key.filmId}"/>
                            <input type="submit" style="color: #6495ED; background: none; border: none; cursor: pointer;" value='<fmt:message key="cart.remove"/>'/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </form>
    </div>

    <div style="height: 30px; padding: 0 10%" name="customer_select">
        <c:choose>
            <c:when test="${sessionScope.customerFullName eq null}">
                <form class="search" role="form" action="controller" method="post">
                    <input type="hidden" name="command" value="cartSearch"/>
                    <input id="customer_search" style="float: left; width: 85%; padding-right: 10px;"
                           name="customerFullName"
                           type="text"
                           class="form-control"
                           placeholder='<fmt:message key="cart.selectcustomer"/>' required="" autofocus="" autocomplete="off"
                           data-provide="typeahead">
                    <button style="float: left; width: 12%; min-width: 100px; margin-left: 3%;" id="select_button"
                            class="btn btn-primary" type="submit">
                        <fmt:message key="cart.select"/>
                    </button>
                </form>
            </c:when>
            <c:otherwise>
                <form class="search" role="form" action="controller" method="post">
                    <div style="width: 50%; float: right; text-align: right;">
                        <input type="hidden" name="command" value="cartCancelCustomer"/>
                        <p style="float: left; font-weight: bold; padding-top: 8px; margin-right: 15px;"><fmt:message key="cart.customer"/></p>                    
                        <p style="float: left; font-weight: bold; padding-top: 8px;">${sessionScope.customerFullName}</p>
                        <button style="float: left; width: 12%; min-width: 100px; margin-left: 3%;" id="select_button"
                                class="btn btn-danger" type="submit">
                            <fmt:message key="cart.cancel"/>
                        </button>
                </div>
                </form>
            </c:otherwise>
        </c:choose>
    </div>

    <div style="width: 100%; float: right; padding: 0 10%; padding-top: 20px;">
        <div style="width: 50%; float: right; text-align: right;">
            <p style="clear: both; float: left; font-weight: bold;"><fmt:message key="cart.totalwithoutbonus"/>
                $ <fmt:formatNumber type="number" minFractionDigits="2" value="${cart.totalCost/100}"/></p>
            <p style="clear: both; float: left; font-weight: bold;"><fmt:message key="cart.bonusforcurrentrent"/>
                $ <fmt:formatNumber type="number" minFractionDigits="2" value="${cart.bonusForRent/100}"/></p>
            <c:if test="${customerFullName ne null}">
                <p style="clear: both; float: left; font-weight: bold;"><fmt:message key="cart.bonusavailable"/>
                    $ <fmt:formatNumber type="number" minFractionDigits="2" value="${cart.currentCustomer.bonus/100}"/></p>
                <p style="clear: both; float: left; font-weight: bold;"><fmt:message key="cart.bonusactiveted"/>
                    $ <fmt:formatNumber type="number" minFractionDigits="2" value="${sessionScope.bonusInUse/100}"/></p>
                <div style="float: right; width:100%;">
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="cartUseBonus"/>
                        <label style="float: left; padding-top: 8px;" for="bonus-to-use"><fmt:message key="cart.bonususe"/></label>
                        <div style="float: left; margin-bottom:2%; margin-left:3%; margin-right:3%; width: 15%;"><input id="bonus-to-use" type="number" name="bonusToUse" class="form-control"
                                                                                                                        min="0"
                                                                                                                        max="${cart.currentCustomer.bonus + 0}" value="0">
                            <p style="font-size:12px; float:left;">1 bonus=$0.01</p>
                        </div>
                        
                        <button style="float: left;" id="use-bonus" class="btn btn-primary" type="submit"><fmt:message key="cart.bonusbtn"/></button>
                    </form>
                </div>
                
            </c:if>
        </div>
    </div>

    <div style="margin-top: 10px; width: 100%; float: left; padding: 0 10%;">
        <div style="float: left;">
            <form action="controller" method="post">
                <input type="hidden" name="command" value="cartClear"/>
                <button id="clear-cart" class="btn btn-danger" type="submit"><fmt:message key="cart.clearcart"/></button>
            </form>
        </div>
        <div style="float: right;">
            <form action="controller" method="post">
                <input type="hidden" name="command" value="cartContinue"/>
                <button type="submit" style="margin-right: 10px;" class="btn btn-success"><fmt:message key="cart.continue"/></button>
            </form>
        </div>
    </div>
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
<%--
  Created by IntelliJ IDEA.
  User: Sergey Laposhko
  Date: 28.10.14
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:directive.page language="java"
                    contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"/>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:c="http://java.sun.com/jsp/jstl/core">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/customer_details.css" rel="stylesheet">
<link rel="stylesheet" href="css/font-awesome.min.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Customer details">
    <meta name="author" content="Sergey Laposhko">
    <link rel="icon" href="">
    <title>Customer</title>
    <fmt:setLocale value="en_US"/>
</head>
<body>
<f:view>
    <div id="header">
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
    </div>
    <div id="customer-info">
        <div id="customer-name-block">
            <p><span id="customer-name">${customerDetailsList.customer.lastName}</span>
                <a href="controller?command=editCustomer&customerId=${customerDetailsList.customer.customerID}&get=true">Edit</a>
            </p>
        </div>
        <img id="avatar" data-src="holder.js/140x140" class="center"
             src="customerPhoto/${customerDetailsList.customer.customerPhoto}">
    </div>
    <div id="content-body">
        <div id="buttons">
            <div id="filter-buttons" class="btn-group" data-toggle="buttons">
                <label class="btn btn-primary active" id="now-rented">
                    <input type="radio" checked> Now rented
                </label>
                <label class="btn btn-primary" id="history-lable">
                    <input type="radio"> History
                </label>
            </div>
        </div>

        <div id="items">
            <table id="items-table" class="table table-striped">
                <thead>
                <tr>
                    <th class="name-column" scope="col">Title
                        <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['name','up'])"><u><i
                                class="fa fa-arrow-up"></i></u></a>
                        <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['name','down'])"><u><i
                                class="fa fa-arrow-down"></i></u></a></th>
                    <th class="start-date-column" scope="col">Start date
                        <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['startDate','up'])"><u><i
                                class="fa fa-arrow-up"></i></u></a>
                        <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['startDate','down'])"><u><i
                                class="fa fa-arrow-down"></i></u></a>
                    </th>
                    <th class="end-date-column" scope="col">End date
                        <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['endDate','up'])"><i
                                class="fa fa-arrow-up"></i></a>
                        <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['endDate','down'])"><i
                                class="fa fa-arrow-down"></i></a></th>
                    <c:choose>
                        <c:when test="${filter == 'history'}">
                            <th class="amount-column" scope="col">Amount</th>
                            <th class="copies-left-column" scope="col">Copies left</th>
                            <th class="status-column" scope="col">Status
                                <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['status','up'])"><i
                                        class="fa fa-arrow-up"></i></a>
                                <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['status','down'])"><i
                                        class="fa fa-arrow-down"></i></a></th>
                            </th>
                        </c:when>
                        <c:otherwise>
                            <th class="days-left-column" scope="col">Days left
                                <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['daysLeft','up'])"><u><i
                                        class="fa fa-arrow-up"></i></u></a>
                                <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['daysLeft','down'])"><u><i
                                        class="fa fa-arrow-down"></i></u></a></th>
                            <th class="copies-left-column" scope="col">Copies left</th>
                            <th class="status-column" scope="col">Return</th>
                        </c:otherwise>
                    </c:choose>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${customerDetailsList.model}" var="current">
                    <tr>
                        <td class="name-column-value">${current.name}</td>
                        <td class="start-date-column-value"><c:out value="${current.startDate}"/></td>
                        <td class="end-date-column-value">
                            <c:choose>
                                <c:when test="${current.futureDate == null}">
                                    <c:out value="No date"/>
                                </c:when>
                                <c:otherwise>
                                    <c:out value="${current.futureDate}"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <c:choose>
                            <c:when test="${filter == 'history'}">
                                <td class="amount-column-value"><c:out value="${current.filmForRent.copies - current.copiesLeft}/${current.filmForRent.copies}"/></td>
                                <td class="copies-left-column-value">
                                    <c:choose>
                                        <c:when test="${current.copiesLeft!=null}">
                                            <c:out value="${current.copiesLeft}"/>
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td class="status-column-value">
                                    <c:choose>
                                        <c:when test="${current.acceptedDate == null}">
                                            <c:out value="Rented"></c:out>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value="Returned"></c:out>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td class="days-left-column-value"><c:out value="${current.daysLeft}"/></td>
                                <form method="post" action="controller" style="display: inline;">
                                    <input type="hidden" name="userId" value="${customerDetailsList.customer.customerID}"/>
                                    <td class="copies-lef-column">
                                        <input type="number" required="" name="amount"
                                               class="copies-number form-control"
                                               style="margin-bottom:2%; margin-left:3%;" min="1"
                                               max="${current.copiesLeft}"
                                               value="${current.copiesLeft}">/${current.copiesLeft}
                                    </td>
                                    <td class="return-column-value">
                                        <input type="hidden" name="command" value="returnFilm"/>
                                        <input type="hidden" name="returnTo"
                                               value="controller?command=customerDetails&customerId=${customerDetailsList.customer.customerID}"/>
                                        <input type="hidden" name="filmId" value="${current.film.filmId}"/>
                                        <input type="hidden" name="days" value="${current.days}"/>
                                        <input type="hidden" name="rentId" value="${current.rent.rentID}"/>
                                        <input type="submit"
                                               style="color: #6495ED; background: none; border: none; cursor: pointer;"
                                               value="Return"/>
                                    </td>
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div id="pages">
            <c:set var="pager" value="${customerDetailsList.pager}" scope="request"/>
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
<script type="text/javascript" src="js/customer_details.js"></script>
<script type="text/javascript" src="js/attribute_funcs.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="js/bootstrap-typeahead.js"></script>
</html>

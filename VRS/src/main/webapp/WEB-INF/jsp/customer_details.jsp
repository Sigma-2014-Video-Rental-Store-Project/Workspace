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
    <fmt:setLocale value="en_US" />
</head>
<body>
<f:view>
    <div id="header">
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
    </div>
    <div id="customer-info">
        <div id="customer-name-block">
            <p id="customer-name">${films.customer.lastName}
                <a href="controller?command=editCustomer&customerId=${films.customer.customerId}&get=true">Edit</a></p>
        </div>
        <img id="avatar" data-src="holder.js/140x140" class="center"
             src="customerPhoto/${films.customer.customerPhoto}">
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
                    <th class="name-column" scope="col">Name
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
                        <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['endDate','up'])"><u><i
                                class="fa fa-arrow-up"></i></u></a>
                        <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['endDate','down'])"><u><i
                                class="fa fa-arrow-down"></i></u></a></th>
                    <th class="days-left-column" scope="col">Days left
                        <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['daysLeft','up'])"><u><i
                                class="fa fa-arrow-up"></i></u></a>
                        <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['daysLeft','down'])"><u><i
                                class="fa fa-arrow-down"></i></u></a></th>
                    <th class="return-column" scope="col">Return</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${films.model}" var="current">
                    <tr>
                        <td>>${current.name}</td>
                        <td><c:out value="${current.startDate}"/></td>
                        <td><c:out value="${current.endDate}"/></td>
                        <td><c:out value="${current.daysLeft}"/></td>
                        <td style="text-align:center;"><a href="">return</a>&nbsp;</td>
                        <%--TODO deside func return.--%>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div id="pages">
            <c:set var="pager" value="${films.pager}" scope="request"/>
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

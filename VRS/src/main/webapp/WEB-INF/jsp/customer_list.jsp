<%--
  Created by IntelliJ IDEA.
  User: Sergey
  Date: 20.10.14
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:directive.page language="java"
                    contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"/>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:c="http://java.sun.com/jsp/jstl/core">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/customer_list.css" rel="stylesheet">
<link rel="stylesheet" href="css/font-awesome.min.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Film list page.">
    <meta name="author" content="Sergey Laposhko">
    <link rel="icon" href="">
    <title>Customers</title>
</head>
<body>
<f:view>
    <div id="header">
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
    </div>
    <div id="content-body">
        <div id="buttons">
            <div id="filter-buttons" class="btn-group" data-toggle="buttons">
                <label class="btn btn-primary active" id="all-filter-label">
                    <input type="radio" id="all-filter" checked> All
                </label>
                <label class="btn btn-primary" id="with-films-filter-label">
                    <input type="radio" id="available-filter"> With films
                </label>

            </div>
            <div id="add-new-film-button">
                <form class="add-new-film" role="form" action="controller?command" method="get">
                    <input type="hidden" name="command" value="newCustomer"/>
                    <button class="btn btn-success" type="submit">Add customer</button>
                </form>
            </div>
            <div id="search-buttons">
                <form class="search" role="form" action="controller" method="get">
                    <input type="hidden" name="command" value="customerList"/>
                    <input id="product_search" style = "float: left; width: 88%;" name="key" type="text"class="form-control"
                           placeholder="Keywords" required="" autofocus="" autocomplete="off" data-provide="typeahead">
                    <button id="search-button" class="btn btn-primary" type="submit">Search</button>
                </form>
            </div>
        </div>

        <div id="customers">
            <table id="customers-table" class="table table-striped">
                <thead>
                <tr>
                    <th class="name-column" scope="col">Name
                        <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['name','up'])"><u><i
                                class="fa fa-arrow-up"></i></u></a>
                        <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['name','down'])"><u><i
                                class="fa fa-arrow-down"></i></u></a></th>

                    <th class="copies-rented-column" scope="col">Copies rented
                        <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['rentedCopies','up'])"><u><i
                                class="fa fa-arrow-up"></i></u></a>
                        <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['rentedCopies','down'])"><u><i
                                class="fa fa-arrow-down"></i></u></a></th>

                    <th class="return-date-column" scope="col">Return date
                        <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['returnDate','up'])"><u><i
                                class="fa fa-arrow-up"></i></u></a>
                        <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['returnDate','down'])"><u><i
                                class="fa fa-arrow-down"></i></u></a></th>

                    <th class="bonus-column" scope="col">Bonus
                        <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['bonus','up'])"><u><i
                                class="fa fa-arrow-up"></i></u></a>
                        <a class="sort-icon" onclick="setSeveralAttr(['sorting','direct'],['bonus','down'])"><u><i
                                class="fa fa-arrow-down"></i></u></a></th>

                    <th class="edit-column" scope="col">Edit</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${customers.model}" var="current">
                    <c:choose>
                        <c:when test="${current.leftDays < 4}">
                            <tr bgcolor="#e0ffff">
                        </c:when>
                        <c:when test="${current.leftDays < 3}">
                            <tr bgcolor="#ffb6c1">
                        </c:when>
                        <c:when test="${current.leftDays < 2}">
                            <tr bgcolor="#f08080">
                        </c:when>
                        <c:when test="${current.leftDays < 0}">
                            <tr bgcolor="#808080">
                        </c:when>
                    </c:choose>
                        <td><a href="controller?command=customerDetails&customerId=${current.customerID}">${current.lastName}</a></td>
                        <td><c:out value="${current.copiesRented}"/></td>
                        <td>
                            <c:choose>
                                <c:when test="${current.returnDate == null}">
                                    <c:out value="No rents"/>
                                </c:when>
                                <c:otherwise>
                                    <c:out value="${current.returnDate}"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td><c:out value="${current.bonus}"/></td>
                        <td><a href="controller?command=editCustomer&customerId=${current.customerID}&get=true">edit</a>&nbsp;</td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div id="pages">
            <c:set var="pager" value="${customers.pager}" scope="request"/>
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
<script type="text/javascript" src="js/customer_list.js"></script>
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
                return [<c:forEach items="${customers.model}" var="current">"<c:out value="${current.firstName} ${current.lastName}"/>",</c:forEach>];
            }
        });
    })
</script>
</html>
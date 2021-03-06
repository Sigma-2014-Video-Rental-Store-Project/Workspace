<%--
  Created by IntelliJ IDEA.
  User: Sergey
  Date: 20.10.14
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:directive.page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" />
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:c="http://java.sun.com/jsp/jstl/core">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/customer_list.css" rel="stylesheet">
<link rel="stylesheet" href="css/font-awesome.min.css"><%@ taglib
	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Film list page.">
<meta name="author" content="Sergey Laposhko">
<link rel="icon" href="">
<title><fmt:message key="customerlist.title" /></title>

</head>
<body>
	<f:view>
		<div id="header">
			<%@ include file="/WEB-INF/jspf/header.jspf"%>
		</div>
		<div id="content-body">
			<div id="buttons">
				<div id="filter-buttons" class="btn-group" data-toggle="buttons">
					<label class="btn btn-primary active" id="all-filter-label">
						<input type="radio" id="all-filter" checked>
					<fmt:message key="customerlist.all" />
					</label> <label class="btn btn-primary" id="with-films-filter-label">
						<input type="radio" id="available-filter">
					<fmt:message key="customerlist.withfilms" />
					</label>

				</div>
				<div id="add-new-film-button">
					<form class="add-new-film" role="form" action="controller?command"
						method="get">
						<input type="hidden" name="command" value="addNewCustomer" />
						<button class="btn btn-success" type="submit">
							<fmt:message key="customerlist.addcustomer" />
						</button>
					</form>
				</div>
				<div id="search-buttons">
					<form class="search" role="form" action="controller" method="get">
						<input type="hidden" name="command" value="customerList" /> <input
							id="product_search" style="float: left; width: 88%;" name="key"
							type="text" class="form-control"
							placeholder='<fmt:message key="filmlist.searchPlaceholder"/>'
							required="" autofocus="" autocomplete="off"
							data-provide="typeahead">
						<button id="search-button" class="btn btn-primary" type="submit">
							<fmt:message key="filmlist.searchbtn" />
						</button>
					</form>
				</div>
			</div>

			<div id="customers">
				<table id="customers-table" class="table">
					<thead>
						<tr>
							<th class="name-column" scope="col"><fmt:message
									key="customerlist.name" /><a class="sort-icon"
								onclick="setSeveralAttr(['sorting','direct'],['name','up'])"><u><i
										class="fa fa-arrow-up"></i></u></a> <a class="sort-icon"
								onclick="setSeveralAttr(['sorting','direct'],['name','down'])"><u><i
										class="fa fa-arrow-down"></i></u></a></th>

							<th class="copies-rented-column" scope="col"><fmt:message
									key="customerlist.copies" /><a class="sort-icon"
								onclick="setSeveralAttr(['sorting','direct'],['rentedCopies','up'])"><u><i
										class="fa fa-arrow-up"></i></u></a> <a class="sort-icon"
								onclick="setSeveralAttr(['sorting','direct'],['rentedCopies','down'])"><u><i
										class="fa fa-arrow-down"></i></u></a></th>

							<th class="return-date-column" scope="col"><fmt:message
									key="customerlist.return" /><a class="sort-icon"
								onclick="setSeveralAttr(['sorting','direct'],['returnDate','up'])"><u><i
									class="fa fa-arrow-up"></i></u></a> <a class="sort-icon"
								onclick="setSeveralAttr(['sorting','direct'],['returnDate','down'])"><u><i
										class="fa fa-arrow-down"></i></u></a></th>

							<th class="bonus-column" scope="col"><fmt:message
									key="customerlist.bonus" /><a class="sort-icon"
								onclick="setSeveralAttr(['sorting','direct'],['bonus','up'])"><u><i
										class="fa fa-arrow-up"></i></u></a> <a class="sort-icon"
								onclick="setSeveralAttr(['sorting','direct'],['bonus','down'])"><u><i
										class="fa fa-arrow-down"></i></u></a></th>

							<th class="edit-column" scope="col"><fmt:message
									key="customerlist.edit" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${customers.model}" var="current">
							<c:choose>
								<c:when test="${current.leftDays >= 4}">
									<tr bgcolor="#ffffffff">
								</c:when>
								<c:when test="${current.leftDays >= 3}">
									<tr bgcolor="#c0ffc0">
								</c:when>
								<c:when test="${current.leftDays >= 1}">
									<tr bgcolor="#ffffcc">
								</c:when>
								<c:when test="${current.leftDays == 0}">
									<tr bgcolor="#ffffa0">
								</c:when>
								<c:when test="${current.leftDays < 0}">
									<tr bgcolor="#ffb7b7">
								</c:when>
							</c:choose>
							<td><a
								href="controller?command=customerDetails&customerId=${current.customerID}">${current.firstName}
									${current.lastName}</a></td>
							<td><c:out value="${current.copiesRented}" /></td>
							<td><c:choose>
									<c:when test="${current.returnDate == null}">
										<c:out value="No rents" />
									</c:when>
									<c:otherwise>
										<fmt:formatDate value="${current.returnDate}" pattern="dd.MM.yyyy"/>
									</c:otherwise>
								</c:choose></td>
							<td style="text-align: right; padding-right: 5%;">\$<fmt:formatNumber
									type="number" minFractionDigits="2"
									value="${current.bonus/100}" /></td>
							<td class="edit-column value"><a
								href="controller?command=editCustomer&customerId=${current.customerID}&get=true"><fmt:message
										key="filmlist.editLink" /></a>&nbsp;</td>

							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div id="pages">
				<c:set var="pager" value="${customers.pager}" scope="request" />
				<jsp:include page="pager.jsp" />
			</div>
		</div>
		<div id="footer">
			<jsp:include page="../jspf/footer.jspf" />
		</div>
	</f:view>
</body>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/customer_list.js"></script>
<script type="text/javascript" src="js/attribute_funcs.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="js/bootstrap-typeahead.js"></script>

<script>
    $(document).ready(function ($) {
// Workaround for bug in mouse item selection
        $.fn.typeahead.Constructor.prototype.blur = function () {
            var that = this;
            setTimeout(function () {
                that.hide()
            }, 250);
        };

        $('#product_search').typeahead({
            source: function (query, process) {
                return [<c:forEach items="${customers.model}" var="current">"<c:out value="${current.firstName} ${current.lastName}"/>", </c:forEach>];
            }
        });
    })
</script>
</html>
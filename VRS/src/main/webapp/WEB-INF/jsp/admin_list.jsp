<?xml version="1.0" encoding="UTF-8" ?>
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/film_list.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Admin list page.">
    <meta name="author" content="Denys Shevchenko">
    <title><fmt:message key="adminlist.title" /></title>
</head>
<body>
<div id="header">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
</div>

<div style="height: 30px; padding: 0 10%; margin-bottom: 20px;">
    <form class="search" role="form" action="controller" method="get">
        <input type="hidden" name="command" value="adminList"/>
        <input id="admin_search"
               style="float: left; width: 68%; padding-right: 10px;"
               name="adminEmail"
               type="text"
               class="form-control"
               placeholder='<fmt:message key="adminlist.placeholder" />'
               autocomplete="off"
               data-provide="typeahead">
        <button style="float: left; width: 12%; min-width: 100px; margin-left: 3%;"
                class="btn btn-primary"
                type="submit">
            <fmt:message key="filmlist.searchbtn" />
        </button>
        <a style="float: left; width: 12%; min-width: 120px; margin-left: 3%;"
                class="btn btn-success"
                href="controller?command=addNewAdmin">
            <fmt:message key="adminlist.addnewadmin" />
        </a>
    </form>
</div>

<c:if test="${not empty param.messageCAPC}">
    <div style="padding: 0 10%; color: #a9453f; text-align: center; margin-bottom: 10px;">
        ${param.messageCAPC}
    </div>
</c:if>

<div style="padding: 0 10%;">
    <table id="films-table" class="table table-striped" style="border: 1px solid #CCCCCC;">
        <thead>
        <tr>
            <th class="name-column" style="width:15%;" scope="col"><fmt:message key="adminlist.adminname" /></th>
            <th class="name-column" scope="col"><fmt:message key="adminlist.pass" /></th>
            <th class="price-column" scope="col" style="padding-right: 38px; width: 10%;"><fmt:message key="adminlist.delete" /></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="admin" items="${requestScope.admins}">
            <tr>
                <td style="padding-top: 15px;">${admin.email}</td>
                <td style="padding-top: 15px;">
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="changeAdminPassword"/>
                        <input type="hidden" name="adminId" value="${admin.id}"/>
                        <input type="password" name="password" class="form-control" placeholder='<fmt:message key="adminlist.newpass" />' style="width: 30%; float: left; margin-right: 10px;"/>
                        <input type="password" name="passwordRetype" class="form-control" placeholder='<fmt:message key="adminlist.retrynewpass" />' style="width: 30%; float: left;margin-right: 10px;"/>
                        <input type="submit" value='<fmt:message key="adminlist.applynewpass" />' class="btn btn-warning" style="float: left;"/>
                    </form>
                </td>
                <td style="padding-top: 15px;">
                    <form action="controller" method="post">
                        <c:choose>
                            <c:when test="${admin.roleId eq 2}">
                                <input type="hidden" name="command" value="deleteAdmin"/>
                                <input type="hidden" name="adminId" value="${admin.id}"/>
                                <input type="submit" value=<fmt:message key="adminlist.delete" /> class="btn btn-danger" style="float: right; margin-right: 15px; padding: 5px 25px;"/>
                            </c:when>
                            <c:otherwise>
                                <span style="float:right;margin-right: 15px;font-weight: bold;"><fmt:message key="adminlist.roodcant" /></span>
                            </c:otherwise>
                        </c:choose>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<div id="footer">
    <jsp:include page="../jspf/footer.jspf"/>
</div>
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
        $('#admin_search').typeahead({
            source: function (query, process) {
                return [<c:forEach items="${requestScope.admins}" var="admin">"<c:out value="${admin.email}"/>", </c:forEach>];
            }
        });
    })
</script>
<script>
    function willBeFurther(){
        alert('Will be implemented further.');
    }
</script>
</html>
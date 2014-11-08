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
    <title>Admin list</title>
</head>
<body>
<div id="header">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
</div>

<div style="padding: 0 10%;">
    <div style="font-weight: bold; font-size: 2em; margin-bottom: 20px;">Add new administrator</div>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="createNewAdmin"/>
        <div style="width: 40%; float: left; margin: 10px 0;">
            <label for="email" style="float: left;">Email:</label>
            <input id="email" type="email" name="email" class="form-control" placeholder="Enter email" style="float: left;"/>
        </div>
        <div style="width: 40%; float: left; margin: 10px 0; clear:both;">
            <label for="password" style="float: left;">Password:</label>
            <input id="password" type="password" name="password" class="form-control" placeholder="Enter password" style="float: left;"/>
        </div>
        <div style="width: 40%; float: left; margin: 10px 0; clear:both;">
            <label for="passwordRetype" style="float: left;">Retype password:</label>
            <input id="passwordRetype" type="password" name="passwordRetype" class="form-control" placeholder="Retype password" style="float: left;"/>
        </div>
        <div style="width: 40%; float: left; margin: 10px 0; clear:both;">
            <label for="locale" style="float: left;">Locale to set:</label>
            <select id="locale" name="email" class="form-control" style="float: left;">
                <option>en</option>
                <option>ru</option>
            </select>
        </div>
        <input type="submit" value="Create new administrator" class="btn btn-success" style="float: left; clear: both; margin-top: 15px;"/>
    </form>
    <c:if test="${not empty param.errMessage}">
        <div style="color: #a9453f; text-align: left; margin-top: 20px; float: left; clear:both;">
                ${param.errMessage}
        </div>
    </c:if>
</div>


<div id="footer">
    <jsp:include page="../jspf/footer.jspf"/>
</div>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 04.11.14
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Order details</title>
  <link href="css/bootstrap.css" rel="stylesheet">
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="css/print.css" rel="stylesheet">
</head>
<body>
<div class="content">
  <%--
  <div id="header">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
  </div>
  --%>
  <div id="center-content-area">
    <div class="row">
      <div class="span4">
        <span>Order number:</span>
      </div>
      <div class="span5">
        <span id="order">${sessionScope.rentView.rentId}</span>
      </div>
    </div>

    <div class="row">
      <div class="span4">
        <span>Customer:</span>
      </div>
      <div class="span5">
        <span id="customer-name">${sessionScope.rentView.customerName}</span>
      </div>
    </div>

    <div class="row">
      <div class="span4">
        <span>Date:</span>
      </div>
      <div class="span5">
        <span id="date">${sessionScope.rentView.rentedDate}</span>
      </div>
    </div>
    <div class="row">
      <table id="film" class="table">
        <thead>
        <tr>
          <th class="name-column span3" scope="col">  Film   </th>
          <th class="count span2" scope="col"> Copies rented  </th>
          <th class="return-date-column span2" scope="col"> Acception date </th>
          <th class="bonus-column span2" scope="col">Price </th>
          <th class="note span3" scope="col">Note</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${sessionScope.rentView.filmViewList}" var="current">

          <td> ${current.title}"</td>
          <td><${current.count}"</td>
          <td> ${current.acceptedDate}</td>
          <td style="text-align:right; padding-right:5%;">
            <fmt:formatNumber type="CURRENCY" value="${current.price/100}"/>
          </td>
          <td></td>
        </c:forEach>
        </tbody>
      </table>
    </div>
    <div>
      <div class="row">
        <div class="span4">TOTAL:</div>
        <div class="span5">
          <fmt:formatNumber type="CURRENCY" value="${sessionScope.rentView.total/100}"/>
        </div>
      </div>
      <div class="row">
        <div class="span4">BONUS:</div>
        <div class="span5">
          <fmt:formatNumber type="CURRENCY" value="${sessionScope.rentView.usedBonus/100}"/>
        </div>
      </div>
      <div class="row">
        <div class="span4">FOR PAID:</div>
        <div class="span5">
          <fmt:formatNumber type="CURRENCY" value="${sessionScope.rentView.forPaid/100}"/>
        </div>
      </div>
    </div>

  </div>
  <div class="buttons" >
    <div class="row">
      <div style="align-self: flex-start">
        <form>
          <a id="link" href="controller?command=fullFilmList">
            <button id="cancel" type="button" class="btn btn-default">Cancel</button></a>
        </form>
      </div>
    </div>

  </div>

</div>

</body>
</html>

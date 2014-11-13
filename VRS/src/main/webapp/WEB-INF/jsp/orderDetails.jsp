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
<div id="content" style="max-width: 50%; margin-left: 3%; margin-top:1%;">
  <div id="center-content-area">
    <div class="row">
      <div class="span4">
        <span>Order number: <span id="order">${sessionScope.rentView.rentId}</span></span>
      </div>
    </div>

    <div class="row">
      <div class="span4">
        <span>Customer: <span id="customer-name">${sessionScope.rentView.customerName}</span></span>
      </div>
    </div>

    <div class="row">
      <div class="span4">
        <span>Date: <span id="date">${sessionScope.rentView.rentedDate}</span></span>
      </div>
    </div>
    <div class="row">
      <table id="film" class="table">
        <thead>
        <tr>
          <th style="width:20%;">  Film   </th>
          <th style="width:20%;"> Copies rented  </th>
          <th style="width:20%;"> Acception date </th>
          <th style="text-align:right; width:7%;">Price </th>
          <th style="text-align:center;">Note</th>
        </tr>
        <tbody>
        <c:forEach items="${sessionScope.rentView.filmViewList}" var="current">

          <td>${current.title}</td>
          <td>${current.count}</td>
          <td><fmt:formatDate type="date" pattern="dd-MM-yyyy" value="${current.acceptedDate}"></fmt:formatDate></td>
          <td style="text-align:right; margin-left:auto; margin-right:auto;">
             $<fmt:formatNumber type="number" minFractionDigits="2" value="${current.price/100}"/>
          </td>
          <td></td>
        </c:forEach>
        </tbody>
      </table>
    </div>
    <div>
      <div class="row">
        <div class="span4">TOTAL: $<fmt:formatNumber type="number" minFractionDigits="2" value="${sessionScope.rentView.total/100}"/></div>
      </div>
      <div class="row">
        <div class="span4">BONUS: $<fmt:formatNumber type="number" minFractionDigits="2" value="${sessionScope.rentView.usedBonus/100}"/></div>
      </div>
      <div class="row">
        <div class="span4">FOR PAID: $<fmt:formatNumber type="number" minFractionDigits="2" value="${sessionScope.rentView.forPaid/100}"/></div>
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

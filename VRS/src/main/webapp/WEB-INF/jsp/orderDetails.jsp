<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 04.11.14
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order details</title>
  <link href="css/bootstrap.css" rel="stylesheet">
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="css/print.css" rel="stylesheet">
</head>
<body>
  <div id="header">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
  </div>
  <div id="center-content-area" style="width:  595pt">
    <div class="row">
      <div class="span4">
        <span>Order number:</span>
      </div>
      <div class="span5">
        <span id="order">${rentView.rentId}</span>
      </div>
    </div>

      <div class="row">
        <div class="span4">
          <span>Customer:</span>
        </div>
        <div class="span5">
          <span id="customer-name">${rentView.customerName}</span>
        </div>
      </div>

        <div class="row">
          <div class="span4">
            <span>Date:</span>
          </div>
          <div class="span5">
            <span id="date">${rentView.rentedDate}</span>
          </div>
        </div>
        <div class="row">
            <table id="film" class="table">
              <thead>
              <tr>
                <th class="name-column" scope="col">  Film   </th>
                <th class="count" scope="col"> Copies rented  </th>
                <th class="return-date-column" scope="col"> Acception date </th>
                <th class="bonus-column" scope="col">Price </th>
                <th class="note" scope="col">Note</th>
              </tr>
              </thead>
              <tbody>
              <c:forEach items="${rentView.filmViewList}" var="current">

                <td> <c:out value="${current.title}"/></td>
                <td><c:out value="${current.count}"/></td>
                <td> <c:out value="${current.acceptedDate}"/></td>
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
                <fmt:formatNumber type="CURRENCY" value="${rentView.total/100}"/>
              </div>
            </div>
            <div class="row">
              <div class="span4">BONUS:</div>
              <div class="span5">
                <fmt:formatNumber type="CURRENCY" value="${rentView.usedBonus/100}"/>
              </div>
            </div>
            <div class="row">
              <div class="span4">FOR PAID:</div>
              <div class="span5">
                <fmt:formatNumber type="CURRENCY" value="${rentView.forPaid/100}"/>
              </div>
            </div>
        </div>

    </div>
  <div class="row"> <div style="align-self: flex-start">
    <form>
      <a href="controller?command=fullFilmList"><button type="button" class="btn btn-default">Cancel</button></a>
    </form>
  </div></div>
</body>
</html>

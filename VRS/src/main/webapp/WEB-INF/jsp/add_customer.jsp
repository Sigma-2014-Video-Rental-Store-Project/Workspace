<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:directive.page language="java"
                    contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"/>
<html>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/customer_list.css" rel="stylesheet">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/add_customer.css">
<link rel="stylesheet" href="css/bootstrap-formhelpers.min.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="Create new customer">
  <meta name="author" content="Vlad Samotskiy">
  <link rel="icon" href="">
  <title>Create new customer</title>
</head>
<body>
	<div id="header">
		<%@ include file="/WEB-INF/jspf/header.jspf" %>
	  </div>
		<div id="content-body"> 
			<form action="controller" method="post" enctype="multipart/form-data">
                 <input type="hidden" name="command" value="addNewCustomerSave" />
			     <div id=leftside>
				    <div id="bordered" style="margin-bottom:2%;">
					    <img id="cover" data-src="holder.js/140x140" class="center" src="customerPhoto/0.jpg" >
				    </div>
				    <span class="btn btn-primary btn-file">
				        Browse...<input type="file" id="inputFile" name="inputFile" onChange="setUpCoverRepresentation(this);">
				    </span>
			    </div>
			    <div id=rightside>
					<div style="float:left; width:100%;">
						<div style="float:left;">
							<p id="text">First name: </p>
						</div>
						<div style="float:right; width:60%;">
							<input type="text" name="firstName" class="form-control" style="margin-bottom:2%; margin-left:1%;">
						</div>
					</div>
					<div style="float:left; width:100%;">
						<div style="float:left;">
							<p id="text">Last name: </p>
						</div>
						<div style="float:right; width:60%;">
							<input type="text" name="lastName" class="form-control" style="margin-bottom:2%; margin-left:1%;">
						</div>
					</div>
					<div style="float:left; width:100%;">
						<div style="float:left;">
							<p id="text">Middle name: </p>
						</div>
						<div style="float:right; width:60%;">
							<input type="text" name="middleName" class="form-control" style="margin-bottom:2%; margin-left:1%;">
						</div>
					</div>
					<div style="float:left; width:100%;">
						<div style="float:left;">
							<p id="text">Email: </p>
						</div>
						<div style="float:right; width:60%;">
							<input type="email" name="email" class="form-control" style="margin-bottom:2%; margin-left:1%;" placeholder="example@domen.com">
						</div>
					</div>
					<div style="float:left; clear:left; width:100%;">
						<div style="float:left;">
							<p id="text">Phone: </p>
						</div>
						<div style="float:right; width:60%;">
							<input name="phone" type="text" class="form-control bfh-phone" value="" data-format="+38 (ddd) ddd-dddd" style="margin-bottom:2%; margin-left:1%;">
						</div>
					</div>
					<div style="float:left; clear:left; width:100%;">
						<div style="float:left;">
							<p id="text">Bonus account (\$): </p>
						</div>
						<div style="float:right; width:60%;">
							<input name="bonus" type="text" class="form-control" style="margin-bottom:2%; margin-left:1%;">
						</div>
					</div>
					<div style="float:left; clear:left; width:100%;">
						<div style="float:left;">
							<p id="text">Sex: </p>
						</div>
						<div style="float:right; width:60%;">
							<select name="sex" class="form-control" style="margin-bottom:2%; margin-left:1%;">
								<c:forEach items="${sexes.model}" var="current">
									<option value="<c:out value="${current.sexName}"/>"> 
										<c:out value="${current.sexName}"/>
									</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div style="float:right;">
						<div style="float:right;">
							<button type="submit" class="btn btn-success" style="margin-left:3%;">Add new customer</button>
						</div>
						<div style="float:right;">
							<a href="controller?command=customerList"><button type="button" class="btn btn-default">Cancel</button></a>
						</div>
					</div>
					<c:if test="${not empty errorMessage}">
                        <div class="error" style="margin-top: 130px;"><font face="verdana" size="4" color="red">${errorMessage}</font></div>
                    </c:if>
			    </div>
			</form>
		</div>
  <div id="footer">
    <jsp:include page="../jspf/footer.jspf"/>
  </div>
</body>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/bootstrap-spinedit.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/attribute_funcs.js"></script>
<script type="text/javascript" src="js/photo.js"></script>
<script type="text/javascript" src="js/bootstrap-formhelpers-phone.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
</html>

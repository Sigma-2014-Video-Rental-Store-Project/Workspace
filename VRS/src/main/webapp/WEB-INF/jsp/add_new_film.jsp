<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:directive.page language="java"
                    contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"/>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:c="http://java.sun.com/jsp/jstl/core">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/edit_film.css" rel="stylesheet">
<link href="css/bootstrap-spinedit.css" rel="stylesheet" >
<link href="css/sumoselect.css" rel="stylesheet" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Film edit page.">
    <meta name="author" content="Vlad Samotskiy">
    <link rel="icon" href="">
    <title><fmt:message key="addfilm.title"/></title>
</head>
<body>
    <f:view>
        <div id="header">
            <%@ include file="/WEB-INF/jspf/header.jspf" %>
        </div>
		<div id="content-body">
            <form action="controller" method="post" enctype="multipart/form-data">
                 <input type="hidden" name="command" value="addNewFilmSave" />
			     <div id=leftside>
				    <p id="text"><fmt:message key="addfilm.name"/></p>
				    <input type="text" name="filmTitle" class="form-control" style="margin-bottom:2%;">
				    <div id="bordered" style="margin-bottom:2%;">
					    <img id="cover" data-src="holder.js/140x140" class="center" src="filmCovers/0.jpg" >
				    </div>
				    <span class="btn btn-primary btn-file">
				        <fmt:message key="addfilm.browse"/><input type="file" id="inputFile" name="inputFile" onChange="setUpCoverRepresentation(this);">
				    </span>
			    </div>
			    <div id=rightside>
				    <div>
					    <div id="genre" style="float:left;">
						    <p id="text"><fmt:message key="addfilm.genre"/></p>
							<select name="categoryName" multiple="multiple" onchange="console.log($(this).children(':selected').length)" class="testsel">
							
							</select>
					    </div>
				    </div>
				    <div>
					    <textarea name="description" class="form-control" rows="6" style="margin-bottom:2%;"></textarea>
				    </div>
				    <div>
					    <div style="float:left; width:45%;">
						    <div style="float:left;">
							    <p id="text"><fmt:message key="addfilm.copies"/> </p>
						    </div>
						    <div style="float:right; width:30%">
							    <input type="number" name="amount" class="form-control" style="margin-bottom:2%; margin-left:3%;" min="0" max="150" value="0">
						    </div>
						</div>
						<div style="float:right; width:45%; max-width:45%;">
								<div style="float:left;">
									<p id="text"><fmt:message key="addfilm.genPrice"/></p>
								</div>
								<div style="float:right; width:30%;">
									<input type="text" name="generalPrice" class="form-control" style="margin-bottom:2%; margin-left:3%;">
								</div>
						</div>
					</div>
					<div>
					    <div style="float:left; width:45%;">
						    <div style="float:left;">
							    <p id="text"><fmt:message key="addfilm.rentPrice"/> </p>
						    </div>
						    <div style="float:right; width:30%">
							    <input name="rentPrice" type="text" class="form-control" style="margin-bottom:2%; margin-left:3%;">
						    </div>
						</div>
						<div style="float:right; width:45%; max-width:45%;">
							
								<div style="float:left;">
									<p id="text" ><fmt:message key="addfilm.bonus"/></p>
								</div>
								<div style="float:right; width:30%;">
									<input name="bonus" type="text" class="form-control" style="margin-bottom:2%; margin-left:3%;"  value="">
								</div>
						</div>
					</div>
					<div>
					    <div style="float:left; width:45%;">
						    <div style="float:left;">
							    <p id="text"><fmt:message key="addfilm.year"/></p>
						    </div>
						    <div style="float:right; width:30%">
							    <input name="year" type="text" class="form-control" style="margin-bottom:2%; margin-left:3%;" value="">
						    </div>
						</div>
					</div>
					<div>
						<div style="float:right; clear:left; width:50%;">
							<div style="float:right;">
								<button type="submit" class="btn btn-success" style="margin-left:3%;"><fmt:message key="filmlist.addNewFilmbtn"/></button>
							</div>
							<div style="float:right;">
								<a href="controller?command=fullFilmList"><button type="button" class="btn btn-default"><fmt:message key="addfilm.cancel"/></button></a>
							</div>
						</div>
					</div>
					<c:if test="${not empty errorMessage}">
                        <div class="error" style="margin-top: 130px;"><font face="verdana" size="4" color="red">${errorMessage}</font><div>
                    </c:if>
			    </div>
			</form>
        </div>
        <div id="footer">
        	<jsp:include page="../jspf/footer.jspf"/>
            <%@include file="/WEB-INF/jspf/locale.jspf"%>
        </div>
    </f:view>
</body>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/bootstrap-spinedit.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/attribute_funcs.js"></script>
<script type="text/javascript" src="js/cover.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script src="js/jquery.sumoselect.min.js"></script>
<script type="text/javascript">
        $(document).ready(function () {
            window.asd = $('.SlectBox').SumoSelect({ csvDispCount: 3 });
            window.test = $('.testsel').SumoSelect({okCancelInMulti:true });
        });
  </script>
</html>
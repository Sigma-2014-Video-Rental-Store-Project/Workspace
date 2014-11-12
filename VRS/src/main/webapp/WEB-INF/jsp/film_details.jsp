<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:directive.page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" />
<%@ taglib uri="http://ua.nure.sigma.store/functions" prefix="uf"%>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:c="http://java.sun.com/jsp/jstl/core">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/edit_film.css" rel="stylesheet">
<link href="css/bootstrap-spinedit.css" rel="stylesheet"><%@ taglib
	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Film edit page.">
<meta name="author" content="Vlad Samotskiy">
<link rel="icon" href="">
<title><fmt:message key="filmdetails.title" /></title>
</head>
<body>
	<f:view>
		<div id="header">
			<%@ include file="/WEB-INF/jspf/header.jspf"%>
		</div>
		<form action="controller" method="post" name="filmDetails">
			<input type="hidden" name="command" value="cartAdd" /> <input
				type="hidden" name="returnTo"
				value="controller?command=filmDetails&filmId=${editFilmObject.filmId}" />
			<input type="hidden" name="filmId" value="${editFilmObject.filmId}" />

			<div id="content-body">
				<div id=leftside>
					<p id="text">
						${editFilmObject.title} (${editFilmObject.year}) <a
							href="controller?command=editFilm&filmId=${editFilmObject.filmId}&get=true"><fmt:message
								key="filmdetails.editlink" /></a>
					</p>

					<div id="bordered" style="margin-bottom: 2%;">
						<img id="cover" data-src="holder.js/140x140" class="center"
							src="filmCovers/${editFilmObject.cover}" />
					</div>
					<p id="text"><fmt:message key="filmdetails.availableinstock" />
						${editFilmObject.copiesLeft}/${editFilmObject.amount}</p>
				</div>
				<div id=rightside>
					<div>
						<div style="margin-bottom: 0.5%;">
							<p id="text"><fmt:message key="addfilm.genre"/> ${catString}</p>
						</div>
					</div>
					<div>
						<textarea name="description" class="form-control" rows="6"
							style="margin-bottom: 2%;" readonly>${editFilmObject.description}</textarea>
					</div>
					<div>
						<div style="float: left; width: 45%;">
							<div style="float: left;">
								<p id="text"><fmt:message key="addfilm.copies"/></p>
							</div>
							<div style="float: right; width: 30%">
								<input type="number" name="amount" class="form-control"
									style="margin-bottom: 2%; margin-left: 3%;" min="0"
									max=${editFilmObject.copiesLeft } value="0"/>
                        </div>
						</div>
						<div style="float: right; width: 45%; max-width: 45%;">
							<div style="float: left;">
								<p id="text"><fmt:message key="filmdetails.days"/></p>
							</div>
							<div style="float: right; width: 30%;">
								<input type="number" name="Days" class="form-control"
									style="margin-bottom: 2%; margin-left: 3%;" min="1" max="100"
									value="1"/>
							</div>
						</div>
					</div>
					<div style="margin-top: 10%;">
						<div style="float: right; clear: left; width: 50%;">
							<div style="float: right;">
								<c:choose>
									<c:when
										test="${uf:isAdded(editFilmObject.filmId,sessionScope.cart)}">
										<span style="font-weight: bold;">Already added to the
											cart.</span>
									</c:when>
									<c:otherwise>
										<button type="submit" class="btn btn-success"
											style="margin-left: 3%;"><fmt:message key="filmdetails.addtocartbtn"/></button>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</div>
				<div id="footer">
					<jsp:include page="../jspf/footer.jspf" />
				</div>
			</div>
		</form>
	</f:view>
</body>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/bootstrap-spinedit.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/attribute_funcs.js"></script>
<script type="text/javascript" src="js/cover.js"></script>
</html>
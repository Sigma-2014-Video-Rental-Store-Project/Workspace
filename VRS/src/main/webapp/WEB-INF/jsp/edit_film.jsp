<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:directive.page language="java"
                    contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"/>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:c="http://java.sun.com/jsp/jstl/core">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/edit_film.css" rel="stylesheet">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Film edit page.">
    <meta name="author" content="">
    <link rel="icon" href="">
    <title>Films edit</title>
</head>
<body>
    <f:view>
        <div id="header">
            <%@ include file="/WEB-INF/jspf/header.jspf" %>
        </div>

        <%-- This form contains all data fields, that are necessary for film edition. --%>
        <%-- Notice, that it does not contain "Remove" button. --%>
        <form action="controller" method="post" enctype="multipart/form-data">
            <div id="content-body">

                <%-- This block is responsible for uploading an image that will be set up as film coverage. --%>
                <div class="cover">
                    <div class="figure">
		                <div class="photo">
			                <img src="filmCovers/0.jpg" alt="No cover =(" width="200" height="200" />
			            </div>
		                    <p>Image to set up as film cover.</p>
	                </div>
                    <input type="file" name="fileName" value="Choose cover" />
                </div>

                <input type="submit" class="btn btn-success" value="Save"/>
                <button type="button" class="btn btn-danger" onclick="setSeveralAttr(['filmId', 'remove'],['22', 'true'])">Remove</button>
            </div>
        </form>

        <div id="footer">
            <jsp:include page="../jspf/footer.jspf"/>
        </div>
    </f:view>
</body>
<script type="text/javascript" src="js/attribute_funcs.js"></script>
</html>
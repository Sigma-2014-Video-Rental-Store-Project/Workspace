<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:directive.page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
	<head>
		<title>
			<fmt:message key="error_page.jsp.title"/>
		</title>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<link rel="stylesheet" media="screen" type="text/css" href="css/error.css"/>
	</head>

	<body id="background">
	
		<div id="error">
		
			<h1>
				<fmt:message key="error_page.jsp.subtitle"/>
			</h1>

				<p>
					<fmt:message key="error_page.jsp.p1"/>
				</p>
			<ul>
				<li>
					<fmt:message key="error_page.jsp.li1"/>
				</li>
				<li>
					<fmt:message key="error_page.jsp.li2"/>
				</li>
				<li>
					<fmt:message key="error_page.jsp.li3"/>
				</li>
			</ul>
			
		</div>
		
	</body>
</html>
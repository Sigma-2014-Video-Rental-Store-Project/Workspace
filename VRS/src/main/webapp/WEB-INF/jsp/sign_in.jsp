<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="signin page for Rental Store">
    <meta name="author" content="Vlad Samotskiy">
    <link rel="icon" href="">

    <title><fmt:message key="login.title"/></title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/signin.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
    <div class="container">
      <form class="form-signin" role="form" action="controller" method="post">
        <input type="hidden" name="command" value="signIn"/>
        <h2 class="form-signin-heading"><fmt:message key="login.CongratText"/></h2>
        <input name="email" type="email" class="form-control" placeholder='<fmt:message key="login.EmailPlaceholder"/>' required="" autofocus="">
        <input name="password" type="password" class="form-control" placeholder='<fmt:message key="login.PassPlaceholder"/>' required="">
        <label class="checkbox">
          <input name="remember-me" type="checkbox" value="remember-me"><fmt:message key="login.remember"/>
        </label>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="login.btn"/></button>
		<c:if test="${not empty param.errorCode}">
			<span class="error"><font face="verdana" size="4" color="red"><fmt:message key="login.error"/>'</font><span>
		</c:if>
      </form>
	</div>
    <!-- /container -->

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="./Signin Template for Bootstrap_files/ie10-viewport-bug-workaround.js"></script>

</body>
</html>

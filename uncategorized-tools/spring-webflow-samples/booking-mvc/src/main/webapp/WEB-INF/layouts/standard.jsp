<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "https://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Spring Travel: Spring MVC and Web Flow Reference Application</title>
	<link type="text/css" rel="stylesheet" href="<c:url value="/resources/dijit/themes/tundra/tundra.css" />" />
	<link rel="stylesheet" href="<c:url value="/resources/styles/blueprint/screen.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/resources/styles/blueprint/print.css" />" type="text/css" media="print" />
	<!--[if lt IE 8]>
	        <link rel="stylesheet" href="<c:url value="/resources/blueprint/ie.css" />" type="text/css" media="screen, projection" />
	<![endif]-->
	<link rel="stylesheet" href="<c:url value="/resources/styles/booking.css" />" type="text/css" media="screen" />
    <script type="text/javascript" src="<c:url value="/resources/dojo/dojo.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/spring/Spring.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/spring/Spring-Dojo.js" />"></script>
</head>
<body class="tundra">
<div id="page" class="container">
	<div id="header">
		<div id="topbar">
			<security:authorize access="hasRole('ROLE_USER')">
				<form action="<c:url value="/logout" />" method="post" class="inline">
					<div class="span-4 label">
						<c:if test="${pageContext.request.userPrincipal != null}">
							Welcome, ${pageContext.request.userPrincipal.name}
						</c:if>
					</div>
					<div class="last">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<button id="submit" type="submit">Logout</button>
					</div>
				</form>
			</security:authorize>
			<security:authorize access="hasRole('ROLE_ANONYMOUS')">
				<a href="<c:url value="/login" />">Login</a>
			</security:authorize>
		</div>
		<div id="logo">
			<p>
				<a href="<c:url value="/" />">
					<img src="<c:url value="/resources/images/header.jpg"/>" alt="Spring Travel" />
				</a>
			</p>
		</div>
	</div>
	<div id="content">
		<div id="local" class="span-6">
			<p>
				<a href="https://www.thespringexperience.com">
					<img src="<c:url value="/resources/images/diplomat.jpg"/>" alt="generic hotel" />
				</a>
			</p>
			<p>
				<a href="https://www.thespringexperience.com">
					<img src="<c:url value="/resources/images/springone2gx.jpeg"/>" alt="SpringOne 2GX" />
				</a>
			</p>
		</div>
		<div id="main" class="span-18 last">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
	<hr />
	<div id="footer">
		<a href="https://www.springframework.org">
			<img src="<c:url value="/resources/images/powered-by-spring.png"/>" alt="Powered by Spring" />
		</a>
	</div>
</div>
</body>
</html>
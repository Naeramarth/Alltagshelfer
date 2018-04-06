
<%@tag pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<%@attribute name="title"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="menu" fragment="true"%>
<%@attribute name="content" fragment="true"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>Alltagshelfer: ${title}</title>

<link rel="stylesheet"
	href="<c:url value="/fontello/css/fontello.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/main.css"/>" />
<link rel="stylesheet" href="<c:url value="/css/form.css"/>" />

<jsp:invoke fragment="head" />
</head>


<body>

	<script src="/assets/js/jquery.min.js"></script>
	<script src="/assets/js/notify.js"></script>
	<script>
		$(function() {
			if (window.navigator.userAgent.indexOf("MSIE ") > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./)) {
				$.notify("Internet Explorer wird nicht unterstützt! Wechseln Sie zu einem anderen Browser für ein besseres Erlebnis.", "warn");
			}
		})
	</script>
	<%-- Kopfbereich --%>
	<header>

		<%-- Titelzeile --%>
		<div id="titlebar">
			<div class="appname"></div>
			<div class="content">${title}</div>
		</div>

		<%-- Menü --%>
		<div id="menubar">
			<jsp:invoke fragment="menu" />

			<security:authorize access="hasRole('ADMIN')">
				<div class="menuitem">
					<a href="<c:url value="/admin/"/>">Admin Tools</a>
				</div>
			</security:authorize>

			<security:authorize access="hasRole('SUPERADMIN')">
				<div class="menuitem">
					<a href="<c:url value="/super/admin/"/>">Super Admin Tools</a>
				</div>
			</security:authorize>

			<security:authorize access="isAuthenticated()">
				<div class="menuitem">
					<a href="<c:url value="/logout/"/>" class="icon-logout">Logout
						<security:authentication property="principal.username" />
					</a>
				</div>
			</security:authorize>
		</div>
	</header>

	<%-- Hauptinhalt der Seite --%>
	<main> <jsp:invoke fragment="content" /> </main>
</body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>

	<jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/login.css"/>" />
    </jsp:attribute>

	<jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/"/>">Home</a>
        </div>
    </jsp:attribute>

	<jsp:attribute name="content">
        <div class="container">
			<c:url value="/login" var="loginProcessingUrl" />
            <form action="${loginProcessingUrl}" method="post"
				class="stacked">
                <div class="column">
                    <%-- Eingabefelder --%>
                    <h1>Login</h1>
                    <label for="username">
                        Benutzername:
                        <span class="required">*</span>
                    </label> <input type="text" id="username" name="username" required="required"/>

                    <label for="password">
                        Passwort:
                        <span class="required">*</span>
                    </label> <input type="password" id="password"
						name="password" required="required"/>
                    
                    <%-- Button zum Abschicken --%>
                    <a></a>
                    <button class="icon-login" type="submit">
                        Einloggen
                    </button>
                    
                    <%-- Button zum Regestrieren --%>
                    <a href="<c:url value="/signup/"/>">
                    <button class="icon" type="submit">
                        Registrieren
                    </button>
                    </a>
                </div>
            </form>
        </div>
    </jsp:attribute>
</template:base>
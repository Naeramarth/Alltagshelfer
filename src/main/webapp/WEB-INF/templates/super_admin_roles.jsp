<%-- 
    Copyright © 2018 Dennis Schulmeister-Zimolong

    E-Mail: dhbw@windows3.de
    Webseite: https://www.wpvs.de/

    Dieser Quellcode ist lizenziert unter einer
    Creative Commons Namensnennung 4.0 International Lizenz.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
	<jsp:attribute name="title">
        Rollenverwaltung
    </jsp:attribute>

	<jsp:attribute name="head">
        <link rel="stylesheet"
			href="<c:url value="/css/task_list.css"/>" />
    </jsp:attribute>

	<jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/adverts/"/>">Übersicht</a>
        </div>
        <div class="menuitem">
            <a href="<c:url value="/super/admin/categories/"/>">Kategorien Verwalten</a>
        </div>
        <div class="menuitem">
            <a href="<c:url value="/super/admin/remove/"/>">Benutzer Löschen</a>
        </div>
        <div class="menuitem">
            <a href="<c:url value="/super/admin/remove/all/"/>">Alle Benutzer Löschen</a>
        </div>
        <div class="menuitem">
            <a href="<c:url value="/super/admin/reset/"/>">Datenbank Reset</a>
        </div>
    </jsp:attribute>

	<jsp:attribute name="content">
        <%-- Suchfilter --%>
        <form method="POST" class="horizontal" id="search">
            <input type="text" name="username" required="required"
				placeholder="Benutzername" />
	
            <button class="icon-pencil" type="submit">
            	Benutzer Adminrechte nehmen
            </button>
        </form>
        
		<c:if test="${!empty message}">
			<p>
				Benutzer ${message} hat jetzt keine Admin Rechte mehr.
			</p>
		</c:if>

		<c:if test="${!empty errors}">
			<ul class="errors">
				<c:forEach items="${errors}" var="error">
					<li>${error}</li>
				</c:forEach>
			</ul>
		</c:if>
    </jsp:attribute>
</template:base>
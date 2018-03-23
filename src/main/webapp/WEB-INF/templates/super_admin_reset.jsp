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
        Datenbank Reset
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
            <a href="<c:url value="/super/admin/roles/"/>">Rollenverwaltung</a>
        </div>
        <div class="menuitem">
            <a href="<c:url value="/super/admin/remove/"/>">Benutzer Löschen</a>
        </div>
        <div class="menuitem">
            <a href="<c:url value="/super/admin/remove/all/"/>">Alle Benutzer Löschen</a>
        </div>
    </jsp:attribute>

	<jsp:attribute name="content">
        <%-- Suchfilter --%>
        <form method="POST" class="horizontal" id="search">
			<div class="checkbox">
    			<input id="check" name="checkbox" type="checkbox">
    			<label for="checkbox">
      				Diese Operation löscht alle Daten außer den Rollen und den Superadmin
    			</label>
  			</div>
  			<input type="submit" name="anmelden" class="button" id="btncheck" required="required"
				value="Send" />
        </form>
        
		<c:if test="${!empty message}">
			<p>
				${message}
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
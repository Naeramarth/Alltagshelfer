
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">
        Super Admin Tools
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/login.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/adverts/"/>">Übersicht</a>
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
        <div class="menuitem">
            <a href="<c:url value="/super/admin/reset/"/>">Datenbank Reset</a>
        </div>
    </jsp:attribute>
    
    <jsp:attribute name="content">
        <p>
            Wählen Sie eine Option von oben aus.
        </p>
    </jsp:attribute>
</template:base>
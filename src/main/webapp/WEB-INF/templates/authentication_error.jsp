

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">
        Fehlerhafte Eingabe
    </jsp:attribute>

    <jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/"/>">Nochmal versuchen</a>
        </div>
    </jsp:attribute>

    <jsp:attribute name="content">
        <p>
            Die Login Daten konnten nicht verifiziert werden.
        </p>
    </jsp:attribute>
</template:base>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">
        Fehler
    </jsp:attribute>

    <jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/"/>">Nochmal versuchen</a>
        </div>
    </jsp:attribute>

    <jsp:attribute name="content">
        <p>
            Das hat leider nicht geklappt.
        </p>
    </jsp:attribute>
</template:base>
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
        Übersicht
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/task_list.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/advert/new/"/>">Angebot anlegen</a>
        </div>

        <div class="menuitem">
            <a href="<c:url value="/user/"/>">Benutzer bearbeiten</a>
        </div>
    </jsp:attribute>

    <jsp:attribute name="content">
        <%-- Suchfilter --%>
        <form method="GET" class="horizontal" id="search">
            <input type="text" name="text" value="${param.text}" placeholder="Beschreibung"/>

            <select name="category">
                <option value="">Alle Kategorien</option>

                <c:forEach items="${categories}" var="category">
                    <option value="${category.id}" ${param.category == category.id ? 'selected' : ''}>
                        <c:out value="${category.name}" />
                    </option>
                </c:forEach>
            </select>

            <button class="icon-search" type="submit">
                Suchen
            </button>
        </form>

        <%-- Gefundene Aufgaben --%>
        <c:choose>
            <c:when test="${empty adverts}">
                <p>
                    Es wurden keine Anzeigen gefunden.
                </p>
            </c:when>
            <c:otherwise>
                
                <table>
                    <thead>
                        <tr>
                            <th>Bezeichnung</th>
                            <th>Kategorie</th>
                            <th>Benutzer</th>
                            <th>Preis</th>
                            <th>Preistyp</th>
                            <th>Datum</th>
                            <th>Erstelldatum</th>
                            <th>Online Bis</th>
                        </tr>
                    </thead>
                    <c:forEach items="${adverts}" var="advert">
                        <tr>
                            <td>
                                <a href="<c:url value="/advert/${advert.id}/"/>">
                                    <c:out value="${advert.titel}"/>
                                </a>
                            </td>
                            <td>
                                <c:out value="${advert.kategorie.name}"/>
                            </td>
                            <td>
                                <c:out value="${advert.benutzer.benutzername}"/>
                            </td>
                            <td>
                                <c:out value="${advert.preisvorstellung}"/>
                            </td>
                            <td>
                                <c:out value="${advert.artDesPreises}"/>
                            </td>
                            <td>
                                <c:out value="${advert.erstelldatum}"/>
                            </td>
                            <td>
                                <c:out value="${advert.onlineBis}"/>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </jsp:attribute>
</template:base>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="base_url" value="<%=request.getContextPath()%>" />

<template:base>
    <jsp:attribute name="title">
        Registrierung
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/login.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">
        <div class="menuitem">
            <c:choose>
                <c:when test="${edit}">
                    <a href="<c:url value="/app/tasks/"/>">Übersicht</a>
                </c:when>
                <c:otherwise>
                    <a href="<c:url value="/"/>">Einloggen</a>
                </c:otherwise>
            </c:choose>
        </div>
    </jsp:attribute>

    <jsp:attribute name="content">
        <div class="container">
            <form method="post" class="stacked">
                <div class="column">
                    <%-- CSRF-Token --%>
                    <input type="hidden" name="csrf_token" value="${csrf_token}">
                    <input type="hidden" name="edit" value="${edit}">

                    <%-- Eingabefelder --%>
                    <c:choose>
                        <c:when test="${edit}">
                            <h1>Passwort ändern</h1>
                            <label for="signup_username">
                                Benutzername:
                                <span class="required">*</span>
                            </label>
                            <div class="side-by-side">
                                <input type="text" name="signup_username" value="${signup_form.values["signup_username"][0]}" readonly = "readonly">
                            </div>
                        </c:when>
                        <c:otherwise>
                            <h1>Logindaten</h1>
                            <label for="signup_username">
                                Benutzername:
                                <span class="required">*</span>
                            </label>
                            <div class="side-by-side">
                                <input type="text" name="signup_username" value="${signup_form.values["signup_username"][0]}">
                            </div>
                        </c:otherwise>
                    </c:choose>

                    <c:choose>
                        <c:when test="${edit}">
                            <label for="signup_password1">
                                Passwort:
                            </label>
                            <div class="side-by-side">
                                <input type="password" name="signup_password1">
                            </div>

                            <label for="signup_password2">
                                Passwort (wdh.):
                            </label>
                            <div class="side-by-side">
                                <input type="password" name="signup_password2">
                            </div>
                        </c:when>
                        <c:otherwise>
                            <label for="signup_password1">
                                Passwort:
                                <span class="required">*</span>
                            </label>
                            <div class="side-by-side">
                                <input type="password" name="signup_password1">
                            </div>

                            <label for="signup_password2">
                                Passwort (wdh.):
                                <span class="required">*</span>
                            </label>
                            <div class="side-by-side">
                                <input type="password" name="signup_password2">
                            </div>
                        </c:otherwise>
                    </c:choose>

                    <h1>Anschrift</h1>

                    <label for="signup_name">
                        Vor- und Nachname: 
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="signup_name" value="${signup_form.values["signup_name"][0]}">
                    </div>

                    <label for="signup_strasse">
                        Straße und Hausnummer: 
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="signup_strasse" value="${signup_form.values["signup_strasse"][0]}">
                    </div>

                    <label for="signup_plzort">
                        Postleitzahl und Ort: 
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="signup_postleitzahl" value="${signup_form.values["signup_postleitzahl"][0]}">
                        <input type="text" name="signup_ort" value="${signup_form.values["signup_ort"][0]}">
                    </div>

                    <h1>Kontaktdaten</h1>

                    <label for="signup_eMail">
                        E-Mail: 
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="signup_eMail" value="${signup_form.values["signup_eMail"][0]}">
                    </div>

                    <label for="signup_telefonnummer">
                        Telefonnummer: 
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="signup_telefonnummer" value="${signup_form.values["signup_telefonnummer"][0]}">
                    </div>
                    <c:choose>
                        <c:when test="${edit}">
                            <label for="signup_oldpassword">
                                Änderungen mit Passwort bestätigen
                                <span class="required">*</span>
                            </label>
                            <div class="side-by-side">
                                <input type="password" name="signup_oldpassword">
                            </div>
                            <%-- Button zum Abschicken --%>
                            <div class="side-by-side">
                                <button class="icon-pencil" type="submit">
                                    Änderungen speichern
                                </button>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <%-- Button zum Abschicken --%>
                            <div class="side-by-side">
                                <button class="icon-pencil" type="submit">
                                    Registrieren
                                </button>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>

                <%-- Fehlermeldungen --%>
                <c:if test="${!empty signup_form.errors}">
                    <ul class="errors">
                        <c:forEach items="${signup_form.errors}" var="error">
                            <li>${error}</li>
                            </c:forEach>
                    </ul>
                </c:if>
            </form>
        </div>
    </jsp:attribute>
</template:base>
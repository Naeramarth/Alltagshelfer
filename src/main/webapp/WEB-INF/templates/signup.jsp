
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="base_url" value="<%=request.getContextPath()%>" />

<template:base>

	<jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/login.css"/>" />
    </jsp:attribute>
		<jsp:attribute name="additional">
		<c:if test="${!edit}">
			<div class="menuitem">
        		<a href="<c:url value="/login/"/>" class="icon">Einloggen </a>
			</div>
		</c:if>
    </jsp:attribute>

	<jsp:attribute name="menu">
            <c:choose>
                <c:when test="${edit}">
        			<div class="menuitem">
                    	<a href="<c:url value="/adverts/"/>">Übersicht</a>
        			</div>
                </c:when>
                <c:otherwise>
        			<div class="menuitem">
            			<a href="<c:url value="/"/>"> Home</a>
        			</div>
				</c:otherwise>
            </c:choose>
    </jsp:attribute>

	<jsp:attribute name="content">
        <div class="container">
            <form method="post" class="stacked" style="width: 30em">
                <div class="column">
                    <input type="hidden" name="edit" value="${edit}">

                    <%-- Eingabefelder --%>
                    <c:choose>
                        <c:when test="${edit}">
                            <h1>Passwort ändern</h1>
                            <label for="username">
                                Benutzername:
                                <span class="required">*</span>
                            </label>
                            <div class="side-by-side">
                                <input type="text" name="username"
									value="${username}" readonly="readonly">
                            </div>
                        </c:when>
                        <c:otherwise>
                            <h1>Logindaten</h1>
                            <label for="username">
                                Benutzername:
                                <span class="required">*</span>
                            </label>
                            <div class="side-by-side">
                                <input type="text" name="username"
									value="${username}" required="required">
                            </div>
                        </c:otherwise>
                    </c:choose>

                    <c:choose>
                        <c:when test="${edit}">
                            <label for="password1">
                                Passwort:
                            </label>
                            <div class="side-by-side">
                                <input type="password" name="password1">
                            </div>

                            <label for="password2">
                                Passwort (wdh.):
                            </label>
                            <div class="side-by-side">
                                <input type="password" name="password2">
                            </div>
                        </c:when>
                        <c:otherwise>
                            <label for="password1">
                                Passwort:
                                <span class="required">*</span>
                            </label>
                            <div class="side-by-side">
                                <input type="password" name="password1"
									required="required">
                            </div>

                            <label for="password2">
                                Passwort (wdh.):
                                <span class="required">*</span>
                            </label>
                            <div class="side-by-side">
                                <input type="password" name="password2"
									required="required">
                            </div>
                        </c:otherwise>
                    </c:choose>

                    <h1>Anschrift</h1>

                    <label for="name">
                        Vor- und Nachname: 
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="name" value="${name}"
							required="required">
                    </div>

                    <label for="anschrift">
                        Straße und Hausnummer: 
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="anschrift"
							value="${anschrift}" required="required">
                    </div>

                    <label for="plzort">
                        Postleitzahl und Ort: 
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="postleitzahl"
							value="${postleitzahl}" required="required">
                        <input type="text" name="ort" value="${ort}"
							required="required">
                    </div>

                    <h1>Kontaktdaten</h1>

                    <label for="eMail">
                        E-Mail: 
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="eMail" value="${eMail}"
							required="required">
                    </div>

                    <label for="telefonnummer">
                        Telefonnummer: 
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="telefonnummer"
							value="${telefonnummer}" required="required">
                    </div>
                    <c:choose>
                        <c:when test="${edit}">
                            <label for="oldpassword">
                                Änderungen mit Passwort bestätigen
                                <span class="required">*</span>
                            </label>
                            <div class="side-by-side">
                                <input type="password"
									name="oldPassword" required="required">
                            </div>
                            <%-- Button zum Abschicken --%>
                            <div class="side-by-side">
                                <button class="icon" type="submit">
                                    Änderungen speichern
                                </button>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <%-- Button zum Abschicken --%>
                            <div class="side-by-side">
                                <button class="icon" type="submit">
                                    Registrieren
                                </button>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
                <%-- Fehlermeldungen --%>
                <c:if test="${!empty errors}">
                    <ul class="errors">
                        <c:forEach items="${errors}" var="error">
                            <li>${error}</li>
                            </c:forEach>
                    </ul>
                </c:if>
            </form>
        </div>
    </jsp:attribute>
</template:base>
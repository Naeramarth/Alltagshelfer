
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
	<jsp:attribute name="title">
        <c:choose>
            <c:when test="${other_user}">
                ${advert.titel}
            </c:when>
            <c:when test="${edit}">
                Anzeige bearbeiten
            </c:when>
            <c:otherwise>
                Anzeige anlegen
            </c:otherwise>
        </c:choose>
    </jsp:attribute>

	<jsp:attribute name="head">
        <link rel="stylesheet"
			href="<c:url value="/css/task_edit.css"/>" />
    </jsp:attribute>

	<jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/adverts/"/>">Übersicht</a>
        </div>
    </jsp:attribute>

	<jsp:attribute name="content">
        <form method="post" class="stacked">
            <div class="column">
                <%-- CSRF-Token --%>
                <input type="hidden" name="csrf_token"
					value="${csrf_token}">
                <input type="hidden" name="edit" value="${edit}">
                <input type="hidden" name="other_user"
					value="${other_user}">

                <%-- Eingabefelder --%>
                <label for="advert_owner">Eigentümer:</label>
                <div class="side-by-side">
                    <input type="text" name="advert_owner"
						value="${advert.benutzer.benutzername}" readonly="readonly">
                </div>

                <c:choose>
                    <c:when test="${other_user}">
                        <label for="advert_category">Kategorie:</label>
                        <div class="side-by-side">
                            <select name="advert_category"
								disabled="disabled">
                                <c:forEach items="${categories}"
									var="category">
                                    <option value="${category.id}"
										${advert.kategorie.id == category.id ? 'selected' : ''}>
                                        <c:out value="${category.name}" />
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <label for="advert_short_text">
                            Bezeichnung:
                            <span class="required">*</span>
                        </label>
                        <div class="side-by-side">
                            <input type="text" name="advert_short_text"
								value="${advert.titel}" readonly="readonly">
                        </div>

                        <label for="advert_long_text">
                            Beschreibung:
                        </label>
                        <div class="side-by-side">
                            <textarea name="advert_long_text"
								readonly="readonly"><c:out
									value="${advert.beschreibung}" /></textarea>
                        </div>

                        <label for="advert_pay">
                            Preis:
                        </label>
                        <div class="side-by-side">
                            <select name="advert_pay_type"
								disabled="disabled">
                                <c:forEach items="${values}" var="value">
                                    <option value="${value}"
										${advert.artDesPreises == value ? 'selected' : ''}>
                                        <c:out value="${value.label}" />
                                    </option>
                                </c:forEach>
                            </select>
                            <input type="number" name="advert_pay" required="required"
								value="${(empty advert.preisvorstellung) ? '0' : advert.preisvorstellung}" readonly="readonly">
                        </div>
                        
                    	<label for="advert_until">
                    		Online Bis:
                    	</label>
                        <div class="side-by-side">
                        	<input id=date type="date" name="advert_until" value="${advert.onlineBis}" readonly="readonly">
                        </div>
                    </c:when>
                    <c:otherwise>
                        <label for="advert_category">Kategorie:</label>
                        <div class="side-by-side">
                            <select name="advert_category">
                                <c:forEach items="${categories}"
									var="category">
                                    <option value="${category.id}"
										${advert.kategorie.id == category.id ? 'selected' : ''}>
                                        <c:out value="${category.name}" />
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <label for="advert_short_text">
                            Bezeichnung:
                            <span class="required">*</span>
                        </label>
                        <div class="side-by-side">
                            <input type="text" name="advert_short_text" required="required"
								value="${advert.titel}">
                        </div>

                        <label for="advert_long_text">
                            Beschreibung:
                        </label>
                        <div class="side-by-side">
                            <textarea name="advert_long_text"><c:out
									value="${advert.beschreibung}" /></textarea>
                        </div>

                        <label for="advert_pay">
                            Preis:
                        </label>
                        <div class="side-by-side">
                            <select name="advert_pay_type">
                                <c:forEach items="${values}" var="value">
                                    <option value="${value}"
										${advert.artDesPreises == value ? 'selected' : ''}>
                                        <c:out value="${value.label}" />
                                    </option>
                                </c:forEach>
                            </select>
                            <input type="number" name="advert_pay" required="required"
								value="${(empty advert.preisvorstellung) ? '0' : advert.preisvorstellung}">
                        </div>
                        
                    	<label for="advert_until">
                    		Online Bis:
                    	</label>
                        <div class="side-by-side">
                        	<input id=date type="date" name="advert_until" value="${advert.onlineBis}" required="required">
                        </div>
                    </c:otherwise>
                </c:choose>
                <c:if test="${edit}">
                    <label>Angelegt am:</label>
                    <p>${advert.erstelldatum}</p>
                    <label>Anbieter:</label>
                    <span>${advert.benutzer.vorname} ${advert.benutzer.nachname}</span>
                    <span>${advert.benutzer.strasse} ${advert.benutzer.hausnummer}</span>
                    <span>${advert.benutzer.postleitzahl} ${advert.benutzer.ort}</span>
                    <span>${advert.benutzer.telefonnummer}</span>
                    <span>${advert.benutzer.email}</span>
                </c:if>

                <c:if test="${!other_user}">
                    <%-- Button zum Abschicken --%>
                    <div class="side-by-side">
                        <button class="icon-pencil" type="submit"
							name="action" value="save">
                            Sichern
                        </button>

                        <c:if test="${edit}">
                            <button class="icon-trash" type="submit"
								name="action" value="delete">
                                Löschen
                            </button>
                        </c:if>
                    
				
				
				
				
				</c:if>
                </div>
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
    </jsp:attribute>
</template:base>
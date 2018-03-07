
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">
        <c:choose>
            <c:when test="${edit}">
                Aufgabe bearbeiten
            </c:when>
            <c:otherwise>
                Aufgabe anlegen
            </c:otherwise>
        </c:choose>
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/task_edit.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/secured/anzeigen/"/>">Übersicht</a>
        </div>
    </jsp:attribute>

    <jsp:attribute name="content">
        <form method="post" class="stacked">
            <div class="column">
                <%-- CSRF-Token --%>
                <input type="hidden" name="csrf_token" value="${csrf_token}">
                <input type="hidden" name="edit" value="${edit}">
                <input type="hidden" name="other_user" value="${other_user}">

                <%-- Eingabefelder --%>
                <label for="task_owner">Eigentümer:</label>
                <div class="side-by-side">
                    <input type="text" name="task_owner" value="${task_form.values["task_owner"][0]}" readonly="readonly">
                </div>

                <c:choose>
                    <c:when test="${other_user}">
                        <label for="task_category">Kategorie:</label>
                        <div class="side-by-side">
                            <select name="task_category" disabled="disabled">
                                <option value="">Keine Kategorie</option>

                                <c:forEach items="${categories}" var="category">
                                    <option value="${category.id}" ${task_form.values["task_category"][0] == category.id ? 'selected' : ''}>
                                        <c:out value="${category.name}" />
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <label for="task_status">
                            Art der Anzeige:
                            <span class="required">*</span>
                        </label>
                        <div class="side-by-side margin">
                            <select name="task_status" disabled ="disabled">
                                <c:forEach items="${statuses}" var="status">
                                    <option value="${status}" ${task_form.values["task_status"][0] == status ? 'selected' : ''}>
                                        <c:out value="${status.label}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <label for="task_short_text">
                            Bezeichnung:
                            <span class="required">*</span>
                        </label>
                        <div class="side-by-side">
                            <input type="text" name="task_short_text" value="${task_form.values["task_short_text"][0]}" readonly = "readonly">
                        </div>

                        <label for="task_long_text">
                            Beschreibung:
                        </label>
                        <div class="side-by-side">
                            <textarea name="task_long_text" readonly = "readonly"><c:out value="${task_form.values['task_long_text'][0]}"/></textarea>
                        </div>

                        <label for="task_value">
                            Preis:
                        </label>
                        <div class="side-by-side">
                            <select name="task_value_type" disabled ="disabled">
                                <c:forEach items="${values}" var="value">
                                    <option value="${value}" ${task_form.values["task_value_type"][0] == value ? 'selected' : ''}>
                                        <c:out value="${value.label}" />
                                    </option>
                                </c:forEach>
                            </select>
                            <input type="text" name="task_value" value="${task_form.values['task_value'][0]}" readonly = "readonly">
                        </div>
                    </c:when>
                    <c:otherwise>
                        <label for="task_category">Kategorie:</label>
                        <div class="side-by-side">
                            <select name="task_category">
                                <option value="">Keine Kategorie</option>

                                <c:forEach items="${categories}" var="category">
                                    <option value="${category.id}" ${task_form.values["task_category"][0] == category.id ? 'selected' : ''}>
                                        <c:out value="${category.name}" />
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <label for="task_status">
                            Art der Anzeige:
                            <span class="required">*</span>
                        </label>
                        <div class="side-by-side margin">
                            <select name="task_status">
                                <c:forEach items="${statuses}" var="status">
                                    <option value="${status}" ${task_form.values["task_status"][0] == status ? 'selected' : ''}>
                                        <c:out value="${status.label}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <label for="task_short_text">
                            Bezeichnung:
                            <span class="required">*</span>
                        </label>
                        <div class="side-by-side">
                            <input type="text" name="task_short_text" value="${task_form.values["task_short_text"][0]}">
                        </div>

                        <label for="task_long_text">
                            Beschreibung:
                        </label>
                        <div class="side-by-side">
                            <textarea name="task_long_text"><c:out value="${task_form.values['task_long_text'][0]}"/></textarea>
                        </div>

                        <label for="task_value">
                            Preis:
                        </label>
                        <div class="side-by-side">
                            <select name="task_value_type">
                                <c:forEach items="${values}" var="value">
                                    <option value="${value}" ${task_form.values["task_value_type"][0] == value ? 'selected' : ''}>
                                        <c:out value="${value.label}" />
                                    </option>
                                </c:forEach>
                            </select>
                            <input type="text" name="task_value" value="${task_form.values['task_value'][0]}">
                        </div>
                    </c:otherwise>
                </c:choose>
                <c:if test="${edit}">
                    <label>Angelegt am:</label>
                    <p>${task_form.values["task_date"][0]} ${task_form.values["task_time"][0]}</p>
                    <label>Anbieter:</label>
                    <span>${user.vorname} ${user.nachname}</span>
                    <span>${user.strasse} ${user.hausnummer}</span>
                    <span>${user.postleitzahl} ${user.ort}</span>
                    <span>${user.telefonnummer}</span>
                    <span>${user.email}</span>
                </c:if>

                <c:if test="${!other_user}">
                    <%-- Button zum Abschicken --%>
                    <div class="side-by-side">
                        <button class="icon-pencil" type="submit" name="action" value="save">
                            Sichern
                        </button>

                        <c:if test="${edit}">
                            <button class="icon-trash" type="submit" name="action" value="delete">
                                Löschen
                            </button>
                        </c:if>
                    </c:if>
                </div>
            </div>

            <%-- Fehlermeldungen --%>
            <c:if test="${!empty task_form.errors}">
                <ul class="errors">
                    <c:forEach items="${task_form.errors}" var="error">
                        <li>${error}</li>
                        </c:forEach>
                </ul>
            </c:if>
        </form>
    </jsp:attribute>
</template:base>
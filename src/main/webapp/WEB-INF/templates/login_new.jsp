<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>

<!-- <link
	href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Lato:400,100,300,700,900'
	rel='stylesheet' type='text/css'>

<link rel="stylesheet" href="css/animate.css">

<!-- Custom Stylesheet -->

<link rel="stylesheet" href="css/style.css">

	<jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/signup/"/>">Registrieren</a>
        </div>
    </jsp:attribute>

	<jsp:attribute name="content">
		<div class="container">
			<c:url value="/login_new" var="loginProcessingUrl" />
			<form action="${loginProcessingUrl}" method="post"
				class="stacked">
			<div class="top">	
				<h1 id="title" class="hidden">
					<span id="logo">Login <span>Login</span></span>
				</h1>	
			</div>
	
			<div class="login-box animated fadeInUp">	
				<div class="box-header">
					<h2>Log In</h2>
				</div>
				<label for="username">Benutzername</label> 
				<br /> 
				<input type="text" id="username" name="username"> 
				<br /> 
				<label for="password">Passwort</label>
				<br /> 
				<input type="password" id="password" name="password"> 
				<br />
				<button class="icon-login" type="submit">Login</button>
				<br /> 
				<a href="#">
					<p class="small">Passwort vergessen?</p>
				</a>
			</div>
			</form>
		</div>
	  </jsp:attribute>


<!--  
	$(document).ready(function() {
		$('#logo').addClass('animated fadeInDown');
		$("input:text:visible:first").focus();
	});
	$('#username').focus(function() {
		$('label[for="username"]').addClass('selected');
	});
	$('#username').blur(function() {
		$('label[for="username"]').removeClass('selected');
	});
	$('#password').focus(function() {
		$('label[for="password"]').addClass('selected');
	});
	$('#password').blur(function() {
		$('label[for="password"]').removeClass('selected');
	});
	-->
	
</template:base>

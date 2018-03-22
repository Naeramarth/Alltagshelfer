
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Alltagshelfer</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="<c:url value="/assets/css/main.css"/>" />
	</head>
	<body>

		<!-- Header -->
			<header id="header">
				<div class="inner">
					<nav id="nav">
                    	<a href="<c:url value="/login"/>">Login</a>
						<a href="<c:url value="/signup"/>"Registrieren</a>
					</nav>
					<a href="#navPanel" class="navPanelToggle"><span class="fa fa-bars"></span></a>
				</div>
			</header>

		<!-- Banner -->
			<section id="banner">
				<h1>Alltagshelfer</h1>
				<p>Besser kann ich´s auch nicht'</p>
			</section>

		<!-- One -->
			<section id="one" class="wrapper">
				<div class="inner">
					<div class="flex flex-3">
						<article>
							<header>
								<h3>Wer sind wir?!<br /> <br /></h3>
							</header>
							<p>Eine Gruppe junger Menschen, die die Gemeinschaft und die Nachbarschaftshilfe stärken wollen</p>
						</article>
						<article>
							<header>
								<h3>Was machen wir?!<br /> <br /> </h3>
							</header>
							<p>Vermittlung kleinerer Tätigkeiten in der Nachbarschaft und unterstützen bei der Suche nach Hilfe im und ums Haus </p>
						</article>
						<article>
							<header>
								<h3>Warum sollstest du dich uns anschliessen?<br /> </h3>
							</header>
							<p>Du solltest dich anmelden falls du gerne anderen Menschen hilfst oder auch selbst die ein oder andere Hilfe benötigst</p>
					</div>
				</div>
			</section>

		<!-- Two -->
			<section id="two" class="wrapper style1 special">
				<div class="inner">
					<header>
						<h2>Beispiele</h2>
					</header>
					<div class="flex flex-4">
						<div class="box person">
							<div class="image round">
								<img src="images/Babysitten.jpg" alt="Person 1" />
							</div>
							<h3>Kinderbetreuung</h3>
						</div>
						<div class="box person">
							<div class="image round">
								<img src="images/rasen_maehen.jpg" alt="Person 2" />
							</div>
							<h3>Gartenarbeit</h3>
						</div>
						<div class="box person">
							<div class="image round">
								<img src="images/Handwerker.jpg" alt="Person 3" />
							</div>
							<h3>Handwerkliche Tätigkeiten</h3>
						</div>
						<div class="box person">
							<div class="image round">
								<img src="images/Einkaufen.jpg" alt="Person 4" />
							</div>
							<h3>Einkäufe</h3>
						</div>
					</div>
				</div>
			</section>


		<!-- Footer -->
			<footer id="footer">
				<div class="inner">
					<div class="flex">
						<div class="copyright">
							&copy; Untitled. Design: <a href="https://templated.co">TEMPLATED</a>. Images: <a href="https://unsplash.com">Unsplash</a>.
						</div>
						<ul class="icons">
							<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
							<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
							<li><a href="#" class="icon fa-linkedin"><span class="label">linkedIn</span></a></li>
							<li><a href="#" class="icon fa-pinterest-p"><span class="label">Pinterest</span></a></li>
							<li><a href="#" class="icon fa-vimeo"><span class="label">Vimeo</span></a></li>
						</ul>
					</div>
				</div>
			</footer>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/skel.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>

	</body>
</html>
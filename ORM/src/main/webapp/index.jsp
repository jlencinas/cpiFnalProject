<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="resources/css/index.css">
<link rel="stylesheet" href="resources/css/display.css">
<link rel="stylesheet" href="resources/css/product-page.css">
<script type="text/javascript" src="resources/js/controller.js"></script>
<script type="text/javascript" src="resources/js/main.js"></script>
<script type="text/javascript" src="resources/js/product-page.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
.mySlides {
	display: none;
}
</style>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script type="module"
	src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule
	src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
<title>Home page</title>

<script>
	var contextPath = '${pageContext.request.contextPath}' + '/';
</script>

</head>
<body>
	<header id="myHeader">
		<nav>
			<div class="desktop-nav-container">
			<img class = "homepage-logo" src="resources/images/Logo-01.png"
					alt="logo-image" onclick = "loginButton(contextPath)">
				<ul>
					<li><a href="/ORM/" id="a-home">HOME</a></li>
					<li><a href="#" onclick="redirectShop(contextPath)"
						id="a-shop">SHOP</a></li>
					<li><a href="#" onclick="redirectAbout(contextPath)"
						id="a-about">ABOUT</a></li>
					<li><a href="#" onclick="redireContact(contextPath)"
						id="a-contact">CONTACT US</a></li>
				</ul>
				<a href="#" class="basket-a" name="Checkout" onclick="openPopup()"><ion-icon
						name="basket"></ion-icon></a>
			</div>
		</nav>
	</header>

<div class="card-container1" id="card-container1"></div>
	<main id="myMain">
	
		<div class="container">
			<div class="left-container">
				<div class="logo-title">
					<img class="logo-text-image"
						src="resources/images/Logo-Text-02.png" alt="">
				</div>
				<div class="message">
					<h1>
						"<span class="gold">Good bread</span> is the most fundamentally <span
							class="reddish">satisfying of all foods</span>; <br>and <span
							class="gold">Food bread</span> with fresh butter, <span
							class="reddish">the greatest of feasts</span>."
					</h1>
				</div>
				<div class="buttons">
					<button class="orderButton" onclick="redirectShop(contextPath)">Order Now</button>
				</div>
			</div>
			<div class="right-container">
				<div class="screen" style="max-width: 500px">
					<img class="mySlides" src="resources/images/bread1.jpg"> <img
						class="mySlides" src="resources/images/bread2.jpg"> <img
						class="mySlides" src="resources/images/bread3.jpg"> <img
						class="mySlides" src="resources/images/bread4.jpg">
				</div>
			</div>
			<script>
			  var myIndex = 0;
			  carousel();
			  function carousel() {
			    var i;
			    var x = document.getElementsByClassName("mySlides");
			    for (i = 0; i < x.length; i++) {
			      x[i].style.display = "none";
			    }
			    myIndex++;
			    if (myIndex > x.length) {
			      myIndex = 1;
			    }
			    x[myIndex - 1].style.display = "block";
			    setTimeout(carousel, 2000); // Change image every 2 seconds
			  }
			</script>
		</div>
		<div class="bottom-container">
			<h4>Already have an Order? Click here to Track your Order.</h4>
			<button class="trackButton">Track Order</button>
		</div>

		<script>
			$(document).ready(function() {
				initLoginButton(contextPath);
			});
		</script>
	</main>
	<footer>
		<div class="copyright-container">
			<h2>© Copyright 2023 Q1 Developer Trainees</h2>
			<!-- <button id="testOutputButton">Test</button> -->
			
			<!-- <button onClick="window.location.href='/ORM/pages/login.jsp'">Login</button><br /> -->
		</div>
	</footer>

</body>
</html>


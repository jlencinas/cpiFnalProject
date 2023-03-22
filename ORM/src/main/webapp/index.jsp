<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">


<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
        rel="stylesheet">
      
	
	<link rel="stylesheet" href="<c:url value="/resources/css/index.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/css/display.css"/>" >
	<link rel="stylesheet" href="<c:url value="/resources/css/product-page.css"/>">
	<script type="text/javascript" src="<c:url value="/resources/js/controller.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/main.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/product-page.js"/>"></script>
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<style>
	.mySlides {
		display: none;
	}
	</style>
	<script>var contextPath = '${pageContext.request.contextPath}' + '/';</script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.6.4.min.js"/>"></script>
	<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
	<title>Home page</title>

</head>
<body>
	<header id="myHeader">
		<nav>
			<div class="desktop-nav-container">
			<img class = "homepage-logo" src="<c:url value="/resources/images/Logo-01.png"/>"
					alt="logo-image" onclick = "loginButton(contextPath)">
				<ul>
					<li><a href="/ORM/" id="a-home">HOME</a></li>
					<li><a href="#"id="a-shop">SHOP</a></li>
				</ul>
				<div class="basket-logo-container disabled-container" onclick="openPopup()" id = "basket-logo-container" >
                        <div class="basket-logo-quantity disabled-logo-quantity" id = "basket-logo-quantity">
                            <p id="basket-logo-quantity-text"></p>
                        </div>
                        <a href="#" id="basket-logo" class="basket-a disabled-basket" name="Checkout"><ion-icon
                                name="basket"></ion-icon></a>
                        <div class="basket-logo-totalprice disabled-logo-totalprice" id = "basket-logo-totalprice">
                            <p id="basket-logo-totalprice-text"></p>
                        </div>
                </div>
			</div>
		</nav>
	</header>

<div class="card-container1" id="card-container1"></div>
	<main id="myMain">
	
	<c:set var="seshinfo" value="${cart}" />
	<c:set var="seshinfoPrice" value="${price_cart}" />
	<c:set var="seshcart" value="${not empty seshinfo ? 'true' : 'false'}"/>
	<script>var boolCart = '${seshcart}'</script>
	<c:set var="quantitySesh" value="${0}"/>
	<c:set var="totalpriceSesh" value="${0}"/>
	<c:forEach items="${seshinfo}" var="sesh">
		<c:set var="quantitySesh" value="${sesh.value + quantitySesh}"/>
	</c:forEach>
	<c:forEach items="${seshinfoPrice}" var="sesh2">
		<c:set var="totalpriceSesh" value="${sesh2.value + totalpriceSesh}"/>
	</c:forEach>
	<script>var quantity = '${quantitySesh}'</script>
	<script>var totalPrice = '${totalpriceSesh}'</script>
		<div class="container">
			<div class="left-container">
				<div class="logo-title">
					<img class="logo-text-image"
						src="<c:url value="/resources/images/Logo-Text-02.png"/>" alt="">
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
					<img class="mySlides" src="<c:url value="/resources/images/bread1.jpg"/>">
					<img class="mySlides" src="<c:url value="/resources/images/bread2.jpg"/>"> 
					<img class="mySlides" src="<c:url value="/resources/images/bread3.jpg"/>">
					<img class="mySlides" src="<c:url value="/resources/images/bread4.jpg"/>">
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
			<button class="trackButton" onclick="openTrackOrderPopup()">Track Order</button>
		</div>

		<script>
		var popup = document.getElementById("card-container1");
		var header = document.getElementById("myHeader");
		var main = document.getElementById("myMain");
		function openPopup() {
			openOrderDesc(contextPath);
			popup.classList.add("open-popup");
			header.style.filter = "blur(8px)";
			main.style.filter = "blur(8px)";
			header.style.pointerEvents = "none";
			main.style.pointerEvents = "none";
		}

		function closePopup() {
			popup.classList.remove("open-popup");
			header.style.filter = "none";
			main.style.filter = "none";
			header.style.pointerEvents = "all";
			main.style.pointerEvents = "all";
			closeOrderDetails();
		}
			$(document).ready(function() {
				checkCartSession(boolCart);
				initLoginButton(contextPath);
				initTestButton(contextPath);
				initFunction()
			});
		</script>
	</main>
	<footer id = "myFooter">
		<div class="copyright-container">
			<h2>Copyright 2023 Q1 Developer Trainees</h2>
		</div>
	</footer>

</body>
</html>


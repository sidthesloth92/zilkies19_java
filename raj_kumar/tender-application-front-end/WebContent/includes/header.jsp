<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- nav-bar -->
<div class="Navbar">
	<div class="Navbar__Link Navbar__Link-brand">
		<a href="index.jsp" class="nav-bar-brand"> <i class="fas fa-home"></i>
		</a>
	</div>
	<div class="Navbar__Link Navbar__Link-toggle">
		<i class="fas fa-bars"></i>
	</div>
	<!-- <nav class="Navbar__Items">
            <div class="Navbar__Link">
                All Projects
            </div>
            <div class="Navbar__Link">
                View Contractors
            </div>

        </nav> -->
	<nav class="Navbar__Items Navbar__Items--right">
		<div class="dropdown-container">
			<li class="nav__item nav__right-item dropdown-outer"><a href="#"
				class="dropdown-container__register-link">Register <i
					class="fa fa-caret-down"></i></a></li>
			<!-- Drop Down -->
			<div class="dropdown-content">
				<a href="GeneralUser?get-type=register-user">Register User</a> <a
					href="GeneralUser?get-type=register-contractor">Register
					Contractor</a>
			</div>
		</div>
		<c:choose>
			<c:when test="${empty userSession}">
				<div class="Navbar__Link">
					<a href="./index.jsp" class="nav-link">Login</a>
				</div>
			</c:when>
			<c:otherwise>
				<div class="Navbar__Link">
					<a href="Logout"  class="nav-link">Logout</a>
				</div>
			</c:otherwise>
		</c:choose>
	</nav>
</div>


<!-- Navigation Bar Ending -->
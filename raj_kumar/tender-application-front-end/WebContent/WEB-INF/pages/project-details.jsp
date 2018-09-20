<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title></title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href=${pageContext.request.contextPath}/css/normalize.css>
<link rel="stylesheet"
	href=${pageContext.request.contextPath}/css/grid.css>
<link rel="stylesheet"
	href=${pageContext.request.contextPath}/css/utility.css>
<link rel="stylesheet"
	href=${pageContext.request.contextPath}/css/snack-bar.css>
<link rel="stylesheet"
	href=${pageContext.request.contextPath}/css/project-details.css>
<link href="https://fonts.googleapis.com/css?family=Roboto"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">
</head>

<body>
	<!--[if lt IE 7]>
            <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="#">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
	<!-- nav-bar -->
	<%@include file="../../includes/header.jsp"%>
	<c:set var="comments" value="${commentList}" scope="session" />
	<c:set var="list" value="${projectDetail}" scope="session" />
	<c:set var="responseList" value="${responseData}" scope="session" />
	<!-- Navigation Bar Ending -->
	<!-- Project Details Starting -->
	<div class="content-wrapper">
		<div class="row project-detail-container">
			<div class="col-lg-12 project-detail-container__jumbotron">
				<h1>${list.get(0).getProjectName()}</h1>
				<p class="project-detail-container__duration">${list.get(0).getStartDate()}
					- ${list.get(0).getEndDate()}</p>
				<p class="project-detail-container__contractor-name">Contractor
					Name :Mr. ${ contractorName }</p>
				<p class="project-detail-container__description">${list.get(0).getDescription()}</p>
				<p class="project-detail-container__description">$
					${list.get(0).getEstCost()}</p>
				<p class="project-detail-container__description">Status
					:${list.get(0).getProjectStatus()}</p>
				<p class="project-detail-container__location">Location
					:${list.get(0).getLocation()}</p>

			</div>
		</div>
		<!-- responses section -->
		<div class="response-container">
			<!-- card starting -->
			<c:choose>
				<c:when test="${ empty responseList }">
					<div class="card card-2">
						<p class="box__project-name box__item">No reason is provided,
							You can comment Why ?!</p>
					</div>
				</c:when>
				<c:otherwise>
					<c:forEach var="currentListItem" items="${responseList}">
						<div class="card card-2">
							<div class="project-detail__response-section">
								<div class=" row">
									<div class="col-lg-12">
										<div class="project-detail__response-section__detail">
											<p class="project-detail__response-section__detail__heading">Response
												is :</p>
											<p class="project-detail__response-section__detail__reason">${ currentListItem.getResponseText() }</p>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- card ending -->
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
		<!-- response ending section -->
		<!-- Comment Section Starting -->
		<!-- comment Form starting -->
		<div class="row">
			<div class="col-lg-2 col-md-1"></div>
			<div class="col-lg-8 col-sm-12 col-md-10">
				<div class="comment-input-container">
					<form
						action="SetComment?post-type=add-comment&project_id=${list.get(0).getProjectID()}"
						method="POST">
						<div class="comment-input__elements">
							<input type="text" placeholder="What are your Thoughts ?"
								class="form__input comment-input__elements__input"
								name="comment-data">
							<button type="submit"
								class="comment-input__elements__add-comment-btn form__button">Add
								Comment</button>
						</div>
					</form>
				</div>
			</div>
			<div class="col-lg-2 col-md-1"></div>
		</div>
		<!-- Comment Form Ending -->
		<!-- Display Comment Section -->
		<div class="row">
			<div class="col-lg-2"></div>
			<div class="col-lg-8 col-sm-12">
				<!-- Comment Starting -->
				<c:choose>
					<c:when test="${ empty commentList }">
						<div class="card card-2">
							<p class="box__project-name box__item">Guess No one wants to
								comment !</p>
						</div>
					</c:when>
					<c:otherwise>
						<c:forEach var="currentListItemComment" items="${comments}">
							<div class="comment-container">
								<div class="comment-container__user-detail">
									<p class="comment-container__user-detail__user-name">@${ currentListItemComment.getUserName() }</p>
									<p class="comment-container__user-detail__date"># ${ currentListItemComment.getCommentDate() }</p>
								</div>
								<div class="comment-container__message">
									<p class="comment-container__user-detail__message">${ currentListItemComment.getCommentMsg() }</p>
								</div>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="col-lg-2"></div>
		</div>
		<!-- Display Comment Section Ending -->
		<!-- Comment Section Ending -->
		<!-- Project Details Ending -->
		<!-- Footer Starting -->
		<div class="row footer-section">
			<div class="col-lg-4"></div>
			<div class="col-lg-4 footer__copyright">copyright &#169;
				zilker.io</div>
			<div class="col-lg-4">
				<i class="fab fa-facebook fa-2x footer-icon"></i> <i
					class="fab fa-instagram fa-2x footer-icon"></i> <i
					class="fab fa-twitter fa-2x footer-icon"></i>
			</div>
		</div>
	</div>
	<!-- Footer Ending -->
	<c:if test="${param.success eq 1}">
		<div id="snackbar">Comment Added</div>
		<script src=${pageContext.request.contextPath}/js/snack-bar.js async
			defer></script>
	</c:if>
	<c:if test="${param.success eq 0}">
		<div id="snackbar">Comment Not Added</div>
		<script src=${pageContext.request.contextPath}/js/snack-bar.js async
			defer></script>
	</c:if>
	<script src="../js/main.js" async defer></script>
</body>

</html>
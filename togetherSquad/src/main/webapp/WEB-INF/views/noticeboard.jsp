<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>    
<!DOCTYPE html PUBLIC >
<html>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Start Simple Web</title>
<link rel="stylesheet"
	href="https://unpkg.com/swiper@8/swiper-bundle.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
	integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/play/index.css">


</head>
<body>

<jsp:include page="testHeader.jsp"></jsp:include>


	<!-- Page Header -->
	<!-- Set your background image for this header on the line below. -->
	<header class="intro-header"
		style="background-color: #141414">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
					<div class="site-heading" align="center"
						style="margin-top: 100px; padding-top: 35px; padding-bottom: 35px">
						<h2>공지사항 게시판</h2>
						<span class="subheading"></span>
					</div>
				</div>
			</div>
		</div>
	</header>
	<div class="board-field">
		<div class="col-md-2"></div>
		<div class="col-md-8">
		<c:choose>
		<c:when test="${userAuthority == 'Admin_Permissions'}"> <%-- 권한이 있는 경우 --%>
			<div class="board-toolbar" 	style="text-align: right; margin-bottom: 0px">
				<a href="/web/play/noticeboardwrite.jsp?job=new&writer_id=${param.writer_id}"  class="btn btn-primary btn-sm"
				style="margin-right: 400px; font-size:20px;">
				<i class="glyphicon glyphicon-pencil" ></i>
				공지사항 글쓰기
				</a>
			</div>
		</c:when>
		</c:choose> 


			<div class="list-group" style="margin-left: 100px; margin-top: 100px">
				
				<c:forEach items="${notice}" var="i" varStatus="cnt">	
			
				<div class="title">
						<a href="/web/selectNoticeBoard?no=${i.noticeboard_no}" style="color:white;">
							${i.title}<span class="badge">New</span>
						</a>
					</div>
					
					<div class="board-meta" style="font-weight: 400; font-size: 1.2rem; color: #141414;">
						<p>
							<i class="glyphicon glyphicon-time"></i>${i.regdate}
						</p>
					</div>
				
				</c:forEach>
				
				
			</div>

	</div>

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="js/clean-blog.min.js"></script>

</body>

</html>

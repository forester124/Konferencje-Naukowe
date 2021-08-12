<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title>Konferencje</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>

<body>
	<c:set var="uzyt" value="${uzyt}" />
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a class="navbar-brand">Konferencje </a>
			</div>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/listKonfUser"
					class="nav-link">Konferencje</a></li>
				<li><a href="<%=request.getContextPath()%>/mojePrace"
					class="nav-link">Prace</a></li>
				<li><a href="<%=request.getContextPath()%>/kontakt"
					class="nav-link">Kontakt</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbardrop"
					data-toggle="dropdown"> <c:out value="${uzyt.username}" />
				</a>
					<div class="dropdown-menu">
						<a class="dropdown-item"
							href="<%=request.getContextPath()%>/editUser?id=<c:out value='${uzyt.id}' />"">edytuj</a>
						<a class="dropdown-item"
							href="<%=request.getContextPath()%>/wyloguj">wyloguj</a>
					</div></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<div class="container">
			<h3 class="text-center">Lista konferencji</h3>
			<hr>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Tytuł</th>
						<th>Data</th>
						<th>Edycja</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="konf" items="${listKonf}">

						<tr>
							<td><c:out value="${konf.id}" /></td>
							<td><c:out value="${konf.nazwa}" /></td>
							<td><c:out value="${konf.dataKonf}" /></td>
							<td><a href="seeUser?id=<c:out value='${konf.id}' />">Zobacz</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>

</html>
l>

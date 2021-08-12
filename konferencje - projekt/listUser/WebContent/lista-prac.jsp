<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title>Konferencje</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
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
				<li><a href="<%=request.getContextPath()%>/listKonfRec"
					class="nav-link">Konferencje</a></li>
				<li><a href="<%=request.getContextPath()%>/wyswietlPrace"
					class="nav-link">Prace</a></li>
				<li><a href="<%=request.getContextPath()%>/mojeOceny"
					class="nav-link">Moje oceny</a></li>
				<li><a href="<%=request.getContextPath()%>/kontakt"
					class="nav-link">Kontakt</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbardrop"
					data-toggle="dropdown"> <c:out value="${uzyt.username}" />
				</a>
					<div class="dropdown-menu">
						<a class="dropdown-item"
							href="<%=request.getContextPath()%>/editRec?id=<c:out value='${uzyt.id}' />"">edytuj</a>
						<a class="dropdown-item"
							href="<%=request.getContextPath()%>/wyloguj">wyloguj</a>
					</div></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">Lista Prac</h3>
			<hr>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Tytul</th>
						<th>ID_Twórcy</th>
						<th>ID_Konferencji</th>
						<th>Ocena1</th>
						<th>Ocena2</th>
						<th>Ocena3</th>
						<th>Status</th>
						<th>Opcje</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="praca" items="${listaPrac}">

						<tr>
							<td><c:out value="${praca.id}" /></td>
							<td><c:out value="${praca.tytul}" /></td>
							<td><c:out value="${praca.id_uzytkownika}" /></td>
							<td><c:out value="${praca.id_konferencji}" /></td>
							<td><c:out value="${praca.ocena1}" /></td>
							<td><c:out value="${praca.ocena2}" /></td>
							<td><c:out value="${praca.ocena3}" /></td>
							<td><c:out value="${praca.status}" /></td>

							<td><a href="pobierz?id=<c:out value='${praca.id}' />">Pobierz</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="formularzOceny?id=<c:out value='${praca.id}' />">Oceń</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="usunPrace?id=<c:out value='${praca.id}' />">Usuń</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>

</html>
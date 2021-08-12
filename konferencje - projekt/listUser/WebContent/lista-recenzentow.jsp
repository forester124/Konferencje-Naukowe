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
				<li><a href="<%=request.getContextPath()%>/listKonfOrg"
					class="nav-link">Konferencje</a></li>
				<li><a href="<%=request.getContextPath()%>/listaUzytkownik"
					class="nav-link">Użytkownicy</a></li>
				<li><a href="<%=request.getContextPath()%>/listaRecenzent"
					class="nav-link">Recenzenci</a></li>
				<li><a href="<%=request.getContextPath()%>/kontakt"
					class="nav-link">Kontakt</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbardrop"
					data-toggle="dropdown"> <c:out value="${uzyt.username}" />
				</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="<%=request.getContextPath()%>/editOrg?id=<c:out value='${uzyt.id}' />"">edytuj</a> <a
							class="dropdown-item" href="<%=request.getContextPath()%>/wyloguj">wyloguj</a>
					</div></li>

			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">Lista recenzentów</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/formularzDodaniaRecenzenta"
					class="btn btn-success">Dodaj Recenzenta</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Tytuł</th>
						<th>Imie</th>
						<th>Nazwisko</th>
						<th>Pesel</th>
						<th>Telefon</th>
						<th>Email</th>
						<th>Nazwa użytkownika</th>
						<th>Opcje</th>

					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="rec" items="${listaRecenzentow}">

						<tr>
							<td><c:out value="${rec.id}" /></td>
							<td><c:out value="${rec.tytul}" /></td>
							<td><c:out value="${rec.imie}" /></td>
							<td><c:out value="${rec.nazwisko}" /></td>
							<td><c:out value="${rec.pesel}" /></td>
							<td><c:out value="${rec.telefon}" /></td>
							<td><c:out value="${rec.email}" /></td>
							<td><c:out value="${rec.username}" /></td>

							<td><a
								href="formularzEdycjiRecenzenta?id=<c:out value='${rec.id}' />">Edytuj</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="usunRecenzenta?id=<c:out value='${rec.id}' />">Usuń</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>

</html>
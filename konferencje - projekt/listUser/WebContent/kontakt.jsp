<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title>Konferencje</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: papayawhip">
			<div>
				<a class="navbar-brand"> Kontakt </a>
			</div>
		</nav>
	</header>
	<br>

	<div class="row">
		<div class="container">
			<c:set var="dane" value="${dane}" />
			<h3 class="text-center">Dane do przelewu</h3>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Tytul przelewu</th>
						<th>Odbiorca</th>
						<th>Numer konta</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><c:out value="${dane.tytulPrzelew}" /></td>
						<td><c:out value="${dane.nazwaOdbiorcy}" /></td>
						<td><c:out value="${dane.nrKonta}" /></td>
					</tr>
				</tbody>
			</table>
			<br> <br>
			<h3 class="text-center">Osoby kontaktowe</h3>
			<hr>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Imie</th>
						<th>Nazwisko</th>
						<th>Telefon</th>
						<th>Email</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="org" items="${listOrg}">

						<tr>
							<td><c:out value="${org.id}" /></td>
							<td><c:out value="${org.imie}" /></td>
							<td><c:out value="${org.nazwisko}" /></td>
							<td><c:out value="${org.telefon}" /></td>
							<td><c:out value="${org.email}" /></td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>

</html>
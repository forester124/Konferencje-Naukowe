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
			style="background-color: tomato">
			<div>
				<a class="navbar-brand"> Ocena </a>
			</div>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">


				<form action="ocen" method="post">


					<h2>Oceń prace</h2>

					<fieldset class="form-group">
						<label>ID: </label>
						<c:out value="${praca.id}" />
					</fieldset>

					<input type="hidden" name="id"
						value="<c:out value='${praca.id}' />" />

					<fieldset class="form-group">
						<label>Tytul: </label>
						<c:out value="${praca.tytul}" />
					</fieldset>

					<fieldset class="form-group">
						<label>ID_konferencji: </label>
						<c:out value="${praca.id_konferencji}" />
					</fieldset>

					<fieldset class="form-group">
						<label>Ocena</label> <input type="text" class="form-control"
							name="ocena">
					</fieldset>

					<button type="submit" class="btn btn-success">Zapisz</button>
					<fieldset class="form-group" style="color: red;">
						<label> </label>
						<c:out value="${komentarz}" />
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</body>

</html>
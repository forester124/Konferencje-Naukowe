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
				<a class="navbar-brand"> Edytuj dane </a>
			</div>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">

				<form action="edytujRecenzenta" method="post">



					<h2>Edycja danych recenzenta</h2>



					<input type="hidden" name="id" value="<c:out value='${rec.id}' />" />


					<fieldset class="form-group">
						<label>Tytul naukowy</label> <input type="text"
							value="<c:out value='${rec.tytul}' />" class="form-control"
							name="tytul" required="required">
					</fieldset>

					<fieldset class="form-group">
						<label>Imie</label> <input type="text"
							value="<c:out value='${rec.imie}' />" class="form-control"
							name="imie" required="required">
					</fieldset>

					<fieldset class="form-group">
						<label>Nazwisko</label> <input type="text"
							value="<c:out value='${rec.nazwisko}' />" class="form-control"
							name="nazwisko" required="required">
					</fieldset>

					<fieldset class="form-group">
						<label>Pesel</label> <input type="text"
							value="<c:out value='${rec.pesel}' />" class="form-control"
							name="pesel" required="required">
					</fieldset>
					<fieldset class="form-group">
						<label>Telefon</label> <input type="text"
							value="<c:out value='${rec.telefon}' />" class="form-control"
							name="telefon" required="required">
					</fieldset>
					<fieldset class="form-group">
						<label>Email</label> <input type="text"
							value="<c:out value='${rec.email}' />" class="form-control"
							name="email" required="required">
					</fieldset>
					<fieldset class="form-group">
						<label>Nazwa użytkownika</label> <input type="text"
							value="<c:out value='${rec.username}' />" class="form-control"
							name="username" required="required">
					</fieldset>
					<fieldset class="form-group">
						<label>Hasło</label> <input type="password"
							value="<c:out value='${rec.password}' />" class="form-control"
							name="password" required="required">
					</fieldset>

					<button type="submit" class="btn btn-success">zapisz</button>
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
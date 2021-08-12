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
				<a class="navbar-brand"> Praca </a>
			</div>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">


				<form action="dodajPrace" method="post">



					<h2>Dodaj prace</h2>


					<fieldset class="form-group">
						<label>Tytul</label> <input type="text" class="form-control"
							name="tytul" required="required">
					</fieldset>

					<fieldset class="form-group">
						<label>ID_konferencji</label> <input type="text"
							class="form-control" name="id_konferencji">
					</fieldset>

					<fieldset class="form-group">
						<label>Ścieżka pliku</label> <input type="text"
							class="form-control" name="uploadPath">
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
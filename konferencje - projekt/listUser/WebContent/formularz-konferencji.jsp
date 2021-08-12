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
				<a class="navbar-brand">Konferencja </a>
			</div>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${konferencja != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${konferencja == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${konferencja != null}">
                                    Edytuj konferencje
                                </c:if>
						<c:if test="${konferencja == null}">
                                    Dodaj konferencje
                                </c:if>
					</h2>
				</caption>

				<c:if test="${konferencja != null}">
					<input type="hidden" name="id"
						value="<c:out value='${konferencja.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Temat</label> <input type="text"
						value="<c:out value='${konferencja.nazwa}' />"
						class="form-control" name="nazwa" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Koszt</label> <input type="text"
						value="<c:out value='${konferencja.koszt}' />"
						class="form-control" name="koszt" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Opis</label> <textarea
						<c:out value='${konferencja.opis}' /> class="form-control"
						name="opis" required="required" rows="10"><c:out value='${konferencja.opis}' /></textarea>
				</fieldset>
				
				<fieldset class="form-group">
					<label>Harmonogram</label> <input type="text"
						value="<c:out value='${konferencja.harmonogram}' />" class="form-control"
						name="harmonogram" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Adres</label> <input type="text"
						value="<c:out value='${konferencja.adres}' />"
						class="form-control" name="adres" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Organizatorzy</label> <input type="text"
						value="<c:out value='${konferencja.organizator}' />"
						class="form-control" name="organizator" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Data Konferencji</label> <input type="text"
						value="<c:out value='${konferencja.dataKonf}' />"
						class="form-control" name="dataRoz" required="required"
						placeholder="rrrr-mm-dd">
				</fieldset>

				<fieldset class="form-group">
					<label>Data do końca rejestracji</label> <input type="text"
						value="<c:out value='${konferencja.dataZapis}' />"
						class="form-control" name="dataZap" required="required"
						placeholder="rrrr-mm-dd">
				</fieldset>

				<fieldset class="form-group">
					<label>Godzina rozpoczęcia</label> <input type="text"
						value="<c:out value='${konferencja.godzina}' />"
						class="form-control" name="godzina" required="required"
						placeholder="00:00">
				</fieldset>

				<button type="submit" class="btn btn-success">Zapisz</button>
				</form>
			</div>
		</div>
	</div>
</body>

</html>
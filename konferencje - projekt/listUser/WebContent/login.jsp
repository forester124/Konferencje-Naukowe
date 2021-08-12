<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Konferencje</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

	<div class="container col-md-8 col-md-offset-3" style="overflow: auto">
		<h1>Strona logowania</h1>
		<form action="<%=request.getContextPath()%>/login" method="post">
			<div class="form-group">
				<label for="uname">Nazwa użytkownika:</label> <input type="text"
					class="form-control" id="username" placeholder="Username"
					name="username" required>
			</div>
			<div class="form-group">
				<label for="uname">Hasło:</label> <input type="password"
					class="form-control" id="password" placeholder="Password"
					name="password" required>
			</div>
			<button type="submit" class="btn btn-primary">Zaloguj</button>
			<a href="user-registration.jsp" type="button" class="btn btn-primary">Zarejestruj
				się!</a>
		</form>
	</div>
</body>
</html>
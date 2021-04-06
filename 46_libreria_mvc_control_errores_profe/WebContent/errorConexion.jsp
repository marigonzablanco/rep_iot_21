<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h1>No se ha podido completar la operación</h1>
		<h3>${requestScope.mensajeError}</h3>
		<a href="Controller?option=toSalir">Salir</a>
	</center>
</body>
</html>
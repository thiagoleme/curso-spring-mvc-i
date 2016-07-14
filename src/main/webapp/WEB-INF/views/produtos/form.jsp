<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:form action="${s:mvcUrl('PC#add').build()}" method="post" commandName="produto" enctype="multipart/form-data">
	<div>
        <label>T�tulo</label> 
        <form:input path="titulo" />
        <form:errors path="titulo" />
    </div>
    <div>
        <label>Descri��o</label>
        <form:textarea rows="10" cols="20" path="descricao" />
        <form:errors path="descricao" />
    </div>
    <div>
		<label>N� de P�ginas: </label>
        <form:input path="paginas" />
        <form:errors path="paginas" /> 
    </div>
    <div>
        <label>Data de Lan�amento</label>
        <form:input path="dataLancamento" />
        <form:errors path="dataLancamento" />
    </div>
 	<c:forEach items="${tiposPreco}" var="tipoPreco" varStatus="status">
        <div>
            <label>${tipoPreco}</label>
            <form:input path="precos[${status.index}].valor" /> 
            <form:hidden path="precos[${status.index}].tipo" value="${tipoPreco}" />
        </div>
    </c:forEach>
    <div>
    	<label>Sum�rio</label>
        <input type="file" name="sumario" />
    </div>
	<button type="submit">Cadastrar</button>
</form:form>
</body>
</html>
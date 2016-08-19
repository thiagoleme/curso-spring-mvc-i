<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:url value="/resources" var="resourcePath" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link href="${resourcePath}/css/bootstrap.min.css"
				rel="stylesheet" type="text/css" media="all" />
<link href="${resourcePath}/css/bootstrap-theme.min.css"
				rel="stylesheet" type="text/css" media="all" />
<script src="${resourcePath}/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-inverse">
	  <div class="container">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand" href="${s:mvcUrl('HC#homePage').build()}">Casa do C�digo</a>
	    </div>
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      		<ul class="nav navbar-nav">
			    <li><a href="${s:mvcUrl('PC#listar').build()}">Lista de Produtos</a></li>
			    <li><a href="${s:mvcUrl('PC#form').build()}">Cadastro de Produtos</a></li>
			</ul>
	    </div><!-- /.navbar-collapse -->
	  </div>
	</nav>
	
    <div class="container">
    	<h1>Cadastro de Produto</h1>
		<form:form action="${s:mvcUrl('PC#add').build()}" method="post" commandName="produto" enctype="multipart/form-data">
			<div class="form-group">
		        <label>T�tulo</label> 
		        <form:input path="titulo" cssClass="form-control" />
		        <form:errors path="titulo" />
		    </div>
		    <div class="form-group">
		        <label>Descri��o</label>
		        <form:textarea rows="10" cols="20" path="descricao" cssClass="form-control" />
		        <form:errors path="descricao" />
		    </div>
		    <div class="form-group">
				<label>N� de P�ginas: </label>
		        <form:input path="paginas" cssClass="form-control" />
		        <form:errors path="paginas" /> 
		    </div>
		    <div class="form-group">
		        <label>Data de Lan�amento</label>
		        <form:input path="dataLancamento" cssClass="form-control" />
		        <form:errors path="dataLancamento" />
		    </div>
		 	<c:forEach items="${tiposPreco}" var="tipoPreco" varStatus="status">
		        <div class="form-group">
		            <label>${tipoPreco}</label>
		            <form:input path="precos[${status.index}].valor" cssClass="form-control" /> 
		            <form:hidden path="precos[${status.index}].tipo" value="${tipoPreco}" />
		        </div>
		    </c:forEach>
		    <div class="form-group">
		    	<label>Sum�rio</label>
		        <input type="file" name="sumario" class="form-control" />
		    </div>
			<button type="submit">Cadastrar</button>
		</form:form>
	</div>
</body>
</html>
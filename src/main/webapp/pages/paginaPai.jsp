<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<h1>Pagina pai load jQuery</h1>
<input type="button" value="carregar página" onclick="carregar();">
<div id="mostrarPaginaFilho"><!-- local de carregamento da pagina filho -->

</div>
<script type="text/javascript">

function carregar(){
$("#mostrarPaginaFilho").load('paginaFilho.jsp')
	
}

</script>
</body>
</html>
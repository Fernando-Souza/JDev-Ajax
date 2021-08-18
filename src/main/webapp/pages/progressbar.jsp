<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Barra de progresso</title>
<style type="text/css">
/*fundo da barra de progresso - cor cinza */
#myProgress {
	width: 100%;
	background-color: #ddd;
}
/*cor da barra de progresso*/
#myBar {
	width: 0.1%;
	height: 30px;
	background-color: #4CAF50;
}
</style>
</head>
<body>
	<h1>Exemplo com java script</h1>
	<div id="myProgress">
		<!-- Fundo de barra -->
		<div id="myBar">
			<!-- Barra de progresso -->

		</div>
	</div>
	<br>
	<button onclick="exibirBarra()">Iniciar Barra de Progresso</button>
	<script type="text/javascript">

function exibirBarra(){
var elem = document.getElementById("myBar");
var width =0.1;
var id = setInterval(frame,500);

function frame(){
	if(width>=100){
	clearInterval(id);
	}else{
		width++;
		elem.style.width = width +"%";
		}
	}
}

</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.0.min.js">
</script>
</head>
<body>
	<h3>Capturando exceções com jquery</h3>
	<input type="text" value="valor informado" id="txtValor">
	<input type="button" onClick="testarExcecao();" value="Testar">
	<script type="text/javascript">
		function testarExcecao() {

			var valorInformado = $('#txtValor').val();
						

			$.ajax({
				method : "POST",
				url : "CapturarExcecao",
				data : {
					"valorParam" : valorInformado
				}
			}).done(function(response) {

				alert("Sucesso:" + response);

			}).fail(function(xhr,status,errorThrown) {

				alert("Error:" + xhr.responseText);

			})
		}
	</script>
</body>
</html>
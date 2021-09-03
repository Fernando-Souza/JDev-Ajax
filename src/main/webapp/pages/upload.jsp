<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload files</title>
<script type="text/javascript"	src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<input type="file" id="file" name="file" onchange="uploadFile()">
<img alt="Imagem" src="" id="target" width="200" height="200" >
<br>
<br>
<br>
<br>

<div>
<a href="fileUpload?acao=carregar">Carregar Arquivo</a>
<br>
<br>
<table>
<c:forEach items="${listaUserImagem}" var="user">

<tr>
<td>"${user.id}"</td>
<td>"${user.login}"</td>
<td><a href="fileUpload?acao=download&iduser=${user.id}">Download Arquivo</a></td>
</tr>


</c:forEach>

</table>
</div>
</body>
<script type="text/javascript">

function uploadFile(){
	
var target = document.querySelector("img");
var file =  document.querySelector("input[type=file]").files[0];
var reader = new FileReader();

reader.onloadend = function(){
	target.src = reader.result;
	//--upload ajax
    $.ajax({
     method:"POST",
     url : "fileUpload",
     data: {fileUpload:reader.result}              
        }).done(function(response){
            alert("Sucesso:" + response);
            }).fail(function(xhr,status,errorThrown){
                alert("Error:" + xhr.responseText)
                });
};

if(file){
	reader.readAsDataURL(file);    
	
}else{
	target.src="";
}

}

</script>
</html>
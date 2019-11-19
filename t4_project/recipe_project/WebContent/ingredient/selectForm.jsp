<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script>
//<-- type="text/javascript"--?>
	 function wname(category){
		 var category = document.getElementById("small").text;
		 document.getElementById("small").innerHTML="<c:forEach var='계란류' items='${ingreList}'>"+
				 " <li>${ingredient.iname}</li>"+
				 "</c:forEach>";
		 };
	
	
	
</script>
<meta charset="utf-8">
<style>
	.big {
    width: 100px;
	box-sizing: border-box;
	float: left;
	
	}
	
	.small {
    width: 200px;
    height: 270px;
    float: left;
    padding: 10px 13px;
    box-sizing: border-box;
    font-family: Microsoft YaHei,'NS';
	}
	
	fieldset {
	
    display: block;
    margin-inline-start: 2px;
    margin-inline-end: 2px;
    padding-block-start: 0.35em;
    padding-inline-start: 0.75em;
    padding-inline-end: 0.75em;
    padding-block-end: 0.625em;
    min-inline-size: min-content;
    border-width: 2px;
    border-style: groove;
    border-color: threedface;
    border-image: initial;
	}
	
	.f1 {
    position: relative;
    width: 300px;
    float:left
	}
	
	li {
    display: list-item;
    text-align: match-parent;
    
	}
	.big>li{
		line-height: 40px;
		border-bottom: 1px solid #dddddd;
		
	}
	.f1>ul {
    height: 200px;
    border: 1px solid #a73f40;
    background: #fff;
	}
	
	ul {
    display: block;
    list-style-type: disc;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    padding-inline-start: 0px;
    padding: 5px;
    list-style: none;
    float: left;
	}
	a {
		text-decoration: none;
	}
</style>
<title>재료</title>
</head>
<body>
<fieldset class="f1">
	<ul class = "big">
		<li>
			<a>빵류</a> 
		</li>
		<li>
			<a href="#" onclick="wname('계란류')">계란류</a>
		</li>
	</ul>
	<ul class="small" id="small">
	
		 <c:forEach var="1000" items='${ingreList}'>
		 <jsp:include page="selectForm.jsp">
        <jsp:param name="myVar" value="계란류"/>
    </jsp:include>
		 <li>
		 ${ingredient.iname}
		 </li>
		 </c:forEach> 
	</ul>
</fieldset>
</body>
</html>
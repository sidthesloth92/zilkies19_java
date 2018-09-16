<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="io.ztech.cricalert.beans.Player"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function dragStart(ev) {
		ev.dataTransfer.setData("text/plain", ev.target.id);
	}
</script>
</head>
<body>
	<div id="19" class="box"
		style="border: 1px solid hotpink; width: 100px; height: 100px; background-color: wheat; cursor: pointer;"
		draggable='true' ondragstart='dragStart(event)'></div>
</body>
</html>
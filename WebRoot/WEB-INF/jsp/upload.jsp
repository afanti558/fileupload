<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body>
	<form action="uploadinfomation" method="post" name="uploadinfomation" enctype="multipart/form-data">
		姓名：<input type="text" name="name"><br>
		相片：<input type="file" name="pic"><br> 
		兴趣：<input type="checkbox" name="inst" value="dancing">跳舞
		    <input type="checkbox" name="inst" value="singing">唱歌
		    <input type="checkbox" name="inst" value="reading">阅读<br>
		<input type="submit" value="提交"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="reset" value="重置">
	</form>    
  </body>
</html>

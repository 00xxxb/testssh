<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/12
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<html>
<head>
    <title><bean:message key="success.title"/> </title>
</head>
<body>
<center><h1><bean:message key="success.title"/> </h1></center><br>
<center><h1><bean:message key="success.back.login"/> </h1></center>
<%response.setHeader("Refresh","3;URL=/goLogin.html");%>
<center><h1><a href="/goLogin.html"><bean:message key="success.back.part1"/> </a></h1></center>
</body>
</html>

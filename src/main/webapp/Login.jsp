<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/13
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>

<html>
<head>
    <title><bean:message key="index.title"/> </title>
</head>
<script type="text/javascript" src="md5.js"></script>
<script type="text/javascript">
    function aaa() {
        document.getElementById("lopassword").value =  hex_md5(document.getElementById("lopassword").value);
        with (document.getElementById("login")){
            submit();
        }
    }
</script>

<body>

<style type="text/css">
    body{
        background-image: url(111.jpg);
        background-size:cover;
    }
</style>
<div align="center">
<br><br><br><br><br><br><br><br><br><br><br>
<div style="width: 300px;">
    <fieldset>
        <legend><h2 style="color: lightpink"><bean:message key="login.head"/> </h2></legend>
        <form action="/login.html" method="post" id="login">
            <b style="color: deeppink"><bean:message key="user.username"/> ： <br></b><input type="text" name="louser"><br>
            <b style="color: deeppink"><bean:message key="user.password"/> ：<br></b><input type="password" id="lopassword" name="lopassword"><br>
            <br>
            <input type="button" onclick="aaa()" value= <bean:message key="user.button.login"/>><br>
            <a href="/jiadeChangeLan/zh.html">中文版</a>&nbsp;&nbsp;<a href="/jiadeChangeLan/en.html">English</a>
        </form>
    </fieldset>
</div>
<html:errors/>
</div>

<center><a href="/goRegister.html"><bean:message key="login.goto.register"/> </a> </center>
</body>
</html>

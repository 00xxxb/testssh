<%@ page import="MailSupport.SendMail" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title><bean:message key="register.title"/> </title>
</head>

<script type="text/javascript" src="Ajax.js">

</script>
<script type="text/javascript">

</script>

<body>
<div align="center">
    <br><br><br><br><br><br>
    <div style="width: 400px;">
        <fieldset>
            <legend><h2 style="color: lightpink"><bean:message key="register.head"/> </h2></legend>
            <form id="reForm" name="reForm" method="post">
                <span id="info"></span><br>
                <bean:message key="register.input.user"/> ：<br><input type="text" id="reuser" name="reuser" onblur="checkUserName()"><br>
                <bean:message key="register.input.password"/> ：<br><input type="password" id="repassword" name="repassword"><br>
                <bean:message key="register.confirm.password"/> ：<br><input type="password" id="repassword1" name="repassword1"><br>
                <bean:message key="register.email"/> : <br><input type="text" id="email" name="email"><br>
                <input type="button" id="getma" name="getma" value="免费获取验证码" onclick="settime(this)"><br>
                <span id="send"></span><br>
                请输入验证码：<br>
                <input type="text" id="sign" name="sign"/><br>
                <input type="button" onclick="Register_sub()" value=<bean:message key="register.submit"/>>
                <input type="button" onclick="Register_back()" value=<bean:message key="register.back"/>>
                <span id="check"></span><br>
            </form>
        </fieldset>
    </div>
    <html:errors/>
</div>
</body>
</html>
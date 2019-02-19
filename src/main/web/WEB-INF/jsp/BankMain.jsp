<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/13
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>欢迎使用</title>
</head>

<script type="text/javascript" src="../../md5.js"></script>
<script type="text/javascript">
    function s1() {
        var money = document.getElementById("deposit").value;
        var re = /^(-?[0-9]+).?[0-9]*$/; //判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/
        if (money==""){
            alert('存款金额不能为空');
            return false;
        }
        else if (!re.test(money)){
            alert('请输入数字(例:20.02)');
            return false;
        }
        else if (money<0){
            alert('输入金额必须为正数');
            return false;
        }
        else {
            with (document.getElementById("save")) {
                submit();
            }
        }
    }

    function s2() {
        var money = document.getElementById("withdrawals").value;
        var re = /^(-?[0-9]+).?[0-9]*$/; //判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/
        if (money==""){
            alert('取款金额不能为空 ');
            return false;
        }
        else if (!re.test(money)){
            alert('请输入数字(例:20.02)');
            return false;
        }
        else if (money<0){
            alert('输入金额必须为正数');
            return false;
        }
        else {
            with (document.getElementById("getout")) {
                submit();
            }
        }
    }

    function s3() {
        var money = document.getElementById("transmoney").value;
        var user = document.getElementById("truser").value;
        var re = /^(-?[0-9]+).?[0-9]*$/; //判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/

        if (user==""){
            alert('转账用户不能为空 ');
            return false;
        }
        else if (money==""){
            alert('转账金额不能为空 ');
            return false;
        }
        else if (!re.test(money)){
            alert('请输入数字(例:20.02)');
            return false;
        }
        else if (money<0){
            alert('输入金额必须为正数');
            return false;
        }
        else {
            document.getElementById("transuser").value = hex_md5("che")+document.getElementById("truser").value+hex_md5("ck");
            with (document.getElementById("transfer")) {
                submit();
            }
        }
    }
</script>

<body>

<style type="text/css">
    body{
        background-image: url(../../333.jpg);
        background-size:cover;
    }
</style>
<h1><font color="red">
    <html:messages id="errorFrozen" property="Frozen.User">
        <bean:write name="errorFrozen"/>
    </html:messages>
</font></h1>
<center><h1 style="color: yellow">欢迎使用辣鸡银行</h1></center>
<form action="/nomoney/inquiry.html" method="post">
    <input type="submit" value="查询余额">
</form>
<h1 style="color: red">${money}</h1>

<font color="red">
    <html:messages id="error" property="number.error">
        <bean:write name="error"/>
    </html:messages>
</font>
<font color="red">
    <html:messages id="error2" property="money.overamount">
        <bean:write name="error2"/>
    </html:messages>
</font>

<hr>
<h1>存款 </h1>
<form action="/nomoney/deposit.html" id="save" name="save" method="post">
    请输入存款金额 ：<input type="text" id="deposit" name="deposit">
    <input type="button" onclick="s1()" value="存款">
</form>

<hr>
<h1>取款 </h1>
<form action="/nomoney/withdrawals.html" id="getout" method="post">
    请输入取款金额 ：<input type="text" id="withdrawals" name="withdrawals">
    <input type="button" onclick="s2()" value="取款">
</form>

<hr>
<h1>转账</h1>
<form action="/nomoney/transfer.html" id="transfer" method="post">
    请输入转账用户 ：<input type="text" id="truser" name="truser"><br>
    请输入转账金额 ：<input type="text" id="transmoney" name="transmoney"><br>
    <input type="hidden" id="transuser" name="transuser">
    <input type="button" onclick="s3()" value="转账">
</form>
<font color="red">
</font>

<hr>
<form action="/showInfo.html" method="post">
    <input type="submit" value="查看交易记录"><br>
</form>
<hr>
<form action="/nomoney/loan.html">
    <input type="submit" value="花呗"><br>
</form>
<hr>
<form action="/nomoney/exit.html" method="post">
    <input type="submit" value="退出">
</form>
</body>
</html>

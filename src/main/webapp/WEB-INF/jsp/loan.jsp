<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/17
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<script type="text/javascript">
    function loan() {
        var remain = "${loanInfo.leftduration}";
        var money = document.getElementById("loanmoney").value;
        var left = "${loanInfo.canloan}";
        if (remain!=0){
            alert("您还有尚未还清的贷款");
            return false;
        }
        else if(money>left){
            alert("您的额度不足");
            return false;
        }
        else {
            with (document.getElementById("loanform")){
                submit();
            }
        }
    }
    
    function payback() {
        var shouldpay = "${loanInfo.payback}";
        if (shouldpay==0){
            alert("您今日债务已全部还清啦");
            return false;
        }
        else {
           var result=confirm("确认还款？");
            if(result==true){
                location.href ='/loan/payback.html?terms='+'${loanInfo.loanduration}';
            }
        }
    }

    function payallback() {
        var allpay = "${loanInfo.leftduration}";
        if (allpay==0){
            alert("您还没有一笔贷款哦");
            return false;
        }
        else {
            var allmoney = "${loanInfo.allLoan}";
            var result=confirm("确认还款？，还款金额为:"+allmoney);
            if(result==true){
                location.href ='/loan/payback.html?terms='+allpay;
            }
        }
    }
</script>
<head>
    <title>花呗</title>
</head>
<body>
剩余额度为：${loanInfo.canloan}
当前应还：${loanInfo.payback}<br>
还剩${loanInfo.leftduration}期<br>
该还${loanInfo.loanduration}期<br>
 <hr>
 贷款：<br>
 <form action="/loan/loan.html" method="post" id="loanform">
     贷款金额：<input type="text" id="loanmoney" name="loanmoney"><br>
     贷款时间：<select name = "pasttime" id="pasttime">
         <option value="1">1</option>
         <option value="2">2</option>
         <option value="3">3</option>
         <option value="4">4</option>
         <option value="5">5</option>
         <option value="6">6</option>
         <option value="7">7</option>
     </select>（天）
     <input type="button" value="贷款" onclick="loan()">
 </form>
<html:errors/>
 <input type="button" value="立即还款" onclick="payback()"><br>
<input type="button" value="全部还清" onclick="payallback()"><br>
<input type="button" value="返回" onclick="location.href ='/nomoney/inquiry.html'">
</body>
</html>

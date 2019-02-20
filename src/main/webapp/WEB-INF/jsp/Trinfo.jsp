<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/12
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript">
    function upup() {
        var page = ${page};
        if (page==1){
            alert("已经是第一页了");
            return false;
        }

        else{
            var goto = page-1;
            var url="/trinfo/"+goto+".html";
            window.location.href=url;
        }
    }

    function downdown() {
        var page = ${page};
        var maxPage = ${maxPages};
        if (page==maxPage){
            alert("已经是最后一页了");
            return false;
        }

        else{
            var goto = page+1;
            var url="/trinfo/"+goto+".html";
            window.location.href=url;
        }
    }
    
    function first() {
        var url="/trinfo/"+1+".html";
        window.location.href=url;
    }

    function last() {
        var maxPage = ${maxPages};
        var url="/trinfo/"+maxPage+".html";
        window.location.href=url;
    }
</script>
<body>
<table border="1">
<tr>
    <td width="24%">序号</td>
    <td width="24%">操作详情</td>
    <td width="24%">金额</td>
    <td width="24%">日期</td>
</tr>
<c:forEach items="${showList}" var="s">
    <tr>
        <td width="24%">
            <c:out value="${s.id}"/>
        </td>
        <td width="24%">
            <c:out value="${s.mes}"/>
        </td>
        <td width="24%">
            <c:out value="${s.money}"/>
        </td>
        <td width="24%">
            <c:out value="${s.timestamp}"/>
        </td>
    </tr>
</c:forEach>
</table>
<input type="button" id="firstPage" onclick="first()" value="首页">
<input type="button" id="upPage" onclick="upup()" value="上一页">${page}/${maxPages}<input type="button" id="downPage" onclick="downdown()" value="下一页">
<input type="button" id="lastPage" onclick="last()" value="尾页"><br>
<input type="button" value="返回" onclick="location.href ='/nomoney/inquiry.html'">
</body>
</html>

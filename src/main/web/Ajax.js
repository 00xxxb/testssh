var req;

function Register_back() {
    with (document.getElementById("reForm")){
        action="/goLogin.html";
        submit();
    }
}

function checkUserName() {
    //获取表单提交的内容
    var idField = document.getElementById("reuser");
    //访问validate.do这个servlet，同时把获取的表单内容idField加入url字符串，以便传递给validate.do
    var url = "/verify.do?me=verify&name=" + escape(idField.value);
    //创建一个XMLHttpRequest对象req
    if(window.XMLHttpRequest) {
        //IE7, Firefox, Opera支持
        req = new XMLHttpRequest();
    }else if(window.ActiveXObject) {
        //IE5,IE6支持
        req = new ActiveXObject("Microsoft.XMLHTTP");
    }
    /*
     open(String method,String url, boolean )函数有3个参数
     method参数指定向servlet发送请求所使用的方法，有GET,POST等
     boolean值指定是否异步，true为使用，false为不使用。
     我们使用异步才能体会到Ajax强大的异步功能。
     */
    req.open("POST", url, true);
    //onreadystatechange属性存有处理服务器响应的函数,有5个取值分别代表不同状态
    req.onreadystatechange = callback;
    //send函数发送请求
    req.send(null);
}

function callback() {
    if(req.readyState == 4 && req.status == 200) {
        var check = req.responseText;
        show (check);
    }
}

function show(str) {
    if(str == "OK") {
        var show = "<font color='green'>恭喜！！用户名可用！</font>";
        document.getElementById("info").innerHTML = show;
    }
    else if( str == "NO") {
        var show = "<font color='red'>对不起，用户名不可用！！请重新输入！</font>";
        document.getElementById("info").innerHTML = show;
    }
}

var countdown=10;
function settime(val) {
    if (countdown == 0) {
        val.removeAttribute("disabled");
        val.value="免费获取验证码";
        countdown = 10;
    }
    else if (countdown==10) {
        countdown--;
        sendMail();
    }
    else{
        val.setAttribute("disabled", true);
        val.value="重新发送(" + countdown + ")";
        countdown--;
    }
    if (countdown!=10) {
        setTimeout(function () {
            settime(val)
        }, 1000)
    }
}

function sendMail() {
    var getma = document.getElementById("getma");
    getma.setAttribute("disabled",true);
    //获取表单提交的内容
    var idField = document.getElementById("email");
    var email = document.getElementById("email").value;
    if (email.indexOf("@")==-1||email.indexOf(".com")!=email.length-4){
        alert('邮箱格式不正确 ');
        return false;
    }
    //访问validate.do这个servlet，同时把获取的表单内容idField加入url字符串，以便传递给validate.do
    var url = "/verify.do?me=sendMail&name=" + escape(idField.value);
    //创建一个XMLHttpRequest对象req
    if(window.XMLHttpRequest) {
        //IE7, Firefox, Opera支持
        req = new XMLHttpRequest();
    }else if(window.ActiveXObject) {
        //IE5,IE6支持
        req = new ActiveXObject("Microsoft.XMLHTTP");
    }
    /*
     open(String method,String url, boolean )函数有3个参数
     method参数指定向servlet发送请求所使用的方法，有GET,POST等
     boolean值指定是否异步，true为使用，false为不使用。
     我们使用异步才能体会到Ajax强大的异步功能。
     */
    req.open("POST", url, true);
    //onreadystatechange属性存有处理服务器响应的函数,有5个取值分别代表不同状态
    req.onreadystatechange = sendback;
    //send函数发送请求
    req.send(null);
}

function sendback() {
    if(req.readyState == 4 && req.status == 200) {
        var check = req.responseText;
        sendshow (check);
    }
}

function sendshow(str) {
    if(str == "OK") {
        var show = "<font color='green'>该邮箱可用</font>";
        document.getElementById("send").innerHTML = show;
    }
    else if( str == "NO") {
        var show = "<font color='red'>对不起，邮箱已被注册！！请重新输入！</font>";
        document.getElementById("send").innerHTML = show;
    }
}

function Register_sub() {
    var user = document.getElementById("reuser").value;
    var password1 = document.getElementById("repassword").value;
    var password2 = document.getElementById("repassword1").value;
    var email = document.getElementById("email").value;
    var re = [a-zA-Z]+[a-zA-Z0-9]*
    if (user.length==0){
        alert('用户名不能为空');
        return false;
    }
    else if (!re.test(user)){
        alert('用户名必须为数字和字母的组合，且第一位必须为字母');
        return false;
    }
    else if(password1.length<6){
        alert('密码长度不能小于6位');
        return false;
    }
    else if (password1!=password2){
        alert('两次输入的密码不一致');
        return false;
    }
    else if (email==""){
        alert('邮箱不能为空 ');
        return false;
    }
    else if (email.indexOf("@")==-1||email.indexOf(".com")!=email.length-4){
        alert('邮箱格式不正确 ');
        return false;
    }
    else {
        //获取表单提交的内容
        var idField = document.getElementById("sign");
        //访问validate.do这个servlet，同时把获取的表单内容idField加入url字符串，以便传递给validate.do
        var url = "/verify.do?me=check&name=" + escape(idField.value);
        //创建一个XMLHttpRequest对象req
        if(window.XMLHttpRequest) {
            //IE7, Firefox, Opera支持
            req = new XMLHttpRequest();
        }else if(window.ActiveXObject) {
            //IE5,IE6支持
            req = new ActiveXObject("Microsoft.XMLHTTP");
        }
        /*
         open(String method,String url, boolean )函数有3个参数
         method参数指定向servlet发送请求所使用的方法，有GET,POST等
         boolean值指定是否异步，true为使用，false为不使用。
         我们使用异步才能体会到Ajax强大的异步功能。
         */
        req.open("POST", url, true);
        //onreadystatechange属性存有处理服务器响应的函数,有5个取值分别代表不同状态
        req.onreadystatechange = checkSign;
        //send函数发送请求
        req.send(null);
    }
}

function checkSign() {
    if(req.readyState == 4 && req.status == 200) {
        var check = req.responseText;
        checkshow (check);
    }
}

function checkshow(str) {
    if(str == "OK") {
        with (document.getElementById("reForm")) {
                action="/register.html";
                submit();
            }
    }
    else if( str == "NO") {
        var show = "<font color='red'>验证码不正确</font>";
        document.getElementById("check").innerHTML = show;
    }
}
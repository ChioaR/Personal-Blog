<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<script src="<%=contextPath%>/static/jquery-3.1.1.js"></script>
<script src="<%=contextPath%>/static/bootstrap/js/bootstrap.js"></script>
<link href="<%=contextPath%>/static/bootstrap/css/bootstrap.css" rel="stylesheet"/>
<link href="<%=contextPath%>/static/bootstrap/css/bootstrap-theme.css" rel="stylesheet"/>
<div class="row">
    <nav class="navbar navbar-inverse">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<%=contextPath%>/" style="color: #00c4ff">Personal Blog</a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav" id="nv1">
                    <li class="active" id="firstPage"><a href="<%=contextPath%>/">首页</a></li>
                    <li id="webPage"><a href="<%=contextPath%>/column/JavaWeb专栏/webPage">JavaWeb专栏</a></li>
                    <li id="androidPage"><a href="<%=contextPath%>/column/Android专栏/androidPage">Android专栏</a>
                    </li>
                    <li id="rnPage"><a href="<%=contextPath%>/column/React Native专栏/rnPage">React Native专栏</a>
                    </li>
                    <li id="ubuntuPage"><a href="<%=contextPath%>/column/Ubuntu专栏/ubuntuPage">Ubuntu专栏</a>
                    </li>
                </ul>
                <form class="navbar-form navbar-right">
                    <div class="input-group">
                        <input type="text" class="form-control"  id="searchContent" placeholder="请输入标题关键字">
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button" id="button">Go!</button>
                            </span>
                    </div>
                </form>

                <form class="navbar-form navbar-left">
                    <div class="bullet-button">
                        <button class="btn btn-default" type="button" style="color: #01a0e4" onclick="window.location.href='/login'">登录</button>
                    </div>
                </form>

            </div>
        </div>
    </nav>
</div>
<script>
    var href = location.href;
    var id = href.substring(href.lastIndexOf("/") + 1, href.length);
    if (id=="") {
        id = "firstPage";
    }
    var ids = ["firstPage", "webPage", "androidPage", "rnPage", "ubuntuPage"];
    for (var i = 0; i < ids.length; i++) {
        if (id == ids[i]) {
            $("#" + id).attr("class", "active");
        } else {
            $("#" + ids[i]).removeAttr("class");
        }
    }
    $("#button").click(function () {
        var content = document.getElementById("searchContent").value;
        if (content == ""){
            alert("请先输点东西吧!，崽种!!!");
        }else {
            $.get('/search',{"content":content},function (data) {
                if (data.length == 0){
                    alert("锤子都没得!!!");
                } else {
                    for (var i = 0; i <data.length ; i++) {
                        alert(data[i].content);
                    }
                }
            });

        }
    });
</script>
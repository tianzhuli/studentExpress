<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
if(request.getSession().getAttribute("manager")==null){
	response.sendRedirect(basePath+"/xuedi_manager/Views/"+"login.html");
}


%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="../bootstrap2.3.2/css/bootstrap.min.css" rel="stylesheet" />
    <title>学递来啦后台管理系统</title>
    <link href="../styles/Common.css" rel="stylesheet" />
    <link href="../styles/Index.css" rel="stylesheet" />
    <script src="../scripts/jquery-1.9.1.min.js"></script>
     <script src="../scripts/initializtion.js"></script>
</head>
<body>
    <div class="header">

        <img class="logo" src="../images/logo.png" />
        <label class="logo-title">学递来啦后台管理系统</label>
        <ul class="inline">
            <li>
                <img src="../images/32/client.png" />&nbsp;&nbsp;欢迎您,Admin
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle mymsg" data-toggle="dropdown" href="#"><img src="../images/32/166.png" />&nbsp;&nbsp;修改个人信息<b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="#">修改密码</a></li>
                </ul>

            </li>
            <li>
                <img src="../images/32/200.png" />&nbsp;&nbsp;<a class="loginout" href="login.html">退出</a>
            </li>

        </ul>


    </div>


    <div class="nav">

        <ul class="breadcrumb">
            <li>
                <img src="../images/32/5025_networking.png" />
            </li>
            <li><a href="Index.html">首页</a> <span class="divider">>></span></li>
            <li class="active"></li>
        </ul>
    </div>



    <div class="container-fluid content">
        <div class="row-fluid">
            <div class="span2 content-left">
                <div class="accordion">
                    <div class="accordion-group">
                        <div class="accordion-heading">
                            <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
                                <img class="left-icon" src="../images/32/5026_settings.png" /><span class="left-title">系统设置</span>
                            </a>
                        </div>
                        <div id="collapseOne" class="accordion-body collapse in">
                            <div class="accordion-inner" id="tupian">
                                <img class="left-icon-child" src="../images/32/4962_sitemap.png" /><span class="left-body"> 图片认证</span>
                            </div>
                            <div class="accordion-inner" id="gonggao">
                                <img class="left-icon-child" src="../images/32/4957_customers.png" /><span class="left-body"> 发布公告</span>

                            </div>
                            <div class="accordion-inner">
                                <img class="left-icon-child" src="../images/32/4992_user.png" /><span class="left-body"> 用户管理</span>

                            </div>
                            <div class="accordion-inner" id="gonggaomanager">
                                <img class="left-icon-child" src="../images/32/612.png" /><span class="left-body"> 公告管理</span>

                            </div>
                            <div class="accordion-inner">
                                <img class="left-icon-child" src="../images/32/8.png" /><span class="left-body"> 状态管理</span>

                            </div>
                        </div>
                    </div>
                </div>

            </div>

            <div class="span10 content-right">
               <!--  <iframe src="Index2.html" class="iframe"></iframe> -->
            <div class="list">
            </div>
            <div class="result-wrap">
            <div class="result-content">
                <!-- <form action="/student/servlet/InformationServlet" method="post" id="myform" name="myform" enctype="multipart/form-data"> -->
                <form id="myform" name="myform" enctype="multipart/form-data">
                    <table class="insert-tab" width="100%">
                        <tbody>
                            <tr>
                                <th><i class="require-red">*</i>标题：</th>
                                <td>
                                    <input class="common-text required" id="title" name="title" size="50" value="" type="text">
                                </td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>类型：</th>
                                <td>
                                    <input class="common-text required" id="type" name="type" size="50" value="" type="text">
                                </td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>图片：</th>
                                <td><input name="smallimg" id="" type="file"><!--<input type="submit" onclick="submitForm('/jscss/admin/design/upload')" value="上传图片"/>--></td>
                            </tr>
                            <tr>
                                <th>详细内容：</th>
                                <td><textarea name="content" class="common-textarea" id="content" cols="30" style="width: 98%;" rows="10"></textarea></td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <input class="fabu" value="发布" type="button">
                                    <input name="reset"  type="reset" style="display:none;">
                                    <input class="btn btn6" onclick="history.go(-1)" value="返回" type="button">
                                </td>
                            </tr>
                        </tbody></table>
                </form>
            </div>
        </div>
            </div>
        </div>
    </div>
    <div class="clearfix"></div>
    <div class="foot"></div>   
    <script src="../bootstrap2.3.2/js/bootstrap.min.js"></script>
    <script src="../scripts/Index.js"></script>
    <script src="../scripts/indexnew.js"></script>
    <script src="../scripts/image.js"></script>
	<div style="text-align:center;">
<p>制作方:<a href=# target="_blank">学递来啦开发团队</a></p>
</div>
<script type="text/javascript">
 function show(obj){
        alert(obj.value);
    }
    function check(obj,exuserID){
        if (confirm("确认操作")) { 
           /*  alert(obj.value);
            alert(exuserID); */
            $.ajax({
					 type: "post",
					 url: "/studentExpress/activateExpress.do",
					 data:{'exuserID':exuserID,'value':obj.value},
					 dataType: "json",
				success: function (simuData) {
					$("#tupian").trigger("click");
					if (simuData){
						alert("激活成功!");
					}else
						{
						alert("驳回成功！");
						}				
					},
					});
        }
        else{
            //alert("xiexie");
        }
       
    }
    function updateInformation(obj,systemInformationID){
        if (confirm("确认操作")) { 
           /*  alert(obj.value);
            alert(exuserID); */
            $.ajax({
					 type: "post",
					 url: "/studentExpress/updateInformation.do",
					 data:{'systemInformationID':systemInformationID,'value':obj.value},
					 dataType: "json",
				success: function (simuData) {
					$("#gonggaomanager").trigger("click");
					if (simuData){
						alert("删除成功!");
					}else
						{
						alert("更新成功！");
						}				
					},
					});
        }
        else{
            //alert("xiexie");
        }
       
    }
    
</script>
</body>
</html>

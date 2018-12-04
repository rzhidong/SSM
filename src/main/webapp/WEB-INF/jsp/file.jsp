<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<script type="text/javascript" src="${ctx}/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>
<script type="text/javascript">
	$(function(){
		$('#upload').click(function(){
			
			 $.ajaxFileUpload({
		            url : serverUrl+'/user/doUpload',//后台请求地址
		            type: 'post',//请求方式  当要提交自定义参数时，这个参数要设置成post
		            secureuri : false,//是否启用安全提交，默认为false。 
		            fileElementId : 'file',// 需要上传的文件域的ID，即<input type="file">的ID。
		            dataType : 'json',//服务器返回的数据类型。可以为xml,script,json,html。如果不填写，jQuery会自动判断。如果json返回的带pre,这里修改为json即可解决。
		            success : function (json, status) {//提交成功后自动执行的处理函数，参数data就是服务器返回的数据。
		                  alert(json);
		            },
		            error : function (json, status, e) {//提交失败自动执行的处理函数。
		            	alert(e);
		            }
		        });
		});
	});
</script>
</head>
<body>
    <h1>上传文件</h1>
    <form method="post" action="${pageContext.request.contextPath }/user/doUpload" enctype="multipart/form-data">
        <input type="file" id="file" name="file"/>
        <input type="submit" value="上传文件"/>
        <input id="upload" type="button" value="ajax上传文件"/>
    </form>
</body>
</html>
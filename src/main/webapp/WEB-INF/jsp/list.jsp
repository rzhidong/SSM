<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'test.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="https
://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

</head>

<body class="container">
	<center>
		<table width="200" border="1" class="table table-striped">
			<tr>
				<th scope="col">ID</th>
				<th scope="col">姓名</th>
				<th scope="col">密码</th>
				<th scope="col">年龄</th>
			</tr>
			<c:forEach begin="0" step="1" items="${userList}" var="list"
				varStatus="userlist">
				<tr>
					<td>${list.id}</td>
					<td>${list.userName}</td>
					<td>${list.password}</td>
					<td>${list.age}</td>
				</tr>
			</c:forEach>
		</table>
		<p>当前是第${pageInfo.pageNum}页、共${pageInfo.pages}页、${pageInfo.total}条记录</p>


		<nav aria-label="Page navigation">
		<ul class="pagination">
			<li><a
				href="<%=request.getContextPath()%>/user/getAll?page=${pageInfo.firstPage}"
				aria-label="Previous"> <span aria-hidden="true">«</span>
			</a></li>


					<!-- 页码  -->
			<!-- 当总页数小于等于7时，显示页码1...7页 -->
			<c:if test="${pageInfo.pages<=7}">
				<c:forEach begin="1" end="${pageInfo.pages}" step="1" var="itemPage">
					<li <c:if test="${pageInfo.pageNum == itemPage}">class="active"</c:if>>
						<a
						href="${pageContext.request.contextPath}/user/getAll?page=${itemPage}">${itemPage}</a>
					</li>
				</c:forEach>
			</c:if>

			<!-- 当总页数大于7时 -->
			<c:if test="${pageInfo.pages>7}">
				<!-- 当前页数小于等于4时，显示1到5...最后一页 -->
				<c:if test="${pageInfo.pageNum <=4 }">
					<c:forEach begin="1" end="5" step="1" var="itemPage">
						<li <c:if test="${pageInfo.pageNum == itemPage}">class="active"</c:if>>
							<a
							href="${pageContext.request.contextPath}/user/getAll?page=${itemPage}">${itemPage}</a>
						</li>
					</c:forEach>
					<li><a href="javascript:void(0)">...</a></li>
					<li><a
						href="${pageContext.request.contextPath}/user/getAll?page=${pageInfo.pages}">${pageInfo.pages}</a></li>
				</c:if>
				<!-- 当前页数小于等于4时，显示1到5...最后一页  end -->

				<!-- 当前页数大于4时，如果当前页小于总页码书-3，则显示1...n-1,n,n+1...最后一页 -->
				<c:if test="${pageInfo.pageNum > 4 && (pageInfo.pageNum < pageInfo.pages-4) }">
					<li><a
						href="${pageContext.request.contextPath}/user/getAll?page=1">1</a></li>
					<li><a href="javascript:void(0)">...</a></li>
					<c:forEach begin="${pageInfo.pageNum-1 }" end="${pageInfo.pageNum +1  }" step="1"
						var="itemPage">
						<li <c:if test="${pageInfo.pageNum == itemPage}">class="active"</c:if>>
							<a
							href="${pageContext.request.contextPath}/user/getAll?page=${itemPage}">${itemPage}</a>
						</li>
					</c:forEach>
					<li><a href="javascript:void(0)">...</a></li>
					<li><a
						href="${pageContext.request.contextPath}/user/getAll?page=${pageInfo.pages}">${pageInfo.pages}</a></li>
				</c:if>
				<!-- 当前页数大于4时，如果当前页小于总页码书-3，则显示1...n-1,n,n+1...最后一页 end-->

				<!-- 当前页码小于等于n-4  -->
				<c:if test="${pageInfo.pageNum >=  pageInfo.pages-4}">
					<li><a
						href="${pageContext.request.contextPath}/user/getAll?page=1">1</a></li>
					<li><a href="javascript:void(0)">...</a></li>
					<c:forEach begin="${pageInfo.pages-5}" end="${pageInfo.pages}" step="1"
						var="itemPage">
						<li <c:if test="${pageInfo.pageNum == itemPage}">class="active"</c:if>>
							<a
							href="${pageContext.request.contextPath}/user/getAll?page=${itemPage}">${itemPage}</a>
						</li>
					</c:forEach>
				</c:if>
				<!-- 当前页码小于等于n-4 end-->
			</c:if>
			<!-- 页码  end-->


			<li><a
				href="<%=request.getContextPath()%>/user/getAll?page=${pageInfo.lastPage}"
				aria-label="Next"> <span aria-hidden="true">»</span>
			</a></li>
		</ul>
		</nav>


	</center>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/layouts/head.jsp">
	<jsp:param name="title" value="List of Posts" />
</jsp:include>
<body>
	<div class="container">
		<h1>List of posts</h1>

		<table class="table">
			<thead>
				<tr>
					<th>Title</th>
					<th>Content</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="posts">
					<tr>
						<td><s:property value="title" /></td>
						<td><s:property value="content" /></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
</body>
</html>
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
		<h1 class="mt-5">List of posts</h1>
		<div class="container">
			<div class="row">
				<div class="col"></div>
				<div class="col text-end">
					<a href="posts/new" class="btn btn-primary">New Post</a>
				</div>
			</div>
		</div>
		<table class="table">
			<thead>
				<tr>
					<th>Title</th>
					<th>Content</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="posts">
					<tr>
						<td><s:property value="title" /></td>
						<td><s:property value="content" /></td>
						<td>
							<s:url action="posts/show" var="showLink">
								<s:param name="id"><s:property value="id" /></s:param>
							</s:url> <a href="${showLink}" class="btn btn-secondary">Show</a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
</body>
</html>

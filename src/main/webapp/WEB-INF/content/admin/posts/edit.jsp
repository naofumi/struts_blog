<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/layouts/head.jspf">
    <jsp:param name="title" value="Edit Post"/>
</jsp:include>
<body>
<%@ include file="/WEB-INF/layouts/topNav.jspf" %>
<div class="container">
    <h1 class="mt-5"><s:property value="post.title"/>Edit</h1>
    <form action="update" method="post">
        <s:hidden key="post.id"></s:hidden>
        <s:include value="_form.jsp">
        </s:include>
        <input type="submit" value="Update" class="btn btn-warning mt-5"/>
    </form>
</div>
</body>
</html>

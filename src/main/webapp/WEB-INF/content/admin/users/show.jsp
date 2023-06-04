<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/layouts/head.jspf">
    <jsp:param name="title" value="Show User"/>
</jsp:include>
<body>
<%@ include file="/WEB-INF/layouts/topNav.jspf" %>
<div class="container">
    <h1 class="mt-5"><s:property value="user.email"/>: Show</h1>

    <s:url action="edit" var="editLink">
        <s:param name="id"><s:property value="id"/></s:param>
    </s:url> <a href="${editLink}" class="btn btn-secondary">Edit</a>

    <form action="delete" method="POST" class="d-inline">
        <input type="hidden" name="id" value="<s:property value="id" />"/>
        <input type="submit" class="btn btn-danger ms-5" value="Delete">
    </form>
</div>
</body>
</html>

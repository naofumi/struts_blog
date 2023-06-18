<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/layouts/head.jspf">
    <jsp:param name="title" value="${windowTitle}"/>
</jsp:include>
<body>
<%@ include file="/WEB-INF/layouts/topNav.jspf" %>

<div class="container" id="nav-buttons">
    <div class="row mt-3">
        <div class="col text-start">
            <a href="<s:url namespace="/admin/users" action="index" />" class="btn btn-outline-secondary">&lt; Back to list</a>
        </div>
        <div class="col text-end">
            <s:url action="edit" var="editLink">
                <s:param name="id"><s:property value="id"/></s:param>
            </s:url> <a href="${editLink}" class="btn btn-primary">Edit</a>
        </div>
    </div>
</div>

<div class="container">
    <h1 class="mt-5">
        <s:property value="user.email"/>
    </h1>

    <form action="delete" method="POST" class="text-end">
        <input type="hidden" name="id" value="<s:property value="id" />"/>
        <input type="submit" class="btn btn-danger" value="Delete">
    </form>
</div>
</body>
</html>

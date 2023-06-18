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
            <a href="/struts_blog/admin/posts/index" class="btn btn-outline-secondary">&lt; Back to list</a>
            <a href="/struts_blog/admin/posts/chained_back" class="btn btn-outline-secondary">&lt; Back to list with
                chaining</a>
        </div>
        <div class="col text-end">
            <s:url action="posts/edit" var="editLink">
                <s:param name="id"><s:property value="id"/></s:param>
            </s:url> <a href="${editLink}" class="btn btn-primary">Edit</a>
        </div>
    </div>
</div>

<div class="container">
    <h1 class="mt-5"><s:property value="post.title"/></h1>

    <label>Content</label>
    <p class="mt-3 border border-secondary p-3 rounded">
        <s:property value="post.content"/>
    </p>

    <form action="posts/delete" method="POST" class="text-end">
        <input type="hidden" name="id" value="<s:property value="id" />"/>
        <s:token/>
        <input type="submit" class="btn btn-danger" value="Delete">
    </form>
</div>
</body>
</html>

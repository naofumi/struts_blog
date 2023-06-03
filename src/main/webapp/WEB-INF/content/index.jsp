<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/layouts/head.jspf">
    <jsp:param name="title" value="Struts Blog"/>
</jsp:include>
<body>
<%@ include file="/WEB-INF/layouts/topNav.jspf" %>
<div class="container">
    <h1 class="mt-5">Struts Blog</h1>
    <div class="container mb-5">
        <div class="row">
            <div class="col text-start">
                <a href="/struts_blog/users/index" class="btn btn-outline-secondary">Users</a>
            </div>
            <s:if test="user == null">
                <div class="col text-end">
                    <a href="/struts_blog/sessions/new" class="btn btn-primary">Login</a>
                </div>
            </s:if>
            <s:else>
                <div class="col text-end">
                    <s:property value="user"/>
                    <a href="/struts_blog/sessions/delete" class="btn btn-danger">Logout</a>
                </div>
            </s:else>
        </div>
    </div>
    <s:iterator value="posts">
        <h2><s:property value="title"/></h2>
        <div class="container border rounded p-2">
            <s:property value="content"/>
        </div>
        <div class="text-end">
            <s:url action="posts/show" var="showLink">
                <s:param name="id"><s:property value="id"/></s:param>
            </s:url> <a href="${showLink}" class="btn btn-outline-secondary mt-3 mb-5">See more...</a>
        </div>
    </s:iterator>
    <div class="text-end">
        <a href="posts" class="btn btn-outline-secondary">View all posts</a>
    </div>
</div>
</body>
</html>

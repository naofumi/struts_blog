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
        <input type="hidden" name="post.id" value="<s:property value="post.id" />"/>
        <div class="form-group mt-3">
            <label for="post_title">Title</label>
            <input id="post_title"
                   type="text"
                   value="<s:property value="post.title" />"
                   name="post.title"
                   class="form-control"
            />
            <div class="text-danger"><s:fielderror><s:param value="%{'post.title'}"/></s:fielderror></div>
        </div>
        <div class="form-group mt-3">
            <label for="post_content">Content</label>
            <textarea id="post_content"
                      name="post.content"
                      class="form-control"><s:property value="post.content"/></textarea>
            <div class="text-danger"><s:fielderror><s:param value="%{'post.content'}"/></s:fielderror></div>
        </div>
        <input type="submit" value="Submit" class="btn btn-warning mt-5"/>
    </form>
</div>
</body>
</html>

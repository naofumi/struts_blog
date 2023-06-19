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
            <a href="<s:url namespace="/admin/posts" action="index"/>" class="btn btn-outline-secondary">&lt; Back to
                index</a>
        </div>
        <div class="col"></div>
    </div>
</div>

<div class="container">
    <h1 class="mt-5">New Post</h1>
    <form action="create" method="post">
        <s:include value="_form.jsp">
        </s:include>
        <input type="submit" value="Create" class="btn btn-warning mt-5"/>
        <input type="submit" formaction="chained_create" value="Submit with Chaining"
               class="btn btn-outline-warning mt-5"/>
        <input type="submit" formaction="no_redirect_create" value="Submit without Redirect"
               class="btn btn-outline-warning mt-5"/>
    </form>
</div>
</body>
</html>

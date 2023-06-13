<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/layouts/head.jspf">
    <jsp:param name="title" value="Struts Blog"/>
</jsp:include>
<body class="background_image">
<%@ include file="/WEB-INF/layouts/topNav.jspf" %>
<div class="container">
    <h1 class="mt-5">Struts Blog</h1>
    <div class="mt-5">
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
    </div>
</div>
</body>
</html>

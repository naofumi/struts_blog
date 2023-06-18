<%--@elvariable id="new" type=""--%>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="struts_blog.services.HTMLSanitizer" %>
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
<div class="container">
    <h1 class="mt-5"><s:property value="post.title"/>: Show</h1>

    <p class="mt-3 border border-secondary p-3 rounded">
<%--         This is sanitization using an HTML sanitizer. It is safe against XSS and can also display HTML.
             You can select what HTML tags will allow formatting. --%>
        ${HTMLSanitizer.sanitize(post.content)}
<%--         This does not do any sanitization and is vulnerable to XSS --%>
<%--        ${post.content} --%>
<%--         This does html entity escaping. It is safe against HTML but all HTML is neutralized and HTML loses all formatting ability --%>
<%--        <s:property value="post.content" />--%>
<%--         This does not do any sanitization and is vulnerable to XSS --%>
<%--        <s:property value="post.content" escapeHtml="false"/>--%>
    </p>
</div>
</body>
</html>

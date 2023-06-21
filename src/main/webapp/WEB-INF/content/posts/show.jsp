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

    <div>
        <s:url var="sanitizerUrl">
            <s:param name="id" value="%{post.id}"/>
            <s:param name="xssProtection" value="%{'sanitizer'}"/>
        </s:url>
        <a href="${sanitizerUrl}" class="btn btn-outline-primary">Sanitizer</a>
        <s:url var="rawUrl">
            <s:param name="id" value="%{post.id}"/>
            <s:param name="xssProtection" value="%{'raw'}"/>
        </s:url>
        <a href="${rawUrl}" class="btn btn-outline-primary">Raw</a>
        <s:url var="escapedUrl">
            <s:param name="id" value="%{post.id}"/>
            <s:param name="xssProtection" value="%{'escaped'}"/>
        </s:url>
        <a href="${escapedUrl}" class="btn btn-outline-primary">Escaped</a>
        <s:url var="nonEscapedUrl">
            <s:param name="id" value="%{post.id}"/>
            <s:param name="xssProtection" value="%{'nonEscaped'}"/>
        </s:url>
        <a href="${nonEscapedUrl}" class="btn btn-outline-primary">Non Escaped</a>
    </div>
    <div class="mt-3">
        <s:if test="%{xssProtection == null || xssProtection == 'sanitizer'}">
            <div class="mt-3 border border-secondary p-3 rounded">
                    <%-- This is sanitization using an HTML sanitizer. It is safe against XSS and can also display HTML.
                                 You can select what HTML tags will allow formatting. --%>
                <code>
                    <pre>&dollar;{HTMLSanitizer.sanitize(post.content)}</pre>
                </code>
                    ${HTMLSanitizer.sanitize(post.content)}
            </div>
            <div class="mt-3 border border-secondary p-3 rounded">
                    <%-- This is sanitization using an HTML sanitizer. It is safe against XSS and can also display HTML.
                                 You can select what HTML tags will allow formatting. --%>
                <code>
                    <pre>&dollar;{HTMLSanitizer.sanitize(post.xssEscapedContent)}</pre>
                </code>
                    ${HTMLSanitizer.sanitize(post.xssEscapedContent)}
            </div>
        </s:if>
        <s:elseif test="%{xssProtection == 'raw'}">
            <div class="mt-3 border border-secondary p-3 rounded">
                    <%-- This does not do any sanitization and is vulnerable to XSS --%>
                <code>
                    <pre>&dollar;{post.content}</pre>
                </code>
                    ${post.content}
            </div>
            <div class="mt-3 border border-secondary p-3 rounded">
                    <%-- This does not do any sanitization and is vulnerable to XSS --%>
                <code>
                    <pre>&dollar;{post.xssEscapedContent}</pre>
                </code>
                    ${post.xssEscapedContent}
            </div>
        </s:elseif>
        <s:elseif test="%{xssProtection == 'escaped'}">
            <div class="mt-3 border border-secondary p-3 rounded">

                    <%--         This does html entity escaping. It is safe against HTML but all HTML is neutralized and HTML loses all formatting ability --%>
                <code>
                    <pre>&lt;s:property value="post.content"/&gt;</pre>
                </code>
                <s:property value="post.content"/>
            </div>
            <div class="mt-3 border border-secondary p-3 rounded">

                    <%--         This does html entity escaping. It is safe against HTML but all HTML is neutralized and HTML loses all formatting ability --%>
                <code>
                    <pre>&lt;s:property value="post.xssEscapedContent"/&gt;</pre>
                </code>
                <s:property value="post.xssEscapedContent"/>
            </div>
        </s:elseif>
        <s:elseif test="%{xssProtection == 'nonEscaped'}">
            <div class="mt-3 border border-secondary p-3 rounded">

                    <%--         This does not do any sanitization and is vulnerable to XSS --%>
                <code>
                    <pre>&lt;s:property value="post.content" escapeHtml="false"/&gt;</pre>
                </code>
                <s:property value="post.content" escapeHtml="false"/>
            </div>
            <div class="mt-3 border border-secondary p-3 rounded">

                    <%--         This does not do any sanitization and is vulnerable to XSS --%>
                <code>
                    <pre>&lt;s:property value="post.xssEscapedContent" escapeHtml="false"/&gt;</pre>
                </code>
                <s:property value="post.xssEscapedContent" escapeHtml="false"/>
            </div>
        </s:elseif>
    </div>
</div>
</body>
</html>

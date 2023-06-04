<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/layouts/head.jspf">
    <jsp:param name="title" value="List of Posts"/>
</jsp:include>
<body>
<%@ include file="/WEB-INF/layouts/topNav.jspf" %>
<div class="container">
    <h1 class="mt-5">List of posts</h1>
    <h2 class="mt-3">Visits: <s:property value="visitsCount"/></h2>
    <div class="container">
        <div class="row">
            <div class="col"></div>
            <div class="col text-end">
                <a href="new" class="btn btn-primary">New Post</a>
            </div>
        </div>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th>Title</th>
            <th>Content</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="posts">
            <tr>
                <td><s:property value="title"/></td>
                <td><s:property value="content"/></td>
                <td>
                    <s:url action="posts/show" var="showLink">
                        <s:param name="id"><s:property value="id"/></s:param>
                    </s:url> <a href="${showLink}" class="btn btn-secondary">Show</a>
                </td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <s:if test="paginationLinks.previous != null">
                <li class="page-item"><a class="page-link bg-secondary text-light"
                                         href="<s:property value="paginationLinks.previous" />">Previous</a></li>
            </s:if>
            <s:iterator value="paginationLinks.middle">
                <li class="page-item"><a class="page-link" href="<s:property value="value"/>"><s:property
                        value="key"/></a></li>
            </s:iterator>
            <s:if test="paginationLinks.next != null">
                <li class="page-item"><a class="page-link bg-secondary text-light"
                                         href="<s:property value="paginationLinks.next" />">Next</a></li>
            </s:if>
        </ul>
    </nav>
</div>
</body>
</html>

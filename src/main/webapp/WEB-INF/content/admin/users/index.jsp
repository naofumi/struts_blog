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
        </div>
        <div class="col text-end">
            <a href="<s:url namespace="/admin/users" action="new"/>" class="btn btn-primary">New</a>
        </div>
    </div>
</div>

<div class="container">
    <h1 class="mb-5">List of Users</h1>
    <table class="table">
        <thead>
        <tr>
            <th>Title</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="users">
            <tr>
                <td><s:property value="email"/></td>
                <td>
                    <s:url action="show" var="showLink">
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

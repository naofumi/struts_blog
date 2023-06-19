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
    <h1 class="mt-5">List of guests</h1>

    <div class="text-end mb-5"><a href="<s:url namespace="/guests" action="nicknameForm" />" class="btn btn-success">Please
        post on our guestbook!</a></div>
    <table class="table">
        <thead>
        <th>Nickname</th>
        <th>Country</th>
        <th>Twitter</th>
        </thead>
        <tbody>
        <s:iterator value="guests">
            <tr>
                <td><s:property value="nickname"/></td>
                <td><s:property value="country"/></td>
                <td><s:property value="twitter"/></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/layouts/head.jspf">
    <jsp:param name="title" value="New Post"/>
</jsp:include>
<body>
<%@ include file="/WEB-INF/layouts/topNav.jspf" %>
<div class="container">
    <h1 class="mt-5">New User</h1>
    <form action="create" method="post">
        <s:token/>
        <div class="form-group mt-3">
            <label for="user_email">Email</label>
            <input id="user_email"
                   type="text"
                   value="<s:property value="user.email" />"
                   name="user.email"
                   class="form-control"
            />
            <div class="text-danger"><s:fielderror><s:param value="%{'user.email'}"/></s:fielderror></div>
        </div>
        <div class="form-group mt-3">
            <label for="user_password">Password</label>
            <input id="user_password"
                   type="text"
                   value="<s:property value="user.password" />"
                   name="user.password"
                   class="form-control"
            />
            <div class="text-danger"><s:fielderror><s:param value="%{'user.password'}"/></s:fielderror></div>
        </div>
        <input type="submit" value="Submit" class="btn btn-warning mt-5"/>
    </form>
</div>
</body>
</html>

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
<div class="container w-50">
    <h1 class="mt-5">Login</h1>
    <form action="create" method="post">
        <div class="form-group mt-3">
            <label for="login_email">email</label>
            <input id="login_email"
                   type="text"
                   value="<s:property value="login.email" />"
                   name="login.email"
                   class="form-control"
            />
            <div class="text-danger"><s:fielderror><s:param value="%{'login.email'}"/></s:fielderror></div>
            <label for="login_password">password</label>
            <input id="login_password"
                   type="text"
                   value="<s:property value="login.password" />"
                   name="login.password"
                   class="form-control"
            />
            <div class="text-danger"><s:fielderror><s:param value="%{'login.password'}"/></s:fielderror></div>
        </div>
        <input type="submit" value="Login" class="btn btn-warning mt-5"/>
    </form>
</div>
</body>
</html>

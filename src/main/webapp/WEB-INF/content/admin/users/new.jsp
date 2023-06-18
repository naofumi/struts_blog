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
    <h1 class="mt-5">New User</h1>
    <form action="create" method="post">
        <s:token/>
        <div class="form-group mt-3">
            <s:textfield key="user.email"
                         label="Email"
                         labelSeparator=""
                         cssClass="form-control"
                         errorPosition="bottom"
                         cssErrorClass="border border-danger">
            </s:textfield>
        </div>
        <div class="form-group mt-3">
            <s:textfield type="password"
                         key="user.password"
                         label="Password"
                         labelSeparator=""
                         cssClass="form-control"
                         errorPosition="bottom"
                         cssErrorClass="border border-danger">
            </s:textfield>
        </div>
        <div class="form-group mt-3">
            <s:textfield type="password"
                         key="user.passwordConfirm"
                         label="Password Confirmation"
                         labelSeparator=""
                         cssClass="form-control"
                         errorPosition="bottom"
                         cssErrorClass="border border-danger">
            </s:textfield>
        </div>
        <input type="submit" value="Submit" class="btn btn-warning mt-5"/>
    </form>
</div>
</body>
</html>

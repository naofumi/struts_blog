<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/layouts/head.jspf">
    <jsp:param name="title" value="${windowTitle}"/>
</jsp:include>
<body>
<%@ include file="/WEB-INF/layouts/topNav.jspf" %>
<div class="container w-50">
    <h1 class="mt-5 mb-5">Login</h1>
    <s:if test="flash != null">
        <div class="alert alert-warning"><s:property value="flash"/></div>
    </s:if>
    <form action="create" method="post">
        <div class="form-group mt-3">
            <s:textfield key="login.email"
                         label="Email"
                         labelSeparator=""
                         cssClass="form-control"
                         errorPosition="bottom"
                         cssErrorClass="border border-danger">

            </s:textfield>
        </div>
        <div class="form-group mt-3">
            <s:textfield type="password"
                         key="login.password"
                         label="Password"
                         labelSeparator=""
                         cssClass="form-control"
                         errorPosition="bottom"
                         cssErrorClass="border border-danger">

            </s:textfield>
        </div>
        <input type="submit" value="Login" class="btn btn-warning mt-5"/>
    </form>
</div>
</body>
</html>

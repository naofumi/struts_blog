<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                 key="passwordConfirm"
                 label="Password Confirmation"
                 labelSeparator=""
                 cssClass="form-control"
                 errorPosition="bottom"
                 cssErrorClass="border border-danger">
    </s:textfield>
</div>
<div class="form-group mt-3">
    <s:hidden name="__checkbox_user.admin" value="false" />
    <s:checkbox key="user.admin" />
</div>

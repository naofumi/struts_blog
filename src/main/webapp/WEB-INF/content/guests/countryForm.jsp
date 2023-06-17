<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/layouts/head.jspf">
    <jsp:param name="title" value="List Guests"/>
</jsp:include>
<body>
<%@ include file="/WEB-INF/layouts/topNav.jspf" %>
<div class="container">
    <h1 class="mt-5">Enter your country</h1>

    <form action="createCountry" method="post">
        <div class="form-group mt-3">
            <label for="guestForm_nickname">Nickname</label>
            <input id="guestForm_nickname"
                   type="text"
                   value="<s:property value="guestForm.nickname" />"
                   name="guestForm.nickname"
                   class="form-control"
                   disabled
            />
            <div class="text-danger"><s:fielderror><s:param value="%{'guestForm.country'}"/></s:fielderror></div>
        </div>
        <div class="form-group mt-3">
            <label for="guestForm_country">Country</label>
            <select id="guestForm_country" name="guestForm.country" class="form-select">
                <s:iterator value="countries" var="country">
                    <option value="<s:property value="name" />"
                            <s:if test="guestForm.country == #country.name">selected</s:if>>
                        <s:property value="name" />
                    </option>
                </s:iterator>
            </select>
            <div class="text-danger"><s:fielderror><s:param value="%{'guestForm.country'}"/></s:fielderror></div>
        </div>

        <input type="submit" value="Submit Country" class="btn btn-warning mt-5"/>
    </form>
</div>
</body>
</html>

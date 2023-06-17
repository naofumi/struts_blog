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
    <h1 class="mt-5">Enter your Twitter name</h1>

    <form action="createTwitter" method="post">
        <div class="form-group mt-3">
            <label for="guestForm_nickname">Nickname</label>
            <input id="guestForm_nickname"
                   type="text"
                   value="<s:property value="guestForm.nickname" />"
                   name="guestForm.nickname"
                   class="form-control"
                   disabled
            />
            <div class="text-danger"><s:fielderror><s:param value="%{'guestForm.nickname'}"/></s:fielderror></div>
        </div>
        <div class="form-group mt-3">
            <label for="guestForm_country">Country</label>
            <input id="guestForm_country"
                   type="text"
                   value="<s:property value="guestForm.country" />"
                   name="guestForm.country"
                   class="form-control"
                   disabled
            />
            <div class="text-danger"><s:fielderror><s:param value="%{'guestForm.country'}"/></s:fielderror></div>
        </div>
        <div class="form-group mt-3">
            <label for="guestForm_twitter">Twitter</label>
            <input id="guestForm_twitter"
                   type="text"
                   value="<s:property value="guestForm.twitter" />"
                   name="guestForm.twitter"
                   class="form-control"
            />
            <div class="text-danger"><s:fielderror><s:param value="%{'guestForm.twitter'}"/></s:fielderror></div>
        </div>

        <input type="submit" value="Submit Twitter" class="btn btn-warning mt-5"/>
    </form>
</div>
</body>
</html>

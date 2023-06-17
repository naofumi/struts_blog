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
    <h1 class="mt-5">Confirm your information</h1>

    <form action="createGuest" method="post">
        <div class="form-group mt-3">
            <label for="guestForm_nickname">Nickname</label>
            <input id="guestForm_nickname"
                   type="text"
                   value="<s:property value="guestForm.nickname" />"
                   name="guestForm.nickname"
                   class="form-control"
                   disabled
            />
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
        </div>
        <div class="form-group mt-3">
            <label for="guestForm_twitter">Twitter</label>
            <input id="guestForm_twitter"
                   type="text"
                   value="<s:property value="guestForm.twitter" />"
                   name="guestForm.twitter"
                   class="form-control"
                   disabled
            />
        </div>
        <a href="<s:url namespace="/guests" action="nicknameForm" />" class="btn btn-info mt-5">Go back to Nickname</a>
        <a href="<s:url namespace="/guests" action="countryForm" />" class="btn btn-info mt-5 ms-1">Go back to Country</a>
        <a href="<s:url namespace="/guests" action="twitterForm" />" class="btn btn-info mt-5 ms-1">Go back to Twitter</a>
        <input type="submit" value="Create Guest" class="btn btn-danger mt-5 ms-5"/>
    </form>
</div>
</body>
</html>

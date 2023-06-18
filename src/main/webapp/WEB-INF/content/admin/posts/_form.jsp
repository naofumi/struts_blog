<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="form-group mt-3">
  <s:textfield key="post.title"
               label="Title"
               labelSeparator=""
               cssClass="form-control"
               errorPosition="bottom"
               cssErrorClass="border border-danger">

  </s:textfield>
</div>
<div class="form-group mt-3">
  <s:textarea key="post.content"
              label="Content"
              labelSeparator=""
              cssClass="form-control"
              errorPosition="bottom"
              cssErrorClass="border border-danger">

  </s:textarea>
</div>

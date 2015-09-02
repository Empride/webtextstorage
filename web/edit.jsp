<%@ page import="model.TextDataDAO" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 01.09.2015
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Web text storage</title>
</head>
<body>
<h1 style="text-align: center">Web text storage</h1>

<h2>Edit text:</h2>

<FORM name="form1" method="post" action="main">
  <label>
    <textarea name="textField" rows="10" style="width:90%; padding-bottom: 10%"
              autocomplete="off"><%=new TextDataDAO().getTextNode((String) session.getAttribute("TextIdToEdit"))%></textarea>
  </label>
  <BR/>
  <INPUT type="submit" style="width: 100px; height: 30px" name="save" value="Save"/>
  <INPUT type="submit" style="width: 100px; height: 30px" name="cancel" value="Cancel"/>
</FORM>
</body>
</html>

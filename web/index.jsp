<jsp:useBean id="authText" scope="request" class="java.lang.String"/>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 31.08.2015
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Web text storage login</title>
</head>
<body>
<h1 style="text-align: center">Web text storage</h1>

<div style="text-align: center;">
    <form action="">
        Username:<input type="text" name="uname">
        Password: <input type="password" name="pwd">
        <input type="submit" value="Submit">
    </form>
    ${authText}
    <BR/>
 <%

    if (request.getParameter("username") == null || request.getParameter("username").equals("")) {
        out.println("<html><font color=red>Enter username</font></html><BR/>");
    }
    if (request.getParameter("password") == null || request.getParameter("password").equals("")) {
        out.println("<html><font color=red>Enter password</font></html>");
    }

%>
</div>
</body>
</html>

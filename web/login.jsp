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
    <form action="" method="POST">
        Username:<label>
        <input type="text" name="uname">
    </label>
        Password: <label>
        <input type="password" name="pwd">
    </label>
        <input type="submit" name="login" value="Log in"> <input type="submit" name="register" value="Fast register">
    </form>
    ${authText}
    <BR/>
</div>
</body>
</html>

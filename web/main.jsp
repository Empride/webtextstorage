<%!
    private Integer id;
%>

<%@ page import="model.TextDataDAO" %>
<%@ page import="java.util.Map" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("currentSessionUser") == null) {
        response.sendRedirect("");
        return;
    }
%>
<html>
<head>
    <title>Web text storage</title>
</head>
<body>
<h1 style="text-align: center">Web text storage</h1>

<h2>Enter text to store:</h2>

<FORM name="form1" method="post" action="main">
    <label>
        <textarea name="textField" rows="10" style="width:100%; padding-bottom: 10%" autocomplete="off"></textarea>
    </label>
    <BR/>
    <INPUT type="submit" style="width: 100px; height: 30px" name="submit" value="Sumbit"/>
    <INPUT type="submit" style="width: 100px; height: 30px; float: right" name="logout" value="Logout"/>
</FORM>
<FORM name="form2" method="post" action="main">
    <table border="1px" cellpadding="10px">
        <tr>
            <th style="width: 60%; word-break: break-all">Text</th>
            <th style="width: 100px;max-width: 10%; word-break: break-all">Edit</th>
            <th style="width: 100px;max-width: 10%; word-break: break-all">Delete</th>
            <%

                String username = (String) session.getAttribute("currentSessionUser");
                for (Map.Entry<Integer, String> mapEntry : new TextDataDAO().getTextData(username).entrySet()) {
                    out.println("<tr>");
                    out.println("<td>" + mapEntry.getValue() + "</td>");
                    out.println("<td>");
                    id = mapEntry.getKey();
            %>
            <INPUT type="submit" style="width: 100px; height: 30px" value="Edit" name="<%=id%>"/>
            <% out.println("</td>");
                out.println("<td>");%>
            <INPUT type="submit" style="width: 100px; height: 30px" value="Delete" name="<%=id%>"/>
            <% out.println("</td>");
                out.println("</tr>");
            }
            %>
        </tr>
    </table>
</FORM>
</body>
</html>

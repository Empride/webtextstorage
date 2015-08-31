<%!
    private Integer id;
%>
<jsp:useBean id="modelData" scope="page" class="java.util.TreeMap"/>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Map" %>
<%@ page import="model.Pair" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Web text storage</title>
</head>
<body>
<h1 style="text-align: center">Web text storage</h1>

<h2>Enter text to store:</h2>

<FORM name="form1" method="post" action="main">
    <textarea name="textField" rows="10" style="width:90%; padding-bottom: 10%" autocomplete="off"></textarea>
    <BR/>
    <INPUT type="submit" style="width: 100px; height: 30px" name="submit" value="Sumbit"/>
</FORM>
<FORM name="form2" method="post" action="edit">
    <table border="1px" cellpadding="10px">
        <tr>
            <th style="width: 75px; max-width: 10%; word-break: break-all">Date</th>
            <th style="width: 60%; word-break: break-all">Text</th>
            <th style="width: 100px;max-width: 10%; word-break: break-all">Edit</th>
            <th style="width: 100px;max-width: 10%; word-break: break-all">Delete</th>
            <% for (Object mapEntry : modelData.entrySet()) {
                Map.Entry<Integer, Pair<Date, String>> entry = (Map.Entry<Integer, Pair<Date, String>>) mapEntry;
                out.println("<tr>");
                out.println("<td>" + entry.getValue().getFirstAttr() + "</td>");
                out.println("<td>" + entry.getValue().getSecondAttr() + "</td>");
                out.println("<td>");
                id = entry.getKey();%>
            <INPUT type="submit" style="width: 100px; height: 30px" value="Edit"  name="<%=id%>" />
            <% out.println("</td>");
                out.println("<td>");%>
            <INPUT type="submit" style="width: 100px; height: 30px" value="Delete" name="<%=id%>" />
            <% out.println("</td>");
                out.println("</tr>");
            }
            %>
        </tr>
    </table>
</FORM>
</body>
</html>

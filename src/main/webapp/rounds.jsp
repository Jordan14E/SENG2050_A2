<%@ page import="pkg.gameBean" %><%--
  Created by IntelliJ IDEA.
  User: jeade
  Date: 25/03/2022
  Time: 10:28 am
  To change this template use File | Settings | File Templates.
--%>
<%--<jsp:useBean id="sessionBean" class="pkg.sessionBean" scope="session"/> --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rounds</title>
</head>
<body>
    <header><h1>Choose your numbers </h1></header>

    <form action="game" method="post">
        <% int round = (int) request.getAttribute("round");
            if(round >= 1){%>
                <label for="1">Select 1st Number: </label>
                <input type="number" min="1" max="11" name="1" id="1">
                <br/>
           <%}%>
            <%if(round >= 2){%>
                <label for="2">Select 2nd Number: </label>
                <input type="number" min="1" max="11" name="2" id="2">
                <br/>
            <%}%>
            <%if(round >= 3){%>
                <label for="3">Select 3rd Number: </label>
                <input type="number" min="1" max="11" name="3" id="3">
                <br/>
            <%}%>
            <%if(round >= 4){%>
                <label for="4">Select 4th Number: </label>
                <input type="number" min="1" max="11" name="4" id="4">
                <br/>
            <%}%>
        <input type="submit" value="Submit">
    </form>
</body>
</html>

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
    <script type="text/javascript" src="script.js"></script>
    <script type="text/javascript">
        function preventBack() {
            window.history.forward();
        }
        setTimeout("preventBack()", 0);
        window.onunload = function () { null };
    </script>
    <link rel="stylesheet" href="style.css"/>
</head>
<body>
    <header><h1>Choose your numbers </h1></header>
    <div class="form">
        <% int round = (int) request.getAttribute("round");%>
    <form action="game" name="roundForm" method="post" onsubmit="return roundValidation(<%=round%>)">

            <%if(round >= 1){%>
                <label for="first">Select 1st Number: </label>
                <input type="number" min="1" max="11" name="1" id="first">
                <br/>
           <%}%>
            <%if(round >= 2){%>
                <label for="second">Select 2nd Number: </label>
                <input type="number" min="1" max="11" name="2" id="second">
                <br/>
            <%}%>
            <%if(round >= 3){%>
                <label for="third">Select 3rd Number: </label>
                <input type="number" min="1" max="11" name="3" id="third">
                <br/>
            <%}%>
            <%if(round >= 4){%>
                <label for="fourth">Select 4th Number: </label>
                <input type="number" min="1" max="11" name="4" id="fourth">
                <br/>
            <%}%>
        <input type="submit" value="Submit">
        <input type="hidden" value="2" name="pageID">
    </form>
    </div>
    <%if(request.getAttribute("error") != null){%>
        <p class="error"><%=(int)request.getAttribute("revealed")%> has been selected in a previous round. Please choose a new number.</p>
    <%}%>

</body>
</html>

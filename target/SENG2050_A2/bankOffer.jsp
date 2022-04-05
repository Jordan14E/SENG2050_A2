<%--
  Created by IntelliJ IDEA.
  User: jeade
  Date: 25/03/2022
  Time: 10:28 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bank Offer</title>
</head>
<body>
    <header><h1>!!!Bank Offer!!!</h1></header>

    <%if(request.getAttribute("type").equals("win")){%>
        <h2>You Won! Here is your prize: </h2>
        <br/>
        <p><%= request.getAttribute("offer")%></p>
    <form action="game" method="post">
        <button type="submit" value="accept">Accept</button>
        <input type="hidden" value="3" name="pageID">
    </form>
    <%} else{%>
        <h2>The bank is offering:</h2>
        <br/>
        <p><%= request.getAttribute("offer")%></p>
        <form action="game" method="post">
            <button type="submit" value="accept">Accept</button>
            <button type="submit" value="continue">Continue</button>
            <input type="hidden" value="3" name="pageID">
        </form>
    <%}%>

</body>
</html>

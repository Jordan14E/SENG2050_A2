<%@ page import="java.util.Arrays" %><%--
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
    <script type="text/javascript" src="script.js"></script>
    <script type="text/javascript">
        function preventBack() {
            window.history.forward();
        }
        setTimeout("preventBack()", 0);
        window.onunload = function () { null };
    </script>
</head>
<body>
    <header><h1>!!!Bank Offer!!!</h1></header>

    <%if(request.getAttribute("type").equals("win")){%>
        <h2>You Won! Here is your prize: </h2>
        <br/>
        <p><%= request.getAttribute("offer")%></p>
    <form action="game" method="post">
        <button type="submit" value="accept" name="submit">Accept</button>
        <input type="hidden" value="3" name="pageID">
    </form>
    <%} else if(request.getAttribute("type").equals("loss")){%>
    <h2>You lost!</h2>
    <p>Unfortunately you revealed the secret number. Better luck next time!<br/>
    The number was <%=(int)request.getAttribute("secret")%></p><br/>
    <form action="game" method="post">
        <button type="submit" value="accept" name="submit">Exit</button>
        <input type="hidden" value="3" name="pageID">
    <%} else{%>
        <h2>Remaining numbers:</h2>
    <p>
        <% int[] nums = (int[]) request.getAttribute("nums");
        for(int i=0; i< nums.length; i++){ %>
            <%= nums[i]%>
        <%}
        %>
    </p>

        <h2>The bank is offering:</h2>
        <br/>
        <p><%= request.getAttribute("offer")%></p>
        <form action="game" method="post">
            <button type="submit" value="accept" name="submit">Accept</button>
            <button type="submit" value="continue" name="submit">Continue</button>
            <input type="hidden" value="3" name="pageID">
        </form>
    <%}%>

</body>
</html>

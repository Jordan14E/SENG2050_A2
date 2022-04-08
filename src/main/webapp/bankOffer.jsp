<%@ page import="java.util.Arrays" %><%--
  Created by IntelliJ IDEA.
  User: Jordan Eade
  Date: 25/03/2022
  Time: 10:28 am
  The bankOffer page is used to display the amount the bank is offering, tell the player they have lost when they reveal the
  secret number or tell the player they have won when they reach the end of round 4 without revealing the secret number
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Bank Offer</title>
        <script type="text/javascript" src="script.js"></script>
        <script type="text/javascript">     <%--This javascript function detects when the page has been accessed using the back
        button and immediately pushes the page back forward. This prevents the submission of any data impacting the
        application or saved data--%>
            function preventBack() {
                window.history.forward();
            }
            setTimeout("preventBack()", 0);
            window.onunload = function () { null };
        </script>
        <link rel="stylesheet" href="style.css"/>
    </head>
    <body>
        <header><h1>!!!Bank Offer!!!</h1></header>
        <%--Logic to display win content if passed by controller--%>
        <%if(request.getAttribute("type").equals("win")){%>
            <h2>You Won! Here is your prize: </h2>
            <br/>
            <p><%= request.getAttribute("offer")%></p>
        <form action="game" method="post">
            <button type="submit" value="accept" name="submit">Accept</button>
            <input type="hidden" value="3" name="pageID">
        </form>
        <%--Logic to display loss page if passed by controller--%>
        <%} else if(request.getAttribute("type").equals("loss")){%>
        <h2>You lost!</h2>
        <p>Unfortunately you revealed the secret number. Better luck next time!<br/>
        The number was <%=(int)request.getAttribute("secret")%></p><br/>
        <form action="game" method="post">
            <button type="submit" value="accept" name="submit">Exit</button>
            <input type="hidden" value="3" name="pageID">
            <%--Logic to display regular bankOffer page if not a win or loss--%>
        <%} else{%>
            <h2>Remaining numbers:</h2>
            <p>
                <%--Logic to output the list of numbers not yet revealed--%>
                <% int[] nums = (int[]) request.getAttribute("nums");
                for(int i=0; i< nums.length; i++){ %>
                    <%= nums[i]%>
                <%}
                %>
            </p>

            <h2>The bank is offering:</h2>
            <br/>
            <%--displaying the bank offer amount--%>
            <p><%= request.getAttribute("offer")%></p>
            <form action="game" method="post">
                <button type="submit" value="accept" name="submit">Accept</button>
                <button type="submit" value="continue" name="submit">Continue</button>
                <input type="hidden" value="3" name="pageID">
            </form>
        <%}%>

    </body>
</html>

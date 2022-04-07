<%--
  Created by IntelliJ IDEA.
  User: Jordan Eade
  Date: 25/03/2022
  Time: 10:28 am
  The save page is used to ask the user if they want to save their game or continue. A form with two buttons achieve this
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Save Game</title>
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
    <header><h1>Would you like to save and exit your game?</h1></header>
    <form action="game" method="post">
        <button type="submit" value="yes" name="submit">Save & Exit</button>
        <button type="submit" value="no" name="submit">Continue</button>
        <input type="hidden" value="4" name="pageID">
    </form>
</body>
</html>

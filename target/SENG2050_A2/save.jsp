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
    <title>Save Game</title>
    <script type="text/javascript">
        function preventBack() {
            window.history.forward();
        }
        setTimeout("preventBack()", 0);
        window.onunload = function () { null };
    </script>
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

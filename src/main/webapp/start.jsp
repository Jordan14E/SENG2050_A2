<%--
  Created by IntelliJ IDEA.
  User: Jordan Eade
  Date: 25/03/2022
  Time: 10:28 am
  The start page is used as the entry page for the application. The start page gives the ability to create a new game or
  load an existing game. The page differentiates between the two with the value passed by the button pressed and the field entered.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Secret Game</title>
        <script type="text/javascript"> <%--This javascript function detects when the page has been accessed using the back
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
        <header>
            <h1>The Secret Game</h1>
        </header>
        <div class="form">
            <form action="game" method="post">
                <label for="newUser">New Username: </label>
                <input type="text" name="newUser" id="newUser">
                <br/>
                <button type="submit" value="New" name="submit">New Game</button>
                <br/>
                <label for="oldUser">Existing Username: </label>
                <input type="text" name="oldUser" id="oldUser">
                <br/>
                <button type="submit" value="Existing" name="submit">Load Existing</button>
                <input type="hidden" value="1" name="pageID">
            </form>
        </div>
        <%if(request.getParameter("error") != null){%>  <%--logic to display the error message if applicable--%>
            <p class="error">The entered username could not be found.</p>
        <%}%>
    </body>
</html>

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
    <title>Secret Game</title>
    <script type="text/javascript">
        function preventBack() {
            window.history.forward();
        }
        setTimeout("preventBack()", 0);
        window.onunload = function () { null };
    </script>
</head>
<body>
    <header>
        <h1>The Secret Game</h1>
    </header>
    <div>
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
    <%if(request.getParameter("error") != null){%>
        <p class="error">The entered username could not be found.</p>
    <%}%>
</body>
</html>

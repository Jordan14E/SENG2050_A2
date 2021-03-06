<%@ page import="pkg.gameBean" %><%--
  Created by IntelliJ IDEA.
  User: Jordan Eade
  Date: 25/03/2022
  Time: 10:28 am
  The rounds page is used to take the numbers the user wants to input for their guesses and send them to the controller object
  through a form. The page uses javascript validation to ensure there are no duplicates entered in the one submission and uses
  the attributes of the number input type to ensure numbers are in the correct range.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rounds</title>
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
    <header><h1>Choose your numbers </h1></header>
    <div class="form">
        <% int round = (int) request.getAttribute("round");%>   <%--creating the local round variable to decide the
        number of inputs--%>
    <form action="game" name="roundForm" method="post" onsubmit="return roundValidation(<%=round%>)">

            <%--Logic for round 1 display--%>
            <%if(round >= 1){%>
                <label for="first">Select 1st Number: </label>
                <input type="number" min="1" max="11" name="1" id="first">
                <br/>
           <%}%>
            <%--Logic for round 2 display--%>
            <%if(round >= 2){%>
                <label for="second">Select 2nd Number: </label>
                <input type="number" min="1" max="11" name="2" id="second">
                <br/>
            <%}%>
                <%--Logic for round 3 display--%>
            <%if(round >= 3){%>
                <label for="third">Select 3rd Number: </label>
                <input type="number" min="1" max="11" name="3" id="third">
                <br/>
            <%}%>
                <%--Logic for round 4 display--%>
            <%if(round >= 4){%>
                <label for="fourth">Select 4th Number: </label>
                <input type="number" min="1" max="11" name="4" id="fourth">
                <br/>
            <%}%>
        <input type="submit" value="Submit">
        <input type="hidden" value="2" name="pageID">
    </form>
    </div>
    <%--Logic to display error message if applicable--%>
    <%if(request.getAttribute("error") != null){%>
        <p class="error"><%=(int)request.getAttribute("revealed")%> has been selected in a previous round. Please choose a new number.</p>
    <%}%>

</body>
</html>

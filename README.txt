Objects

    Controller.java

        The Controller object is a servlet. The servlet is used as the main controller for the entire application. It takes
        requests from the pages, applies the information gathered to the java beans and passes information back to the correct page
        to display.

    gameBean.java

        The gameBean object is a java bean. The gameBean is used to store all information relevant to a game. This includes;
        An array of numbers and a way to determine which have been revealed, the secret number, the round number, and the username.

    SessionBean.java

        The sessionBean is a javaBean. The sessionBean is used to store all gameBeans that have been created. The sessionBean
        uses a HashMap to store each game bean, with the key being the username entered when creating the game.

    bankOffer.jsp

        The bankOffer page is used to display the amount the bank is offering, tell the player they have lost when they reveal the
        secret number or tell the player they have won when they reach the end of round 4 without revealing the secret number

    rounds.jsp

        The rounds page is used to take the numbers the user wants to input for their guesses and send them to the controller object
        through a form. The page uses javascript validation to ensure there are no duplicates entered in the one submission and uses
        the attributes of the number input type to ensure numbers are in the correct range.

    save.jsp

        The save page is used to ask the user if they want to save their game or continue. A form with two buttons achieve this

    start.jsp

        The start page is used as the entry page for the application. The start page gives the ability to create a new game or
        load an existing game. The page differentiates between the two with the value passed by the button pressed and the field entered.

    script.js

        The script javaScript file is used to provide validation for the round page's form. It checks that none of the fields are empty
        and none of the fields hold the same information.

    style.css

        The style css file is used to provide css formatting each page.

Structure

    The application uses an MVC structure:
        The java beans are used as the models, storing information and performing logic related to the information they hold.
        The java beans have no knowledge of views, only the information they store.

        The jsp files are used as the views, they are passed information by a controller and display the information in a set way.
        The jsp files do not perform any data manipulation and have no knowledge of models. The jsp files do a small amount of logic,
        only related to how to display the provided information.

        The servlet is used as the controller for the application. All requests go through the servlet, the servlet has access to all views and models.
        The servlet takes input from the views, manipulates the data in the models, retrieves data from the models and provides
        data to the views to display.

Session Tracking

    Session tracking is implemented using both the java Session interface and hidden input fields.
    Each page uses a hidden input field in order to communicate to the controller which page the current request
    originated from.
    Session tracking is also implemented using the Session interface. At the beginning of each post request, the controller
    retrieves the current game and the sessionBean holding all games from the session. The controller then loads these back
    to the session before it finishes the request.

Game Saving

    Games are saved using a HashMap inside the sessionBean object. This HashMap contains gameBeans, mapped against
    the username for each gameBean. When a user loads an existing game, the gameBean is removed from the HashMap.When
    the user reaches the save page and selects save and exit, the gameBean is then added to the HashMap again. This
    prevents reloading an earlier save.

starting URL


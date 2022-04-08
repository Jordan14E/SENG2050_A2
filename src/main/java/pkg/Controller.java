/**
 * The Controller object is a servlet. The servlet is used as the main controller for the entire application. It takes
 * requests from the pages, applies the information gathered to the java beans and passes information back to the correct page
 * to display.
 */
package pkg;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


public class Controller extends HttpServlet{

    /*
     * overriding the doGet() method. Make it so the start page is shown. Used on entry to the application
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("start.jsp");
    }

    /* Overriding the doPost() method. Used to perform logic and control the operation of the application.
    Further explained inside.
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        sessionBean games = (sessionBean) request.getSession().getAttribute("games");
        gameBean game = (gameBean) request.getSession().getAttribute("currentGame");    //downloading information needed to a local copy for thread safety
        if(games == null){      //if the sessionBean is null, the first time in the session a request has been made
            games = new sessionBean();
        }
        int pageID = Integer.parseInt(request.getParameter("pageID"));  //finding which page request is coming from

        //this switch case is used to decide which method to call based on the ID number of the page the last request came from
        switch (pageID){
            case 1:
                    game = startPage(request, response, game, games);   //hand over to start page to perform the
                                                                        // functions needed for the start page
                    request.getSession().setAttribute("currentGame", game);
                    request.getSession().setAttribute("games", games);  //uploading back to the session after all
                                                                            // editing is done
                    if (game == null) {     //will be null if there is no game linked to the current username (for existing game)
                        response.sendRedirect("start.jsp?error=true");
                    } else {        //moving to the next page
                        response.sendRedirect("rounds.jsp");
                    }
                break;
            case 2:
                roundPage(request, response, game); //hand over to the roundPage() method to perform all functions for the round page
                request.getSession().setAttribute("currentGame", game);
                request.getSession().setAttribute("games", games);      //uploading back to session after editing is done
                response.sendRedirect("bankOffer.jsp");     //moving to next page
                break;
            case 3:
                bankPage(request, response, game, games);   //hand over to the bankPage() method to perform all functions for the bank page
                break;
            case 4:
                savePage(request, response, game, games);   //hand over to the savePage() method to perform all functions for the save page
                break;
        }

    }

    /**
     * The startPage() method is used to process the input from the start page and make ready for the round page.
     * The startPage() method takes input from the form on the start page jsp. depending on ths input it can create a new game,
     * or retrieve a game based off the username entered.
     * @param request takes the request object to get input from the start jsp form
     * @param response takes the response object in order to use the request dispatcher method for sending information to the round jsp
     * @param game takes the local gameBean variable for the current game to retrieve information when needed
     * @param games takes the local sessionBean variable for the datastructure of games to retrieve information and add information when needed
     * @return  returns the current gameBean to be updated when the method finishes
     * @throws ServletException could be thrown by the use of request dispatcher
     * @throws IOException could be thrown by retrieving information from the request
     */
    public gameBean startPage(HttpServletRequest request, HttpServletResponse response, gameBean game, sessionBean games) throws ServletException, IOException {
        gameBean current;
        //if the page has been refreshed any data manipulation is skipped and the information needed to display the round page is sent again
        if(request.getSession().getAttribute("lastPage") != null && (int)request.getSession().getAttribute("lastPage") == 1){
            request.setAttribute("game", game);
            request.setAttribute("round", game.getRound());
            request.getSession().setAttribute("lastPage", 1);   //placing the page number on the session to recognise when the refresh button is pressed
            request.getRequestDispatcher("rounds.jsp").forward(request, response);
            return game;
        }
        //if it is a legitimate call of the startPage() method data manipulation will occur
        else{
            //if the user selected to create a new game and provided a username
            if(request.getParameter("submit").equals("New") && !Objects.equals(request.getParameter("newUser"), "")){
                int secret = generateSecret();
                games.addGame(secret, request.getParameter("newUser"));     // creating game and adding to the data structure
                current = games.findGame(request.getParameter("newUser"));  //retrieving the game
            }
            //if the user has selected existing game and provided a username
            else if(request.getParameter("submit").equals("Existing") && !Objects.equals(request.getParameter("oldUser"), "")){
                current = games.findGame(request.getParameter("oldUser"));  //finding the game
                if(current == null){    //if the game is null, there is no game linked to this username and an error will be displayed
                    return current;
                }
            }
            //just in case something else went wrong such as no username entered
            else{
                return null;
            }
                //setting up to display round page
                request.setAttribute("game", current);
                request.setAttribute("round", current.getRound());
                request.getSession().setAttribute("lastPage", 1);
                request.getRequestDispatcher("rounds.jsp").forward(request, response);
            return current;
        }

    }

    /**
     * The roundPage() method is used to process input from the round jsp. The roundPage() method takes the numbers input by
     * the user and perfors the games actual function. Checks whether any numbers have already been revealed and passes off for an error if so,
     * checks if the secret number has been revealed, if not updates the numbers that have been revealed in the gameBean and
     * updates the round number.
     * @param request takes the request object to get input from the round jsp form
     * @param response  takes the response object in order to use the request dispatcher method for sending information to the bank jsp
     * @param game takes the local gameBean variable for the current game to retrieve and update information when needed
     * @throws ServletException could be thrown by the use of request dispatcher
     * @throws IOException could be thrown by retrieving information from the request
     */
    public void roundPage(HttpServletRequest request, HttpServletResponse response, gameBean game) throws IOException, ServletException {


        int num1 = 0, num2 = 0, num3 = 0, num4 = 0; //numbers for the input from round jsp

        //if the request has been sent using the refresh button the round is adjusted to make sure the correct amount of
        //numbers are parsed in the switch case
        if((int)request.getSession().getAttribute("lastPage") == 2){
            game.decrementRound();
        }

        //switch case decides the amount of inputs that should be parsed into integers based off the round number
        switch(game.getRound()){
            case 1:
                num1 = Integer.parseInt(request.getParameter("1"));
                break;
            case 2:
                num1 = Integer.parseInt(request.getParameter("1"));
                num2 = Integer.parseInt(request.getParameter("2"));
                break;
            case 3:
                num1 = Integer.parseInt(request.getParameter("1"));
                num2 = Integer.parseInt(request.getParameter("2"));
                num3 = Integer.parseInt(request.getParameter("3"));
                break;
            case 4:
                num1 = Integer.parseInt(request.getParameter("1"));
                num2 = Integer.parseInt(request.getParameter("2"));
                num3 = Integer.parseInt(request.getParameter("3"));
                num4 = Integer.parseInt(request.getParameter("4"));
                break;
        }

        //if the request has been made by the refresh button does not check if numbers have already been revealed
        if(!((int)request.getSession().getAttribute("lastPage") == 2)) {
            //if any of the numbers have already been revealed
            if (game.isRevealed(num1) || (num2 > 0 && game.isRevealed(num2)) || (num3 > 0 && game.isRevealed(num3)) || (num4 > 0 && game.isRevealed(num4))) {
                //set up to show error on round page
                request.setAttribute("game", game);
                request.setAttribute("round", game.getRound());
                request.setAttribute("error", "true");
                //find which number has already been revealed
                if (game.isRevealed(num1)) {
                    request.setAttribute("revealed", num1);
                } else if (game.isRevealed(num2) && num2 > 0) {
                    request.setAttribute("revealed", num2);
                } else if (game.isRevealed(num3) && num3 > 0) {
                    request.setAttribute("revealed", num3);
                } else if (game.isRevealed(num4) && num4 > 0) {
                    request.setAttribute("revealed", num4);
                }
                request.getRequestDispatcher("rounds.jsp").forward(request, response);
                return;
            }
        }
        //if the any of the inputted numbers is the secret number
        if(num1 == game.getSecret() || num2 == game.getSecret() || num3 == game.getSecret() || num4 == game.getSecret()){
            //set up to show the game over page
            request.setAttribute("type", "loss");
            request.setAttribute("secret", game.getSecret());
            request.getSession().setAttribute("lastPage", 2);
            request.getRequestDispatcher("bankOffer.jsp").forward(request, response);
        }
        //if the numbers are ready to be revealed
        else{
            //if the request was made by the refresh button skip revealing the numbers
            if(!((int)request.getSession().getAttribute("lastPage") == 2)) {
                //reveal the numbers
                game.revealNum(num1);
                if (num2 > 0) {
                    game.revealNum(num2);
                }
                if (num3 > 0) {
                    game.revealNum(num3);
                }
                if (num4 > 0) {
                    game.revealNum(num4);
                }
            }

            game.incrementRound();  //move round number to the next round

            //finding how many numbers have not been revealed to create an array of the correct size
            int[] nums = game.getNums();
            int count = 0;
            for(int i=0; i<nums.length; i++){
                if(nums[i] == 0){
                    count++;
                }
            }
            int[] unrevealed = new int[count];  //create an array big enough to hold all numbers that have not been revealed
            count = 0;  //reuse variable as index for unrevealed array
            //adding the unrevealed numbers to the array
            for(int i=0; i<nums.length; i++){
                if(nums[i] == 0){
                    unrevealed[count] = i+1;
                    count++;
                }
            }
            //set up to display the bank offer and the unrevealed numbers on the bank jsp
            request.setAttribute("offer",generateBankOffer(game));
            request.setAttribute("type", "continue");
            request.setAttribute("nums", unrevealed);
            request.getSession().setAttribute("lastPage", 2);
            request.getRequestDispatcher("bankOffer.jsp").forward(request, response);
        }
    }

    /**
     * The bankPage() method is used to process input from the bank jsp. if the user accepts the offer, the game is erased
     * and the application returns to the starting page. If the user decides to continue playing, the method sets up to display the save page
     * @param request takes the request object to get input from the bank jsp form
     * @param response  takes the response object in order to use the request dispatcher method for sending information to the save jsp
     * @param game takes the local gameBean variable for the current game to retrieve information when needed
     * @param games takes the local sessionBean variable for the data structure of games to delete information when needed
     * @throws IOException could be thrown by retrieving information from the request
     */
    public void bankPage(HttpServletRequest request, HttpServletResponse response, gameBean game, sessionBean games) throws IOException {

        //if the offer has been accepted
        if(request.getParameter("submit").equals("accept")){
            games.findGame(game.getUser()); //deleting the game from the data structure
            game = null;    //erase game
            //set up to recognise if the page is refreshed
            request.getSession().setAttribute("lastPage", 3);
            //uploading the information back to the session once all editing is complete
            request.getSession().setAttribute("currentGame", game);
            request.getSession().setAttribute("games", games);
            response.sendRedirect("start.jsp"); //move back to start page
        }
        //if continuing game
        else{
            //set up to recognise if the page is refreshed
            request.getSession().setAttribute("lastPage", 3);
            //uploading the information back to the session once all editing is complete
            request.getSession().setAttribute("currentGame", game);
            request.getSession().setAttribute("games", games);
             response.sendRedirect("save.jsp"); //move to save page
        }

    }

    /**
     * The savePage() method is used to process input from the save jsp. If the user selects to save the game, the game
     * is saved to the data structure and application returns to the start page. If the user selects to continue, the method
     * sets up to display the round page for the next round.
     * @param request takes the request object to get input from the save jsp form
     * @param response takes the response object in order to use the request dispatcher method for sending information to the round jsp
     * @param game takes the local gameBean variable for the current game to store information when needed
     * @param games takes the local sessionBean variable for the data structure of games to store information when needed
     * @throws ServletException could be thrown by the use of request dispatcher
     * @throws IOException could be thrown by retrieving information from the request
     */
    public void savePage(HttpServletRequest request, HttpServletResponse response, gameBean game, sessionBean games) throws IOException, ServletException {
        //if the user selects to save the game
        if(request.getParameter("submit").equals("yes")){
            games.saveGame(game);   //saving the game to the data structure
            //set up to recognise if the page is refreshed
            request.getSession().setAttribute("lastPage", 4);
            //uploading the information back to the session once all editing is complete
            request.getSession().setAttribute("currentGame", game);
            request.getSession().setAttribute("games", games);
            response.sendRedirect("start.jsp");}    //move to the start jsp
        //if the user selects to continue
        else{
            //setting up to display round page
            request.setAttribute("game", game);
            request.setAttribute("round", game.getRound());
            //set up to recognise if the page is refreshed
            request.getSession().setAttribute("lastPage", 4);
            request.getRequestDispatcher("rounds.jsp").forward(request, response);
            //uploading the information back to the session once all editing is complete
            request.getSession().setAttribute("currentGame", game);
            request.getSession().setAttribute("games", games);
            response.sendRedirect("rounds.jsp");    //moving to round page
        }
    }

    /**
     * used to generate a random number between 1 and 11 for the secret number of the game
     * @return  a random number between 1 and 11
     */
    public int generateSecret(){

        return (int) ((Math.random()* 10)+1);   //the +1 is to account for the fact that the Math.random() can give a 0
        //making this result an integer will cut off the decimal places but still allows for any number between 1 and 11
    }

    /**
     * Used to calculate the offer to be made on the bank jsp. uses the lowest unrevealed number or the secret number if won
     * @param game The game to generate the offer for
     * @return return an integer representing the bank offer
     */
    public int generateBankOffer(gameBean game){

        int offer = 0;

        //finding the lowest unrevealed number that is not the secret number
        for(int i=0; i < game.getNums().length && offer == 0; i++){
            if(game.getNums()[i] == 0 && (i+1) != game.getSecret()){
                offer = i+1;
            }
        }
        //if the secret number is the only unrevealed number calculate the offer based on the secret number
        if(offer == 0){
            offer = game.getSecret() * 100;
        }
        //caluclate the offer using the lowest unrevealed number
        else{
            offer = offer * 100;
        }

        return offer;
    }
}

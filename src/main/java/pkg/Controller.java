package pkg;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

public class Controller extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("start.jsp");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        sessionBean games = (sessionBean) request.getSession().getAttribute("games");
        gameBean game = (gameBean) request.getSession().getAttribute("currentGame");
        if(games == null){
            games = new sessionBean();
        }
        int pageID = Integer.parseInt(request.getParameter("pageID"));

        switch (pageID){
            case 1:
                    game = startPage(request, response, game, games);
                    request.getSession().setAttribute("currentGame", game);
                    request.getSession().setAttribute("games", games);
                    if (game == null) {   //startPage didn't work for some reason
                        response.sendRedirect("start.jsp?error=true");
                    } else {
                        response.sendRedirect("rounds.jsp");
                    }
                break;
            case 2:
                roundPage(request, response, game);
                request.getSession().setAttribute("currentGame", game);
                request.getSession().setAttribute("games", games);
                response.sendRedirect("bankOffer.jsp");
                break;
            case 3:
                bankPage(request, response, game, games);
                break;
            case 4:
                savePage(request, response, game, games);
                break;
        }

    }

    public gameBean startPage(HttpServletRequest request, HttpServletResponse response, gameBean game, sessionBean games) throws ServletException, IOException {
        gameBean current;
        if(request.getSession().getAttribute("lastPage") != null && (int)request.getSession().getAttribute("lastPage") == 1){
            request.setAttribute("game", game);
            request.setAttribute("round", game.getRound());
            request.getSession().setAttribute("lastPage", 1);
            request.getRequestDispatcher("rounds.jsp").forward(request, response);
            return game;
        }
        else{
            if(request.getParameter("submit").equals("New") && request.getParameter("newUser")!= null){
                int secret = generateSecret();
                games.addGame(secret, request.getParameter("newUser"));
                current = games.findGame(request.getParameter("newUser"));
            }
            else if(request.getParameter("submit").equals("Existing") && request.getParameter("oldUser")!= null){
                current = games.findGame(request.getParameter("oldUser"));
                if(current == null){
                    return current;
                }
            }
            else{
                return null;
            }

                request.setAttribute("game", current);
                request.setAttribute("round", current.getRound());
                request.getSession().setAttribute("lastPage", 1);
                request.getRequestDispatcher("rounds.jsp").forward(request, response);
            return current;
        }

    }

    public void roundPage(HttpServletRequest request, HttpServletResponse response, gameBean game) throws IOException, ServletException {


        int num1 = 0, num2 = 0, num3 = 0, num4 = 0;

        if((int)request.getSession().getAttribute("lastPage") == 2){
            game.decrementRound();
        }

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

        if(!((int)request.getSession().getAttribute("lastPage") == 2)) {
            if (game.isRevealed(num1) || (num2 > 0 && game.isRevealed(num2)) || (num3 > 0 && game.isRevealed(num3)) || (num4 > 0 && game.isRevealed(num4))) {
                request.setAttribute("game", game);
                request.setAttribute("round", game.getRound());
                request.setAttribute("error", "true");
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
        if(num1 == game.getSecret() || num2 == game.getSecret() || num3 == game.getSecret() || num4 == game.getSecret()){
            request.setAttribute("type", "loss");
            request.setAttribute("secret", game.getSecret());
            request.getSession().setAttribute("lastPage", 2);
            request.getRequestDispatcher("bankOffer.jsp").forward(request, response);
        }
        else{
            if(!((int)request.getSession().getAttribute("lastPage") == 2)) {
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

            game.incrementRound();

            int[] nums = game.getNums();
            int count = 0;
            for(int i=0; i<nums.length; i++){
                if(nums[i] == 0){
                    count++;
                }
            }
            int[] unrevealed = new int[count];
            count = 0;
            for(int i=0; i<nums.length; i++){
                if(nums[i] == 0){
                    unrevealed[count] = i+1;
                    count++;
                }
            }
            request.setAttribute("offer",generateBankOffer(game));
            request.setAttribute("type", "continue");
            request.setAttribute("nums", unrevealed);
            request.getSession().setAttribute("lastPage", 2);
            request.getRequestDispatcher("bankOffer.jsp").forward(request, response);
        }
    }

    public void bankPage(HttpServletRequest request, HttpServletResponse response, gameBean game, sessionBean games) throws IOException {

        if(request.getParameter("submit").equals("accept")){
            games.findGame(game.getUser());
            game = null;
            request.getSession().setAttribute("lastPage", 3);
            request.getSession().setAttribute("currentGame", game);
            request.getSession().setAttribute("games", games);
            response.sendRedirect("start.jsp");
        }
        else{
            request.getSession().setAttribute("lastPage", 3);
            request.getSession().setAttribute("currentGame", game);
            request.getSession().setAttribute("games", games);
             response.sendRedirect("save.jsp");
        }

    }

    public void savePage(HttpServletRequest request, HttpServletResponse response, gameBean game, sessionBean games) throws IOException, ServletException {
        if(request.getParameter("submit").equals("yes")){
        games.saveGame(game);
            request.getSession().setAttribute("lastPage", 4);
            request.getSession().setAttribute("currentGame", game);
            request.getSession().setAttribute("games", games);
        response.sendRedirect("start.jsp");}
        else{
            request.setAttribute("game", game);
            request.setAttribute("round", game.getRound());
            request.getSession().setAttribute("lastPage", 4);
            request.getRequestDispatcher("rounds.jsp").forward(request, response);
            request.getSession().setAttribute("currentGame", game);
            request.getSession().setAttribute("games", games);
            response.sendRedirect("");
        }
    }

    public int generateSecret(){

        return (int) ((Math.random()* 10)+1);
    }

    public int generateBankOffer(gameBean game){

        int offer = 0;

        for(int i=0; i < game.getNums().length && offer == 0; i++){
            if(game.getNums()[i] == 0 && (i+1) != game.getSecret()){
                offer = i+1;
            }
        }

        if(offer == 0){
            offer = game.getSecret() * 100;
        }
        else{
            offer = offer * 100;
        }

        return offer;
    }
}

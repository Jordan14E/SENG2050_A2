package pkg;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

public class Controller extends HttpServlet{

    sessionBean games;
    static gameBean game;

    @Override
    public void init() throws ServletException {
        super.init();
        games = new sessionBean();
        game = null;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("start.jsp");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int pageID = Integer.parseInt(request.getParameter("pageID"));

        switch (pageID){
            case 1:
                game = startPage(request, response);
                if(game == null){   //startPage didn't work for some reason
                    response.sendRedirect("start.jsp");
                }
                response.sendRedirect("rounds.jsp");
                break;
            case 2:
                roundPage(request, response);
                response.sendRedirect("bankOffer.jsp");
                break;
            case 3:
                bankPage(request, response);
        }

    }

    public gameBean startPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        gameBean current;

        if(request.getParameter("submit").equals("New") && request.getParameter("newUser")!= null){
            int secret = generateSecret();
            games.addGame(secret, request.getParameter("newUser"));
            current = games.findGame(request.getParameter("newUser"));
        }
        else if(request.getParameter("submit").equals("Existing") && request.getParameter("oldUser")!= null){
            current = games.findGame(request.getParameter("oldUser"));
        }
        else{
            return null;
        }
        request.setAttribute("game", current);
        request.setAttribute("round", current.getRound());
        request.getRequestDispatcher("rounds.jsp").forward(request, response);
        return current;
    }

    public void roundPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        int num1 = 0, num2 = 0, num3 = 0, num4 = 0;

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

        if(num1 == game.getSecret() || num2 == game.getSecret() || num3 == game.getSecret() || num4 == game.getSecret()){

            request.setAttribute("offer",generateBankOffer());
            request.setAttribute("type", "win");
            request.getRequestDispatcher("bankOffer.jsp").forward(request, response);
        }
        else{
            boolean worked = game.revealNum(num1);
            if(num2>0){
            worked = game.revealNum(num2);}
            if(num3>0){
            worked = game.revealNum(num3);}
            if(num4>0){
            worked = game.revealNum(num4);}

            if(!worked){
                //something didn't work
            }

            game.incrementRound();

            request.setAttribute("offer",generateBankOffer());
            request.setAttribute("type", "continue");
            request.getRequestDispatcher("bankOffer.jsp").forward(request, response);
        }
    }

    public void bankPage(HttpServletRequest request, HttpServletResponse response){



    }

    public int generateSecret(){

        return (int) (Math.random()* 11);
    }

    public int generateBankOffer(){

        int offer = 0;

        for(int i=0; i < game.getNums().length && offer == 0; i++){
            if(game.getNums()[i] == 0 && i != game.getSecret()){
                offer = i;
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

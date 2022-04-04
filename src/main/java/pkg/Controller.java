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
                game = roundPage(request, response);
                break;
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

    public gameBean roundPage(HttpServletRequest request, HttpServletResponse response) throws IOException {

        gameBean current;
        current = game;
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
            response.sendRedirect("start.jsp");
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

        }



        return current;
    }

    public int generateSecret(){

        return (int) (Math.random()* 11);
    }
}

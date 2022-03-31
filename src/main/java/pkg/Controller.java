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
            case 2:
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

    public int generateSecret(){

        return (int) (Math.random()* 11);
    }
}

package pkg;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("start.jsp");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){

        int pageID = Integer.parseInt(request.getParameter("pageID"));

        switch (pageID){
            case 1:
                startPage(request, response);
        }

    }

    public void startPage(HttpServletRequest request, HttpServletResponse response){

    }
}

/**
 * contains a hashmap of game beans
 */
package pkg;

import java.io.Serializable;
import java.util.*;
import java.util.HashMap;

public class sessionBean {

    private HashMap<String, gameBean> games;

    sessionBean(){
        games = new HashMap<>();
    }

    public HashMap<String, gameBean> getGames() {
        return games;
    }

    public void addGame(int secret, String user){
        gameBean game = new gameBean(secret, user);
        games.put(user, game);
    }

    public gameBean findGame(String user){

       gameBean game = games.get(user);

       games.remove(user);  //removing game when it is loaded to stop back tracking

       return game;
    }

    public void saveGame(gameBean game){
        String user = game.getUser();
        games.put(user, game);
    }

}

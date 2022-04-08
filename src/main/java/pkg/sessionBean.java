/**
 * The sessionBean is a javaBean. The sessionBean is used to store all gameBeans that have been created. The sessionBean
 * uses a HashMap to store each game bean, with the key being the username entered when creating the game.
 */
package pkg;
import java.util.HashMap;

public class sessionBean {

    private HashMap<String, gameBean> games;

    sessionBean(){
        games = new HashMap<>();
    }

    public HashMap<String, gameBean> getGames() {
        return games;
    }

    /**
     * The addGame() method creates a new gameBean and uses the put() method from the HashMap library to add the game to
     * the HashMap
     * @param secret The secret number for the game
     * @param user The username for the game
     */
    public void addGame(int secret, String user){
        gameBean game = new gameBean(secret, user);
        games.put(user, game);
    }

    /**
     * The findGame() method is uses to retrieve a game using the username it was passed. The method returns the game
     * after removing it from the HashMap
     * @param user The username for the game to be retrieved
     * @return The gameBean that matches the username or null if there is no such gameBean
     */
    public gameBean findGame(String user){

       gameBean game = games.get(user);

       if(game != null){
           games.remove(user);  //removing game when it is loaded to stop back tracking
       }
       return game;
    }

    /**
     * The saveGame() method is used to add an existing game to the HashMap. This saves the game and its state
     * @param game The game to be saved
     */
    public void saveGame(gameBean game){
        String user = game.getUser();   //retrieving username to save against
        games.put(user, game);
    }

}

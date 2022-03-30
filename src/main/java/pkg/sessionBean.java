/**
 * contains a hashmap of game beans
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
}

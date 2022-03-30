/**
 * contains all information on a game
 */
package pkg;

public class gameBean {

    private int[] revealed;
    private int[] unrevealed;
    private int secret;

    gameBean(){
        revealed = new int[11];
        unrevealed = new int[11];
        secret = 0;
    }

    gameBean(int secretNum){
        revealed = new int[11];
        unrevealed = new int[11];
        secret = secretNum;
    }

    public int getSecret() {
        return secret;
    }

    public int[] getRevealed() {
        return revealed;
    }

    public int[] getUnrevealed() {
        return unrevealed;
    }
    public void setSecret(int secret) {
        this.secret = secret;
    }
}

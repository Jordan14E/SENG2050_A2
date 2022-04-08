/**
 *  The gameBean object is a java bean. The gameBean is used to store all information relevant to a game. This includes;
 *  An array of numbers and a way to determine which have been revealed, the secret number, the round number, and the username.
 */
package pkg;
import java.util.*;
public class gameBean {


    private int[] nums;
    private int secret;
    private int round;
    private String user;

    //blank constructor
    gameBean(){
        nums = new int[11];
        secret = 0;
        round = 1;
        user = "";
        Arrays.fill(nums, 0);
    }

    //constructor with secret number and username
    gameBean(int secretNum, String User){
        nums = new int[11];     //holds an array of 11 1s and 0s. 0 if the number has not been revealed, 1 if it has.
        secret = secretNum;     //holds the secret number for the game
        round = 1;              //holds the round number for the game
        user = User;            //holds te username for the game
        Arrays.fill(nums, 0);   //fills the nums array with 0s as none of them have been revealed yet
    }

    /**
     * returns secret
     * @return secret number
     */
    public int getSecret() {
        return secret;
    }

    /**
     * returns nums array
     * @return array of 11 numbers
     */
    public int[] getNums() {
        return nums;
    }

    /**
     * returns the round number
     * @return round number
     */
    public int getRound() {
        return round;
    }

    /**
     * returns the username
     * @return game's username
     */
    public String getUser() {
        return user;
    }

    /**
     * used to reveal a number by making it's array position 1
     * @param num the number to reveal
     * @return a boolean judging whether the reveal was successful
     */
    public boolean revealNum(int num){
        num-=1;     //making the number translate to an array position
        if(nums[num] == 0){
            nums[num] = 1;
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * used to increase the round number
     */
    public void incrementRound(){
        round++;
    }

    /**
     * used to decrease the round number. Only used to deal with refreshing the round page
     */
    public void decrementRound(){round--;}

    /**
     * used to fine if the number has been revealed
     * @param num the number to check
     * @return a boolean true if the number has been revealed and a false if not
     */
    public boolean isRevealed(int num){
        num-=1;     //converting number to an array position
        if(nums[num] == 1){
            return true;
        }
        else{
            return false;
        }
    }
}

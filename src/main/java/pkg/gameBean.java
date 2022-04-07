/**
 * contains all information on a game
 */
package pkg;

import java.io.Serializable;
import java.util.*;
public class gameBean {


    private int[] nums;
    private int secret;
    private int round;
    private String user;

    gameBean(){
        nums = new int[11];
        secret = 0;
        round = 1;
        user = "";
        Arrays.fill(nums, 0);
    }

    gameBean(int secretNum, String User){
        nums = new int[11];
        secret = secretNum;
        round = 1;
        user = User;
        Arrays.fill(nums, 0);
    }

    public int getSecret() {
        return secret;
    }


    public int[] getNums() {
        return nums;
    }

    public int getRound() {
        return round;
    }

    public String getUser() {
        return user;
    }

    public void setSecret(int secret) {
        this.secret = secret;
    }

    public boolean revealNum(int num){
        num-=1;
        if(nums[num] == 0){
            nums[num] = 1;
            return true;
        }
        else{
            return false;
        }
    }

    public void incrementRound(){
        round++;
    }

    public void decrementRound(){round--;}

    public boolean isRevealed(int num){
        num-=1;
        if(nums[num] == 1){
            return true;
        }
        else{
            return false;
        }
    }
}

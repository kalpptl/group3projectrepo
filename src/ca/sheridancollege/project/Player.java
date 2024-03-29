/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Name: Deepak Kumar, Kalp Patel, Karandeep Kaur, Mandeep Kaur 
 * Date: 12/06/2022
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 *
 * @author dancye
 * @author Paul Bonenfant
 * @author Prem Parashar
 */
public class Player {

    private String name; //the unique name for this player
    private GroupOfCards groupOfCards; // player's deck
    int score; // players score

    /**
     * A constructor that allows you to set the player's unique ID
     *
     * @param name the unique ID to assign to this player.
     */
    public Player(String name, int numCardsPlayer, ArrayList<Card> cards) {
        this.name = name;
        this.groupOfCards = new GroupOfCards(numCardsPlayer, cards);
        this.score = 0;
    }

    /**
     * @return the player name
     */
    public String getName() {
        return name;
    }

    public GroupOfCards getCards() {return groupOfCards;}
    public int getScore() {return this.score;}
    /**
     * Ensure that the playerID is unique
     *
     * @param name the player name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    public void setScore(int score1) {this.score = score1;}

    /**
     * The method to be overridden when you subclass the Player class with your specific type of Player and filled in
     * with logic to play your game.
     */
//    public abstract void play();
    @Override
    public String toString() {
        String ret = "Player: " + name + "\n";
        ret += groupOfCards;
        ret += "Score: " + score + "\n";
        return ret;
    }

}

/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.Objects;

/**
 * A class to be used as the base Card class for the project. Must be general enough to be instantiated for any Card
 * game. Students wishing to add to the code should remember to add themselves as a modifier.
 * @author Prem Parashar
 * @author dancye
 */
public class Card {
    //default modifier for child classes
    private int rank;
    private String suit;
    /**
     * Students should implement this method for their specific children classes
     *
     * @return a String representation of a card. Could be an UNO card, a regular playing card etc.
     */

    public Card(int rank1, String suit1) {
        this.rank = rank1;
        this.suit = suit1;
    }

    public int getRank() {return rank;}
    public String getSuit() {return suit;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card a = (Card) o;
        return Objects.equals(a.getRank(), ((Card) o).getRank()) &&  Objects.equals(a.getSuit(), ((Card) o).getSuit()) ;
    }

    @Override
    public  String toString() {
        return "[" + this.rank + "-" + this.suit + "], ";
    }

}

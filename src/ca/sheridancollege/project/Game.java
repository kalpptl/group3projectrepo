/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.lang.reflect.Array;
import java.util.*;

/**
 * The class that models your game. You should create a more specific child of this class and instantiate the methods
 * given.
 *
 * @author dancye
 * @author Paul Bonenfant
 * @author Prem Parashar
 * 
 */
public class Game {

    Random rand = new Random();
    private final String name;//the title of the game
    private ArrayList<Player> players;// the players of the game
    int numCardsPlayer = 5;
    int turns = 30;
    private ArrayList<String> playerNames = new ArrayList<>() {
        {
            add("Paul");
            add("Ross");
            add("Mike");
            add("Steve");
            add("Nancy");
            add("Lucas");
        }
    };

    private ArrayList<Card> list = getAllCards();
    public Game(String name, int numPlayers) {

        this.name = name;
        players = new ArrayList<>();
        Collections.shuffle(playerNames);
        for (int i = 0; i < numPlayers; i++) {


            // distribute numCards to each players randomly.
            ArrayList<Card> curSet = new ArrayList<>();
            for (int j = 0; j < numCardsPlayer; j++) {
                int randomCard = rand.nextInt(0, list.size()) % list.size();
                curSet.add(list.get(randomCard));
                list.remove(randomCard);
            }

//            for (int j = 0; j < curSet.size(); j++) {
//                System.out.println(curSet.get(j));
//            }
//            System.out.println();

            players.add(new Player(playerNames.get(i), numCardsPlayer, curSet));
        }
        for (int i = 0; i < players.size(); i++) {
            System.out.println(players.get(i));
        }
    }


    /*
        This method lists all the 52 cards as an Arraylist
     */
    public ArrayList<Card> getAllCards() {
        String suits[] = new String[] {"Diamonds", "Hearts", "Clubs", "Spades"};
        ArrayList<Card> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                list.add(new Card(j+1, suits[i]));
            }
        }
        Collections.shuffle(list);
        return list;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the players of this game
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * @param players the players of this game
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }


    // *************************************************************************************

    /*
        This method returns the max frequency card with the given player
     */
    static int getMaxFreqCard(Player player) {
        ArrayList<Card> playerCards = player.getCards().getCards();
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < playerCards.size(); i++) {
            if (freqMap.containsKey(playerCards.get(i).getRank())) {
                freqMap.put(playerCards.get(i).getRank(), freqMap.get(playerCards.get(i).getRank()) + 1);
            }
            else {
                freqMap.put(playerCards.get(i).getRank(), 1);
            }
        }
        int M = 0;
        int card = 0;
        for (Map.Entry elem : freqMap.entrySet()) {
            int key = (int) elem.getKey();
            int val = (int) elem.getValue();
            if (val > M) {
                M = val;
                card = key;
            }
        }
        return card;
    }

    /*
        This method checks whther the given card is present with the given player's deck
     */
    static boolean checkCard(Player player, int card) {
        ArrayList<Card> playerCards = player.getCards().getCards();
        for (int i = 0; i < playerCards.size(); i++) {
            if (playerCards.get(i).getRank() == card) {
                return true;
            }
        }
        return false;
    }

    /*
        This method is called on successful ask call.
        It gets the other players cards and add it to our deck
        It removes that from others player deck
     */
    static void exchangeCards(Player player1, Player player2, int card) {
        ArrayList<Card> cards1 = player1.getCards().getCards(), cards2 = player2.getCards().getCards();
        ArrayList<Integer> rem = new ArrayList<>();
        for (int i = 0; i < cards2.size(); i++) {
            if (cards2.get(i).getRank() == card) {
                cards1.add(cards2.get(i));
                rem.add(i);
            }
        }
        int removed = 0;
        for (int i = 0; i < rem.size(); i++) {
            cards2.remove(rem.get(i) - removed);
            removed += 1;
        }
        player1.getCards().setCards(cards1);
        player2.getCards().setCards(cards2);
    }

    /*
        This method checks whether there is a set of 4 in the current deck.
        If there is then it removes those card and increases the score of this player
     */
    void updateCardsPoints(Player player) {
        ArrayList<Card> curCards = player.getCards().getCards();
        HashMap<Integer, ArrayList<Integer>> freqMap = new HashMap<>();
        for (int i = 0; i < curCards.size(); i++) {
            if (freqMap.containsKey(curCards.get(i).getRank())) {
                ArrayList<Integer> prev = freqMap.get(curCards.get(i).getRank());
                prev.add(i);
                freqMap.put(curCards.get(i).getRank(), prev);
            }
            else {
                ArrayList<Integer> prev = new ArrayList<>();
                prev.add(i);
                freqMap.put(curCards.get(i).getRank(), prev);
            }
        }

        for (Map.Entry elem: freqMap.entrySet()) {
            int key = (int)elem.getKey();
            ArrayList<Integer> indices = (ArrayList<Integer>) elem.getValue();
            boolean flag[] = new boolean[curCards.size()];
            if (indices.size() >= 4) {
                System.out.println(player.getName() + " placed a set of " + key + "\nGains 1 point\n");
                player.setScore(player.getScore() + 1);

                for (int i = 0; i < indices.size(); i++) {
                    flag[indices.get(i)] = true;
                }
                ArrayList<Card> updatedCards = new ArrayList<>();
                for (int i = 0; i < curCards.size(); i++) {
                    if (flag[i] == false) updatedCards.add(curCards.get(i));
                }
                player.getCards().setCards(updatedCards);
            }
        }



    }

    /**
     * Play the game. This might be one method or many method calls depending on your game.
     */
    public void play() {
        int chance = rand.nextInt(0, players.size());
        boolean gameEnd = false;
        int curTurn = 0;

        // run the simulation for max turns
        while (gameEnd == false && curTurn != turns) {

            // cur turn
            System.out.println("Turn: " + (curTurn + 1) );
            System.out.println("===========================================================");

            // chance is the player who currently is asking for card
            System.out.println("Current chance: " + players.get(chance).getName());

            // this player chooses the max Freq card from his/her deck
            int maxFreqCard = getMaxFreqCard(players.get(chance));
            int chosenPlayer = rand.nextInt(0, players.size()) % players.size();
            while (chosenPlayer == chance) {
                chosenPlayer = rand.nextInt(0, players.size()) % players.size();
            }

            // chosen player is player that gets challenged by the max freq card
            System.out.println("Challenge: " + players.get(chosenPlayer).getName());
            System.out.println("Asks For: " + maxFreqCard);

            // check if other player has maxFreqCard
            boolean hasThatCard = checkCard(players.get(chosenPlayer), maxFreqCard);

            // if other player has it then
            // exchange cards and update points
            // keep chance with the same player
            if (hasThatCard) {
                System.out.println("Card Found\n");
                // chance remains same
                exchangeCards(players.get(chance), players.get(chosenPlayer), maxFreqCard);
                updateCardsPoints(players.get(chance));
                System.out.println("Chance remained with " + players.get(chance).getName() + "\n-------------\n");
            }

            // if doesnt have
            // go fish
            // draw a card from fishing pond
            // change the chance to the challenegd player
            else {
                System.out.println("** GO FISH **");
                // draw a card from fishing pond
                System.out.println(players.get(chance).getName() + " - picked a card from Fishing Pond" );
                ArrayList<Card> curCards = players.get(chance).getCards().getCards();
                int r = rand.nextInt(0, list.size()) % list.size();
                curCards.add(list.get(r));
                players.get(chance).getCards().setCards(curCards);

                // remove the card from the fishing pond
                list.remove(r);

                updateCardsPoints(players.get(chance));

                // chance changes to chosenPlayer
                chance = chosenPlayer;
                System.out.println("Chance Changed to " + players.get(chance).getName() + "\n-------------\n");
            }

            // display state of each player after this round
            System.out.println("Cards of Each Player, After this round: ");
            for (int i = 0; i < players.size(); i++) {
                System.out.println(players.get(i));
            }

            curTurn += 1;
            System.out.println("===========================================================");
        }

        // finally the player the max points is the winner
        declareWinner(players);
    }

    /**
     * When the game is over, use this method to declare and display a winning player.
     */
    public static Player declareWinner(ArrayList<Player> players) {

        // max score player is the winner
        int M = -1;
        Player winner = null;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getScore() > M) {
                M = players.get(i).getScore();
                winner = players.get(i);
            }
        }
        System.out.println("Winner:");
        System.out.println(winner);
        return winner;
    }

}//end class

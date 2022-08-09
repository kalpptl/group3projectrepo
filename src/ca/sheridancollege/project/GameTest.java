package ca.sheridancollege.project;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertArrayEquals;

class GameTest {

    /*
    GetMaxFreqCards - Normal
     */
    @Test
    void testGetMaxFreqCard1() {
        int numCardsPlayer = 4;
        ArrayList<Card> list = new ArrayList<>(){
            {
                add(new Card(1, "Diamonds"));
                add(new Card(2, "Spades"));
                add(new Card(1, "Hearts"));
                add(new Card(10, "Clubs"));
            }
        };
        Player player = new Player("Paul", numCardsPlayer, list);
        assertEquals(1, Game.getMaxFreqCard(player));
    }

    /*
    GetMaxFreqCards - All having same Frequencies
     */
    @Test
    void testGetMaxFreqCard2() {
        int numCardsPlayer = 4;
        ArrayList<Card> list = new ArrayList<>(){
            {
                add(new Card(1, "Diamonds"));
                add(new Card(3, "Spades"));
                add(new Card(2, "Hearts"));
                add(new Card(10, "Clubs"));
            }
        };
        Player player = new Player("Paul", numCardsPlayer, list);
        assertEquals(1, Game.getMaxFreqCard(player));
    }

    /*
    GetMaxFreqCards - All same rank
     */
    @Test
    void testGetMaxFreqCard3() {
        int numCardsPlayer = 4;
        ArrayList<Card> list = new ArrayList<>(){
            {
                add(new Card(3, "Diamonds"));
                add(new Card(3, "Spades"));
                add(new Card(3, "Hearts"));
                add(new Card(3, "Clubs"));
            }
        };
        Player player = new Player("Paul", numCardsPlayer, list);
        assertEquals(3, Game.getMaxFreqCard(player));
    }

    /*
    CheckCard - Positive
     */
    @Test
    void testCheckCard1() {
        int numCardsPlayer = 4;
        ArrayList<Card> list = new ArrayList<>(){
            {
                add(new Card(1, "Diamonds"));
                add(new Card(3, "Spades"));
                add(new Card(2, "Hearts"));
                add(new Card(3, "Clubs"));
            }
        };
        Player player = new Player("Paul", numCardsPlayer, list);
        assertEquals(true, Game.checkCard(player, 2));
    }

    /*
    Check Card - Negative
     */
    @Test
    void testCheckCard2() {
        int numCardsPlayer = 4;
        ArrayList<Card> list = new ArrayList<>(){
            {
                add(new Card(1, "Diamonds"));
                add(new Card(3, "Spades"));
                add(new Card(2, "Hearts"));
                add(new Card(3, "Clubs"));
            }
        };
        Player player = new Player("Paul", numCardsPlayer, list);
        assertEquals(false, Game.checkCard(player, 10));
    }

    /*
    Exchange Cards - Partial Merge
     */
    @Test
    void testExchangeCards1() {
        int numCardsPlayer = 4;
        ArrayList<Card> list1 = new ArrayList<>(){
            {
                add(new Card(1, "Diamonds"));
                add(new Card(3, "Spades"));
                add(new Card(2, "Hearts"));
                add(new Card(3, "Clubs"));
            }
        };

        ArrayList<Card> list2 = new ArrayList<>(){
            {
                add(new Card(1, "Diamonds"));
                add(new Card(3, "Spades"));
                add(new Card(2, "Hearts"));
                add(new Card(3, "Clubs"));
            }
        };
        Player player1 = new Player("Paul", numCardsPlayer, list1);
        Player player2 = new Player("Andrew", numCardsPlayer, list2);
        ArrayList<Card> resPlayer1 = new ArrayList<>() {
            {
                add(new Card(1, "Diamonds"));
                add(new Card(3, "Spades"));
                add(new Card(2, "Hearts"));
                add(new Card(3, "Clubs"));
                add(new Card(3, "Spades"));
                add(new Card(3, "Clubs"));
            }
        };
        ArrayList<Card> resPlayer2 = new ArrayList<>() {
            {
                add(new Card(1, "Diamonds"));
                add(new Card(2, "Hearts"));
            }
        };
        Game.exchangeCards(player1, player2, 3);
        assertArrayEquals(resPlayer1.toArray(), player1.getCards().getCards().toArray());
        assertArrayEquals(resPlayer2.toArray(), player2.getCards().getCards().toArray());


    }

    /*
    Exchange Cards - Complete Merge
     */
    @Test
    void testExchangeCards2() {
        int numCardsPlayer = 4;
        ArrayList<Card> list1 = new ArrayList<>(){
            {
                add(new Card(1, "Diamonds"));
                add(new Card(3, "Spades"));
                add(new Card(2, "Hearts"));
                add(new Card(3, "Clubs"));
            }
        };

        ArrayList<Card> list2 = new ArrayList<>(){
            {
                add(new Card(3, "Diamonds"));
                add(new Card(3, "Spades"));
                add(new Card(3, "Hearts"));
                add(new Card(3, "Clubs"));
            }
        };
        Player player1 = new Player("Paul", numCardsPlayer, list1);
        Player player2 = new Player("Andrew", numCardsPlayer, list2);
        ArrayList<Card> resPlayer1 = new ArrayList<>() {
            {
                add(new Card(1, "Diamonds"));
                add(new Card(3, "Spades"));
                add(new Card(2, "Hearts"));
                add(new Card(3, "Clubs"));
                add(new Card(3, "Diamonds"));
                add(new Card(3, "Spades"));
                add(new Card(3, "Hearts"));
                add(new Card(3, "Clubs"));
            }
        };
        ArrayList<Card> resPlayer2 = new ArrayList<>() {
            {
            }
        };
        Game.exchangeCards(player1, player2, 3);
        assertArrayEquals(resPlayer1.toArray(), player1.getCards().getCards().toArray());
        assertArrayEquals(resPlayer2.toArray(), player2.getCards().getCards().toArray());


    }

    /*
    Exchange Cards - No Merge
     */
    @Test
    void testExchangeCards3() {
        int numCardsPlayer = 4;
        ArrayList<Card> list1 = new ArrayList<>(){
            {
                add(new Card(1, "Diamonds"));
                add(new Card(3, "Spades"));
                add(new Card(2, "Hearts"));
                add(new Card(3, "Clubs"));
            }
        };

        ArrayList<Card> list2 = new ArrayList<>(){
            {
                add(new Card(3, "Diamonds"));
                add(new Card(3, "Spades"));
                add(new Card(3, "Hearts"));
                add(new Card(3, "Clubs"));
            }
        };
        Player player1 = new Player("Paul", numCardsPlayer, list1);
        Player player2 = new Player("Andrew", numCardsPlayer, list2);
        ArrayList<Card> resPlayer1 = new ArrayList<>() {
            {
                add(new Card(1, "Diamonds"));
                add(new Card(3, "Spades"));
                add(new Card(2, "Hearts"));
                add(new Card(3, "Clubs"));
            }
        };
        ArrayList<Card> resPlayer2 = new ArrayList<>() {
            {
                add(new Card(3, "Diamonds"));
                add(new Card(3, "Spades"));
                add(new Card(3, "Hearts"));
                add(new Card(3, "Clubs"));
            }
        };
        Game.exchangeCards(player1, player2, 11);
        assertArrayEquals(resPlayer1.toArray(), player1.getCards().getCards().toArray());
        assertArrayEquals(resPlayer2.toArray(), player2.getCards().getCards().toArray());


    }

    /*
    Declare Winner - Normal
     */
    @Test
    void testDeclareWinner1() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player());
        players.add(new Player());
        players.add(new Player());
        players.add(new Player());

        players.get(0).setScore(11);
        players.get(1).setScore(2);
        players.get(2).setScore(7);
        players.get(3).setScore(10);

        assertEquals(players.get(0), Game.declareWinner(players));
    }

    /*
    Declare Winner - Single Player
     */
    @Test
    void testDeclareWinner2() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player());
        players.get(0).setScore(11);
        assertEquals(players.get(0), Game.declareWinner(players));
    }


    /*
    Declare Winner - All Same Score
     */
    @Test
    void testDeclareWinner3() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player());
        players.add(new Player());
        players.add(new Player());
        players.add(new Player());

        players.get(0).setScore(11);
        players.get(1).setScore(11);
        players.get(2).setScore(11);
        players.get(3).setScore(11);

        assertEquals(players.get(0), Game.declareWinner(players));
    }

    private void assertArrayEquals(Object[] toArray, Object[] toArray0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertEquals(Player get, Player declareWinner) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
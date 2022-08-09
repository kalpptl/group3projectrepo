/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A class to test the methods in our passwordValidator
 * To be used as starter code for ICE 4
 * @author dancye, 2018
 * 
 */
public class GameTest {

    Random rand = new Random();
    public GameTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    void testGetMaxFreqCard() {
        int numCardsPlayer = 5;
        ArrayList<Card> list = new ArrayList<>(){
            {
                add(new Card(1, "Diamonds"));
                add(new Card(1, "Spades"));
                add(new Card(2, "Hearts"));
                add(new Card(10, "Clubs"));
            }
        };
        Player player = new Player("Paul", 5, list);
        assertEquals(1, getMax);
    }
}
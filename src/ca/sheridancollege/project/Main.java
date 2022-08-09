package ca.sheridancollege.project;

import java.util.Random;

/*

GAME - GO FISH
OVERVIEW -
Its a Game of 4-6 players.
Each player is distributed 5 cards
A random player starts the game
1. player asks another random player by a card
2. If that player has that card, give all those card to this player
3. if the player doesnt have it, he says Go FISH and this player will draw a card from fishing pond
4. If any player ahs a set of 4 same ranked card, he can remove it from his deck and his/her score will increment by 1
5. After some turns of the game - whoever has the maximum score wins the game.

 */
public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        int numPlayers = 4;
        String gameName = "***** GoFish_" + rand.nextInt(0,100) + " *****\n";
        System.out.println(gameName);
        Game game = new Game(gameName, numPlayers);

        System.out.println("GAME BEGINS..\n");
        game.play();
    }
}

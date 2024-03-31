package utilities;
/*
 * DeckUtils.java
 * 
 * DeckUtils consists of different utility functions that 
 * manipulate/instantiate a deck of cards
 */

import java.util.*;
import components.Card;
import components.Deck;
import components.Hand;
import components.Rank;
import components.Suit;

public final class DeckUtils {

    private DeckUtils(){
    }

    //Initializes a deck of only number cards
    public static Deck initializeNumbers(){
        //initializing deck of 52
        Deck d1 = new Deck();
        Rank.setKingHigh();
        List r = Rank.VALUES_NUMBER;
        List s = Suit.VALUES;

        //adding cards to deck
        for(Object s2 : s){
            Suit tempSuit = (Suit) s2;
            for(Object r2 : r){
                Rank tempRank = (Rank) r2;
                Card tempCard = new Card(tempSuit, tempRank, null);
                d1.addCard(tempCard);
            }
        }
        
        d1.shuffle();
        return d1;
    }

    //Initializes a deck of 52
    public static Deck initializeWhole(){
        //initializing deck of 52
        Deck d1 = new Deck();
        Rank.setKingHigh();
        List r = Rank.VALUES;
        List s = Suit.VALUES;

        for(Object s2 : s){
            Suit tempSuit = (Suit) s2;
            for(Object r2 : r){
                Rank tempRank = (Rank) r2;
                Card tempCard = new Card(tempSuit, tempRank, null);
                d1.addCard(tempCard);
            }
        }
        
        d1.shuffle();
        return d1;
    }

    //Deals 6 cards for the start of the game
    public static void dealRange(Deck deck, Hand hand, int numOfCards){
        for(int i = 0; i < numOfCards; i++){
            hand.addCard(deck.dealCard());
        }

    }

    //Deals a card from a deck of 52
    public static void dealCard(Deck deck, Hand hand){
        hand.addCard(deck.dealCard());
        Card newest = hand.getCard(hand.getNumberOfCards() - 1);
        System.out.println("\nYOU GOT " + newest);
    }    
}

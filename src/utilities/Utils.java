package utilities;
/*
 * Utils.java
 * 
 * Utils contains utility functions which deals with card inputs/output
 */

import java.util.*;
import components.Card;
import components.Deck;
import components.Hand;
import components.Player;
import components.Rank;
import components.Suit;

public final class Utils {

    private Utils(){
    }
    
    //Get card value as string, ace is 1
    public static String getCardValue(Card card){
        String result = "";
        if (Rank.ACE.getSymbol().equals(card.getRank().getSymbol())) {
            result += 1;
        } else {
            result += card.getRank().getSymbol();
        }
        return result;
    }

    //Wild card check
    public static boolean isWildCard(Card card){

        return Rank.QUEEN.getSymbol().equals(getCardValue(card)) 
            || Rank.KING.getSymbol().equals(getCardValue(card)) 
            || Rank.JACK.getSymbol().equals(getCardValue(card));
    }

    //Get card value as int, ace is 1
    public static int getCardValueInt(Card card){
        if (Rank.ACE.getSymbol().equals(getCardValue(card))) {
            return 1;
        } else {
            return Integer.parseInt(getCardValue(card));
        }
    }

    //String input to card object output
    public static Card stringToCard(String target){
        final int suits = 4;
        Map<String, Card> map = new HashMap<>();

        Rank.setKingHigh();
        List r = Rank.VALUES;
        List s = Suit.VALUES;

        //adding cards to deck
        for(int i = 0; i < suits; i++){
            for(int j = 0; j < r.size(); j++){
                Card temp = new Card((Suit)s.get(i), (Rank)r.get(j), null);
                map.put(temp.toString(), temp);
            }
        }

        return map.get(target);
    }

    //Return points based on range & bet
    public static int calculatePoints(Player p, Card card) {

        int points = 0;
        int lower = p.getLower();
        int upper = p.getUpper();

        //set the current number after drawing a card
        int current = getCardValueInt(card);

        //point logic
        if (current == upper || current == lower) {
            points = p.getBet() * 2;
            System.out.println("Right on the dot!");

        } else if (current > lower && current < upper) {
            points = p.getBet() * 1;
            System.out.println("In between!");

        } else {
            points = p.getBet() * -1;
            System.out.println("Good luck next time!");
        }
        System.out.println("current points = " + p.getPoints());
        p.setPoints(p.getPoints()+ points);
        System.out.println("new points = " + p.getPoints());
        return p.getPoints();
    }

    //Set upper or lower based on wildcard
    public static void processWildCard(Player p, Card card) {
        System.out.println(p.getPlayerID() + card.toString());
        // If queen, extend lower bound
        if (Rank.QUEEN.getSymbol().equals(getCardValue(card))) {
            int lower = p.getLower();
            lower -= 2;
            if (lower < 1) {
                lower = 1;
            }
            p.setLower(lower);

        // If king, extend upper bound
        } else if (Rank.KING.getSymbol().equals(getCardValue(card))) {
            int upper = p.getUpper();
            upper += 2;
            if (upper > 10) {
                upper = 10;
            }
            p.setUpper(upper);
        }
    }

    //Swap range card for a new card
    public static void processJack(Player p, Card cardToSwap, Card swapped) {

        // Swaps the selected bound card for a new bound card
        int swappedCard = getCardValueInt(swapped);

        int upperBound = p.getUpper();
        int lowerBound = p.getLower();

        Card originalUpper = p.getOriginalUpper();
        Card originalLower = p.getOriginalLower();
        //Adjust upper and lower bound accordingly
        if(cardToSwap.isSameAs(originalUpper)){
            if(swappedCard < lowerBound){

                p.setOriginalUpper(originalLower);
                p.setUpper(lowerBound);

                p.setOriginalLower(swapped);
                p.setLower(swappedCard);

            }else{
                p.setOriginalUpper(swapped);
                p.setUpper(swappedCard);
            }

        }else{
            if(swappedCard > upperBound){

                p.setOriginalLower(originalUpper);
                p.setLower(upperBound);

                p.setOriginalUpper(swapped);
                p.setUpper(swappedCard);

            }else{
                p.setOriginalLower(swapped);
                p.setLower(swappedCard);
            }
        }
    }

}

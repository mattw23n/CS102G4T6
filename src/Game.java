import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

public class Game {
    
    public static void main(String[] args) {

        Deck d1 = new Deck();

        Rank.setKingHigh();
        List r = Rank.VALUES_NUMBER;
        List s = Suit.VALUES;

        //initializing deck of 52
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < r.size(); j++){
                d1.addCard(new Card((Suit)s.get(i), (Rank)r.get(j), null));
            }
        }

        d1.shuffle();

        for(int i = 0; i < 6; i++){
            System.out.println(d1.dealCard().toString());
        }
        
        
    }
}

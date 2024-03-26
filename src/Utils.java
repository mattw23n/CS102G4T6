import java.util.*;

public class Utils {
    

    //string input to card object output
    public static Card stringToCard(String target){
        Map<String, Card> map = new HashMap<>();

        Rank.setKingHigh();
        List r = Rank.VALUES;
        List s = Suit.VALUES;

        //adding cards to deck
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < r.size(); j++){
                Card temp = new Card((Suit)s.get(i), (Rank)r.get(j), null);
                map.put(temp.toString(), temp);
            }
        }
        
        //System.out.println(map);

        Set keys = map.keySet();
        Iterator keysIterator = keys.iterator();
        while(keysIterator.hasNext()){
            String current = (String) keysIterator.next();
            if(current.equals(target)){
                return map.get(current);
            }
        }

        return null;
    }


    //get card value as string, ace is 1
    public static String getCardValue(Card card){
        return card.getRank().getSymbol();
    }

    //get card value as int, ace is 1
    public static int getCardValueInt(Card card){
        if ("a".equals(getCardValue(card))) {
            return 1;
        } else {
            return Integer.parseInt(getCardValue(card));
        }
    }




    //swap range card for a new card
    public static void processJack(Player p, Card cardToSwap, Card swapped) {

        // Swaps the selected bound card for a new bound card
        int toSwapCard = getCardValueInt(cardToSwap);
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

        System.out.println("(" + lowerBound + ", " + upperBound + ")");
    }

    //return points based on range & bet
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
        return p.getPoints() + points;
    }

}

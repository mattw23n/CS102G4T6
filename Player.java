// pls feel free to cmiiw....................................
import java.util.*;

public class Player{
    int points = 0;
    ArrayList<Card> handCards = new ArrayList<Card>();
    ArrayList<Card> chosenRangeCards = new ArrayList<Card>();
    ArrayList<Card> wildCards = new ArrayList<Card>();
    // ^ if the card drawn is wildcard add to this arraylist? idk if i should make the
    // method in Player 

    // not sure, i'm assuming will prompt user for card to be chosen from range?
    // but idk how that rly works either lol
    public void chooseRange() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose card for lower bound of range:");
        
        // assuming they type string like "d4" -> diamond 4  
        // to show which card they choose?
        String Card1 = sc.nextLine();
        for (int i = 0; i < handCards.size(); i++) {
            if (Card1.charAt(0) == handCards.get(i).getSuit().getSymbol().charAt(0)) {
                if (Card1.charAt(1) == handCards.get(i).getRank().getSymbol().charAt(0))
                chosenRangeCards.add(handCards.get(i));
                handCards.remove(i);
            }
        }

        System.out.println("Choose card for lower bound of range:");
        String Card2 = sc.nextLine();
        for (int i = 0; i < handCards.size(); i++) {
            if (Card2.charAt(0) == handCards.get(i).getSuit().getSymbol().charAt(0)) {
                if (Card2.charAt(1) == handCards.get(i).getRank().getSymbol().charAt(0))
                chosenRangeCards.add(handCards.get(i));
                handCards.remove(i);
            }
        }

        sc.close();
    }

    // after round ends reset chosen Range cards and wildcards? but hand cards stay the same
    public void resetCards(ArrayList<Card> cardList) {
        cardList.clear();
    }

    public int getPoints(){
        return points;
    }
}
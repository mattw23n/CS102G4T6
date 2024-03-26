/*
 * Application.java
 * 
 * CS102 G4 T6 - InBetween++
 * 
 * InBetween++ is a modification to the classic card game of InBetween,
 * where players bet that the cards they'll draw will be in between the range.
 * However, this version implements wildcards which can dramatically alter the game.
 * 
 */

import java.util.*;
import javax.swing.SwingUtilities;
import org.w3c.dom.ranges.Range;
import GUI.Window;
import components.Deck;
import components.Hand;
import components.Player;
import components.PlayerComparator;
import components.PlayerHand;
import utilities.DeckUtils;
import utilities.Utils;

public class Application {

    public static void runGUI (){
        // SwingUtilities.invokeLater(() -> {
            Window mainWindow = new Window();
            mainWindow.setVisible(true);
        // });
    }

    //check if round should end early
    public static boolean endRound(ArrayList<Player> players){
        int isOutCount = 0;
        for(Player p : players){
            if(p.isOut()){
                isOutCount++;
            }
        }

        return isOutCount >= players.size() - 1;
    }
    
    //Runs one round of the game
    public static boolean doRound(ArrayList<Player> players, Deck rangeDeck, Deck allDeck, int roundCount){

        System.out.println("\n=================================================\n");
        System.out.println("==================ROUND " + roundCount + " START====================");
        System.out.println("\n=================================================\n");
        
        Scanner scan = new Scanner(System.in);

        for(Player p : players){

            //skip player if no money
            if(p.isOut()){
                System.out.println("player " + p.getPlayerID() + "is skipped!");
                continue;
            }

            //Show player's hand
            Hand hand = p.getHand();
            p.playerHandToString();

            //select upper bound and lower bound
            //upper and lower bound will be removed from hand afterwards
            p.chooseRange();

            //make bet and check points
            int bet = p.makeBet();
            if(bet < 0){
                System.out.println("You are OUT!!!");
                p.setOut(true);
                continue;
            }
            p.setBet(bet);
            
            //draw card from deck and add to hand
            DeckUtils.dealCard(allDeck, hand);

            Utils.pointsProcessing(p, allDeck, rangeDeck);
            
            System.out.println("Your Current Points: " + p.getPoints());

            //check points
            if(p.getPoints() <= 1){
                System.out.println("You are OUT!!!");
                p.setOut(true);
            }

            //display hand after change
            p.playerHandToString();

            //move to next player
            System.out.println("\n=================================================\n");
            System.out.println("PLEASE PASS TO THE NEXT PLAYER");
            System.out.println("\n=================================================\n");

        }

        System.out.println("\n=================================================\n");
        System.out.println("==================ROUND " + roundCount + " END====================");
        System.out.println("\n=================================================\n");

        return endRound(players);
    }

    public static void main(String[] args) {
        //initializing deck of only range cards
        Deck rangeDeck = DeckUtils.initializeNumbers();

        //initialize deck of all cards
        Deck allDeck = DeckUtils.initializeWhole();

        // //initialize players
        Player p1 = new Player(1, 5, new PlayerHand());
        Player p2 = new Player(2, 5, new PlayerHand());
        Player p3 = new Player(3, 5, new PlayerHand());
        Player p4 = new Player(4, 5, new PlayerHand());

        ArrayList<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        //initialize each players hand
        for(Player p : players){
            DeckUtils.dealRange(rangeDeck, p.getHand(), 6);
        }
        
        //run game 3 times
        for(int i = 0 ; i < 3; i++){
            boolean endEarly = doRound(players, rangeDeck, allDeck, i + 1);

            //end early check
            if(endEarly){
                System.out.println("Round ended at Round" + i + 1);
                break;
            }
        }
        
        //find out the winner
        Collections.sort(players, new PlayerComparator());
        ArrayList<Player> winner = new ArrayList<>();
        winner.add(players.get(0));

        int maxPoints = players.get(0).getPoints();
        for(Player p : players){
            if(p.getPoints() == maxPoints && !winner.contains(p)){
                winner.add(p);
            }
        }

        //winner
        for(Player pw : winner){
            System.out.println("Player " + pw.getPlayerID() + " is the winner!!");
        }
        
        //participant
        for(Player p : players){
            System.out.println("Player " + p.getPlayerID() + ": " + p.getPoints());
        }

        System.out.println("Thank you for playing!");

    }
}

//Compile.bat and Run.bat runs this file

import java.util.List;

import javax.swing.SwingUtilities;

import GUI.Window;

public class Application {

    public static void initialize(){
        //initializing deck of 52
        Deck d1 = new Deck();
        Rank.setKingHigh();
        List r = Rank.VALUES_NUMBER;
        List s = Suit.VALUES;

        //adding cards to deck
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
    public static void runGUI (){
        SwingUtilities.invokeLater(() -> {
            // Window mainWindow = new Window();
            Window.showWindow();
            // mainWindow.setVisible(true);
        });
    }
<<<<<<< Updated upstream
=======

    public static void doRound(ArrayList<Player> players, Deck RangeDeck, Deck AllDeck, int round_count){
        //one round of the game

        Scanner scan = new Scanner(System.in);
        boolean isfirstRound = (round_count == 1) ? true : false;

        for(Player p : players){

            //each player has a turn
            Hand hand = p.getHand();
            String playerId = "Player " + p.getPlayerID() + "'s Hand";
            System.out.printf("=================%s=================\n", playerId);
            System.out.println(hand.toString());
            System.out.println("=================================================");

            //select upper bound and lower bound
            //upper and lower bound will be removed from hand afterwards
            ArrayList<Card> range = p.chooseRange();

            //make bet
            int temp = p.makeBet(isfirstRound);
            if(temp < 0){
                System.out.println("You are OUT!!!");
                continue;
            }
            p.setBet(temp);
            
            //draw card from deck and add to hand
            DealCard(AllDeck, hand);
            Card newest = hand.getCard(hand.getNumberOfCards() - 1);
            System.out.println("\nYOU GOT " + newest);

            //calculate points
            //Wildcard processing still messy, problem with looping n everything
            //Still cannot figure out Jack logic

            boolean isNonWild = false;

            do {
                if(isWildCard(newest)){
                    p.incrementWildcardCount();

                    //check if wildcard count >= 3
                    if (p.getWildcardCount() >= 3) { 
                        int currPoints = p.getPoints(); 
                        p.setPoints(currPoints - 1); 
                    }
                    
                    //if not jack
                    if(newest.getRank().getSymbol() != "j"){
                        p.processWildCard(hand, newest);

                    }else{
                        Scanner sc = new Scanner(System.in);
                        System.out.print("Choose range card to swap:");
                        String input2 = sc.nextLine();
                        Card cardToSwap = p.StringtoCard(input2);

                        DealCard(RangeDeck, hand);
                        Card swapped = hand.getCard(hand.getNumberOfCards() - 1);
                        Utils.processJack(p, cardToSwap, swapped);
                    }
                    
                    //call process wildcard
                    //remove the last card from the player's deck
                    hand.removeCard(newest);
                    DealCard(AllDeck, hand);
                }else{

                    int add = p.calculatePoints(hand, newest);
                    p.setPoints(add);
                    
                    isNonWild = true;
                    //remove the last card from the player's deck
                    hand.removeCard(newest);
                    break;
                    //calculate points
                }

                newest = hand.getCard(hand.getNumberOfCards() - 1);
                
            } while (isWildCard(newest));

            if(!isNonWild){
                System.out.println("upper" + p.getUpper() + "lower" + p.getLower());
                int add = p.calculatePoints(hand, newest);
                p.setPoints(add);
                
                //remove the last card from the player's deck
                hand.removeCard(newest);
            }
                


            // int add = p.calculatePoints(hand, newest, p, isWildCard(newest), range, AllDeck);
            // p.setPoints(add);
            System.out.println("Your Current Points: " + p.getPoints());

            
            System.out.println("\n=================YOUR CURRENT HAND=================\n" + hand);

            //move to next player
            System.out.println("\n=================================================\n");
            System.out.println("PLEASE PASS TO THE NEXT PLAYER");
            System.out.println("\n=================================================\n");
        }
    }


>>>>>>> Stashed changes
    public static void main(String[] args) {
        // initialize();
        runGUI();   
    }
}

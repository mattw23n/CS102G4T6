// import java.io.File;
import java.util.*;

public class Betting {

    public static int makeBet(int playerMoney, boolean isFirstRound) {
        
        // Set min bet
        int minBet = 1;
        
        int maxBet = playerMoney - 1;
        if (isFirstRound) {
            maxBet = 4;
        }

        // Initialise bet
        int bet = 0;
        
        boolean invalidInt = true;

        Scanner readBet = new Scanner(System.in);

        // Ask player to input their bet, if their bet is below minBet or if they entered a non-integer, ask again
        do {
            
            System.out.printf("The minimum bet is %d and the maximum bet is %d, please enter your bet: ", minBet, maxBet);
            try {
                bet = readBet.nextInt();
                
                if (bet < minBet) {
                    System.out.println("Please do not enter a bet lower than the minimum bet");
                    continue;

                } else if (bet > maxBet) {
                    System.out.println("Please do not enter a bet greater than the maximum bet");
                    continue;

                } else {
                    invalidInt = false;
                }

            } catch (InputMismatchException e) {
                System.out.println("PLease enter an integer value");
                readBet.next();
            }
            
        } while(invalidInt);

        readBet.close();

        return bet;
    }
    
}



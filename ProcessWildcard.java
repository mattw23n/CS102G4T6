public class ProcessWildcard {
    
    public static void processWildCard(Card c, Player p, Hand h, int current, int bet, boolean isWildCard) {

        if (c.getRank.getSymbol() != "j" && c.getRank.getSymbol() != "q" && c.getRank.getSymbol() != "k" && c.getRank.getSymbol() != "x") {
            return;
        }
        
        if (c.getRank.getSymbol() == "k") {
            int lower = h.getLower();
            lower -= 2;
            if (lower < 1) {
                lower = 1;
            }
            h.setLower(lower);
            
        } else if (c.getRank.getSymbol() == "k") {
            int upper = h.getUpper();
            upper += 2;
            if (upper > 10) {
                upper = 10;
            }
            h.setUpper(upper);

        } else if (c.getRank.getSymbol() == 'J') {
            swapCard(c);

        } else {
            int points = p.getBet() * -2;
            p.setAccount(p.getAccount() + points);

        }



    }

}

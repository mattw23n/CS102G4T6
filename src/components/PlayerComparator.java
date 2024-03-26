package components;
/*
 * PlayerComparator.java
 * 
 * Compares and sorts players ascendingly according to their points
 */
import java.util.Comparator;

public class PlayerComparator implements Comparator<Player>{

    public int compare(Player p1, Player p2) {
        return p2.getPoints() - p1.getPoints();

    }
    
}

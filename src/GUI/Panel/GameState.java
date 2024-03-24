package GUI.Panel;

import components.Player;

public class GameState {
    private boolean isRound;
    private int round;
    private Player currPlayer;
    public GameState(boolean isRound, int round, Player currPlayer){
        this.isRound = isRound;
        this.round = round;
        this.currPlayer = currPlayer;
    }
    public int getRound() {
        return round;
    }
    public void setRound(int round) {
        this.round = round;
    }
    public Player getCurrPlayer() {
        return currPlayer;
    }
    public void setCurrPlayer(Player currPlayer) {
        this.currPlayer = currPlayer;
    }

    public boolean getIsRound() {
        return isRound;
    }

    public void setIsRound(boolean isRound) {
        this.isRound = isRound;
    }
    
}

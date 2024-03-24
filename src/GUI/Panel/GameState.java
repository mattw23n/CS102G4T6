package GUI.Panel;

public class GameState {
    private bool isRound;
    private int round;
    private Player currPlayer;
    public GameState(bool isRound, int turn, int round, Player currPlayer){
        this.isRound = isRound;
        this.turn = turn;
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

    public bool getIsRound() {
        return isRound;
    }

    public void setIsRound(bool isRound) {
        this.isRound = isRound;
    }
    
}

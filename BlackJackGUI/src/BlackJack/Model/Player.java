package BlackJack.Model;

import java.util.ArrayList;

public class Player {
    private int ID;
    private double credit;
    private double bet;
    ArrayList <Card> hand = new ArrayList();
    private boolean BJ;
    private boolean out;
    private boolean stand;
    private boolean doubleBet;
    private boolean quit;




    public Player(double startCredit){
        BJ = false;
        out = false;
        stand = false;
        credit = startCredit;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getBet() {
        return bet;
    }

    public void setBet(double bet) {
        this.bet = bet;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setCard(Card newCard){
       this.hand.add(newCard);
    }

    public boolean BJ() {
        return BJ;
    }

    public void setBJ(boolean BJ) {
        this.BJ = BJ;
    }

    public boolean isOut() {
        return out;
    }
    public boolean isStand() {
        return stand;
    }
    public void setOut(boolean out) {
        this.out = out;
    }
    public void setStand(boolean stand) {
        this.stand = stand;
    }

    public boolean isDoubleBet() {
        return doubleBet;
    }

    public void setDoubleBet(boolean doubleBet) {
        this.doubleBet = doubleBet;
    }

    public boolean isQuit() {
        return quit;
    }

    public void setQuit(boolean quit) {
        this.quit = quit;
    }
}

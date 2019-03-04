import java.util.ArrayList;

public class Player {
    private String name;
    private double credit;
    private double bet;
    ArrayList <Card> hand = new ArrayList();
    private boolean BJ;
    private boolean out;
    private boolean stand;

    public Player(){
        int startCredit = 200;
        BJ = false;
        out = false;
        stand = false;
        credit = startCredit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Card getCard(int index){
            return this.hand.get(index);
    }

    public void setCard(Card newCard){
       this.hand.add(newCard);
    }

    public boolean isBJ() {
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
}

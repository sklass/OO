import java.util.ArrayList;

public class Player {
    private int ID;
    private String name;
    private int credit;
    private int bet;
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
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

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
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

public class Player {
    private int ID;
    private String name;
    private int credit;
    private int bet;
    private Card[] hand;
    private boolean BJ;
    private boolean out;

    public Player(){
        int startCredit = 200;
        BJ = false;
        out = false;
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

    public Card[] getHand() {
        return hand;
    }

    public Card getCard(int index) {

        return hand[index];
    }

    public void setHand(Card[] hand) {
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

    public void setOut(boolean out) {
        this.out = out;
    }
}

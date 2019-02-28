public class Player {
    int ID;
    String name;
    int credit;
    int bet;
    Card[] hand;

    public Player(){
        int startCredit = 200;
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
}

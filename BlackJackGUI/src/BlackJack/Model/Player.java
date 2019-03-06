package BlackJack.Model;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Player {
    private String name;
    private double credit;
    private double bet;
    ArrayList <Card> hand = new ArrayList();
    private boolean BJ;
    private boolean out;
    private boolean stand;
    private boolean doubleBet;
    private Pane cardPane;
    private TextField BetField;
    private Button BetButton;
    private Button TakeCardButton;
    private Button StandButton;
    private Button DoubleButton;
    private Button NotDoubleButton;
    private Button PlayAgainButton;
    private Button QuitButton;
    private Label CreditLabel;



    public Player(double startCredit){
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

   /* public Card getCard(int index){
            return this.hand.get(index);
    }
*/
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

    public boolean DoubleBet() {
        return doubleBet;
    }

    public void setDoubleBet(boolean doubleBet) {
        this.doubleBet = doubleBet;
    }

    public TextField getBetField() {
        return BetField;
    }

    public void setBetField(TextField betField) {
        BetField = betField;
    }

    public Button getBetButton() {
        return BetButton;
    }

    public void setBetButton(Button betButton) {
        BetButton = betButton;
    }

    public Button getTakeCardButton() {
        return TakeCardButton;
    }

    public void setTakeCardButton(Button takeCardButton) {
        TakeCardButton = takeCardButton;
    }

    public Button getStandButton() {
        return StandButton;
    }

    public void setStandButton(Button standButton) {
        StandButton = standButton;
    }

    public Button getDoubleButton() {
        return DoubleButton;
    }

    public void setDoubleButton(Button doubleButton) {
        DoubleButton = doubleButton;
    }

    public Button getNotDoubleButton() {
        return NotDoubleButton;
    }

    public void setNotDoubleButton(Button notDoubleButton) {
        NotDoubleButton = notDoubleButton;
    }

    public Pane getCardPane() {
        return cardPane;
    }

    public void setCardPane(Pane cardPane) {
        this.cardPane = cardPane;
    }

    public Label getCreditLabel() {
        return CreditLabel;
    }

    public void setCreditLabel(Label creditLabel) {
        CreditLabel = creditLabel;
    }

    public Button getPlayAgainButton() {
        return PlayAgainButton;
    }

    public void setPlayAgainButton(Button playAgainButton) {
        PlayAgainButton = playAgainButton;
    }

    public Button getQuitButton() {
        return QuitButton;
    }

    public void setQuitButton(Button quitButton) {
        QuitButton = quitButton;
    }
}

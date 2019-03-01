import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {
    int gamestatus;
    Player[] Players;
    Scanner input;
    CardDeck CardDeck;
    //Bank Bank;              //TODO Bank als Spieler definieren
    int numberOfCardDecks;
    int CardCounter;
    int minBet;
    int maxBet;

    public BlackJack(){
        gamestatus = 0;
        input = new Scanner(System.in);
        CardCounter = 0;
        numberOfCardDecks = 4;
        minBet = 10;
        maxBet = 250;
        GameStateHandler();
    }

    public void GameStateHandler(){
        while(gamestatus < 10) {
            switch (gamestatus) {
                case 0:
                    definePlayers();    //Spieler festlegen
                    gamestatus = 1;
                    break;
                case 1:
                    //defineBank();       // Bank erstellen
                    gamestatus = 2;
                    break;
                case 2:
                    CardDeck = new CardDeck(numberOfCardDecks); //kartendeck erstellen
                    gamestatus = 3;
                    break;
                case 3:
                    getPlayerBets();                        //Wetteinsätze abfragen
                    gamestatus = 4;
                    break;
                case 4:
                    drawFirstCard();    //Zu beginn des Spiels wird je eine karte an alle Spieler vergeben (inklusive Bank)
                    gamestatus = 5;
                    break;
                case 5:
                    drawSecondCard();
                    gamestatus = 6;
                    break;
                case 6:
                    anotherCard();    //Jeden spieler fragen ob er weitere Karten haben möchte
                    gamestatus = 7;
                    break;
                case 7:
                    BanksTurn();    //Nachdem alle Spieler ihre karten erhalten haben ist die bank dran
                    gamestatus = 8;
                    break;
                case 8:
                    checkResult();
                     gamestatus = 9;
                    break;

            }
        }
    }

    private void definePlayers(){
        int[] answers = new int[]{1,2,3,4,5,6};
        int numberOfPlayer = getUserInput("How many player?", answers);
        Players = new Player[numberOfPlayer+1];
        Players[0] = new Player();
        Players[0].setName("Bank");
        Players[0].setID(0);
        for(int i=1; i <= numberOfPlayer; i++){
            Player newPlayer = new Player();
            newPlayer.setName(getUserInput("Enter name for Player " + (i)));
            newPlayer.setID(i);
            Players[i] = newPlayer;
        }
    }

    private void getPlayerBets(){

        /////////////// Ein array mit allen gültigen Wettwerten erstellen
        int[] validAnswers = new int[maxBet-minBet+1];
        int j = 0;
        for(int i = minBet; i <= maxBet; i++){
            validAnswers[j] = i;
            j++;
        }
        ////////////

        for (int i=1; i<Players.length; i++){
            System.out.println("Your actual Credit is " + Players[i].getCredit());
            int bet = getUserInput(Players[i].getName() + " place your bet for this Round", validAnswers);
            while(bet > Players[i].getCredit()){
                System.out.println("Your actual Credit is " + Players[i].getCredit() + " , you cant bet more than you have :)");
                bet = getUserInput(Players[i].getName() + " place your bet for this Round", validAnswers);
            }
            Players[i].setCredit(Players[i].getCredit()-bet);
            Players[i].setBet(bet);
        }
    }

    private void drawFirstCard() { //jeder spieler inkl. Bank zieht eine karte
        for(Player player: Players){
            drawCard(player);
        }
    }

    private void drawSecondCard(){              //alle spieler ziehen eine zweite Karte (aber nicht die bank)
        for(int i=1; i < Players.length; i++){
            drawCard(Players[i]);

        }
    }

    private void anotherCard(){      //fragt die spieler solange nach weiteren karten wie sie nicht gewonnen, verloren oder keine weiteren karten haben wollen
        int[] validAnswers = {0,1};
        for(int i=1; i < Players.length; i++){
            while(!Players[i].isOut() && !Players[i].isStand() && !Players[i].isBJ()){
                showPlayerCards(Players[0]);            //karte der Bank anzeigen
                showPlayerCards(Players[i]);
                int answer = getUserInput(Players[i].getName() + " do you want another card?",validAnswers);
                if(answer == 1){
                    drawCard(Players[i]);
                }else{
                    Players[i].setStand(true);
                }
            }
        }
    }

    private void drawCard(Player player) {
            player.setCard(CardDeck.drawCard(CardCounter));
            CardCounter++;
    }

    private void showPlayerCards(Player player){
            System.out.println(player.getName() + "'s cards:");
            for(int j =0; j < player.getHand().size(); j++){
                System.out.println(player.getCard(j).getType() +" " + player.getCard(j).getName());
            }
            checkCardValues(player);
    }
/*
    private void showBankCards(){
        System.out.println("Bank's cards:");
        for(int j =0; j < Players[0].getHand().size(); j++){
            System.out.println(Players[0].getCard(j).getType() +" " + Players[0].getCard(j).getName());
        }
    }
*/
    private void BanksTurn(){
        drawCard(Players[0]);                   //zunächst zieht die Bank eine weitere Karte
        showPlayerCards(Players[0]);            //Die karten der Bank werden angezeigt
        int handValue = 0;
        handValue = getBankHandValue();

        while(!Players[0].isBJ() && !Players[0].isStand() && !Players[0].isOut()){
            if(handValue > 21){                     //hat die Bank mehr als 21 ist sie raus
                Players[0].setOut(true);
                System.out.println("The bank is out");
            }else if(handValue == 21){              //bei 21 Blackjack
                Players[0].setBJ(true);
                System.out.println("The bank has BlackJack");
            }
            if(handValue < 17){                     //bei weniger als 17 -> karte ziehen
                System.out.println("The bank takes another Card");
                drawCard(Players[0]);
                showPlayerCards(Players[0]);            //Die karten der Bank werden angezeigt
                handValue = getBankHandValue();     //und hand wert berechnen
            }else{                                  //ansonsten stehen
                System.out.println("The bank stands");
                Players[0].setStand(true);
            }
        }
    }

    private int getBankHandValue(){
        int totalValue = 0;
        int totalValueWithAce = 0;
        boolean hasAce = false;
        for(Card card: Players[0].getHand()){   //Alle karten der Bank prüfen

            totalValue += card.getValue();      //kartenwerte zusammenrechnen
            totalValueWithAce += card.getValue();
            if(card.getValue() == 1){           //prüfen ob ein ass vorhanden ist
                hasAce = true;
                totalValueWithAce = totalValueWithAce +10;
            }
        }
            if(totalValueWithAce > 21){         //Die Bank muss ein ass immer als 11 nutzen, es sei denn sie überschreitet 21
                return totalValue;
            }else{
                return totalValueWithAce;
            }

    }

    public void checkResult(){
        for (Player player : Players){
            if(!player.isOut()){
                //if(player)
            }
        }
    }

    private int getHandValue(Player player){
        int handValue = 0;
        for(Card card : player.getHand()){
            handValue += card.getValue();

        }

        return handValue;
    }

    private void checkCardValues(Player player){

            int playerTotalCardValue = 0;
            int playerTotalCardValueWithAce = 0;
            boolean hasAce = false;
            for(int cIndex =0; cIndex < player.getHand().size(); cIndex++){    //Alle Karten eines Spielers durchlaufen

                playerTotalCardValue += player.getCard(cIndex).getValue();     // Spieler Kartenwerte um Wert der aktuellen karte erhöhen
                playerTotalCardValueWithAce += player.getCard(cIndex).getValue();
                if(player.getCard(cIndex).getValue() == 1){                    //Prüfen ob der Spieler ein Ass hat
                    playerTotalCardValueWithAce += 10;            //Hat er ein Ass, werden alle bisher addierten Kartenwerte in einer neuen Variable gespeichert und 10 hinzu addiert
                    hasAce = true;
                }
            }
            if((playerTotalCardValue == 21 || playerTotalCardValueWithAce == 21) && player.getHand().size() < 3){   //Kartenwert von 21 max. 2 karten
                player.setBJ(true);
                System.out.println("Player " + player.getName() + " has a BlackJack!");
            }else if (playerTotalCardValue > 21) {
                System.out.println("Player " + player.getName() + " has more than 21 Points, hes out");
                player.setOut(true);
            }else if(hasAce){
                System.out.println("Player " + player.getName() + "'s cards got a total value of: " + playerTotalCardValueWithAce + " if your Ace counts as 11");
                System.out.println("Player " + player.getName() + "'s cards got a total value of: " + playerTotalCardValue + " if your Ace counts as 1");
            }else{
                System.out.println("Player " +player.getName() + "'s cards got a total value of: " + playerTotalCardValue);
            }
        System.out.println();
    }

    private int getUserInput(String question, int[] validAnswers){
        System.out.println(question);
        while(true){
            while(!input.hasNextInt()){
                System.out.println("Numbers only please!");
                input.next();
            }
            int answer = input.nextInt();

            for(int valid : validAnswers){
                if(answer == valid) return answer;
            }
            System.out.println("Only values between " + validAnswers[0] + " and " + validAnswers[validAnswers.length-1] + " are valid!");
        }
    }

    private String getUserInput(String question){
        System.out.println(question);
        return input.next();
    }
}

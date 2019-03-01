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
                    drawCards(1);    //Zu beginn des Spiels werden zwei karten an alle Spieler vergeben
                    gamestatus = 5;
                    break;
                case 5:
                     showPlayerCards();
                     showBankCards(1);
                     gamestatus = 6;
                    break;
                case 6:
                    //gamestatus=4;
                    break;
                case 7:

                    break;
                case 8:

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

    public void drawCards(int numberOfCards) {
        if ((CardCounter + numberOfCards) < (numberOfCardDecks * 52)) {
        for (int i = 1; i < Players.length; i++) {
            Card[] newCards = new Card[numberOfCards];
            for (int j = 0; j < numberOfCards; j++) {
                newCards[j] = CardDeck.drawCard(CardCounter);
                CardCounter++;
            }
            Players[i].setHand(newCards);
        }
        }
    }

    public void showPlayerCards(){

        for(int i=1; i< Players.length; i++){
            System.out.println("Player " + Players[i].getName() + "'s cards:");
            for(int j =0; j < Players[i].getHand().length; j++){
                System.out.println(Players[i].getCard(j).getType() +" " + Players[i].getCard(j).getName()); //+ " -> Card Value: " + Players[i].getCard(j).getValue());
            }
            checkCardValues(Players[i]);
        }
    }

    private void showBankCards(int numberOfCards){
        System.out.println("Bank's cards:");
        for(int j =0; j < numberOfCards; j++){
            System.out.println(Players[0].getCard(j).getType() +" " + Players[0].getCard(j).getName()); //+ " -> Card Value: " + Players[i].getCard(j).getValue());
        }
    }

    public void checkCardValues(Player player){

            int playerTotalCardValue = 0;
            int playerTotalCardValueWithAce = 0;
            boolean hasAce = false;
            for(int cIndex =0; cIndex < player.getHand().length; cIndex++){    //Alle Karten eines Spielers durchlaufen

                playerTotalCardValue += player.getCard(cIndex).getValue();     // Spieler Kartenwerte um Wert der aktuellen karte erhöhen
                playerTotalCardValueWithAce += player.getCard(cIndex).getValue();
                if(player.getCard(cIndex).getValue() == 1){                    //Prüfen ob der Spieler ein Ass hat
                    playerTotalCardValueWithAce += 10;            //Hat er ein Ass, werden alle bisher addierten Kartenwerte in einer neuen Variable gespeichert und 10 hinzu addiert
                    hasAce = true;
                }
            }

            if(playerTotalCardValue == 21 || playerTotalCardValueWithAce == 21){
                player.setBJ(true);
                System.out.println("Player " + player.getName() + " has a BlackJack!");
            }else if (playerTotalCardValue > 21) {
                System.out.println("Player " + player.getName() + " has more than 21 Points, hes out");
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

            for(int i = 0; i < validAnswers.length; i++){
                if(answer == validAnswers[i]) return answer;
            }
            System.out.println("Only values between " + validAnswers[0] + " and " + validAnswers[validAnswers.length-1] + " are valid!");
        }
    }

    private String getUserInput(String question){
        System.out.println(question);
        String answer = input.next();
        return  answer;
    }
}

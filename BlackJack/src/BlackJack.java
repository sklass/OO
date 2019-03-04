import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
//TODO output aufhübschen
//TODO tripple 7 insta win -> dreifacher einsatz als gewinn

 class BlackJack {
    private int gamestatus;         //Kontrolliert den Spielablauf in GameStateHandler() 
    private ArrayList <Player> Players; //Array List mit allen vorhandenen Spielern
    private Player Bank;            //Der Spieler der die Bank repäsentiert
    private Scanner input;          //Für Eingaben in getUserInput
    private CardDeck CardDeck;      //Das bzw. die Kartenspiele
    private int numberOfCardDecks;  //Anzahl der verwendeten Kartenspiele
    private int CardCounter;                //zählt mit jeder gezogenen karte eins hoch
    private int minBet;                     //minimaler Wetteinsatz
    private int maxBet;                     //maximaler Wetteinsatz

    BlackJack(){
        gamestatus = 0;
        input = new Scanner(System.in);
        CardCounter = 0;
        numberOfCardDecks = 4;
        minBet = 10;
        maxBet = 250;
        GameStateHandler();
    }

    private void GameStateHandler(){
        while(gamestatus < 10) {
            switch (gamestatus) {
                case 0:
                    definePlayers();    //Spieler festlegen
                    gamestatus = 2;
                    break;
                case 1:
                    unsetPlayerVars();       //unset all player vars
                    gamestatus = 2;
                    break;
                case 2:
                    CardDeck = new CardDeck(numberOfCardDecks); //kartendeck erstellen
                    gamestatus = 3;
                    break;
                case 3:
                    System.out.println(Players.size());
                    getPlayerBets();                        //Wetteinsätze abfragen
                    gamestatus = 4;
                    break;
                case 4:
                    drawCard(Bank);
                    drawStartCard();    //Zu beginn des Spiels wird je eine karte an alle Spieler vergeben (inklusive Bank)
                    gamestatus = 5;
                    break;
                case 5:
                    drawStartCard();   //Anschließend erhalten nur die spieler eine weitere karte
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
                    GameResult();  //Ist die Bank fertig wird das Ergebnis der spielrunde geprüft und angezeigt
                     gamestatus = 9;
                    break;
                case 9:
                    continueGame();  //Ist die Bank fertig wird das Ergebnis der spielrunde geprüft und angezeigt
                    if(playersLeft()) {
                        gamestatus = 1;
                    }else gamestatus = 11;
                    break;
            }
        }
    }

    private void definePlayers(){               //Anzahl der Spieler abfragen, sowie deren namen
        int[] answers = new int[]{1,2,3,4,5,6};
        int numberOfPlayer = getUserInput("How many player?", answers);
        Players = new ArrayList<>();
        Bank = new Player();
        Bank.setName("Bank");
        for(int i=0; i < numberOfPlayer; i++){
            Player newPlayer = new Player();
            newPlayer.setName(getUserInput("Enter name for Player " + (i)));
            Players.add(newPlayer);
        }
    }

    private void getPlayerBets(){   //Alle spieler (außer Bank) müssen einen einsatz machen
        /////////////// Ein array mit allen gültigen Wettwerten erstellen
        int[] validAnswers = new int[maxBet-minBet+1];
        int j = 0;
        for(int i = minBet; i <= maxBet; i++){
            validAnswers[j] = i;
            j++;
        }
        ////////////
        for(Player player : Players){
            System.out.println("Your actual Credit is " + player.getCredit());
            int bet = getUserInput(player.getName() + " place your bet for this Round", validAnswers);
            while(bet > player.getCredit()){
                System.out.println("Your actual Credit is " + player.getCredit() + " , you cant bet more than you have :)");
                bet = getUserInput(player.getName() + " place your bet for this Round", validAnswers);
            }
            player.setCredit(player.getCredit()-bet);
            player.setBet(bet);
        }
    }

    private void drawStartCard() { //jeder spieler inkl. Bank zieht eine karte

        for(Player player: Players){
            drawCard(player);
        }
    }

    private void anotherCard(){      //fragt die spieler solange nach weiteren karten wie sie nicht gewonnen, verloren haben oder keine weiteren karten haben wollen
        int[] validAnswers = {0,1};
        for(int i=0; i < Players.size(); i++){
            showPlayerCards(Bank);            //karte der Bank anzeigen
            showPlayerCards(Players.get(i));            //Karten des aktuellen spielers zeigen

            while(!Players.get(i).isOut() && !Players.get(i).isStand() && !Players.get(i).isBJ()){
                int answer = getUserInput(Players.get(i).getName() + " do you want another card?",validAnswers);
                if(answer == 1){
                    drawCard(Players.get(i));
                    showPlayerCards(Players.get(i));            //Karten des aktuellen spielers zeigen
                }else{
                    Players.get(i).setStand(true);
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

    private void BanksTurn(){
        drawCard(Bank);                   //zunächst zieht die Bank eine weitere Karte
        showPlayerCards(Bank);            //Die karten der Bank werden angezeigt
        int handValue = 0;
        handValue = getHandValue(Bank);

        while(!Bank.isBJ() && !Bank.isStand() && !Bank.isOut()){
            if(handValue > 21){                     //hat die Bank mehr als 21 ist sie raus
                Bank.setOut(true);
                System.out.println("The bank is out");
            }else if(handValue == 21){              //bei 21 Blackjack
                Bank.setBJ(true);
                System.out.println("The bank has BlackJack");
            }else if(handValue < 17){                     //bei weniger als 17 -> karte ziehen
                System.out.println("The bank takes another Card");
                drawCard(Bank);
                showPlayerCards(Bank);            //Die karten der Bank werden angezeigt
                handValue = getHandValue(Bank);     //und hand wert berechnen
            }else{                                  //ansonsten stehen
                System.out.println("The bank stands");
                Bank.setStand(true);
            }
        }
    }

    public void GameResult(){
        double winnings;
        for (Player player : Players){
            if(!player.isOut()){

                if(player.isBJ()){                                              //Spieler hat BlackJack
                    if(Bank.isBJ()){                                      //und Bank auch
                        player.setCredit(player.getCredit() + player.getBet()); //Spieler bekommt seinen einsatz zurück
                        System.out.println(player.getName() + "gets back his bet of " + player.getBet());
                    }else{                                                      //Spieler hat BJ aber bank nicht
                        winnings = player.getBet()*2.5;                           //Spieler erhält dreifachen einsatz
                        player.setCredit(player.getCredit() + winnings);
                        System.out.println(player.getName() + " wins " + winnings);
                    }
                }else if(Bank.isOut()){                                         //Ist die Bank über 21 und der spieler nicht
                    winnings = player.getBet()*2;                               // erhält er seinen doppelten einsatz als gewinn
                    player.setCredit(player.getCredit() + winnings);
                    System.out.println(player.getName() + " wins " + winnings);
                }else if(getHandValue(player) == getHandValue(Bank)){           //Spieler handwert = Bank
                    player.setCredit(player.getCredit() + player.getBet()); //Spieler bekommt seinen einsatz zurück
                    System.out.println(player.getName() + " gets back his bet of " + player.getBet());
                }else if(getHandValue(player) > getHandValue(Bank) ){
                    winnings = player.getBet()*2;                               // erhält er seinen doppelten einsatz als gewinn
                    player.setCredit(player.getCredit() + winnings);
                    System.out.println(player.getName() + " wins " + winnings);
                }
            }
        }
    }

    private void continueGame(){
        int[] validAnswers = {0,1};
        ArrayList <Player> remPlayers = new ArrayList<>();
        for(Player player : Players){
            if(player.getCredit() == 0){
                remPlayers.add(player);
                System.out.println("Sorry " + player.getName() + " your out. You have no money to play another round");
            }else if(getUserInput(player.getName() + " do you want to play another round?", validAnswers) == 0){
                remPlayers.add(player);
            }
        }
        for(Player player : remPlayers){
           Players.remove(player);
        }
    }
     public void unsetPlayerVars(){
        for (Player player : Players){
            player.getHand().clear();
            player.setBet(0);
            player.setStand(false);
            player.setBJ(false);
            player.setOut(false);
        }
        Bank.getHand().clear();
        Bank.setStand(false);
        Bank.setBJ(false);
        Bank.setOut(false);
     }

    private boolean playersLeft(){
        if(Players.size() == 0) return false;
        return true;
    }

    private int getHandValue(Player player){
        int handValue = 0;                      //HandWert init
        int aces = 0;
        for(Card card : player.getHand()){      //alle Karten eines spieler durchlaufen
            handValue += card.getValue();       //kartenwerte addieren
            if(card.getValue() == 1) aces++; //Anzahl asse zählen
        }
        if((aces == 1) && (handValue + 10) <= 21){                           //Wenn nur ein ass & und der Handwert +10 kleiner 21
            handValue += 10;                                                //Addiere für das ass 10 auf den Handwert (Ass ist entweder 1 oder 11 -> 1 ist der defaultwert für ein ass welcher in der for schleife bereits einberechnet wurde, daher nur +10)
        }
        return handValue;
    }

    private void checkCardValues(Player player){
            int handValue = getHandValue(player);
            if(handValue == 21){                    //Kartenwert von 21
                if(player.getHand().size() < 3){    //und max. 2 karten
                    player.setBJ(true);             //BlackJack!
                    System.out.println(player.getName() + " has a BlackJack!");
                }else{
                    player.setStand(true);          //Kartenwert 21 und mehr als 3 karten -> stand
                    System.out.println(player.getName() + " has reached 21 points. stand!");
                }

            }else if (handValue > 21) {
                System.out.println(player.getName() + " has more than 21 Points, hes out");
                player.setOut(true);
            }else{
                System.out.println(player.getName() + "'s cards total value: " + handValue);
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

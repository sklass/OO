import java.util.Scanner;

public class BlackJack {
    int gamestatus;
    Player[] Players;
    Scanner input;
    CardDeck CardDeck;
    Bank Bank;
    int CardCounter;

    public BlackJack(){
        gamestatus = 0;
        input = new Scanner(System.in);
        CardCounter = 0;
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
                    defineBank();       // Bank erstellen
                    gamestatus = 2;
                    break;
                case 2:
                    CardDeck = new CardDeck(4); //kartendeck erstellen
                    gamestatus = 3;
                    break;
                case 3:
                    getPlayerBets();                        //Wetteinsätze abfragen
                    gamestatus = 4;
                    break;
                case 4:
                    drawPlayerCards(2);    //Zu beginn des Spiels werden zwei karten an alle Spieler vergeben
                    drawBankCards(2);       //und auch an die Bank
                    gamestatus = 5;
                    break;
                case 5:
                     showCards();
                     gamestatus = 6;
                    break;
                case 6:

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
        Players = new Player[numberOfPlayer];
        for(int i=0; i < numberOfPlayer; i++){
            Player newPlayer = new Player();
            newPlayer.setName(getUserInput("Enter name for Player " + (i+1)));
            newPlayer.setID(i);
            Players[i] = newPlayer;
        }
    }

    private void defineBank(){
        Bank = new Bank();
        Bank.setMinBet(10);
        Bank.setMaxBet(250);
    }

    private void getPlayerBets(){

        /////////////// Ein array mit allen gültigen Wettwerten erstellen
        int[] validAnswers = new int[Bank.getMaxBet()-Bank.getMinBet()+1];
        int j = 0;
        for(int i = Bank.getMinBet(); i <= Bank.getMaxBet(); i++){
            validAnswers[j] = i;
            j++;
        }
        ////////////

        for (int i=0; i<Players.length; i++){
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

    public void drawPlayerCards(int numberOfCards){
        for(int i=0; i< Players.length; i++){
            Card[] newCards = new Card[numberOfCards];
            for(int j=0; j < numberOfCards; j++){
                newCards[j] = CardDeck.drawCard(CardCounter);
                CardCounter++;
            }
            Players[i].setHand(newCards);
        }
    }

    public void drawBankCards(int numberOfCards){
        Card[] newCards = new Card[numberOfCards];
        for(int j=0; j < numberOfCards; j++){
            newCards[j] = CardDeck.drawCard(CardCounter);
            CardCounter++;
        }
        Bank.setHand(newCards);
    }

    public void showCards(){

        for(int i=0; i< Players.length; i++){
            int totalValue = 0;
            System.out.println("Player " + Players[i].getName() + "received the following cards:");
            for(int j =0; j < Players[i].getHand().length; j++){
                System.out.println(Players[i].getCard(j).getType() +" " + Players[i].getCard(j).getName() + " -> Card Value: " + Players[i].getCard(j).getValue());
                totalValue += Players[i].getCard(j).getValue();
            }
            System.out.println("Player " + Players[i].getName() + "'s cards got a total value of: " + totalValue + "\n");
        }
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

package BlackJack.Model;

import javafx.scene.image.Image;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CardDeck {
    private int numberOfCardsinDeck;
    private int numberOfDecks;
    private Card[] cards;       //Array das alle Karen beinhaltet


    public CardDeck(int numberOfDecks) {
        numberOfCardsinDeck = 52;         //gesamt Anzahl Karten in einem Deck
        this.numberOfDecks = numberOfDecks;
        int cardsTotal = numberOfDecks * numberOfCardsinDeck;
        cards = new Card[cardsTotal];    //Array das alle Karten beinhaltet
        createCardDeck();                   //Methode zum anlegen aller Spielkarten
        shuffle(cards);                     //Methode zum mischen der karten
        //printCards();                       //methode zur anzeige der Karteneigenschaften - Debug only!
    }


    //Erzeugt Kartenspiel(e), Karten von 2-Ass jeweils 4 mal
    private void createCardDeck(){
        Image AH = new Image("/BlackJack/img/cards/AH.png");
        Image AS = new Image("/BlackJack/img/cards/AS.png");
        Image AD = new Image("/BlackJack/img/cards/AD.png");
        Image AC = new Image("/BlackJack/img/cards/AC.png");

        Image KH = new Image("/BlackJack/img/cards/KH.png");
        Image KS = new Image("/BlackJack/img/cards/KS.png");
        Image KD = new Image("/BlackJack/img/cards/KD.png");
        Image KC = new Image("/BlackJack/img/cards/KC.png");

        Image QH = new Image("/BlackJack/img/cards/QH.png");
        Image QS = new Image("/BlackJack/img/cards/QS.png");
        Image QD = new Image("/BlackJack/img/cards/QD.png");
        Image QC = new Image("/BlackJack/img/cards/QC.png");

        Image JH = new Image("/BlackJack/img/cards/JH.png");
        Image JS = new Image("/BlackJack/img/cards/JS.png");
        Image JD = new Image("/BlackJack/img/cards/JD.png");
        Image JC = new Image("/BlackJack/img/cards/JC.png");

        Image TenH = new Image("/BlackJack/img/cards/10H.png");
        Image TenS = new Image("/BlackJack/img/cards/10S.png");
        Image TenD = new Image("/BlackJack/img/cards/10D.png");
        Image TenC = new Image("/BlackJack/img/cards/10C.png");

        Image NineH = new Image("/BlackJack/img/cards/9H.png");
        Image NineS = new Image("/BlackJack/img/cards/9S.png");
        Image NineD = new Image("/BlackJack/img/cards/9D.png");
        Image NineC = new Image("/BlackJack/img/cards/9C.png");

        Image EightH = new Image("/BlackJack/img/cards/8H.png");
        Image EightS = new Image("/BlackJack/img/cards/8S.png");
        Image EightD = new Image("/BlackJack/img/cards/8D.png");
        Image EightC = new Image("/BlackJack/img/cards/8C.png");

        Image SevenH = new Image("/BlackJack/img/cards/7H.png");
        Image SevenS = new Image("/BlackJack/img/cards/7S.png");
        Image SevenD = new Image("/BlackJack/img/cards/7D.png");
        Image SevenC = new Image("/BlackJack/img/cards/7C.png");

        Image SixH = new Image("/BlackJack/img/cards/6H.png");
        Image SixS = new Image("/BlackJack/img/cards/6S.png");
        Image SixD = new Image("/BlackJack/img/cards/6D.png");
        Image SixC = new Image("/BlackJack/img/cards/6C.png");

        Image FiveH = new Image("/BlackJack/img/cards/5H.png");
        Image FiveS = new Image("/BlackJack/img/cards/5S.png");
        Image FiveD = new Image("/BlackJack/img/cards/5D.png");
        Image FiveC = new Image("/BlackJack/img/cards/5C.png");

        Image FourH = new Image("/BlackJack/img/cards/4H.png");
        Image FourS = new Image("/BlackJack/img/cards/4S.png");
        Image FourD = new Image("/BlackJack/img/cards/4D.png");
        Image FourC = new Image("/BlackJack/img/cards/4C.png");

        Image ThreeH = new Image("/BlackJack/img/cards/3H.png");
        Image ThreeS = new Image("/BlackJack/img/cards/3S.png");
        Image ThreeD = new Image("/BlackJack/img/cards/3D.png");
        Image ThreeC = new Image("/BlackJack/img/cards/3C.png");

        Image TwoH = new Image("/BlackJack/img/cards/2H.png");
        Image TwoS = new Image("/BlackJack/img/cards/2S.png");
        Image TwoD = new Image("/BlackJack/img/cards/2D.png");
        Image TwoC = new Image("/BlackJack/img/cards/2C.png");

        int i = 0;
        for(int decks = 0; decks < numberOfDecks; decks++) {    //Erstellt die vorgegebene Anzahl an KarFourdecks
            for (int value = 1; value <= 13; value++) { //Karten von 1(Ass) - 13(König) erzeugen
                for (int type = 0; type < 4; type++) { //jede karte vier mal erzeugen
                    Card newCard = new Card();  //Karten objekt erzeugen
                    cards[i] = newCard;         //Kartenobjekt in Array cards legen
                    if (value > 10) {                 //Wenn value größer Zehn wird als Kartenwert trotzdem 10 verwendet
                        cards[i].setValue(10);
                    } else {
                        cards[i].setValue(value);//Ansonsten wird value als kartenwert gesetzt
                    }
/*
                    switch (type) {                      //Jede Karte wird 4mal erstellt , je einmal Herz,Pik,Karo,Kreuz
                        case 0: //herz
                            cards[i].setType("Herz");
                            break;
                        case 1: //piek
                            cards[i].setType("Pik");
                            break;
                        case 2: //karo
                            cards[i].setType("Karo");
                            break;
                        case 3: //kreuz
                            cards[i].setType("Kreuz");
                            break;
                    }
*/
                    switch (value) {                          //Zusätzlich zum Wert und Typ wird auch der Name der Karte definiert (hauptsächlich für Bube, Dame, König)
                        case 1:
                            cards[i].setName("Ass");
                            switch (type) {                      //Jede Karte wird 4mal erstellt , je einmal Herz,Pik,Karo,Kreuz
                                case 0: //herz
                                    cards[i].setImg(AH);
                                    break;
                                case 1: //piek
                                    cards[i].setType("Pik");
                                    cards[i].setImg(AS);
                                    break;
                                case 2: //karo
                                    cards[i].setType("Karo");
                                    cards[i].setImg(AD);
                                    break;
                                case 3: //kreuz
                                    cards[i].setType("Kreuz");
                                    cards[i].setImg(AC);
                                    break;
                            }
                            break;
                        case 2:
                            cards[i].setName("Zwei");
                            switch (type) {                      //Jede Karte wird 4mal erstellt , je einmal Herz,Pik,Karo,Kreuz
                                case 0: //herz
                                    cards[i].setImg(TwoH);
                                    break;
                                case 1: //piek
                                    cards[i].setType("Pik");
                                    cards[i].setImg(TwoS);
                                    break;
                                case 2: //karo
                                    cards[i].setType("Karo");
                                    cards[i].setImg(TwoD);
                                    break;
                                case 3: //kreuz
                                    cards[i].setType("Kreuz");
                                    cards[i].setImg(TwoC);
                                    break;
                            }
                            break;
                        case 3:
                            cards[i].setName("Drei");
                            switch (type) {                      //Jede Karte wird 4mal erstellt , je einmal Herz,Pik,Karo,Kreuz
                                case 0: //herz
                                    cards[i].setImg(ThreeH);
                                    break;
                                case 1: //piek
                                    cards[i].setType("Pik");
                                    cards[i].setImg(ThreeS);
                                    break;
                                case 2: //karo
                                    cards[i].setType("Karo");
                                    cards[i].setImg(ThreeD);
                                    break;
                                case 3: //kreuz
                                    cards[i].setType("Kreuz");
                                    cards[i].setImg(ThreeC);
                                    break;
                            }
                            break;
                        case 4:
                            cards[i].setName("Vier");
                            switch (type) {                      //Jede Karte wird 4mal erstellt , je einmal Herz,Pik,Karo,Kreuz
                                case 0: //herz
                                    cards[i].setImg(FourH);
                                    break;
                                case 1: //piek
                                    cards[i].setType("Pik");
                                    cards[i].setImg(FourS);
                                    break;
                                case 2: //karo
                                    cards[i].setType("Karo");
                                    cards[i].setImg(FourD);
                                    break;
                                case 3: //kreuz
                                    cards[i].setType("Kreuz");
                                    cards[i].setImg(FourC);
                                    break;
                            }
                            break;
                        case 5:
                            cards[i].setName("Fünf");
                            switch (type) {                      //Jede Karte wird 4mal erstellt , je einmal Herz,Pik,Karo,Kreuz
                                case 0: //herz
                                    cards[i].setImg(FiveH);
                                    break;
                                case 1: //piek
                                    cards[i].setType("Pik");
                                    cards[i].setImg(FiveS);
                                    break;
                                case 2: //karo
                                    cards[i].setType("Karo");
                                    cards[i].setImg(FiveD);
                                    break;
                                case 3: //kreuz
                                    cards[i].setType("Kreuz");
                                    cards[i].setImg(FiveC);
                                    break;
                            }
                            break;
                        case 6:
                            cards[i].setName("Sechs");
                            switch (type) {                      //Jede Karte wird 4mal erstellt , je einmal Herz,Pik,Karo,Kreuz
                                case 0: //herz
                                    cards[i].setImg(SixH);
                                    break;
                                case 1: //piek
                                    cards[i].setType("Pik");
                                    cards[i].setImg(SixS);
                                    break;
                                case 2: //karo
                                    cards[i].setType("Karo");
                                    cards[i].setImg(SixD);
                                    break;
                                case 3: //kreuz
                                    cards[i].setType("Kreuz");
                                    cards[i].setImg(SixC);
                                    break;
                            }
                            break;
                        case 7:
                            cards[i].setName("Sieben");
                            switch (type) {                      //Jede Karte wird 4mal erstellt , je einmal Herz,Pik,Karo,Kreuz
                                case 0: //herz
                                    cards[i].setImg(SevenH);
                                    break;
                                case 1: //piek
                                    cards[i].setType("Pik");
                                    cards[i].setImg(SevenS);
                                    break;
                                case 2: //karo
                                    cards[i].setType("Karo");
                                    cards[i].setImg(SevenD);
                                    break;
                                case 3: //kreuz
                                    cards[i].setType("Kreuz");
                                    cards[i].setImg(SevenC);
                                    break;
                            }
                            break;
                        case 8:
                            cards[i].setName("Acht");
                            switch (type) {                      //Jede Karte wird 4mal erstellt , je einmal Herz,Pik,Karo,Kreuz
                                case 0: //herz
                                    cards[i].setImg(EightH);
                                    break;
                                case 1: //piek
                                    cards[i].setType("Pik");
                                    cards[i].setImg(EightS);
                                    break;
                                case 2: //karo
                                    cards[i].setType("Karo");
                                    cards[i].setImg(EightD);
                                    break;
                                case 3: //kreuz
                                    cards[i].setType("Kreuz");
                                    cards[i].setImg(EightC);
                                    break;
                            }
                            break;
                        case 9:
                            cards[i].setName("Neun");
                            switch (type) {                      //Jede Karte wird 4mal erstellt , je einmal Herz,Pik,Karo,Kreuz
                                case 0: //herz
                                    cards[i].setImg(NineH);
                                    break;
                                case 1: //piek
                                    cards[i].setType("Pik");
                                    cards[i].setImg(NineS);
                                    break;
                                case 2: //karo
                                    cards[i].setType("Karo");
                                    cards[i].setImg(NineD);
                                    break;
                                case 3: //kreuz
                                    cards[i].setType("Kreuz");
                                    cards[i].setImg(NineC);
                                    break;
                            }
                            break;
                        case 10:
                            cards[i].setName("Zehn");
                            switch (type) {                      //Jede Karte wird 4mal erstellt , je einmal Herz,Pik,Karo,Kreuz
                                case 0: //herz
                                    cards[i].setImg(TenH);
                                    break;
                                case 1: //piek
                                    cards[i].setType("Pik");
                                    cards[i].setImg(TenS);
                                    break;
                                case 2: //karo
                                    cards[i].setType("Karo");
                                    cards[i].setImg(TenD);
                                    break;
                                case 3: //kreuz
                                    cards[i].setType("Kreuz");
                                    cards[i].setImg(TenC);
                                    break;
                            }
                            break;
                        case 11:
                            cards[i].setName("Bube");
                            switch (type) {                      //Jede Karte wird 4mal erstellt , je einmal Herz,Pik,Karo,Kreuz
                                case 0: //herz
                                    cards[i].setImg(JH);
                                    break;
                                case 1: //piek
                                    cards[i].setType("Pik");
                                    cards[i].setImg(JS);
                                    break;
                                case 2: //karo
                                    cards[i].setType("Karo");
                                    cards[i].setImg(JD);
                                    break;
                                case 3: //kreuz
                                    cards[i].setType("Kreuz");
                                    cards[i].setImg(JC);
                                    break;
                            }
                            break;
                        case 12:
                            cards[i].setName("Dame");
                            switch (type) {                      //Jede Karte wird 4mal erstellt , je einmal Herz,Pik,Karo,Kreuz
                                case 0: //herz
                                    cards[i].setImg(QH);
                                    break;
                                case 1: //piek
                                    cards[i].setType("Pik");
                                    cards[i].setImg(QS);
                                    break;
                                case 2: //karo
                                    cards[i].setType("Karo");
                                    cards[i].setImg(QD);
                                    break;
                                case 3: //kreuz
                                    cards[i].setType("Kreuz");
                                    cards[i].setImg(QC);
                                    break;
                            }
                            break;
                        case 13:
                            cards[i].setName("König");
                            switch (type) {                      //Jede Karte wird 4mal erstellt , je einmal Herz,Pik,Karo,Kreuz
                                case 0: //herz
                                    cards[i].setImg(KH);
                                    break;
                                case 1: //piek
                                    cards[i].setType("Pik");
                                    cards[i].setImg(KS);
                                    break;
                                case 2: //karo
                                    cards[i].setType("Karo");
                                    cards[i].setImg(KD);
                                    break;
                                case 3: //kreuz
                                    cards[i].setType("Kreuz");
                                    cards[i].setImg(KC);
                                    break;
                            }
                            break;
                    }
                    i++;                                //nach dem erstellen einer Karte wird der Counter um eins erhöht, damit die nächste karte im nächsten Feld des Arrays angelegt wird
                }
            }
        }
    }

    private void printCards(){
        for(int i = 0; i < cards.length; i++){
            System.out.println("KartenWert: " + cards[i].getValue() + " - KartenTyp: " + cards[i].getType() + " - Kartenname: " + cards[i].getName());
        }

    }

    //Methode zum mischen der karten
    //tauscht die position von zwei karten nach dem zufallsprinzip solange bis alle karten einmal getauscht wurden
    static void shuffle(Card[] CardArray)
    {
        Random rnd = ThreadLocalRandom.current();
        for (int i = CardArray.length - 1; i > 0; i--)  //array von hinten nach vorne durchlaufen
        {
            int randomIndex = rnd.nextInt(i + 1);     //zufallszahl zwischen 0 und 52 generieren

            Card temp = CardArray[randomIndex];       //zufällige Karte in temp speichern
            CardArray[randomIndex] = CardArray[i];    //Anstelle der zufällig bestimmten karte, die karte speichern die anhand der for schleife gerade dran ist
            CardArray[i] = temp;                      //an der position der per schleife definierten karte wird nun die zufällig bestimmte karte abgelegt
        }
    }

    public Card drawCard(int whichCard){
        Card drawnCard = cards[whichCard];
        return drawnCard;
    }
}

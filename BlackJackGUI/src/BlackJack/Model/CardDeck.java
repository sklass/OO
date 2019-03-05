package BlackJack.Model;

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
        int i = 0;
        for(int decks = 0; decks < numberOfDecks; decks++) {    //Erstellt die vorgegebene Anzahl an Kartendecks
            for (int value = 1; value <= 13; value++) { //Karten von 1(Ass) - 13(König) erzeugen
                for (int type = 0; type < 4; type++) { //jede karte vier mal erzeugen
                    Card newCard = new Card();  //Karten objekt erzeugen
                    cards[i] = newCard;         //Kartenobjekt in Array cards legen
                    if (value > 10) {                 //Wenn value größer Zehn wird als Kartenwert trotzdem 10 verwendet
                        cards[i].setValue(10);
                    } else {
                        cards[i].setValue(value);//Ansonsten wird value als kartenwert gesetzt
                    }

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
                    switch (value) {                          //Zusätzlich zum Wert und Typ wird auch der Name der Karte definiert (hauptsächlich für Bube, Dame, König)
                        case 1:
                            cards[i].setName("Ass");
                            switch (type) {                      //Jede Karte wird 4mal erstellt , je einmal Herz,Pik,Karo,Kreuz
                                case 0: //herz
                                    cards[i].setImgPath(/BlackJack/img/cards/AH.jpg);
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
                            break;
                        case 2:
                            cards[i].setName("Zwei");
                            break;
                        case 3:
                            cards[i].setName("Drei");
                            break;
                        case 4:
                            cards[i].setName("Vier");
                            break;
                        case 5:
                            cards[i].setName("Fünf");
                            break;
                        case 6:
                            cards[i].setName("Sechs");
                            break;
                        case 7:
                            cards[i].setName("Sieben");
                            break;
                        case 8:
                            cards[i].setName("Acht");
                            break;
                        case 9:
                            cards[i].setName("Neun");
                            break;
                        case 10:
                            cards[i].setName("Zehn");
                            break;
                        case 11:
                            cards[i].setName("Bube");
                            break;
                        case 12:
                            cards[i].setName("Dame");
                            break;
                        case 13:
                            cards[i].setName("König");
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

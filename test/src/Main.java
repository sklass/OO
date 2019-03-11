import Bar.Getränke.Mischgetränk;
import Bar.Getränke.Saefte.Apfelsaft;
import Bar.Getränke.Saefte.Kiwisaft;
import Bar.Getränke.Spirituosen.Tequila;
import Bar.Getränke.Spirituosen.Wodka;

public class Main {


    public static void main(String[] args) {
        Apfelsaft aSaft = new Apfelsaft(5,true, false);
        aSaft.schuetteln();
        aSaft.info();

        Kiwisaft kSaft = new Kiwisaft(3,false);
        kSaft.info();

        Wodka wodkaO = new Wodka(1,21.5,"Orange");
        wodkaO.info();

        Wodka wodkaE = new Wodka(3, 32.5,"Energy");
        wodkaE.info();

        Tequila tequila = new Tequila(2,40,"silber");
        tequila.info();

        Mischgetränk wildMix = new Mischgetränk(10);
        wildMix.neuerInhalt(wodkaO);
        wildMix.neuerInhalt(aSaft);
        wildMix.mix();
        wildMix.info();
    }
}

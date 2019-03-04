import java.lang.reflect.Array;
import java.util.ArrayList;

public class Verwaltung {
    Kurs Kurs1;
    Kurs Kurs2;
    Kurs Kurs3;
    ArrayList <Teilnehmer> Teilnehmerliste;

    public void start(){

        ArrayList<String> Namensliste = new ArrayList<>();
        Namensliste.add("Ali Anani");
        Namensliste.add("Dawait Ayane");
        Namensliste.add("Ingo Türke");
        Namensliste.add("Ivo Dimov");
        Namensliste.add("Kateryna Romanova");
        Namensliste.add("Kerstin Barthold");
        Namensliste.add("Manuel Stark");
        Namensliste.add("Mirko Wittwer");
        Namensliste.add("Philipp Schnorr");
        Namensliste.add("Roger Marsch");
        Namensliste.add("Sebastian Klaß");
        Namensliste.add("Sören Künzel");
        Namensliste.add("Sven Bergamnn");
        Namensliste.add("Thorsten Klein");
        Namensliste.add("Viktor Stoppel");

        erstelleKurse();
        erstelleTeilnehmer(Namensliste);
        zuordnenTeilnehmer();
        zeigeÜbersicht();

    }


    private void erstelleKurse(){
        Kurs1 = new Kurs("Kurs1");
        Kurs2 = new Kurs("Kurs2");
        Kurs3 = new Kurs("Kurs3");

    }

    private void erstelleTeilnehmer(ArrayList <String> Namensliste) {
        Teilnehmerliste = new ArrayList<>();

        for (String Name : Namensliste) {
            Teilnehmer neuerTeilnehmer = new Teilnehmer();
            String[] NameString = Name.split(" ");
            neuerTeilnehmer.setVorname(NameString[0]);
            neuerTeilnehmer.setNachname(NameString[1]);
            Teilnehmerliste.add(neuerTeilnehmer);
        }
    }

    public void zuordnenTeilnehmer() {                  //Jeder der drei Kurse bekommt 5 teilnehmer
        for(int i = 0; i < Teilnehmerliste.size(); i++)
        if(i < 5){
            Kurs1.hinzufügenTeilnehmer(Teilnehmerliste.get(i));
        }else if(i < 10 ){
            Kurs2.hinzufügenTeilnehmer(Teilnehmerliste.get(i));
        }else if(i < 15 ){
            Kurs3.hinzufügenTeilnehmer(Teilnehmerliste.get(i));
        }
    }

    public void zeigeÜbersicht(){
        Kurs1.zeigeTeilnehmer();
        Kurs2.zeigeTeilnehmer();
        Kurs3.zeigeTeilnehmer();
    }
}

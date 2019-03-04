import java.util.ArrayList;

public class Kurs {
    String Kursname;
    ArrayList <Teilnehmer> TeilnehmerListe;

   public Kurs(String Name){
       this.Kursname = Name;
       TeilnehmerListe = new ArrayList<>();
   }

    public void hinzufügenTeilnehmer (Teilnehmer teilnehmer) {
        TeilnehmerListe.add(teilnehmer);
    }

    public void zeigeTeilnehmer(){
        System.out.println();
        System.out.println("Teilnehmer von " + Kursname);
       for(Teilnehmer teilnehmer: TeilnehmerListe){
           System.out.println(teilnehmer.getVorname() + " " + teilnehmer.getNachname());
       }
    }

    public void entferneTeilnehmer (String Vorname, String Nachname){
       for(Teilnehmer teilnehmer : TeilnehmerListe){
           if(Vorname.equals(teilnehmer.getVorname())&& Nachname.equals(teilnehmer.getNachname())){
               TeilnehmerListe.remove(teilnehmer);
           }
       }
    }
}

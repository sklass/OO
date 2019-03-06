package Krankenhaus;

public class Arzt {
    private String vorname;
    private String nachname;

    public Arzt(String vorname, String nachname){
        this.vorname = vorname;
        this.nachname = nachname;
    }

    public void patientBehandeln(){
        System.out.println("Ich bin in einer Behandlung");
    }

    public void patientBehandeln(String name){
        System.out.println("Ich behandle gerade " + name);
    }

    public void patientBehandeln(int anzahl){
        System.out.println("Ich behandle gerade " + anzahl + " patienten");
    }



}

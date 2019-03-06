package Krankenhaus.Ärzte;

import Krankenhaus.Arzt;

public class Chirurg extends Arzt {

    public Chirurg(String vorname, String nachname){
        super(vorname,nachname);
    }

    @Override
    public void patientBehandeln(){
        System.out.println("Ich operiere");
    }

    @Override
    public void patientBehandeln(String name){
        System.out.println("Ich operiere gerade " + name);
    }

    @Override
    public void patientBehandeln(int anzahl){
        System.out.println("Ich operiere gerade " + anzahl + " patienten");
    }
}

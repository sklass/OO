package Krankenhaus.Ärzte;

import Krankenhaus.Arzt;

public class Hausarzt extends Arzt {

    public Hausarzt(String vorname, String nachname){
        super(vorname,nachname);
    }

    @Override
    public void patientBehandeln(){
        System.out.println("Ich besuche gerade einen Patienten");
    }

    @Override
    public void patientBehandeln(String name){
        System.out.println("Ich besuche gerade " + name);
    }

    @Override
    public void patientBehandeln(int anzahl){
        System.out.println("Ich besuche gerade " + anzahl + " patienten");
    }
}

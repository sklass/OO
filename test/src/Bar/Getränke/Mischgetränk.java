package Bar.Getränke;

import Bar.Getränk;
import Bar.Getränke.Saefte.Apfelsaft;
import Bar.Getränke.Saefte.Kiwisaft;
import Bar.Getränke.Spirituosen.Tequila;
import Bar.Getränke.Spirituosen.Wodka;

import java.util.ArrayList;

public class Mischgetränk extends Getränk{
    private ArrayList <Getränk> Inhalte = new ArrayList();
    private String farbe;
    private String name;
    private String aroma;
    private boolean klar;
    private boolean fruchtfleisch;
    private double alkoholgehalt;


    public Mischgetränk(int Volumen){
        super(Volumen);
        this.farbe = null;
        this.aroma = null;
        this.alkoholgehalt = 0.0;
        this.klar = true;
        this.fruchtfleisch = false;
        this.name = "Mischgetränk aus ";
    }

    public ArrayList<Getränk> getInhalte() {
        return Inhalte;
    }

    public void neuerInhalt(Getränk inhalt){
        Inhalte.add(inhalt);
    }

    public void mix(){
        for(int i=0; i< Inhalte.size(); i++){
            if(Inhalte.get(i) instanceof Tequila){
                Tequila tequila = (Tequila)Inhalte.get(i);
                this.farbe = tequila.getFarbe();
                this.alkoholgehalt = tequila.getAlkoholgehalt();
                this.name += " tequila";
            }else if(Inhalte.get(i) instanceof Wodka){

                Wodka wodka = (Wodka)Inhalte.get(i);
                this.alkoholgehalt = wodka.getAlkoholgehalt();
                this.aroma = wodka.getAroma();
                this.name += " wodka";
            }else if(Inhalte.get(i) instanceof Apfelsaft){

                Apfelsaft apflesaft = (Apfelsaft)Inhalte.get(i);
                this.klar = apflesaft.getKlar();
                this.fruchtfleisch = apflesaft.getFruchtfleisch();
                this.name += " apfelsaft";
            }else if(Inhalte.get(i) instanceof Kiwisaft){

                Kiwisaft kiwisaft = (Kiwisaft) Inhalte.get(i);
                this.fruchtfleisch = kiwisaft.getFruchtfleisch();
                this.name += " kiwisaft";
            }
        }
    }

    public void info(){
        String Fruchtfleisch = this.fruchtfleisch ?  "mit Fruchtfleisch" : "ohne Fruchtfleisch";
        String klar = this.klar ? "klares" : "trübes";
        String aroma = this.aroma == null ? "" : " " + this.aroma + " ";
        String farbe = this.farbe == null ? "" : " " + this.farbe + " ";

        System.out.println(getVolumen() + " Liter " + klar + " " + this.name + farbe + aroma + Fruchtfleisch + " mit einem Alkoholgehalt von " +this.alkoholgehalt);
    }
}

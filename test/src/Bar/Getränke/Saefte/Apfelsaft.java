package Bar.Getränke.Saefte;
import Bar.Getränke.Saft;

public class Apfelsaft extends Saft {
   private Boolean klar;
   private String Name;

   public Apfelsaft(int Volumen , boolean mitFruchtfleisch, boolean istKlar){
       super(Volumen , mitFruchtfleisch);
       this.klar = istKlar;
       this.Name = "Apfelsaft";
   }

    public Boolean getKlar() {
        return klar;
    }

    public String getName() {
        return Name;
    }

    public void info(){
       String Fruchtfleisch = getFruchtfleisch() ?  "mit Fruchtfleisch" : "ohne Fruchtfleisch";
       String klar = getKlar() ? "klarer" : "trüber";
        System.out.println( getVolumen() + " Liter " + klar + " " + getName() + " " + Fruchtfleisch);
    }

}

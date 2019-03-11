package Bar.Getränke.Saefte;
import Bar.Getränke.Saft;

public class Kiwisaft extends Saft {
    private String Name;

    public Kiwisaft(int Volumen, boolean mitFruchtfleisch){
        super(Volumen, mitFruchtfleisch);
        this.Name = "Kiwisaft";
    }

    public void info(){
        String Fruchtfleisch = getFruchtfleisch() ?  "mit Fruchtfleisch" : "ohne Fruchtfleisch";
        System.out.println( getVolumen() + " Liter " + getName() + " " + Fruchtfleisch);
    }

    public String getName() {
        return Name;
    }
}

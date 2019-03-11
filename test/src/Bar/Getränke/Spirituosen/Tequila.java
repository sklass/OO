package Bar.Getränke.Spirituosen;
import Bar.Getränke.Spirituose;
public class Tequila extends Spirituose{
    private String Farbe;
    private String Name;

    public Tequila(int Volumen, double Alkoholgehalt, String Farbe){
        super(Volumen,Alkoholgehalt);
        this.Name = "Tequila";
        this.Farbe = Farbe;
    }

    public String getName() {
        return Name;
    }

    public String getFarbe() {
        return Farbe;
    }

    public void info(){
        System.out.println(getVolumen() + " Liter " + getName() + " " + getFarbe() + " mit einem Alkoholgehalt von " + getAlkoholgehalt() + "%");
    }


}

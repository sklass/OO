package Bar.Getränke.Spirituosen;
import Bar.Getränke.Spirituose;

public class Wodka extends Spirituose{
    private String Aroma;
    private String Name;

    public Wodka(int Volumen, double Alkoholgehalt, String Aroma){
        super(Volumen,Alkoholgehalt);
        this.Aroma = Aroma;
        this.Name = "Wodka";
    }

    public void info(){
        System.out.println(getVolumen() + " Liter " + getName() + " " + getAroma() + " mit einem Alkoholgehalt von " + getAlkoholgehalt() + "%");
    }

    public String getName() {
        return Name;
    }

    public String getAroma() {
        return Aroma;
    }
}

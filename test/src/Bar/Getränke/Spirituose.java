package Bar.Getränke;
import Bar.Getränk;

public abstract class Spirituose extends Getränk {
    private Double Alkoholgehalt;

    public Spirituose(int Volumen, double Alkoholgehalt){
        super(Volumen);
        this.Alkoholgehalt = Alkoholgehalt;
    }

    public Double getAlkoholgehalt() {
        return Alkoholgehalt;
    }
}

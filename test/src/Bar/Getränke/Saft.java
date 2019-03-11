package Bar.Getränke;
import Bar.Getränk;

public abstract class Saft extends Getränk {
    private Boolean Fruchtfleisch;

    public Saft(int Volumen, Boolean mitFruchtfleisch){
        super(Volumen);
        this.Fruchtfleisch = mitFruchtfleisch;
    }

    public void schuetteln(){
        System.out.println("Geschüttelt, nicht gerührt!");
    }

    public Boolean getFruchtfleisch() {
        return Fruchtfleisch;
    }

    public void setFruchtfleisch(Boolean fruchtfleisch) {
        Fruchtfleisch = fruchtfleisch;
    }
}

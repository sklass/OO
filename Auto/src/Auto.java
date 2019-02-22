public class Auto {

    String Seriennummer;
    String Marke;
    String Modell;
    String Farbe;

    double TankVolumen;
    double Verbrauch;
    double TankStand;

    void setzeEigenschaften(String Marke, String Modell, String Farbe, String Seriennummer, double TankVolumen, double Verbrauch){
        this.Marke = Marke;
        this.Modell = Modell;
        this.Farbe = Farbe;
        this.Seriennummer = Seriennummer;
        this.TankVolumen = TankVolumen;
        this.Verbrauch = Verbrauch;
    }

    void druckeEigenschaften(){
        System.out.println("\nDas Fahrzeug : " + Marke + " " + Modell + " hat folgende Eigenschaften: \nFarbe: " + Farbe + "\nSeriennummer: " + Seriennummer + "\nTankVolumen: " + TankVolumen + "\nVerbrauch je 100Km: " + Verbrauch + "L");
    }

    //gebeAktuelleReichweite
    //tankstand / verbrauch * 100

    //fahre(Anzahl km)

    //tanke(Anzahl Liter)
}

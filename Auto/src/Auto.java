public class Auto {

    String Seriennummer;
    String Marke;
    String Modell;
    String Farbe;
    Fahrer Besitzer;

    double TankVolumen;
    double Verbrauch;
    double TankStand;

    void setzeEigenschaften(String Marke, String Modell, String Farbe, String Seriennummer, double TankVolumen, double Verbrauch, double FahrerKontostand){
        this.Marke = Marke;
        this.Modell = Modell;
        this.Farbe = Farbe;
        this.Seriennummer = Seriennummer;
        this.TankVolumen = TankVolumen;
        this.Verbrauch = Verbrauch;
        this.TankStand = 0;
        this.Besitzer = new Fahrer(FahrerKontostand);
    }

    void druckeEigenschaften(){
        System.out.println("\nDas Fahrzeug " + Marke + " " + Modell + " hat folgende Eigenschaften: \nFarbe: " + Farbe + "\nSeriennummer: " + Seriennummer + "\nTankVolumen: " + TankVolumen + "\nVerbrauch je 100Km: " + Verbrauch + "L");
    }

     double maximalReichweite(){
        double reichweite = TankVolumen / Verbrauch * 100;
        return reichweite;
    }

    double holeAktuelleReichweite(){
        return TankStand / Verbrauch * 100;
    }

    //fahre(Anzahl km)
    boolean fahre(double kilometer){
        double FahrtVerbrauch = kilometer *  Verbrauch / 100;
        double maximaleStrecke = TankStand / Verbrauch * 100;
        if(FahrtVerbrauch > TankStand){
            System.out.println("Tankfüllung reicht nicht um " + kilometer + "Km weit zu Fahren");
            System.out.printf("Die maximale Strecke bei aktueller Tankfüllung beträgt: %.2f Km", maximaleStrecke);
        }else{
            TankStand -= FahrtVerbrauch;
            System.out.printf("Die Fahrt hat %.2f L Benzin verbraucht\n", FahrtVerbrauch);
            druckeTankstand();
        }
        return true;
    }

    double holeTankstand(){
        return TankStand;
    }

    void tanke(Tankstelle MeineTankstelle){
        double Liter = MeineTankstelle.vollTanken(this.TankVolumen, this.TankStand, this.Besitzer.Kontostand);
        TankStand += Liter;
        druckeTankstand();
    }

    void druckeReichweite(){
        System.out.printf("Die Reichweite bei aktueller Tankfüllung beträgt %.2f Km\n", holeAktuelleReichweite());
    }

    void druckeTankstand(){
        System.out.printf("Der aktuelle Tankstand beträgt %.1f L\n", holeTankstand());
    }

}

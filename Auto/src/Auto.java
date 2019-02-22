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
        this.TankStand = 0;
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
        double maximaleStrecke = TankStand / Verbrauch;
        if(FahrtVerbrauch > TankStand){
            System.out.println("Tankfüllung reicht nicht um so weit zu Fahren");
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

    void tanke(double Liter){
        double PlatzImTank = TankVolumen-TankStand;
        if(Liter < 0 ){
            System.out.println("Keine negativen Werte möglich");
            Liter = 0;
        }
        if(Liter > PlatzImTank ){
            System.out.println("Maximales Tankvolumen überschritten!\nSetze Anzahl Liter auf " + PlatzImTank);
            Liter = PlatzImTank;
        }
        System.out.println(Liter + " L getankt");
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

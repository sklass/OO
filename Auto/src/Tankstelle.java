public class Tankstelle {
    double LiterPreis;


    public Tankstelle(double LiterPreis){
        this.LiterPreis = LiterPreis;
    }

    double vollTanken(double TankVolumen, double TankStand, double FahrerKontostand){
        double LiterGetankt;
        double PlatzImTank = TankVolumen - TankStand;
        double Kosten = PlatzImTank * LiterPreis;
        System.out.println("Volltanken!");
        if(Kosten > FahrerKontostand){
            System.out.println("Volltanken nicht möglich! Kontostand zu gering!\nKontostand: " + FahrerKontostand + "€\nKosten: " + Kosten + "€");
            double maxTankmenge = FahrerKontostand / LiterPreis;
            System.out.printf("Es werden nur : %.2f L getankt\n", maxTankmenge);
            LiterGetankt = maxTankmenge;
        }else{
            LiterGetankt = PlatzImTank;
        }
        return LiterGetankt;
    }
}

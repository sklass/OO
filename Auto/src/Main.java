public class Main {
    static Auto AudiA4;
    static Auto AudiA6;
    static Auto BMW3er;
    static Tankstelle Aral;
    static Tankstelle Esso;

    public static void main(String[] args) {
        erzeugeObjekte();
        AudiA4();
        AudiA6();
        BMW3er();
    }

    static void erzeugeObjekte(){
        AudiA4 = new Auto();
        AudiA6 = new Auto();
        BMW3er = new Auto();

        Aral = new Tankstelle(1.35);
        Esso = new Tankstelle(1.30);

        AudiA4.setzeEigenschaften("Audi","A4", "Rot", "12345-12345-12345", 65, 8.5, 150);
        AudiA6.setzeEigenschaften("Audi","A6", "Blau", "67890-67890-67890", 70, 10.5, 100);
        BMW3er.setzeEigenschaften("BMW","3er", "Silber", "101112-101112-101112", 55, 9.0, 25);
    }

    static void AudiA4(){
        AudiA4.druckeEigenschaften();
        System.out.printf("Die maximale Reichweite des Wagens beträgt %.1f Km\n", AudiA4.maximalReichweite());
        AudiA4.druckeTankstand();
        AudiA4.tanke(Aral);
        AudiA4.druckeReichweite();
        AudiA4.fahre(100);

    }

    static void AudiA6(){
        AudiA6.druckeEigenschaften();
        System.out.printf("Die maximale Reichweite des Wagens beträgt %.1f Km\n", AudiA6.maximalReichweite());
        AudiA6.druckeTankstand();
        AudiA6.tanke(Esso);
        AudiA6.druckeReichweite();
        AudiA6.fahre(200);

    }

    static void BMW3er(){
        BMW3er.druckeEigenschaften();
        System.out.printf("Die maximale Reichweite des Wagens beträgt %.1f Km\n", BMW3er.maximalReichweite());
        BMW3er.druckeReichweite();
        BMW3er.tanke(Esso);
        BMW3er.fahre(273);
    }
}

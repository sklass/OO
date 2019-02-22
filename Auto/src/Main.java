public class Main {
    static Auto AudiA4;
    static Auto AudiA6;
    static Auto BMW3er;

    public static void main(String[] args) {
        erzeugeAutos();
        AudiA4.druckeEigenschaften();
        AudiA6.druckeEigenschaften();
        BMW3er.druckeEigenschaften();

    }

    static void erzeugeAutos(){
        AudiA4 = new Auto();
        AudiA6 = new Auto();
        BMW3er = new Auto();

        AudiA4.setzeEigenschaften("Audi","A4", "Rot", "12345-12345-12345", 90, 7.5);
        AudiA6.setzeEigenschaften("Audi","A6", "Blau", "67890-67890-67890", 120, 8.5);
        BMW3er.setzeEigenschaften("BMW","3er", "Silber", "101112-101112-101112", 120, 8.5);
    }
}

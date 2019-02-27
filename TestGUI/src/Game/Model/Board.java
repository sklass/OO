package Game.Model;

public class Board { //Klasse für das Spielfeld
    //SpielfeldEigenschaften
    private int rows;           //höhe
    private int cols ;          //breite
    private int[][] coordinates = new int[4][4];//Spielsteine

    public void setSize(int cols, int rows){   //Methode zum festlegen der Spielfeldgröße
        this.rows = rows;
        this.cols = cols;
        //this.coordinates = new int[this.rows+1][this.cols+1];   //TODO +1 aktuell damit bei user eingaben von 1 und nicht von 0 angefangen wird
    }

    public void initialize(int FillWithValue){     //Das Array für die Spielersymbole wird zu beginn des Spiels mit 0en gefüllt

        for(int y  = 1; y < this.rows+1; y++ ){
            for(int x  = 1; x < this.cols+1; x++ ){
                this.coordinates[y][x] = FillWithValue;
            }
        }
    }

    public int[][] getCoordinates(){
        return this.coordinates;
    }   //Array mit den Spielsteine übergeben

    public void setCoordinates(int[][] coordinates) {//Array mit Spielsteinen aktualisieren
        this.coordinates = coordinates.clone();
    }

    public int getCols(){
        return this.cols;
    }   //Array breite übergeben
    public int getRows(){
        return this.rows;
    }   //Array höhe übergeben

    String getHeader(){                   //Überschrift mit Spaltennummerrierung übergeben
        String header = "";
        for(int i = 1; i<=getCols();i++ ){
            header = header + "  " + i + "  ";
        }
        return header;
    }
}



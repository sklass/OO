package Game.Model;

public class Board { //Klasse für das Spielfeld
    //SpielfeldEigenschaften
    private int rows;           //höhe
    private int cols ;          //breite
    private int[][] coordinates;//Spielsteine

    public Board(int rows, int cols){
        coordinates = new int[rows][cols];
    }

    public void setSize(int cols, int rows){   //Methode zum festlegen der Spielfeldgröße
        this.rows = rows;
        this.cols = cols;
    }

    public void initialize(int FillWithValue){     //Das Array für die Spielersymbole wird zu beginn des Spiels mit 0en gefüllt

        for(int y  = 0; y < this.rows; y++ ){
            for(int x  = 0; x < this.cols; x++ ){
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

}



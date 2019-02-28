package Game.Model;

import javafx.scene.paint.Color;

public class Player { //Klasse für die Spieler
    private int ID;
    private Color color;
    private int Points;
    private String name;

    public void setPlayerID(int playerID){
        this.ID =  playerID;
    }
    public void setPlayerColor( Color playerColor){
        this.color = playerColor;
    }
    public int getPlayerID(){
        return this.ID;
    }
    public Color getPlayerColor(){
        return this.color;
    }
    public int getPoints() {
        return Points;
    }
    public void increasePoints() {
        this.Points++;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

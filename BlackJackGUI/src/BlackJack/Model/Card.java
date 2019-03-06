package BlackJack.Model;

import javafx.scene.image.Image;

public class Card {
    private String name;    //Name der Karte (Ass,Bube usw)
    private int value;      //kartenwerte (von 1-10)
    private String type;    //Kartentyp (Pik,Karow,Herz,Kreuz)
    private Image img;      //Bild der Karte (img/cards/)

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }

    String getType() {
        return type;
    }

    void setType(String type) {
        this.type = type;
    }

    public Image getImg() {
        return img;
    }

    void setImg(Image img) {
        this.img = img;
    }
}

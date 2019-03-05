package BlackJack.Model;

import java.nio.file.Path;

public class Card {
    private String name;
    private int value;
    private String type;
    private Path imgPath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Path getImgPath() {
        return imgPath;
    }

    public void setImgPath(Path imgPath) {
        this.imgPath = imgPath;
    }
}

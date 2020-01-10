package wall;

public class StoneWall extends Wall {

    public StoneWall() {
        this.name = "stone wall";
        isDestructible = false;
    }

    public String getName() {
        return this.name;
    }

    public void playWall() {

    }
}

package wall;

public class IceWall extends Wall {

    public IceWall() {
        this.name = "ice wall";
        isDestructible = true;
    }

    public String getName() {
        return this.name;
    }

    public void playWall() {

    }
}

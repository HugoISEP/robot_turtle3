package grid;

public class Case {
    private Object contents;
    private static int countX = 0;
    private static int countY = 0;
    private int positionX;
    private int postionY;

    public Case() {
        this.contents = null;
        if (countX > 7) {
            countX = 0;
            countY++;
        }
        this.positionX = countX;
        this.postionY = countY;

        countX++;
    }

    public Object getContents() {
        return this.contents;
    }

    public void setContents(Object contents1) {
        this.contents = contents1;
    }

    public int getPositionX() {
        return this.positionX;
    }

    public int getPositionY() {
        return this.postionY;
    }
}



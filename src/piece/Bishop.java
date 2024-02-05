package piece;
public class Bishop implements Piece {
    private int x;
    private int y;
    private Side side;

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setPos(int newX, int newY) {
        x=newX;
        y=newY;
    }
}

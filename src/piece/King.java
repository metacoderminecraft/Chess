package piece;
public class King implements Piece {
    private int x;
    private int y;
    private Side side;

    @Override
    public String toString() {
        return "K";
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

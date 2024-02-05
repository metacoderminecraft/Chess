package piece;
public class Queen implements Piece {
    private int x;
    private int y;
    public final Side side;
    
    public Queen(int startX, int startY, Side startSide){
        x=startX;
        y=startY;
        side=startSide;
    }

    @Override
    public String toString() {
        return "Q";
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

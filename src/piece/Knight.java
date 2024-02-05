package piece;
public class Knight implements Piece {
    private int x;
    private int y;
    public final Side side;
    
    public Knight(int startX, int startY, Side startSide){
        x=startX;
        y=startY;
        side=startSide;
    }

    @Override
    public String toString() {
        return "N";
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

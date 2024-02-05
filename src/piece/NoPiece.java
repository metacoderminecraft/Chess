package piece;

public class NoPiece implements Piece {
    private int x;
    private int y;

    public NoPiece(int startX, int startY){
        x=startX;
        y=startY;
    }

    @Override
    public void setPos(int newX, int newY) {}
    
    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "  ";
    }
}

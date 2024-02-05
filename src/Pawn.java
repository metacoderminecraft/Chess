import java.awt.Graphics;

public class Pawn implements Piece {
    private int x;
    private int y;
    private Side side;

    public Pawn(int x, int y, Side side) {
        setPosition(x, y);
        this.side = side;
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
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
    public Side getSide() {
        return side;
    }
}

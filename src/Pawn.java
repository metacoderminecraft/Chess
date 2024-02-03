import java.awt.Graphics;

public class Pawn implements Piece{
    private int x;
    private int y;

    public Pawn(int x, int y) {
        setPosition(x, y);
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
    public void draw(Graphics g) {
        g.fillRect(x * 100, y * 100, 50, 50);
    }
}

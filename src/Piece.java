import java.awt.Graphics;

public interface Piece {
    void setPosition(int x, int y);
    int getX();
    int getY();
    void draw(Graphics g);
    Side getSide();
}
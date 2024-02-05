import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public enum PieceType {
    PAWN() {
        @Override
        public void drawTo(Graphics g, int x, int y) {
            try {
            Image image = ImageIO.read(new URL("https://creazilla-store.fra1.digitaloceanspaces.com/cliparts/3485578/chess-tile-pl-clipart-sm.png"));
            g.drawImage(image, x, y, null);
            } catch (IOException ex) {
                System.out.println("help");
                ex.printStackTrace();
            }
        }
    };
    // KNIGHT,
    // BISHOP,
    // ROOK,
    // QUEEN,
    // KING;

    public abstract void drawTo(Graphics g, int x, int y);
}
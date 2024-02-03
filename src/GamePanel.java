import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private final Piece piece = new Pawn(0, 0, Side.WHITE);

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int row = 0; row < 8; row += 1) {
            for (int column = 0; column < 8; column += 1) {
                if(column % 2 == row % 2) {
                    g.setColor(ExtraColors.LIGHT_BEIGE);
                } else {
                    g.setColor(ExtraColors.DARK_BEIGE);
                }

                g.fillRect(column * 100, row * 100, 100, 100);
            }
        }
    }
}
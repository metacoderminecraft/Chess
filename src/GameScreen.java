import javax.swing.*;

public class GameScreen extends JFrame {

    private final GamePanel gamePanel;

    public GameScreen(int height, int width) {
        setTitle("Game Screen");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel = new GamePanel();
        add(gamePanel);

        setVisible(true);
    }
}

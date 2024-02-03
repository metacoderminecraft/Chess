import java.awt.Color;

public enum Sides {
    WHITE() {
        @Override
        public Color color() {
            return Color.WHITE;
        }
    },
    BLACK() {
        @Override
        public Color color() {
            return Color.BLACK;
        }
    };

    public abstract Color color();
}

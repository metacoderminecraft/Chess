package piece;
public enum Side {
    WHITE() {
        @Override
        public String string() {
            return "w";
        }
    },
    BLACK() {
        @Override
        public String string() {
            return "b";
        }
    };

    public abstract String string();
}

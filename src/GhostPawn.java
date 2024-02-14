public class GhostPawn extends None {
    private final Side side;

    public GhostPawn(Side side) {
        this.side = side;
    }

    @Override
    public Side getSide() {
        return side;
    }

    @Override
    public String toString() {
        return "  ";
    }
}

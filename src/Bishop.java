public class Bishop implements Piece {
    private final Side side;

    public Bishop(Side side) {
        this.side = side;
    }

    @Override
    public boolean isValid(Board.Move move, Board board) {
        return true;
    }

    @Override
    public String toString() {
        return "B" + side;
    }

    @Override
    public Side getSide() {
        return side;
    }
}

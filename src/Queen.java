public class Queen implements Piece {
    private final Side side;

    public Queen(Side side) {
        this.side = side;
    }

    @Override
    public boolean isValid(Board.Move move, Board board) {
        return true;
    }

    @Override
    public String toString() {
        return "Q" + side;
    }

    @Override
    public Side getSide() {
        return side;
    }
}

public class Rook implements Piece {
    private final Side side;

    public Rook(Side side) {
        this.side = side;
    }

    @Override
    public boolean isValid(Board.Move move, Board board) {
        return true;
    }

    @Override
    public String toString() {
        return "R" + side;
    }

    @Override
    public Side getSide() {
        return side;
    }
}

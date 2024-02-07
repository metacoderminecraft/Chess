public class Pawn implements Piece {
    private final Side side;

    public Pawn(Side side) {
        this.side = side;
    }

    @Override
    public boolean isValid(Board.Move move, Board board) {
        return true;
    }

    @Override
    public String toString() {
        return "P" + side;
    }

    @Override
    public Side getSide() {
        return side;
    }
}

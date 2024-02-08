public class King implements Piece {
    private final Side side;

    public King(Side side) {
        this.side = side;
    }

    @Override
    public boolean isValid(Board.Move move, Board board) {
        if (board.getPiece(move.endX, move.endY).getSide() == side) {
            return false;
        }

        return Math.abs(move.endX - move.startX) <= 1 && (Math.abs(move.endY - move.startY) <= 1);
    }

    @Override
    public String toString() {
        return "K" + side;
    }

    @Override
    public Side getSide() {
        return side;
    }
}

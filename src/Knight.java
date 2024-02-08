public class Knight implements Piece {
    private final Side side;

    public Knight(Side side) {
        this.side = side;
    }

    @Override
    public boolean isValid(Board.Move move, Board board) {
        if (board.getPiece(move.endX, move.endY).getSide() == side) {
            return false;
        }

        return Math.abs(move.endX - move.startX) == 2 && Math.abs(move.endY - move.startY) == 1 || Math.abs(move.endX - move.startX) == 1 && Math.abs(move.endY - move.startY) == 2;
    }

    @Override
    public String toString() {
        return "N" + side;
    }

    @Override
    public Side getSide() {
        return side;
    }
}

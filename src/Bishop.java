public class Bishop implements Piece {
    private final Side side;

    public Bishop(Side side) {
        this.side = side;
    }

    @Override
    public boolean isValid(Board.Move move, Board board) {
        if (board.getPiece(move.endX, move.endY).getSide() == side) {
            return false;
        }

        if (Math.abs(move.endX - move.startX) != Math.abs(move.endY - move.startY)) {
            return false;
        }

        for (int i = Math.min(move.endX, move.startX) + 1, j = Math.min(move.endY, move.startY) + 1; i < Math.max(move.startX, move.endX) && j < Math.max(move.startY, move.endY); i++, j++) {
            if (!(board.getPiece(i, j) instanceof None)) {
                return false;
            }
        }

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

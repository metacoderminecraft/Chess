public class Pawn implements Piece {
    private final Side side;
    public final boolean hasMoved;

    public Pawn(Side side) {
        this.side = side;
        this.hasMoved = true;
    }

    public Pawn(Side side, Boolean hasMoved) {
        this.side = side;
        this.hasMoved = hasMoved;
    }

    @Override
    public boolean isValid(Board.Move move, Board board) {
        if (board.getPiece(move.endX, move.endY) instanceof King && move.startX == 6 && move.startY == 0) {
            System.out.println(board.getPiece(move.endX, move.endY));
        }
        if (board.getPiece(move.endX, move.endY).getSide() == side) {
            return false;
        }
        
        if (move.endY - move.startY != 1 && side == Side.BLACK) {
            if (!(move.endY - move.startY == 2 && move.startY == 1 && board.getPiece(move.startX, 2) instanceof None)) {
                return false;
            }
        } else if (move.endY - move.startY != -1 && side == Side.WHITE) {
            if (!(move.endY - move.startY == -2 && move.startY == 6 && board.getPiece(move.startX, 5) instanceof None)) {
            return false;
            }
        }

        if (move.endX - move.startX == 0 && board.getPiece(move.endX, move.endY) instanceof None) {
            return true;
        } else if (Math.abs(move.endX - move.startX) == 1 && !(board.getPiece(move.endX, move.endY) instanceof None)) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "P" + side;
    }

    @Override
    public Side getSide() {
        return side;
    }

    @Override
    public boolean hasMoved() {
        return hasMoved;
    }
}

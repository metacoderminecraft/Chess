import java.util.Arrays;
import java.util.HashMap;

import piece.Bishop;
import piece.King;
import piece.Knight;
import piece.NoPiece;
import piece.Pawn;
import piece.Piece;
import piece.Queen;
import piece.Rookie;
import piece.Side;

public class Board {
    private final PieceType[][] board;

    // new HashMap<Integer, String>();

    public static class Move {
        public final int startX;
        public final int startY;
        public final int endX;
        public final int endY;

        public Move(int startX, int startY, int endX, int endY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }
    }

    public void print(){
        for (PieceType[] i : board) {
            System.out.println(Arrays.toString(i));
        }
    }

    public Board(PieceType[][] board) {
        this.board = board;
    }

    public static PieceType[] pawnRow(Side side, int y) {
        PieceType[] row = new PieceType[8];

        for (int i = 0; i < row.length; i++) {
            row[i] = PieceType.PAWN;
        }

        return row;
    }

    public static PieceType[] faceRow(Side side, int y) {
        PieceType[] row =  {PieceType.ROOK, PieceType.KNIGHT, PieceType.BISHOP, PieceType.QUEEN, PieceType.KING, PieceType.BISHOP, PieceType.KNIGHT, PieceType.ROOK};

        return row;
    }

    public static PieceType[] noRow(int y) {
        PieceType[] row = new PieceType[8];

        for (int i = 0; i < row.length; i++) {
            row[i] = PieceType.NONE;
        }

        return row;
    }

    public static PieceType[][] startBoard() {
        final PieceType[][] board = new PieceType[8][8];

        board[0] = Board.faceRow(Side.BLACK, 0);
        board[1] = Board.pawnRow(Side.BLACK, 1);
        for (int i = 2; i < 6; i++) {
            board[i] = Board.noRow(i);
        }
        board[6] = Board.pawnRow(Side.WHITE, 6);
        board[7] = Board.faceRow(Side.WHITE, 7);

        return board;
    }

    public Board movePiece(Board.Move move) {
        PieceType piece = board[move.startY][move.startX];
        PieceType[][] newBoard = board.clone();

        if (piece.isValid(move, new Board(newBoard))) {
            newBoard[move.endY][move.endX] = piece;
            newBoard[move.startY][move.startX] = PieceType.NONE;
        };

        return new Board(newBoard);
    }
}

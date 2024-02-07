import java.util.Arrays;

public class Board {
    private final Piece[][] board;

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
        for (Piece[] i : board) {
            System.out.println(Arrays.toString(i));
        }
    }

    public Board(Piece[][] board) {
        this.board = board;
    }

    public static Piece[] pawnRow(Side side, int y) {
        Piece[] row = new Piece[8];

        for (int i = 0; i < row.length; i++) {
            row[i] = new Pawn(side);
        }

        return row;
    }

    public static Piece[] faceRow(Side side, int y) {
        Piece[] row = {new Rook(side), new Knight(side), new Bishop(side), new Queen(side), new King(side), new Bishop(side), new Knight(side), new Rook(side)};

        return row;
    }

    public static Piece[] noRow(int y) {
        Piece[] row = new Piece[8];

        for (int i = 0; i < row.length; i++) {
            row[i] = new None();
        }

        return row;
    }

    public static Piece[][] startBoard() {
        final Piece[][] board = new Piece[8][8];

        board[0] = Board.faceRow(Side.BLACK, 0);
        board[1] = Board.pawnRow(Side.BLACK, 1);
        for (int i = 2; i < 6; i++) {
            board[i] = Board.noRow(i);
        }
        board[6] = Board.pawnRow(Side.WHITE, 6);
        board[7] = Board.faceRow(Side.WHITE, 7);

        return board;
    }

    public Board movePiece(Board.Move move) throws Exception {
        Piece piece = board[move.startY][move.startX];
        Piece[][] newBoard = board.clone();

        if (piece.isValid(move, new Board(newBoard))) {
            newBoard[move.endY][move.endX] = piece;
            newBoard[move.startY][move.startX] = new None();
        } else {
            throw new Exception();
        }

        return new Board(newBoard);
    }

    public Piece getPiece(int x, int y) {
        return board[y][x];
    }
}


public class Test {
    public static void main(String[] args) {
        notation();
        kingCheck();
        pawnMove();
        knightMove();
        rookMove();
        queenMove();
        kingMove();
        bishopMove();

    }
    
    public static void assertB(boolean condition, String tag) {
        if (!condition) {
            throw new RuntimeException(tag);
        }
    }

    public static void assertB(boolean condition)  {
        if (!condition) {
            throw new RuntimeException();
        }
    }

    private static void notation() {
        assertB(Human.isValid("a2a3"));
        assertB(!Human.isValid("1111"));
        assertB(!Human.isValid("e9e8"));
        assertB(!Human.isValid("a0a1"));
        assertB(!Human.isValid("j8hh8"));
        assertB(Human.isValid("a8h8"));
    }

    private static void kingCheck() {
        Piece[][] sampleBoardArr = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            sampleBoardArr[i] = Board.noRow();
        }

        sampleBoardArr[0][0] = new King(Side.BLACK);
        sampleBoardArr[1][1] = new Queen(Side.WHITE);
        sampleBoardArr[7][7] = new King(Side.WHITE);
        Board board = new Board(sampleBoardArr);
        assertB(!board.isCheckMate(Side.BLACK));
    }

    private static void kingMove() {
        Board board = new Board(Board.startBoard());
    }

    private static void queenMove() {
        Board board = new Board(Board.startBoard());
    }

    private static void rookMove() {
        Board board = new Board(Board.startBoard());
    }

    private static void bishopMove() {
        Board board = new Board(Board.startBoard());
    }

    private static void knightMove() {
        Board board = new Board(Board.startBoard());
    }

    private static void pawnMove() {
        Board board = new Board(Board.startBoard());

        assertB(board.isValid(Human.moveOf("a7a5"), Side.BLACK));
        assertB(board.isValid(Human.moveOf("a2a4"), Side.WHITE));
        board = board.movePiece(Human.moveOf("a7a5"), Side.BLACK);
        board = board.movePiece(Human.moveOf("a2a4"), Side.WHITE);
        assertB(!board.isValid(Human.moveOf("a5a4"), Side.BLACK));
        board = board.movePiece(Human.moveOf("b2b4"), Side.WHITE);
        assertB(board.isValid(Human.moveOf("a5b4"), Side.BLACK));
    }
}

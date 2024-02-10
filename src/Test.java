
public class Test {
    public static void main(String[] args) {
        notation();
        kingCheck();
        pawnMove();
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
        try {
            board.movePiece(new Board.Move(1, 1, 0, 1), Side.WHITE);
        } catch (Exception e) {
            if (e.getMessage() == "Checkmate!") {
                assertB(e.getMessage() != "Checkmate!", "KingCheck");
            }
        }
    }

    private static void kingMove() {

    }

    private static void queenMove() {

    }

    private static void rookMove() {

    }

    private static void bishopMove() {

    }

    private static void knightMove() {

    }

    private static void pawnMove() {
        Piece[][] boardArr = Board.noBoard();
        boardArr[1][0] = new Pawn(Side.BLACK);
        boardArr[6][0] = new Pawn(Side.WHITE);
        boardArr[7][7] = new King(Side.WHITE);
        boardArr[0][7] = new King(Side.BLACK);

        Board board = new Board(boardArr);

        try {board = board.movePiece(Human.moveOf("a7a5"), Side.BLACK);} catch(Exception e) {throw new RuntimeException("pMove1 " + e);}
        try {board = board.movePiece(Human.moveOf("a2a4"), Side.WHITE);} catch(Exception e) {throw new RuntimeException("pMove2 " + e);}
    }
}

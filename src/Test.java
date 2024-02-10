import javax.management.RuntimeErrorException;

public class Test {

    private static void testNotation() {
        assertB(Main.isValid("a2a3"), "testNotation1");
        assertB(!Main.isValid("1111"), "testNotation2");
    }

    private static void testKingCheck() {
        Piece[][] sampleBoardArr = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            sampleBoardArr[i] = Board.noRow();
        }

        sampleBoardArr[0][0] = new King(Side.BLACK);
        sampleBoardArr[1][1] = new Queen(Side.WHITE);
        sampleBoardArr[7][7] = new King(Side.WHITE);
        Board board = new Board(sampleBoardArr);
        try {
            new Board(sampleBoardArr).movePiece(new Board.Move(1, 1, 0, 1), Side.WHITE);
        } catch (Exception e) {
            if (e.getMessage() == "Checkmate!") {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        testNotation();
        testKingCheck();
    }
    
    public static void assertB(boolean condition, String tag) {
        if (!condition) {
            throw new RuntimeException("tag");
        }
    }

    public static void assertB(boolean condition) throws Exception {
        if (!condition) {
            throw new RuntimeException();
        }
    }
}

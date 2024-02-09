import javax.management.RuntimeErrorException;

public class Test {

    private static void testNotation() {
        assertB(Main.isValid("a2a3"), "testNotation1");
        assertB(!Main.isValid("1111"), "testNotation2");
    }

    private static void testKingMove() {
        Board board = new Board(Board.startBoard());
        try {
            board = board.movePiece(new Board.Move(3, 1, 3, 2), Side.BLACK);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        testNotation();
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

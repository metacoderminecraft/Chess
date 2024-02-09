public class Test {

    private static void testNotation() {
        assertB(Main.isValid("a2a3"), "testNotation1");
        assertB(!Main.isValid("1111"), "testNotation2");
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

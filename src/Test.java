public class Test {

    private static void testNotation() throws Exception {
        run(Main.isValid("a1a1"), "testNotation");
        run(!Main.isValid("1111"), "testNotation");
    }

    public static void main(String[] args) throws Exception {
        testNotation();
    }
    
    public static void run(boolean condition, String tag) throws Exception {
        if (!condition) {
            System.out.println("Error: " + tag);
            throw new RuntimeException();
        }
    }

    public static void run(boolean condition) throws Exception {
        if (!condition) {
            throw new RuntimeException();
        }
    }
}

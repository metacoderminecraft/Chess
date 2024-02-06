public class Test {

    private static void test() throws Exception {
        assert 4 == 5;
    }

    public static void main(String[] args) throws Exception {
        test();
        throw new RuntimeException();
    }
    
}

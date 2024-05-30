import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class ChessHistory {
    public static void main(String[] args) throws Exception {
        String fileName = "resources\\Karpov.pgn";

        // Create the lexer and parser.
        PGNLexer lexer = new PGNLexer(new ANTLRFileStream(fileName));
        PGNParser parser = new PGNParser(new CommonTokenStream(lexer));

        ParseTree tree = parser.parse();
        System.out.println(tree.getChild(0).getChild(0).getChild(0).getChild(0).getText());

        System.out.println("\nDone!");
            
    }
}                                                    
import java.util.ArrayList;
import java.util.Random;

public class BotV1 implements Player {
    public final Side side;

    public BotV1(Side side) {
        this.side = side;
    }

    @Override
    public String toString() {
        return side.toString();
    }

    @Override
    public Side getSide() {
        return side;
    }

    @Override
    public Board.Move getInput(Board board) {
        ArrayList<Board.Move> legalMoves = board.legalMoves(side);
        Random rand = new Random();

        return legalMoves.get(rand.nextInt(legalMoves.size()));
    }
}

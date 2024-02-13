import java.util.ArrayList;

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
        return null;
    }
}

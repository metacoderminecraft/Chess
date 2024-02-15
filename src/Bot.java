import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class Bot implements Player {
    public final Side side;

    public Bot(Side side) {
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
    
    public Board.Move lookAhead(Board board, int depth) {
        ArrayList<Board.Move> legalMoves = board.legalMoves(side);

        int currMaxVal = -1 * 10^7;
        int index = 0;

        if (depth == 1) {
            for (int i = 0; i < legalMoves.size(); i++) {
                if (getValuation(board.movePiece(legalMoves.get(i), side)) > currMaxVal) {
                    currMaxVal = getValuation(board.movePiece(legalMoves.get(i), side));
                    index = i;
                }
            }
        }

        return legalMoves.get(index);
    }

    public int getValuation(Board board) {
        Side otherSide = side == Side.WHITE ? Side.BLACK : Side.WHITE;

        if (board.isCheck(side) && board.legalMoves(side).size() == 0) {
            return -1 * 10^6;
        }

        if (board.isCheck(otherSide) && board.legalMoves(otherSide).size() == 0) {
            return 1 * 10^6;
        }

        int valuation = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                valuation += getPieceValues(board.getPiece(j, i), board);
            }
        }

        return valuation;
    }

    public int getPieceValues(Piece piece, Board board) {
        int scalar = piece.getSide() == side ? 1 : -1;

        if (piece instanceof Pawn) {
            return 1 * scalar;
        } else if (piece instanceof Knight || piece instanceof Bishop) {
            return 3 * scalar;
        } else if (piece instanceof Rook) {
            return 5 * scalar;
        } else if (piece instanceof Queen) {
            return 9 * scalar;
        }

        //King/None
        return 0;
    }
}

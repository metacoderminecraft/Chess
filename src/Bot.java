import java.util.ArrayList;
import java.util.Random;

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
    
    public Board.WrapperMove lookAhead(Board board, int depth) {
        ArrayList<Board.Move> legalMoves = board.legalMoves(side);

        if (depth == 1) {
            int currMaxVal = -1 * 10^7;
            int index = 0;
    
            for (int i = 0; i < legalMoves.size(); i++) {
                int newValuation = getValuation(board.movePiece(legalMoves.get(i), side));
                if (newValuation > currMaxVal) {
                    currMaxVal = newValuation;
                    index = i;
                }
            }

            return new Board.WrapperMove(legalMoves.get(index), currMaxVal);
        }

        Board.WrapperMove currBest = new Board.WrapperMove(null, -1 * 10^7);        
        for (int i = 0; i < legalMoves.size(); i++) {
            
        }
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

        //King or None
        return 0;
    }
}

import java.util.ArrayList;

public class Bot implements Player {
    public final Side side;
    public final RepeatChecker repeatChecker;

    public Bot(Side side) {
        this.side = side;
        repeatChecker = new RepeatChecker();
        repeatChecker.addBoard(new Board(Board.startBoard()));
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
        repeatChecker.addBoard(board);

        Board.Move move = lookAhead(board, 2, side).move();
        move.print();
    
        return move;
    }
    
    public Board.WrapperMove lookAhead(Board board, int depth, Side side) {
        ArrayList<Board.Move> myLegalMoves = board.legalMoves(side);
        Side otherSide = side == Side.WHITE ? Side.BLACK : Side.WHITE;

        if (depth == 1) {
            Board.WrapperMove currBest = new Board.WrapperMove(null, -1 * 10^7);
    
            for (int i = 0; i < myLegalMoves.size(); i++) {
                int newValuation = getValuation(board.movePiece(myLegalMoves.get(i), side), side);
                if (newValuation > currBest.valuation()) {
                    currBest = new Board.WrapperMove(myLegalMoves.get(i), newValuation);
                }
            }

            return currBest;
        }

        Board.WrapperMove oppWorst = new Board.WrapperMove(null, 1 * 10^7);
        int index = 0;

        for (int i = 0; i < myLegalMoves.size(); i++) {
            Board.WrapperMove curr = lookAhead(board.movePiece(myLegalMoves.get(i), side), depth - 1, otherSide);
            if (curr.valuation() < oppWorst.valuation()) {
                oppWorst = curr;
                index = i;
            }
        }
        
        return new Board.WrapperMove(myLegalMoves.get(index), oppWorst.valuation() * -1);
    }

    public int getValuation(Board board, Side side) {
        Side otherSide = side == Side.WHITE ? Side.BLACK : Side.WHITE;

        if (board.isCheck(side) && board.legalMoves(side).size() == 0) {
            return -1 * 10^6;
        }

        if (board.isCheck(otherSide) && board.legalMoves(otherSide).size() == 0) {
            return 1 * 10^6;
        }

        if (repeatChecker.isDraw()) {
            return -100000000;
        }

        int valuation = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                valuation += getPieceValues(board.getPiece(j, i), board, side);
            }
        }

        return valuation;
    }

    public static int getPieceValues(Piece piece, Board board, Side side) {
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

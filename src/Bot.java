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
        Board.WrapperMove move = lookAhead(board, 4, side);
        move.move().print();
        System.out.println(move.valuation());
    
        return move.move();
    }
    
    public static Board.WrapperMove lookAhead(Board board, int depth, Side side) {
        ArrayList<Board.Move> myLegalMoves = board.legalMoves(side);
        Side otherSide = side == Side.WHITE ? Side.BLACK : Side.WHITE;
        int scalar = side == Side.WHITE ? 1 : -1;

        if (depth == 1) {
            Board.WrapperMove currBest = new Board.WrapperMove(null, -scalar * 10^7);
    
            for (int i = 0; i < myLegalMoves.size(); i++) {
                int newValuation = getValuation(board.movePiece(myLegalMoves.get(i), side));
                if (newValuation * scalar > currBest.valuation() * scalar) {
                    currBest = new Board.WrapperMove(myLegalMoves.get(i), newValuation);
                }
            }

            return currBest;
        }

        Board.WrapperMove oppWorst = new Board.WrapperMove(null, -scalar * 10^7);
        int index = 0;
        ArrayList<Thread> threads = new ArrayList<>();
        ArrayList<Board.WrapperMove> threadResponses = new ArrayList<>();

        for (int i = 0; i < myLegalMoves.size(); i++) {
            int this_i = i;
            Thread thread = new Thread(() -> threadResponses.add(lookAhead(board.movePiece(myLegalMoves.get(this_i), side), depth - 1, otherSide)), String.valueOf(i));
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (Exception e) {
                System.out.println("Thread " + thread.getName() + "with error " + e);
            }
        }


        for (int i = 0; i < threadResponses.size(); i++) {
            if (threadResponses.get(i).valuation() * scalar > oppWorst.valuation() * scalar) {
                oppWorst = threadResponses.get(i);
                index = i;
            }
        }
        
        return new Board.WrapperMove(myLegalMoves.get(index), oppWorst.valuation());
    }

    public static int getValuation(Board board) {
        if (board.isCheck(Side.WHITE) && board.legalMoves(Side.WHITE).size() == 0) {
            return -1 * 10^6;
        }

        if (board.isCheck(Side.BLACK) && board.legalMoves(Side.BLACK).size() == 0) {
            return 1 * 10^6;
        }

        int valuation = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                valuation += getPieceValue(board.getPiece(j, i));
            }
        }

        return valuation;
    }

    public static int getPieceValue(Piece piece) {
        int scalar = piece.getSide() == Side.WHITE ? 1 : -1;

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

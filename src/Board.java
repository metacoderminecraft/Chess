import java.util.Arrays;

public class Board {
    private final Piece[][] board;

    // new HashMap<Integer, String>();

    public static class Move {
        public final int startX;
        public final int startY;
        public final int endX;
        public final int endY;

        public Move(int startX, int startY, int endX, int endY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }
    }

    public static class Position {
        public final int x;
        public final int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public void print(){
        for (Piece[] i : board) {
            System.out.println(Arrays.toString(i));
        }
    }

    public Board(Piece[][] board) {
        this.board = board;
    }

    public static Piece[] pawnRow(Side side, int y) {
        Piece[] row = new Piece[8];

        for (int i = 0; i < row.length; i++) {
            row[i] = new Pawn(side);
        }

        return row;
    }

    public static Piece[] faceRow(Side side, int y) {
        Piece[] row = {new Rook(side), new Knight(side), new Bishop(side), new Queen(side), new King(side), new Bishop(side), new Knight(side), new Rook(side)};

        return row;
    }

    public static Piece[] noRow(int y) {
        Piece[] row = new Piece[8];

        for (int i = 0; i < row.length; i++) {
            row[i] = new None();
        }

        return row;
    }

    public static Piece[][] startBoard() {
        final Piece[][] board = new Piece[8][8];

        board[0] = Board.faceRow(Side.BLACK, 0);
        board[1] = Board.pawnRow(Side.BLACK, 1);
        for (int i = 2; i < 6; i++) {
            board[i] = Board.noRow(i);
        }
        board[6] = Board.pawnRow(Side.WHITE, 6);
        board[7] = Board.faceRow(Side.WHITE, 7);

        return board;
    }

    public Board movePiece(Board.Move move, Side currentSide) throws Exception {
        Piece piece = board[move.startY][move.startX];
        Piece[][] newBoardArr = new Piece[8][8];

        //How does java not have a deep copy method
        for (int i = 0; i < 8; i++) {
            newBoardArr[i] = board[i].clone();
        }

        if (board[move.startY][move.startX].getSide() != currentSide) {
            throw new Exception("Keep your filthy hands off that piece!");
        }

        if (piece.isValid(move, this)) {
            newBoardArr[move.endY][move.endX] = piece;
            newBoardArr[move.startY][move.startX] = new None();
        } else {
            throw new Exception("Illegal Move!");
        }

        Board newBoard = new Board(newBoardArr);

        Position currKingPos = findKing(currentSide, newBoardArr);
        Position otherKingPos = findKing(Side.WHITE == currentSide ? Side.BLACK : Side.WHITE, newBoardArr);

        //checking for checks
        for (int i = 0; i < 8; i++) { 
            for (int j = 0; j < 8; j++) {
                if (newBoardArr[i][j] instanceof None) {
                    continue;
                }

                //taking your own king is already not valid, so I don't need to check the piece's side
                if (newBoardArr[i][j].isValid(new Move(j, i, currKingPos.x, currKingPos.y), newBoard)) {
                    throw new Exception("Illegal Move!!");
                }

                if (newBoardArr[i][j].isValid(new Move(j, i, otherKingPos.x, otherKingPos.y), newBoard)) {
                    System.out.println("AAAAAA it's a CHEEEEEECK!!!!");
                } 
            }
        }

        return new Board(newBoardArr);
    }

    public Piece getPiece(int x, int y) {
        return board[y][x];
    }

    public static Position findKing(Side side, Piece[][] board) throws Exception {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] instanceof King && board[i][j].getSide() == side) {
                    return new Position(j, i);
                }
            }
        }
        throw new RuntimeException("King missing!");
    }

    public boolean isCheckMate() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                for (int i2 = 0; i2 < 8; i2++) {
                    for (int j2 = 0; j2 < 8; j2++) {
                        if (piece.isValid(new Move(j, i, j2, i2), this)) {
                            
                        }
                    }
                }
            }
        }
    }
}

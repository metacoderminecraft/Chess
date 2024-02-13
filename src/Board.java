import java.time.Period;
import java.util.ArrayList;

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

        public void print() {
            System.out.println(startX + ", " + startY + ", " + endX + ", " + endY);
        }

        public PieceSupplier getEndPiece(Board board) {
            return (idc) -> board.getPiece(startX, startY);
        }
    }

    public static class Promotion extends Move {
        public final PieceSupplier promotionPiece;

        public Promotion(int startX, int startY, int endX, int endY, PieceSupplier promotionPiece) {
            super(startX, startY, endX, endY);
            this.promotionPiece = promotionPiece;
            
        }

        @Override
        public PieceSupplier getEndPiece(Board board) {
            return promotionPiece;
        }

        @Override
        public void print() {
            System.out.println(startX + ", " + startY + ", " + endX + ", " + endY + ", " + promotionPiece);
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

    public void print() {
        String block = "|----";
        System.out.println("  " + block + block + block + block + block + block + block + block +"|");
        for (int i = 0; i < 8; i++) {
            System.out.print(8-i + " |");
            for (int j = 0; j < 8; j++) {
                System.out.print(" " + board[i][j] + " |");
            }
            System.out.println();
            System.out.println("  " + block + block + block + block + block + block + block + block +"|");
        }

        System.out.println("     a    b    c    d    e    f    g    h");
    }

    public Board(Piece[][] board) {
        this.board = board;
    }

    public static Piece[] pawnRow(Side side) {
        Piece[] row = new Piece[8];

        for (int i = 0; i < row.length; i++) {
            row[i] = new Pawn(side);
        }

        return row;
    }

    public static Piece[] faceRow(Side side) {
        Piece[] row = {new Rook(side), new Knight(side), new Bishop(side), new Queen(side), new King(side), new Bishop(side), new Knight(side), new Rook(side)};

        return row;
    }

    public static Piece[] noRow() {
        Piece[] row = new Piece[8];

        for (int i = 0; i < row.length; i++) {
            row[i] = new None();
        }

        return row;
    }

    public static Piece[][] noBoard() {
        Piece[][] sampleBoardArr = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            sampleBoardArr[i] = Board.noRow();
        }

        return sampleBoardArr;
    }

    public static Piece[][] startBoard() {
        final Piece[][] board = new Piece[8][8];

        board[0] = Board.faceRow(Side.BLACK);
        board[1] = Board.pawnRow(Side.BLACK);
        for (int i = 2; i < 6; i++) {
            board[i] = Board.noRow();
        }
        board[6] = Board.pawnRow(Side.WHITE);
        board[7] = Board.faceRow(Side.WHITE);

        return board;
    }

    public Board movePiece(Board.Move move, Side currentSide) {
        Piece endPiece = move.getEndPiece(this).accept(currentSide);
        Piece[][] newBoardArr = new Piece[8][8];

        for (int i = 0; i < 8; i++) {
            newBoardArr[i] = board[i].clone();
        }

        newBoardArr[move.endY][move.endX] = endPiece;
        newBoardArr[move.startY][move.startX] = new None();

        return new Board(newBoardArr);
    }

    public Piece getPiece(int x, int y) {
        return board[y][x];
    }

    public Position findKing(Side side) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] instanceof King && board[i][j].getSide() == side) {
                    return new Position(j, i);
                }
            }
        }
        throw new RuntimeException("King missing!");
    }

    public boolean isCheck(Side otherSide) {
        Position otherKingPos = findKing(otherSide);

        for (int i = 0; i < 8; i++) { 
            for (int j = 0; j < 8; j++) {
                if (board[i][j].isValid(new Move(j, i, otherKingPos.x, otherKingPos.y), this)) {
                        return true;
                } 
            }
        }
        return false;
    }

    public ArrayList<Move> legalMoves(Side side) {
        ArrayList<Move> legalMoves = new ArrayList<>(); 
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int i2 = 0; i2 < 8; i2++) {
                    for (int j2 = 0; j2 < 8; j2++) {
                        if (isValid(new Move(j, i, j2, i2), side)) {
                            legalMoves.add(new Move(j, i, j2, i2));
                        }
                    }
                }
            }
        }

        return legalMoves;
    }

    public boolean isValid(Move move, Side currentSide) {
        if (move instanceof Promotion && move.getEndPiece(this) != null) {
            if (!(getPiece(move.startX, move.startY) instanceof Pawn && move.startY == 1) && currentSide == Side.WHITE || !(getPiece(move.startX, move.startY) instanceof Pawn && move.startY== 6) && currentSide == Side.BLACK) {
                return false;
            }

            return isValid(new Promotion(move.startX, move.startY, move.endX, move.endY, null), currentSide);
        } else if (!(move instanceof Promotion)) {
            if (move.startY == 1 && currentSide == Side.WHITE || move.startY== 6 && currentSide == Side.BLACK) {
                return false;
            }
        }

        Piece piece = board[move.startY][move.startX];
        Piece[][] newBoardArr = new Piece[8][8];

        //How does java not have a deep copy method
        for (int i = 0; i < 8; i++) {
            newBoardArr[i] = board[i].clone();
        }

        if (board[move.startY][move.startX].getSide() != currentSide) {
            return false;
        }

        if(!piece.isValid(move, this)) {
            return false;
        }

        //staging the move
        newBoardArr[move.endY][move.endX] = piece;
        newBoardArr[move.startY][move.startX] = new None();

        Board newBoard = new Board(newBoardArr);

        if (newBoard.isCheck(currentSide)) {
            return false;
        };

        return true;
    }
}

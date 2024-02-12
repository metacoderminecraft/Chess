import java.util.ArrayList;
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

        public void print() {
            System.out.println(startX + ", " + startY + ", " + endX + ", " + endY);
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
        Piece piece = board[move.startY][move.startX];
        Piece[][] newBoardArr = new Piece[8][8];

        for (int i = 0; i < 8; i++) {
            newBoardArr[i] = board[i].clone();
        }

        newBoardArr[move.endY][move.endX] = piece;
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

    public ArrayList<Move> pseudoLegal(Side side) {
        ArrayList<Move> pseudoLegal = new ArrayList<>(); 
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                for (int i2 = 0; i2 < 8; i2++) {
                    for (int j2 = 0; j2 < 8; j2++) {
                        if (piece.isValid(new Move(j, i, j2, i2), this) && piece.getSide() == side) {
                            pseudoLegal.add(new Move(j, i, j2, i2));
                        }
                    }
                }
            }
        }

        return pseudoLegal;
    }

    public boolean isCheckMate(Side otherSide) {
        ArrayList<Move> pseudoLegal = pseudoLegal(otherSide);

        Piece[][] newBoardArr = new Piece[8][8];
        boolean found;

            for (Move move : pseudoLegal) {
                found = false;
                //setting up standin board
                for (int i = 0; i < 8; i++) {
                    newBoardArr[i] = board[i].clone();
                }

                //making the move
                newBoardArr[move.endY][move.endX] = newBoardArr[move.startY][move.startX];
                newBoardArr[move.startY][move.startX] = new None();
                Board newBoard = new Board(newBoardArr);
                
                if (newBoard.isCheck(otherSide)) {
                    found = true;
                    break;
                }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    public boolean isValid(Move move, Side currentSide) {
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

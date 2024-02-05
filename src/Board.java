import java.util.Arrays;
import java.util.HashMap;

import piece.Bishop;
import piece.King;
import piece.Knight;
import piece.NoPiece;
import piece.Pawn;
import piece.Piece;
import piece.Queen;
import piece.Rookie;
import piece.Side;

public class Board {

    private Piece[][] board = new Piece[8][8];
    private HashMap<String, Integer> notationConverter;
    private HashMap<Integer, String> backendConverter;

    public void print(){
        for (Piece[] i : board) {
            System.out.println(Arrays.toString(i));
        }
    }

    public Board(){
        //initializing board
        board[0] = Board.faceRow(Side.BLACK, 0);
        board[1] = Board.pawnRow(Side.BLACK, 1);
        for (int i = 2; i < 6; i++) {
            board[i] = Board.noRow(i);
        }
        board[6] = Board.pawnRow(Side.WHITE, 6);
        board[7] = Board.faceRow(Side.WHITE, 7);

        //setting up hashmaps
        notationConverter.put("a", 0);
        notationConverter.put("b", 1);
        notationConverter.put("c", 2);
        notationConverter.put("d", 3);
        notationConverter.put("e", 4);
        notationConverter.put("f", 5);
        notationConverter.put("g", 6);
        notationConverter.put("h", 7);

        backendConverter.put(0, "a");
        backendConverter.put(1, "b");
        backendConverter.put(2, "c");
        backendConverter.put(3, "d");
        backendConverter.put(4, "e");
        backendConverter.put(5, "f");
        backendConverter.put(6, "g");
        backendConverter.put(7, "h");
        
    }

    private static Piece[] pawnRow(Side side, int y) {
        Piece[] row = new Piece[8];

        for (int i = 0; i < row.length; i++) {
            row[i] = new Pawn(i, y, side);
        }

        return row;
    }

    private static Piece[] faceRow(Side side, int y) {
        Piece[] row =  {new Rookie(0, y, side), new Knight(1, y, side), new Bishop(2, y, side), new Queen(3, y, side), new King(4, y, side), new Bishop(5, y, side), new Knight(6, y, side), new Rookie(7, y, side)};

        return row;
    }

    private static Piece[] noRow(int y) {
        Piece[] row = new Piece[8];

        for (int i = 0; i < row.length; i++) {
            row[i] = new NoPiece(i, y);
        }

        return row;
    }
}

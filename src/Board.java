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
    private HashMap<String, Integer> notationConverter = new HashMap<String, Integer>();
    private HashMap<Integer, String> backendConverter = new HashMap<Integer, String>();

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
        final String axisLabel = "abcdefgh"; 
        for (int i = 0; i < 8; i++){
            notationConverter.put(""+axisLabel.charAt(i), i);
            backendConverter.put(i, ""+axisLabel.charAt(i));
        }        
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

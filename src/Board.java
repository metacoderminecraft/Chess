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

    private PieceType[][] board = new PieceType[8][8];
    public final HashMap<String, Integer> notationConverter = new HashMap<String, Integer>();
    public final HashMap<Integer, String> backendConverter = new HashMap<Integer, String>();

    public void print(){
        for (PieceType[] i : board) {
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

    private static PieceType[] pawnRow(Side side, int y) {
        PieceType[] row = new PieceType[8];

        for (int i = 0; i < row.length; i++) {
            row[i] = PieceType.PAWN;
        }

        return row;
    }

    private static PieceType[] faceRow(Side side, int y) {
        PieceType[] row =  {PieceType.ROOK, PieceType.KNIGHT, PieceType.BISHOP, PieceType.QUEEN, PieceType.KING, PieceType.BISHOP, PieceType.KNIGHT, PieceType.ROOK};

        return row;
    }

    private static PieceType[] noRow(int y) {
        PieceType[] row = new PieceType[8];

        for (int i = 0; i < row.length; i++) {
            row[i] = PieceType.NONE;
        }

        return row;
    }
}

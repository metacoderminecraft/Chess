import java.util.Arrays;

import piece.Piece;

public class Board {

    private Piece[][] board = new Piece[8][8];

    public void print(){
        for (Piece[] i : board) {
            System.out.println(Arrays.toString(i));
        }
    }
}

import java.util.Arrays;

import piece.Piece;
import piece.Rookie;

public class Board {

    private Piece[][] board = {
        {new Rookie(,},
        {},
        {},
        {},
        {},
        {},
        {},
        {}
    };

    public void print(){
        for (Piece[] i : board) {
            System.out.println(Arrays.toString(i));
        }
    }

    public Board(){

    }
}

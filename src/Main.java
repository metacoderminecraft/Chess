import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Human(Side.WHITE);
        Player player2 = new Human(Side.BLACK);
        Player currPlayer = player1;

        Board board = new Board(Board.startBoard());

        while(true) {
            board.print();
            try {
                board = board.movePiece(currPlayer.getInput(board), currPlayer.getSide());
            } catch(Exception e) {
                if(e.getMessage() == "Checkmate!") {
                    System.out.println(currPlayer.getSide() + " wins!");
                    break;
                }

                System.out.println(e);
                continue;
            }

            currPlayer = currPlayer == player1 ? player2 : player1;
        }
    }
}
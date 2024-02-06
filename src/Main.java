import java.util.Scanner;

import piece.Side;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Board board = new Board();
        board.print();

        Side currentSide = Side.WHITE;
        System.out.println(currentSide + ": Make your move");
        String answer = scanner.nextLine();
        
        while (answer != "quit") {

        }
    }
}
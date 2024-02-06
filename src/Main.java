import java.util.Scanner;

import piece.Side;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Board board = new Board(Board.startBoard());
        board.print();

        Side currentSide = Side.WHITE;
        System.out.println(currentSide + ": Make your move");
        String answer = scanner.nextLine();
        
        while (!answer.equals("quit")) {
            if (!Main.isValid(answer)) {
                System.out.println("Error, incorrect notation!");
                System.out.println(currentSide + ": Make your move");
                answer = scanner.nextLine();
                continue;
            }

            if (!currentSide.isValid(new Board.Move(Main.letterToNumber(answer.charAt(0)), Integer.valueOf(answer.charAt(1)), Main.letterToNumber(answer.charAt(2)), Integer.valueOf(answer.charAt(3))), board))

            if (currentSide == Side.BLACK) {
                currentSide = Side.WHITE;
            } else {
                currentSide = Side.BLACK;
            }
            System.out.println(currentSide + ": Make your move");
            answer = scanner.nextLine();
        }
    }

    public static boolean isValid(String answer) {
        return answer.length() == 4 && "abcdefgh".indexOf(answer.charAt(0)) != -1 && "abcdefgh".indexOf(answer.charAt(2)) != -1 && "12345678".indexOf(answer.charAt(1)) != -1 && "12345678".indexOf(answer.charAt(3)) != -1;
    }

    public static int letterToNumber(char character) {
        return character - 'a';
    }

    public static String numberToLetter(int num) {
        return String.valueOf(num);
    }
}
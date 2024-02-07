import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Board board = new Board(Board.startBoard());

        Side currentSide = Side.WHITE;
        String answer = "start";
        
        while (!answer.equals("quit")) {
            board.print();
            System.out.println(currentSide + ": Make your move");
            answer = scanner.nextLine();

            if (!isValid(answer)) {
                System.out.println("Error, incorrect notation!");
                continue;
            }

            try {
            board = board.movePiece(moveOf(answer));
            } catch (Exception e) {
                System.out.println("Illegal Move!");
                continue;
            }

            if (currentSide == Side.BLACK) {
                currentSide = Side.WHITE;
            } else {
                currentSide = Side.BLACK;
            }
        }
    }

    public static boolean isValid(String answer) {
        return answer.length() == 4 && "abcdefgh".indexOf(answer.charAt(0)) != -1 && "abcdefgh".indexOf(answer.charAt(2)) != -1 && "12345678".indexOf(answer.charAt(1)) != -1 && "12345678".indexOf(answer.charAt(3)) != -1;
    }

    //assumes str follows format of a String where [char, int, char, int]
    public static Board.Move moveOf(String str) {
        return new Board.Move(letterToNumber(str.charAt(0)), 8 - Character.getNumericValue(str.charAt(1)), letterToNumber(str.charAt(2)), 8 - Character.getNumericValue(str.charAt(3)));
    }

    public static int letterToNumber(char character) {
        return character - 'a';
    }

    public static String numberToLetter(int num) {
        return String.valueOf(num);
    }
}
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Board board = new Board(Board.startBoard());
        board.print();

        Side currentSide = Side.WHITE;
        System.out.println(currentSide + ": Make your move");
        String answer = scanner.nextLine();
        
        while (!answer.equals("quit")) {
            if (!isValid(answer)) {
                System.out.println("Error, incorrect notation!");
                System.out.println(currentSide + ": Make your move");
                answer = scanner.nextLine();
                continue;
            }


            board.movePiece(moveOf(answer));

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

    //assumes str follows format of a String where [char, int, char, int]
    public static Board.Move moveOf(String str) {
        return new Board.Move(letterToNumber(str.charAt(0)), Integer.valueOf(str.charAt(1)), letterToNumber(str.charAt(2)), Integer.valueOf(str.charAt(3)));
    }

    public static int letterToNumber(char character) {
        return character - 'a';
    }

    public static String numberToLetter(int num) {
        return String.valueOf(num);
    }
}
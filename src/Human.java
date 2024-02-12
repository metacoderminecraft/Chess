import java.util.Scanner;

public class Human implements Player {
    public final Side side;
    private final Scanner scanner = new Scanner(System.in);

    public Human(Side side) {
        this.side = side;
    }

    @Override
    public String toString() {
        return side.toString();
    }

    @Override
    public Side getSide() {
        return side;
    }

    @Override
    public Board.Move getInput(Board board) {
        System.out.println(side + ": Make your move");
        String answer = scanner.nextLine();

        while (true) {
            if (isValid(answer)) {
                if (board.isValid(moveOf(answer), side)) {
                    break;
                } else {
                    System.out.println("Illegal Move!");
                }
            } else {
                System.out.println("Incorrect notation!");
            }
            board.print();   
            answer = scanner.nextLine();           
        }
        
        return moveOf(answer);
    }

    public static boolean isValid(String answer) {
        if (answer.length() != 4) {
            return false;
        }
        if (answer.charAt(0) == answer.charAt(2) && answer.charAt(1) == answer.charAt(3)) {
            return false;
        }

        return answer.length() == 4 && "abcdefgh".indexOf(answer.charAt(0)) != -1 && "abcdefgh".indexOf(answer.charAt(2)) != -1 && "12345678".indexOf(answer.charAt(1)) != -1 && "12345678".indexOf(answer.charAt(3)) != -1;
    }

    //assumes str follows format of a String where [char, int, char, int] (isValid was called)
    public static Board.Move moveOf(String str) {
        return new Board.Move(letterToNumber(str.charAt(0)), 8 - Character.getNumericValue(str.charAt(1)), letterToNumber(str.charAt(2)), 8 - Character.getNumericValue(str.charAt(3)));
    }

    public static int letterToNumber(char character) {
        return character - 'a';
    }
}

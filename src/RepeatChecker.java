public class RepeatChecker {
    private Board[] lastBoards = new Board[5];

    public RepeatChecker() {
        for (int i = 0; i < lastBoards.length; i++) {
            lastBoards[i] = new Board(Board.noBoard());
        }
    }

    public void addBoard(Board board) {
        for (int i = 0; i < lastBoards.length - 1; i++) {
            lastBoards[i] = lastBoards[i + 1];
        } 

        lastBoards[lastBoards.length - 1] = board;
    }

    public boolean isDraw() {
        for (int i = 0; i < lastBoards.length; i++) {
            for (int j = i+1; j < lastBoards.length; j++) {
                if (lastBoards[i].equals(lastBoards[j]) && !lastBoards[i].equals(new Board(Board.noBoard()))) {
                    return true;
                }
            }
        }

        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        Player player1 = new Human(Side.WHITE);
        Player player2 = new Human(Side.BLACK);
        Player currPlayer = player1;

        Board board = new Board(Board.startBoard());
        board.print();

        while(true) {
            Player otherPlayer = currPlayer == player1 ? player2 : player1;
            board = board.movePiece(currPlayer.getInput(board), currPlayer.getSide());
            board.print();

            if(board.isCheckMate(otherPlayer.getSide())) {
                System.out.println(currPlayer + " is the GOAT!");
                break;
            } else if(board.isCheck(otherPlayer.getSide())) {
                System.out.println("AAAAAAAAAAAAAAAAAAAAA CHEEEEEEEEEEECK!!!!!!!");
            } 
                
            currPlayer = otherPlayer;
        }
    }
}
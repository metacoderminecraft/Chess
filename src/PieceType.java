import piece.Piece;

public enum PieceType {
    KING() {
        @Override
        public String toString() {
            return "K";
        }
    },
    QUEEN() {
        @Override
        public String toString() {
            return "Q";
        }
    },
    ROOK() {
        @Override
        public String toString() {
            return "R";
        }
    },
    BISHOP() {
        @Override
        public String toString() {
            return "B";
        }
    },
    KNIGHT() {
        @Override
        public String toString() {
            return "N";
        }
    },
    PAWN() {
        @Override
        public String toString() {
            return "P";
        }
    },
    NONE() {
        @Override
        public String toString() {
            return " ";
        }
    };

    public boolean isValid(Board.Move move, Board board) {return true;};
    public abstract String toString();
}

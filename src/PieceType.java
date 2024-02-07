public enum PieceType {
    KING() {
        @Override
        public String toString() {
            return "K";
        }

        @Override
        public boolean isValid(Board.Move move, Board board) {
            return true;
        }
    },
    QUEEN() {
        @Override
        public String toString() {
            return "Q";
        }

        @Override
        public boolean isValid(Board.Move move, Board board) {
            return true;
        }
    },
    ROOK() {
        @Override
        public String toString() {
            return "R";
        }


        @Override
        public boolean isValid(Board.Move move, Board board) {
            return true;
        }
    },
    BISHOP() {
        @Override
        public String toString() {
            return "B";
        }

        @Override
        public boolean isValid(Board.Move move, Board board) {
            return true;
        }
    },
    KNIGHT() {
        @Override
        public String toString() {
            return "N";
        }

        @Override
        public boolean isValid(Board.Move move, Board board) {
            return true;
        }
    },
    PAWN() {
        @Override
        public String toString() {
            return "P";
        }

        @Override
        public boolean isValid(Board.Move move, Board board) {
            return true;
        }
    },
    NONE() {
        @Override
        public String toString() {
            return " ";
        }

        @Override
        public boolean isValid(Board.Move move, Board board) {
            return true;
        }
    };

    public abstract boolean isValid(Board.Move move, Board board);
    public abstract String toString();
}

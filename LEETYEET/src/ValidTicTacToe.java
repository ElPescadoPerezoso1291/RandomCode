public class ValidTicTacToe {
    public static void main(String[] args) {
        String[] board = new String[]{"XOX", "O O", "XOX"};
        System.out.println(validTicTacToe(board));
    }
    public static boolean validTicTacToe(String[] board) {
        if(!(win('X', board) && win('O', board))) {
            int countX = 0, countO = 0;
            for(String s : board) {
                for(int i = 0; i < s.length(); i++) {
                    if(s.charAt(i) == 'X') {
                        countX++;
                    } else if (s.charAt(i) == 'O') {
                        countO++;
                    }
                }
            }
            return countX - countO == 1 || countX - countO == 0;
        }
        return false;
    }

    public static boolean win(char x, String[] board) {
        return columnWin(x, board) || rowWin(x, board) || diag(x, board);
    }

    public static boolean columnWin(char x, String[] board) {
        for(int col = 0; col < 3; col++) {
            int count = 0;
            for(int row = 0; row < 3; row++) {
                if (board[row].charAt(col) == x) {
                    count++;
                } else {
                    count--;
                }
            }
            if (count == 3) {
                return true;
            }
        }
        return false;
    }

    public static boolean rowWin(char x, String[] board) {
        for(int row = 0; row < 3; row++) {
            int count = 0;
            for(int col = 0; col < 3; col++) {
                if (board[row].charAt(col) == x) {
                    count++;
                } else {
                    count--;
                }
            }
            if (count == 3) {
                return true;
            }
        }
        return false;
    }

    public static boolean diag(char x, String[] board) {
        return (board[0].charAt(0) == x && board[1].charAt(1) == x && board[2].charAt(2) == x) ||
                (board[0].charAt(2) == x && board[1].charAt(1) == x && board[2].charAt(0) == x);
    }
}

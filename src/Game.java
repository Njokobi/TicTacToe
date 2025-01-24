import java.util.Arrays;

public class Game {
    private final int GAME_X = 3;
    private final int GAME_Y = 3;

    String[][] board = new String[GAME_X][GAME_Y];

    //Class constructor
    public Game () {
        initGameBoard(board);
    }

    /*
    Determines if the board has a win on it

        @Params
            none

     */

    public boolean isLine() {
        int xCount = 0;
        int oCount = 0;

        //Probably a better way to do all of this but lazy
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (i != j) {
                    continue;
                }
                switch (board[i][j]) {
                    case "X" -> xCount += 1;
                    case "O" -> oCount += 1;
                }
            }
        }

        switch (board[0][2]) {
            case "X" -> xCount += 1;
            case "O" -> oCount += 1;
        }
        switch (board[2][0]) {
            case "X" -> xCount += 1;
            case "O" -> oCount += 1;
        }

        if (xCount == 3) {
            System.out.println("X Wins!");
            printGame();
            return true;
        } else if (oCount == 3) {
            System.out.println("O Wins!");
            printGame();
            return true;
        }

        xCount = 0;
        oCount = 0;

        for (String[] row : board) {
            for (int j = 0; j < board.length; j++) {
                switch (row[j]) {
                    case "X" -> xCount += 1;
                    case "O" -> oCount += 1;
                }
                if (xCount == 3) {
                    System.out.println("X Wins!");
                    printGame();
                    return true;
                } else if (oCount == 3) {
                    System.out.println("O Wins!");
                    printGame();
                    return true;
                }
            }
            xCount = 0;
            oCount = 0;
        }

        for (int i = 0; i < board.length; i++) {
            for (String[] column : board) {
                switch (column[i]) {
                    case "X" -> xCount += 1;
                    case "O" -> oCount += 1;
                }
                if (xCount == 3) {
                    System.out.println("X Wins!");
                    printGame();
                    return true;
                } else if (oCount == 3) {
                    System.out.println("O Wins!");
                    printGame();
                    return true;
                }
            }
            xCount = 0;
            oCount = 0;
        }

        return false;
    }
    /*
    Determines if the board is full and prints to console if true

        @Params
            String [][] board
                the board to search

     */

    public boolean isBoardFull() {
        for (String[] row : board) {
            if (Arrays.toString(row).contains("-")) {
                return true;
            }
        }
        return false;
    }

    /*
    Fills board with "-", the base char for the game

        @Params
            String [][] board
                the board to fill

     */
    public void initGameBoard(String[][] board) {
        for (int i = 0; i < board.length; i++) {
            board[i] = new String[]{"-","-","-"};
        }
    }

    /*
    Prints the game board

        @Params
            String [][] board
                the board to print

     */
    public void printGame() {
        System.out.println();
        for (String[] row : board) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(row[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    /*
    Determines if played (inputted) position is legal

        @Params
            int x
                X position to check

            int y
                Y position to check

     */

    public boolean isLegalPosition(int x, int y) {
        boolean isLegal = true;
        if (y > 2) {
            System.out.println("Your y position is greater than the allowed dimensions (3 by 3, counted from 0 - 2).");
            isLegal = false;
        } else if (x > 2) {
            System.out.println("Your x position is greater than the allowed dimensions (3 by 3, counted from 0 - 2).");
            isLegal = false;
        }

        return !isLegal;
    }

    /*
    Determines if played (inputted) position is filled

        @Params
            int x
                X position to check

            int y
                Y position to check

            String[][] game
                The game board to check

     */
    public boolean isLegalPosition(int x, int y, String[][] game) {

        return isLegalPosition(x, y) || !"-".equals(game[y][x]);

    }
}

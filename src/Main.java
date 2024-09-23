import java.util.Arrays;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        int turnCount = 0;
        int y;
        int x;
        String symbol;
        String[][] game = new String[3][3];
        java.util.Scanner sc = new java.util.Scanner(System.in);
        initGameBoard(game);

        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("Who's going first? Input X or O.");

        while (true) {
            try {
                symbol = sc.next();
                if ("X".equals(symbol) || "O".equals(symbol)) {
                    break;
                }
                System.out.println("Please input an X or an O.");
            } catch (Exception e) {
                System.out.println("Please input an X or an O.");
                sc.reset();
            }

        }

        while (true) {
            System.out.println("What position would you like to start in? Enter your position (ex: 0 0 would be top left and 2 2 bottom right)");

            try {
                y = sc.nextInt();
                x = sc.nextInt();

                if (isLegalPosition(x, y)) {
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please input an integer.");
                y = 0;
                x = 0;
                sc = new java.util.Scanner(System.in);
                continue;
            }

            break;
        }

        game[y][x] = symbol;

        if ("X".equals(symbol)) {
            symbol = "O";
        } else {
            symbol = "X";
        }

        while (true) {
            printGame(game);

            System.out.printf("It's %s's turn now. Please enter a valid position (within 3 by 3, counted from 0 - 2). \n", symbol);

            try {
                y = sc.nextInt();
                x = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please input an integer.");
                y = 0;
                x = 0;
                sc = new java.util.Scanner(System.in);
                continue;
            }

            if (isLegalPosition(x, y, game)) {
                System.out.println("That spot is either taken or not on the board!");
                continue;
            }

            game[y][x] = symbol;
            turnCount += 1;

            if ("X".equals(symbol)) {
                symbol = "O";
            } else {
                symbol = "X";
            }

            if (turnCount >= 4 && isLine(game)) {
                break;
            } else if (turnCount > 7 && isBoardFull(game)) {
                break;
            }
        }
        System.out.println("Thanks for playing!");
    }
   
    /*
     Determines if there is a match along a diagonal, along a horizontal and a vertical stretch
     
     Params:
     
        String[][] board
            the game board
    */

    public static boolean isLine(String[][] board) {
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
            printGame(board);
            return true;
        } else if (oCount == 3) {
            System.out.println("O Wins!");
            printGame(board);
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
                    printGame(board);
                    return true;
                } else if (oCount == 3) {
                    System.out.println("O Wins!");
                    printGame(board);
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
                    printGame(board);
                    return true;
                } else if (oCount == 3) {
                    System.out.println("O Wins!");
                    printGame(board);
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

    public static boolean isBoardFull(String[][] board) {
        for (String[] row : board) {
            String temp = Arrays.toString(row);
            if (temp.contains("-")) {
                return true;
            }
        }
        return false;
    }

    public static void initGameBoard(String[][] board) {
        for (int i = 0; i < board.length; i++) {
            board[i] = new String[]{"-","-","-"};
        }
    }

    public static void printGame(String[][] board) {
        System.out.println();
        for (String[] row : board) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(row[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean isLegalPosition(int x, int y) {
        boolean isLegal = true;
        if (y > 2) {
            System.out.println("Your y position is greater than the allowed dimensions (3 by 3, counted from 0 - 2).");
            y = 0;
            x = 0;
            isLegal = false;
        } else if (x > 2) {
            System.out.println("Your x position is greater than the allowed dimensions (3 by 3, counted from 0 - 2).");
            y = 0;
            x = 0;
            isLegal = false;
        }

        return !isLegal;
    }

    public static boolean isLegalPosition(int x, int y, String[][] game) {
        if (isLegalPosition(x, y)) {
            return true;
        }

        return  !"-".equals(game[y][x]);

    }
}
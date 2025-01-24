import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        int turnCount = 0;
        int y;
        int x;
        String symbol;
        Game game = new Game();
        java.util.Scanner sc = new java.util.Scanner(System.in);

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

                if (game.isLegalPosition(x, y)) {
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please input an integer.");
                sc = new java.util.Scanner(System.in);
                continue;
            }

            break;
        }

        game.board[y][x] = symbol;

        if ("X".equals(symbol)) {
            symbol = "O";
        } else {
            symbol = "X";
        }

        while (true) {
            game.printGame();

            System.out.printf("It's %s's turn now. Please enter a valid position (within 3 by 3, counted from 0 - 2). \n", symbol);

            try {
                y = sc.nextInt();
                x = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please input an integer.");
                sc = new java.util.Scanner(System.in);
                continue;
            }

            if (game.isLegalPosition(x, y, game.board)) {
                System.out.println("That spot is either taken or not on the board!");
                continue;
            }

            game.board[y][x] = symbol;
            turnCount += 1;

            if ("X".equals(symbol)) {
                symbol = "O";
            } else {
                symbol = "X";
            }

            if (turnCount >= 4 && game.isLine()) {
                break;
            } else if (turnCount > 7 && game.isBoardFull()) {
                break;
            }
        }
        System.out.println("Thanks for playing!");
    }
   
}
    import java.util.Scanner;

public class TicTacToe {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Board board;

    public TicTacToe() {
        player1 = new Player('X');
        player2 = new Player('O');
        board = new Board();
        currentPlayer = player1;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            board.clear();
            currentPlayer = player1;
            boolean gameEnded = false;

            while (!gameEnded) {
                System.out.println("Current Player: " + currentPlayer.getMarker());
                board.print();

                int row = -1;
                int col = -1;
                boolean validMove = false;

                while (!validMove) {
                    System.out.print("row (0-2): ");
                    row = scanner.nextInt();
                    System.out.print("column (0-2): ");
                    col = scanner.nextInt();

                    if (row >= 0 && row < 3 && col >= 0 && col < 3 && board.isCellEmpty(row, col)) {
                        validMove = true;
                    } else {
                        System.out.println("Invalid move. Square is already taken or out of bounds. Try again.\n");
                    }
                }

                board.place(row, col, currentPlayer.getMarker());

                if (hasWinner()) {
                    System.out.println("Current Player: " + currentPlayer.getMarker());
                    board.print();
                    System.out.println("Player " + currentPlayer.getMarker() + " wins!");
                    gameEnded = true;
                } else if (board.isFull()) {
                    board.print();
                    System.out.println("It's a draw!");
                    gameEnded = true;
                } else {
                    switchCurrentPlayer();
                }
            }

            System.out.print("Play again? (y/n): ");
            char response = scanner.next().toLowerCase().charAt(0);
            playAgain = (response == 'y');
            System.out.println();
        }
        scanner.close();
    }

    private void switchCurrentPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    private boolean hasWinner() {
        char[][] cells = board.getCells();
        char m = currentPlayer.getMarker();

        for (int i = 0; i < 3; i++) {
            if (cells[i][0] == m && cells[i][1] == m && cells[i][2] == m) return true;
            if (cells[0][i] == m && cells[1][i] == m && cells[2][i] == m) return true;
        }

        if (cells[0][0] == m && cells[1][1] == m && cells[2][2] == m) return true;
        if (cells[0][2] == m && cells[1][1] == m && cells[2][0] == m) return true;

        return false;
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.start();
    }
}
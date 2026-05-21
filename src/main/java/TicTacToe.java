import java.util.Scanner;

public class TicTacToe {

    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Board board;

    public TicTacToe() {

        // Spieler erstellen
        player1 = new Player('X');
        player2 = new Player('O');

        // Spielfeld erstellen
        board = new Board();

        // X beginnt
        currentPlayer = player1;
    }

    public void start() {

        Scanner scanner = new Scanner(System.in);

        // läuft bis das Spielfeld voll ist
        while (!board.isFull()) {

            System.out.println("Current Player: " + currentPlayer.getMarker());

            // User Story 2:
            // Spielfeld anzeigen
            board.print();

            int row;
            int col;

            // User Story 1:
            // Spieler soll ein leeres Feld auswählen können
            while (true) {

                System.out.print("row (0-2): ");
                row = scanner.nextInt();

                System.out.print("column (0-2): ");
                col = scanner.nextInt();

                // prüfen ob Feld gültig und leer ist
                if (row >= 0 && row < 3 &&
                        col >= 0 && col < 3 &&
                        board.isCellEmpty(row, col)) {

                    break;

                } else {

                    System.out.println("Invalid move. Try again.\n");
                }
            }

            // Symbol setzen
            board.place(row, col, currentPlayer.getMarker());

            // Spieler wechseln
            switchCurrentPlayer();
        }

        // finales Spielfeld anzeigen
        board.print();

        scanner.close();
    }

    private void switchCurrentPlayer() {

        // zwischen X und O wechseln
        if (currentPlayer == player1) {

            currentPlayer = player2;

        } else {

            currentPlayer = player1;
        }
    }

    public static void main(String[] args) {

        TicTacToe game = new TicTacToe();

        game.start();
    }
}
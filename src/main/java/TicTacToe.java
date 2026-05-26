import java.util.Scanner;

public class TicTacToe {

    private Player player1;
    private Player player2;
    Player currentPlayer;
    Board board;

    public TicTacToe() {
        // Spieler erstellen
        player1 = new Player('X');
        player2 = new Player('O');

        // Spielfeld erstellen
        board = new Board();

        // X beginnt immer
        currentPlayer = player1;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        // Diese äußere Schleife macht es möglich, mehrere Runden zu spielen
        while (true) {

            // Spielfeld und Spieler zurücksetzen für eine neue Runde
            board.clear();
            currentPlayer = player1;

            boolean isGameOver = false;

            // läuft bis das Spielfeld voll ist ODER jemand gewonnen hat
            while (!board.isFull()) {

                System.out.println("Current Player: " + currentPlayer.getMarker());

                // Spielfeld anzeigen
                board.print();

                int row;
                int col;

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

                // prüfen ob dieser Zug gewonnen hat
                if (hasWinner()) {
                    isGameOver = true;
                    break;
                }

                // Spieler wechseln
                switchCurrentPlayer();
            }

            // finales Spielfeld anzeigen
            board.print();

            // Spielende-Nachricht ausgeben
            if (isGameOver) {
                System.out.println("Game Over! Player " + currentPlayer.getMarker() + " has won!");
            } else {
                System.out.println("Game Over! It's a draw.");
            }

            // Spieler fragen ob sie nochmal spielen wollen
            System.out.print("Do you want to play again? (yes/no): ");
            String answer = scanner.next().trim().toLowerCase();

            // wenn nicht "yes" dann Spiel beenden
            if (!answer.equals("yes")) {
                System.out.println("Thanks for playing! Goodbye.");
                break;
            }

            // sonst geht die äußere Schleife nochmal von vorne los
            System.out.println("\nStarting a new game!\n");
        }

        scanner.close();
    }

    boolean hasWinner() {
        char marker = currentPlayer.getMarker();

        // horizontale Reihen prüfen
        for (int i = 0; i < 3; i++) {
            if (board.getCell(i, 0) == marker && board.getCell(i, 1) == marker && board.getCell(i, 2) == marker) {
                return true;
            }
        }

        // vertikale Spalten prüfen
        for (int j = 0; j < 3; j++) {
            if (board.getCell(0, j) == marker && board.getCell(1, j) == marker && board.getCell(2, j) == marker) {
                return true;
            }
        }

        // Diagonale links oben nach rechts unten
        if (board.getCell(0, 0) == marker && board.getCell(1, 1) == marker && board.getCell(2, 2) == marker) {
            return true;
        }

        // Diagonale rechts oben nach links unten
        if (board.getCell(0, 2) == marker && board.getCell(1, 1) == marker && board.getCell(2, 0) == marker) {
            return true;
        }

        return false;
    }

    void switchCurrentPlayer() {
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
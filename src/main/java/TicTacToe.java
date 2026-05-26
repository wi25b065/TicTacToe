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

        // Variable, um sich zu merken, ob jemand gewonnen hat
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

            // Sofort nachdem ein Symbol gesetzt wurde, prüfen wir,
            // ob dieser Zug zum Sieg geführt hat. Wir verwenden die NEUE Methode.
            if (hasWinner()) {
                isGameOver = true; // Wir haben einen Gewinner!
                break;
            }

            // Spieler wechseln
            switchCurrentPlayer();
        }

        // finales Spielfeld anzeigen
        board.print();


        // Spielende-Nachricht ausgeben (Sieg oder Unentschieden)
        if (isGameOver) {
            // Wenn die Schleife durch einen Sieg abgebrochen wurde:
            System.out.println("Game Over! Player " + currentPlayer.getMarker() + " has won!");
        } else {
            // Wenn die Schleife normal zu Ende ging (Feld ist voll):
            System.out.println("Game Over! It's a draw.");
        }


        scanner.close();
    }

    private boolean hasWinner() {
        char marker = currentPlayer.getMarker(); // Das Symbol des Spielers, der gerade dran war

        // 1. Horizontale Reihen prüfen (3 Reihen)
        // Wir benutzen nun board.getCell(row, col)
        for (int i = 0; i < 3; i++) {
            if (board.getCell(i, 0) == marker && board.getCell(i, 1) == marker && board.getCell(i, 2) == marker) {
                return true;
            }
        }

        // 2. Vertikale Spalten prüfen (3 Spalten)
        for (int j = 0; j < 3; j++) {
            if (board.getCell(0, j) == marker && board.getCell(1, j) == marker && board.getCell(2, j) == marker) {
                return true;
            }
        }

        // 3. Diagonale (von links oben nach rechts unten) prüfen
        if (board.getCell(0, 0) == marker && board.getCell(1, 1) == marker && board.getCell(2, 2) == marker) {
            return true;
        }

        // 4. Diagonale (von rechts oben nach links unten) prüfen
        if (board.getCell(0, 2) == marker && board.getCell(1, 1) == marker && board.getCell(2, 0) == marker) {
            return true;
        }

        // Wenn nichts zutrifft, hat der Spieler noch nicht gewonnen
        return false;
    }
    // -----------

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
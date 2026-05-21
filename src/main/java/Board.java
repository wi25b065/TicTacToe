public class Board {

    private char[][] cells;

    public Board() {

        // 3x3 Spielfeld erstellen
        cells = new char[3][3];

        clear();
    }

    public boolean isCellEmpty(int x, int y) {

        // prüfen ob Feld leer ist
        return cells[x][y] == ' ';
    }

    public void place(int x, int y, char marker) {

        // Symbol auf Feld setzen
        cells[x][y] = marker;
    }

    public boolean isFull() {

        // prüfen ob noch freie Felder existieren
        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if (cells[i][j] == ' ') {

                    return false;
                }
            }
        }

        return true;
    }

    public void clear() {

        // alle Felder zurücksetzen
        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                cells[i][j] = ' ';
            }
        }
    }

    public void print() {

        // User Story 2:
        // aktuelles Spielfeld anzeigen

        System.out.println("_______");

        for (int i = 0; i < 3; i++) {

            System.out.print("|");

            for (int j = 0; j < 3; j++) {

                System.out.print(cells[i][j] + "|");
            }

            System.out.println();
        }

        System.out.println("_______");

        System.out.println();
    }
}
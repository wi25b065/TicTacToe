import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private Board board;

    // vor jedem Test ein frisches Board erstellen
    @BeforeEach
    void setUp() {
        board = new Board();
    }

    // --- isCellEmpty ---

    @Test
    void isCellEmpty_shouldReturnTrue_whenCellIsEmpty() {
        // frisches Board, alle Felder sollten leer sein
        assertTrue(board.isCellEmpty(0, 0));
    }

    @Test
    void isCellEmpty_shouldReturnFalse_whenCellIsOccupied() {
        // nach dem Setzen eines Symbols ist das Feld nicht mehr leer
        board.place(0, 0, 'X');
        assertFalse(board.isCellEmpty(0, 0));
    }

    // --- place ---

    @Test
    void place_shouldSetMarkerCorrectly() {
        // Symbol setzen und prüfen ob es wirklich dort steht
        board.place(1, 1, 'X');
        assertEquals('X', board.getCell(1, 1));
    }

    @Test
    void place_shouldNotAffectOtherCells() {
        // nur eine Zelle setzen, der Rest sollte leer bleiben
        board.place(0, 0, 'X');
        assertEquals(' ', board.getCell(2, 2));
    }

    // --- isFull ---

    @Test
    void isFull_shouldReturnFalse_whenBoardIsEmpty() {
        // frisches Board ist nicht voll
        assertFalse(board.isFull());
    }

    @Test
    void isFull_shouldReturnTrue_whenAllCellsAreOccupied() {
        // alle Felder füllen
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board.place(i, j, 'X');
            }
        }
        assertTrue(board.isFull());
    }

    // --- clear ---

    @Test
    void clear_shouldResetAllCells() {
        // Board füllen, dann leeren und prüfen
        board.place(0, 0, 'X');
        board.place(1, 1, 'O');
        board.clear();
        assertTrue(board.isCellEmpty(0, 0));
        assertTrue(board.isCellEmpty(1, 1));
    }

    @Test
    void clear_shouldMakeBoardNotFull() {
        // volles Board leeren → darf nicht mehr voll sein
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board.place(i, j, 'X');
            }
        }
        board.clear();
        assertFalse(board.isFull());
    }

    // --- getCell ---

    @Test
    void getCell_shouldReturnSpace_whenEmpty() {
        // leere Zelle sollte ein Leerzeichen zurückgeben
        assertEquals(' ', board.getCell(0, 0));
    }

    @Test
    void getCell_shouldReturnCorrectMarker_afterPlace() {
        // nach place() soll getCell() das richtige Symbol zurückgeben
        board.place(2, 2, 'O');
        assertEquals('O', board.getCell(2, 2));
    }
}
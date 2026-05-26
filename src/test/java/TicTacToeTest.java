import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeTest {

    private TicTacToe game;

    @BeforeEach
    void setUp() {
        game = new TicTacToe();
    }

    // --- hasWinner ---

    @Test
    void hasWinner_shouldReturnTrue_whenRowIsComplete() {
        game.board.place(0, 0, 'X');
        game.board.place(0, 1, 'X');
        game.board.place(0, 2, 'X');
        assertTrue(game.hasWinner());
    }

    @Test
    void hasWinner_shouldReturnFalse_whenBoardIsEmpty() {
        assertFalse(game.hasWinner());
    }

    // --- switchCurrentPlayer ---

    @Test
    void switchCurrentPlayer_shouldSwitchFromXToO() {
        assertEquals('X', game.currentPlayer.getMarker());
        game.switchCurrentPlayer();
        assertEquals('O', game.currentPlayer.getMarker());
    }

    @Test
    void switchCurrentPlayer_shouldSwitchBackToX() {
        game.switchCurrentPlayer();
        game.switchCurrentPlayer();
        assertEquals('X', game.currentPlayer.getMarker());
    }
}

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    // --- getMarker ---

    @Test
    void getMarker_shouldReturnX_forPlayerX() {
        // Spieler mit X erstellen und prüfen
        Player player = new Player('X');
        assertEquals('X', player.getMarker());
    }

    @Test
    void getMarker_shouldReturnO_forPlayerO() {
        // Spieler mit O erstellen und prüfen
        Player player = new Player('O');
        assertEquals('O', player.getMarker());
    }

    @Test
    void getMarker_shouldNotReturnWrongMarker() {
        Player player = new Player('X');
        assertNotEquals('O', player.getMarker());
    }

}


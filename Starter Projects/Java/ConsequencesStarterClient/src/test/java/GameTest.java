import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private final Game game = new Game(new SocketClient("ws://51.141.52.52:1234"));

    @Test
    void shouldRun() {
        game.run();
    }

    @Test
    void shouldHello() {
        game.hello();
        assertEquals("Welcome to Consequences, to get started send your name and room code.", game.hello());
    }

    @Test
    void shouldNameAndRoom() {
        game.sendNameAndRoom("Juan", "33");
        assertEquals("Welcome to Consequences, to get started send your name and room code.", game.hello());
    }
}

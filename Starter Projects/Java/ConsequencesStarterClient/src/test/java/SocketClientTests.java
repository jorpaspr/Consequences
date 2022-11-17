import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SocketClientTests {

    @Test
    void demoTestMethod() {
        // Arrange
        SocketClient socketClient = new SocketClient("ws://51.141.52.52:1234");

        // Act
        OutboundMessage message = new OutboundMessage();
        message.Hello = "Hello";
        socketClient.Send(message);
        var response = socketClient.Receive();

        // Assert
        assertEquals("Welcome to Consequences, to get started send your name and room code.", response.Message);
    }

    @Test
    void demoTest2() {
        // Arrange
        SocketClient socketClient = new SocketClient("ws://51.141.52.52:1234");

        // Act
        OutboundMessage message = new OutboundMessage();
        message.Hello = "Hello";
        socketClient.Send(message);
        var response = socketClient.Receive();
        System.out.println(response.Message);
        assertEquals("Welcome to Consequences, to get started send your name and room code.", response.Message);

        message = new OutboundMessage();
        message.Name = "Juan";
        message.Room = "33";
        socketClient.Send(message);
        response = socketClient.Receive();
        System.out.println(response.Message);
        System.out.println(response.Question);
        assertEquals("Welcome to room '33'. Please answer your first question when all players have joined the room.", response.Message);
        assertEquals("Please enter an adjective, followed by a person's name", response.Question);

        message = new OutboundMessage();
        message.Answer = "Ugly Peter";
        socketClient.Send(message);
        response = socketClient.Receive();
        System.out.println(response.Message);
        System.out.println(response.Question);
        assertEquals("Here is your next question.", response.Message);
        assertEquals("Please enter another adjective, followed by a different person's name", response.Question);

        message = new OutboundMessage();
        message.Answer = "Beautiful Sarah";
        socketClient.Send(message);
        response = socketClient.Receive();
        System.out.println(response.Message);
        System.out.println(response.Question);
        assertEquals("Here is your next question.", response.Message);
        assertEquals("Please enter a place where people could meet", response.Question);

        message = new OutboundMessage();
        message.Answer = "London";
        socketClient.Send(message);
        response = socketClient.Receive();
        System.out.println(response.Message);
        System.out.println(response.Question);
        assertEquals("Here is your next question.", response.Message);
        assertEquals("Please enter why they were there", response.Question);

        message = new OutboundMessage();
        message.Answer = "Shopping";
        socketClient.Send(message);
        response = socketClient.Receive();
        System.out.println(response.Message);
        System.out.println(response.Question);
        assertEquals("Here is your next question.", response.Message);
        assertEquals("Please enter something the first person did", response.Question);

        message = new OutboundMessage();
        message.Answer = "Wash the car";
        socketClient.Send(message);
        response = socketClient.Receive();
        System.out.println(response.Message);
        System.out.println(response.Question);
        assertEquals("Here is your next question.", response.Message);
        assertEquals("Please enter something the second person did", response.Question);

        message = new OutboundMessage();
        message.Answer = "Eat pancakes";
        socketClient.Send(message);
        response = socketClient.Receive();
        System.out.println(response.Message);
        System.out.println(response.Question);
        assertEquals("Here is your next question.", response.Message);
        assertEquals("Please enter the consequence of their actions", response.Question);

        message = new OutboundMessage();
        message.Answer = "Got married";
        socketClient.Send(message);
        response = socketClient.Receive();
        System.out.println(response.Message);
        System.out.println(response.Results);
        assertEquals("Your game is complete! Here are your final results. To play again send your name and room code.", response.Message);
        assertEquals(Collections.singletonList("Ugly Peter met Beautiful Sarah at London to Shopping. Peter Wash the car, whilst Sarah Eat pancakes. The consequence of their actions was Got married."), response.Results);
    }
}

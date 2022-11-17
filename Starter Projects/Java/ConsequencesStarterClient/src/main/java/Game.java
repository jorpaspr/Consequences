import java.util.ArrayList;
import java.util.List;

public class Game {
    private final SocketClient socketClient;

    List<String> players = new ArrayList<>();

    public Game(SocketClient socketClient) {
        this.socketClient = socketClient;
    }

    public void run() {
        hello();
    }

    public String hello() {
        OutboundMessage message = new OutboundMessage();
        message.Hello = "Hello";
        socketClient.Send(message);
        var response = socketClient.Receive();

        // TODO add printer
        return response.Message;
    }

    public void sendNameAndRoom(String name, String room) {
        OutboundMessage message = new OutboundMessage();
        message.Name = name;
        message.Room = room;
        socketClient.Send(message);
        var response = socketClient.Receive();
        players = response.Players;

        // TODO Add printer
//        printer.print(message);
    }
}

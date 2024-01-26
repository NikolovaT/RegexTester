import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final String REGEX_DB = "regex_db.bin";
    ServerSocket server = null;

    public Server() {
        // TODO: initDB()
    }

    public void start() {
        try {
            server = new ServerSocket(8080);
            System.out.println("Server is up.");

            while (true) {
                Socket client = server.accept();
                ClientHandler clientHandler = new ClientHandler(client);
                new Thread(clientHandler).start();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

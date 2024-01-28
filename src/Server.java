import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Server {
    public static final String REGEX_DB = "regex_db.bin";
    ServerSocket server = null;

    public Server() {
        initDB();
    }

    public void start() {
        try {
            server = new ServerSocket(8080);
            System.out.println("Server is up.");
            List<Regex> regs = ClientHandler.loadRegex();
            System.out.println(regs);

            while (true) {
                Socket client = server.accept();
                ClientHandler clientHandler = new ClientHandler(client);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
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

    protected static void initDB() {
        if (new File(REGEX_DB).exists())
            return;
        List<Regex> regexes = new ArrayList<>();
        regexes.add(new Regex(Pattern.compile(""), "first one empty test"));
        ClientHandler.saveRegex(regexes);
    }
}

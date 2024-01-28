import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket server = null;
        Scanner console = null;
        Scanner in = null;
        PrintStream out = null;

        try {
            server = new Socket("localhost", 8080);
            console = new Scanner(System.in);
            in = new Scanner(server.getInputStream());
            out = new PrintStream(server.getOutputStream());

            userMenu(console, in, out);
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
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    protected static void userMenu(Scanner console, Scanner in, PrintStream out) {
        while (true) {
            System.out.println(in.nextLine());
            out.println(console.nextLine());

            String nextAction = in.nextLine();
            System.out.println(nextAction);

            switch (nextAction) {
                case "Invalid input.":
                    continue;
                case "REGEX CREATION":
                    createMenu(console, in, out);
                    break;
                case "BROWSING":
                    browseMenu(console, in, out);
                    break;
                case "GOODBYE":
                    return;
            }
        }
    }

    protected static void createMenu(Scanner console, Scanner in, PrintStream out) {
        System.out.println(in.nextLine());
        out.println(console.nextLine());

        System.out.println(in.nextLine());
        out.println(console.nextLine());

        //TODO:

    }

    protected static void browseMenu(Scanner console, Scanner in, PrintStream out) {
        System.out.println(in.nextLine());

        //TODO:

    }

}

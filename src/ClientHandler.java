import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ClientHandler implements Runnable{
    Socket clientSocket = null;
    public ClientHandler(Socket client){
        this.clientSocket = client;
    }


    @Override
    public void run(){
        System.out.println("New client connected.");
        Scanner in = null;
        PrintStream out = null;

        try {
            in = new Scanner(clientSocket.getInputStream());
            out = new PrintStream(clientSocket.getOutputStream());

            userMenu(in, out);

        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if (clientSocket != null) {
                try {
                    clientSocket.close();
                }
                catch (IOException e){
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

    protected static void userMenu(Scanner in, PrintStream out){
        out.println("Do you want to create new regex or browse the existing ones: [create/browse]");
        String nextAction = in.nextLine();

        if (nextAction.equalsIgnoreCase("create")) {
            createMenu(in, out);
        }
         else if (nextAction.equalsIgnoreCase("browse")){
            browseMenu(in, out);
        }

    }

    protected static void createMenu(Scanner in, PrintStream out){
        out.println("REGEX CREATION");
        out.println("Enter regex pattern: ");
        Pattern pattern = Pattern.compile(in.nextLine());

        out.println("Enter regex description: ");
        String description = in.nextLine();

        Regex regex = new Regex(pattern, description);
        //TODO: finish menu
    }
    protected static void browseMenu(Scanner in, PrintStream out){
        out.println("BROWSING");
        out.println("Enter keyword: ");
        String keyword = in.nextLine();
        //TODO: finish menu
    }
}

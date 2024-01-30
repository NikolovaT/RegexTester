import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class ClientHandler implements Runnable {
    Socket clientSocket = null;
    static final Object lock = new Object();

    public ClientHandler(Socket client) {
        this.clientSocket = client;
    }


    @Override
    public void run() {
        System.out.println("New client connected.");

        try (Scanner in = new Scanner(clientSocket.getInputStream()); PrintStream out = new PrintStream(clientSocket.getOutputStream())) {

            userMenu(in, out);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static void userMenu(Scanner in, PrintStream out) throws IOException {
        while (true) {

            out.println("Do you want to create new regex or browse the existing ones: [create/browse/exit]");
            String nextAction = in.nextLine();

            if (nextAction.equalsIgnoreCase("create")) createMenu(in, out);
            else if (nextAction.equalsIgnoreCase("browse")) browseMenu(in, out);
            else if (nextAction.equalsIgnoreCase("exit")) out.println("GOODBYE");
            else out.println("Invalid input.");


        }
    }

    protected static void createMenu(Scanner in, PrintStream out) throws IOException {
        out.println("REGEX CREATION");
        out.println("Enter regex pattern: ");
        Pattern pattern = Pattern.compile(in.nextLine());

        out.println("Enter regex description: ");
        String description = in.nextLine();

        Regex regex = new Regex(pattern, description);
        //TODO: add testing
        out.println("How many test to run: ");
        int numTest = Integer.parseInt(in.nextLine());
        String[] strings = new String[numTest];

        out.println("Enter the tests.");

        // Get test
        for (int i=0; i<numTest; i++){
            strings[i] = in.nextLine();
        }

        List<Boolean> results = RegexTester.test(regex, strings);

        // Send test
        for (int i=0; i<numTest; i++){
            if(results.get(i))
                out.println("Test No." + (i+1) + ": matches");
            else
                out.println("Test No." + (i+1) + ": no match");
        }

        out.println("Do you want to save your regex [y/n]");

        String nextAction = in.nextLine();

        if (nextAction.equalsIgnoreCase("y")){
            addRegexToFile(regex);
            out.println("Regex saved successfully");
        }
        else
            out.println("The regex is not saved");

    }

    protected static void browseMenu(Scanner in, PrintStream out) {
        out.println("BROWSING");
        out.println("Enter keyword: ");
        String keyword = in.nextLine();
        //TODO: finish menu
    }

    @SuppressWarnings("unchecked")
    protected static List<Regex> loadRegex() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(Server.REGEX_DB))) {
            return (List<Regex>) in.readObject();
        } catch (IOException e) {
            if (e instanceof InvalidClassException) {
                throw new RuntimeException("One or more of the User subclasses has likely changed." + " Serializable versions are not supported." + " Recreate the users file.", e);
            }

            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // Should never happen
            throw new RuntimeException(e);
        }

        return null;
    }

    public static void saveRegex(List<Regex> regexes) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(Server.REGEX_DB))) {
            out.writeObject(regexes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static void addRegexToFile(Regex regex) {
        synchronized (lock) {
            List<Regex> regexes = loadRegex();
            if (regexes != null) {
                regexes.add(regex);
            }
            saveRegex(regexes);
        }
    }

}

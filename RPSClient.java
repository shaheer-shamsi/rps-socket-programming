import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class RockPaperScissorsClient {

    public static void main(String[] args) {
        try {
            //connect to server
            Socket socket = new Socket("localhost", 36200);
            System.out.println("Connected to game server");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner input = new Scanner(System.in);

            //read prompt from server and send name
            System.out.println(in.readLine());
            out.println(input.nextLine());

            //read move prompt and send move
            System.out.println(in.readLine());
            out.println(input.nextLine());

            //get result
            System.out.println(in.readLine());

            //close everthing
            socket.close();
        } catch (IOException e) {
            //can not connect to server
            System.out.println("Server is unavilable or connection has failed!");
        }
    }
}

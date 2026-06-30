import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class RockPaperScissorsServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(36200);
        System.out.println("Server is running and waiting for players...");

        //wait for player 1
        Socket player1Socket = serverSocket.accept();
        System.out.println("Player 1 connected");

        //wait for player 2
        Socket player2Socket = serverSocket.accept();
        System.out.println("Player 2 connected");

        //setup in/out streams for both
        BufferedReader in1 = new BufferedReader(new InputStreamReader(player1Socket.getInputStream()));
        PrintWriter out1 = new PrintWriter(player1Socket.getOutputStream(), true);

        BufferedReader in2 = new BufferedReader(new InputStreamReader(player2Socket.getInputStream()));
        PrintWriter out2 = new PrintWriter(player2Socket.getOutputStream(), true);

        //get player names
        out1.println("Enter your name:");
        String name1 = in1.readLine();

        out2.println("Enter your name:");
        String name2 = in2.readLine();

        //ask moves from both players
        out1.println("Choose your move (1=Rock, 2=Paper, 3=Scissors):");
        int move1 = Integer.parseInt(in1.readLine());

        out2.println("Choose your move (1=Rock, 2=Paper, 3=Scissors):");
        int move2 = Integer.parseInt(in2.readLine());

        //decide winner
        String result1, result2;

        if (move1 == move2) {
            result1 = result2 = "It's a draw!";
        } else if ((move1 == 1 && move2 == 3) ||
                   (move1 == 2 && move2 == 1) ||
                   (move1 == 3 && move2 == 2)) {
            result1 = "You win!";
            result2 = "You lose!";
        } else {
            result1 = "You lose!";
            result2 = "You win!";
        }

        //send result to both
        out1.println("Hi " + name1 + ", " + result1);
        out2.println("Hi " + name2 + ", " + result2);

        //close everthing
        player1Socket.close();
        player2Socket.close();
        serverSocket.close();
    }
}
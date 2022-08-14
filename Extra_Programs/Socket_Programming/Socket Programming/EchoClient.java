import java.io.*;
import java.net.*;

public class EchoClient {

    public static void main(String[] args) throws IOException {

        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

		System.out.println();
		System.out.println("Connecting to echo server . . .");

        try {
            echoSocket = new Socket(args[0], 7);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                                        echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + args[0] + ".");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                               + "connection to: " + args[0] + ".");
            System.exit(1);
        }

		printInfo(echoSocket);

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader stdIn = new BufferedReader(isr);
                                   
		String userInput;

		while ((userInput = stdIn.readLine()) != null) {
			out.println(userInput);
			System.out.println("echo: " + in.readLine());
		}

		out.close();
		in.close();
		stdIn.close();
		echoSocket.close();
    }

	private static void printInfo(Socket socket){		
		
		try{
		System.out.println();
		System.out.println("Here is some info about your connection.");
		System.out.println();
		System.out.println("Remote Host: " + socket.getInetAddress().getHostName());
		System.out.println("Remote Port: " + socket.getPort());
		System.out.println("SoTimeout: " + socket.getSoTimeout());
		System.out.println("TcpNoDelay: " + socket.getTcpNoDelay());
		System.out.println("SoLinger: " + socket.getSoLinger());
		System.out.println("SendBufferSize: " + socket.getSendBufferSize());
		System.out.println("ReceiveBufferSize: " + socket.getReceiveBufferSize());
		}catch(SocketException se){}

		System.out.println();
		System.out.print("Please enter something ");
		System.out.println("and I'll echo it back (Ctrl-Z to quit)");
		System.out.println();

	}
}


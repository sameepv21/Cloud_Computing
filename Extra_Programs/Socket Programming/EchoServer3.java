import java.io.*;
import java.net.*;

public class EchoServer3{

	public static void main(String[] args) throws IOException{
		
		ServerSocket ss = new ServerSocket(getPort(args));
		System.out.println("Started: " + ss);

		try{

			while(true){

				Socket s = ss.accept();

				try{

					new ClientHandler(s);

				} catch(IOException ioe){

					s.close();

				}

			}

		} finally{
			
			ss.close();

		}
	}

	private static int getPort(String[] args){

		int port = -1;

		if(args.length != 1){

			System.err.println("Usage: EchoServer <port number>");
			System.exit(1);

		}

		try{

			port = Integer.parseInt(args[0]);

			if(port < 1024 ){

				System.err.print("port number must be greater ");
				System.err.println("than 1024");
				System.exit(1);

			}

		}catch(NumberFormatException nfe){

			System.err.println("port number must be an integer");
			System.exit(1);

		}

		return port;
	}

}		

class ClientHandler extends Thread{

	private Socket s;
	private BufferedReader in;
	private PrintWriter out;

	public ClientHandler(Socket socket) throws IOException{

		s = socket;
		in = new BufferedReader(new InputStreamReader(
				                    s.getInputStream()));

		out = new PrintWriter(new BufferedWriter(
				                  new OutputStreamWriter(
								  s.getOutputStream())), true);

		start();	//calls run

	}

	public void run(){

		try{

			while(true){

				String str = in.readLine();
				if(str.equals("END")) break;
				System.out.println("Echoing: " + str);
				out.println(str);

			}

			System.out.println("Closing . . .");

		} catch(IOException ioe1){
		} finally{
			try{
				s.close();
			}catch (IOException ioe2){}
		}

	}

}

		
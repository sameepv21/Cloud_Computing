import java.io.*;
import java.net.*;

public class EchoServer{


	public static void main(String[] args) throws IOException{
		
		ServerSocket ss = new ServerSocket(getPort(args));
		System.out.println("Started: " + ss);

		try{

			Socket s = ss.accept(); 

			try{

				System.out.println("Connection accepted: " + s);

				BufferedReader in = new BufferedReader(
				                    new InputStreamReader(
				                    s.getInputStream()));

				PrintWriter out = new PrintWriter(
				                  new BufferedWriter(
				                  new OutputStreamWriter(
								  s.getOutputStream())), true);

				while(true){

					String str = in.readLine();
					if(str.equals("END")) break;
					System.out.println("Echoing: " + str);
					out.println(str);

				}

			} finally{

				System.out.println("Closing . . .");
				s.close();

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

		
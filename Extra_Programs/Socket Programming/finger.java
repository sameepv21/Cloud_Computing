import java.io.*;
import java.net.*;


public class finger{

	private static InetAddress host;
	private static String request;
	private static boolean verbose;
	private static Socket socket;
	private static BufferedReader in;
	private static PrintWriter out;

	private finger(){} //can't instantiate

	public static void main(String[] args){

			processCommandLine(args);
			connect();
			sendRequest();
			getResponse();
			disconnect();
					
	}

	private static void processCommandLine(String[] args){

	  try{	
		
		switch(args.length){

			case 0:

				host = InetAddress.getLocalHost();
				break;

			case 1:
				
				parse(args[0]);
				break;
				
			case 2:

				if(args[0] != "-v") printUsage();
				verbose = true;
				parse(args[1]);
				break;
				
			default:

				printUsage();

		}

	  }catch(UnknownHostException uhe){
			
		 System.err.println();
		 System.err.println("Unknown Host");
		 System.err.println();
		 System.exit(1);
	  }

	}

	private static void parse(String arg) throws UnknownHostException{

		// should implement a real grammar parser to validate
		// request

		int index = arg.lastIndexOf('@');

		if(index == -1){

			host = InetAddress.getLocalHost();
			request = arg;

		}

		else{

			host = InetAddress.getByName(arg.substring(index+1));
			request = arg.substring(0, index);

		}

	}

	private static void printUsage(){

		System.err.println();
		System.err.println("Usage: finger [-v][user(@host)*]");
		System.err.println();
		System.exit(1);

	}

	private static void connect(){

		try{
			socket = new Socket(host, 79); 
		}catch(IOException ioe1){
			System.err.println("Unable to connect to " + 
			                    host.getHostName());
			System.exit(1);
		}
		try{
            out = new PrintWriter(new OutputStreamWriter(
			      socket.getOutputStream(), "latin1"));
        } catch (IOException ioe2) {
            System.err.print("Unable to get output stream for ");
            System.err.print("the connection to ");
			System.err.println(host.getHostName());
            try{socket.close();}catch(IOException ioe){}
			System.exit(1);
        }

		try{
			in = new BufferedReader(new InputStreamReader(
			     socket.getInputStream()));
        } catch (IOException ioe3) {
            System.err.print("Unable to get input stream for ");
            System.err.println("the connection to " );
			System.err.println(host.getHostName());
            out.close();
			try{socket.close();}catch(IOException ioe){}
			System.exit(1);
        }

	}

	private static void sendRequest(){

		if (verbose) out.print("/W");
		if (request != null) out.print(request);
		out.print("\r\n");
		out.flush();

	}

	private static void getResponse(){

		String line;

		try{

			while((line = in.readLine()) != null){

				System.out.println(line);

			}

		}catch(IOException ioe){

			System.err.println("Error reading from server");
			disconnect();
			System.exit(1);

		}

	}

	private static void disconnect(){

		try{

			if(out != null) out.close();
			if(in != null) in.close();
			if(socket != null) socket.close();

		}catch(IOException ignore){}

	}

}





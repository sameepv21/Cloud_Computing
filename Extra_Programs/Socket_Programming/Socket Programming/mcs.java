import java.io.*;
import java.net.*;

public class mcs{

	private static DatagramSocket inSocket;
	private static MulticastSocket outSocket;
	private static DatagramPacket incoming, outgoing;

	public static void main(String[] args) throws IOException{

		if(args.length != 3){

			System.err.print("\nUsage: java mcs <server port> ");
			System.err.print(" <group> <group port>");

		}

		int serverPort = Integer.parseInt(args[0]);
		InetAddress group = InetAddress.getByName(args[1]);
		int groupPort = Integer.parseInt(args[2]);

		init(serverPort, group, groupPort);

		while(true){

			relay();

		}

	}

	private static void init(int serverPort, InetAddress group,
	                         int groupPort) throws IOException{

		inSocket = new DatagramSocket(serverPort);
		outSocket = new MulticastSocket();
		outSocket.setTimeToLive(1);
		byte[] buffer = new byte[65508];
		incoming = new DatagramPacket(buffer, buffer.length);
		outgoing = new DatagramPacket(buffer, buffer.length,
		                              group, groupPort);
		System.out.println("\nServer started .  .  .");
	}

	private static void relay()throws IOException{

		incoming.setLength(incoming.getData().length);
		inSocket.receive(incoming);
		outgoing.setLength(incoming.getLength());
		outSocket.send(outgoing);

	}

}


		
/* WeatherClient
 * A client who communicates with the
 * WeatherServer with RMI.
 */

import java.rmi.*;
import java.net.*;
import java.io.*;

public class WeatherClient {

    WeatherInterface server;

    public WeatherClient(String host, int port) 
	throws RemoteException, NotBoundException, MalformedURLException{
	String name = "//" + host + ":" + port + "/WeatherServer";
	System.out.println(name);
	server = (WeatherInterface) Naming.lookup(name);
    }

    public void askServer() {
	boolean keepGoing = true;
	BufferedReader userInput = 
	    new BufferedReader(new InputStreamReader(System.in));
	String request;
	Weather weatherObject;

	// String with command information
	String commands = "List of Commands to get Weather Information :\n";
	commands += "all - Get all information available\n";
	commands += "wind - Get the wind speed\n";
	commands += "temp - Get the temperature\n";
	commands += "type - Get the weather type (for example \"dry or cloudy\"\n";
	commands += "help - View these commands again\n";
	commands += "quit - Quit the connection and the program.\n";
	System.out.println(commands);

	while (keepGoing) {
	    System.out.print("\n>> ");
	    try {
		// Read the user's request.
		request = userInput.readLine();
		
		// Interpret the request
		// Command:

		// help
		if (request.equals("help"))
		    System.out.println(commands);

		// all
		else if (request.equals("all")){
		    System.out.println("-----------------------------");
		    System.out.println("Todays weather:");
		    System.out.println("-----------------------------");
		    System.out.println(((Weather) server.all()).printWeather());

		// type
		} else if (request.equals("type")) {
		    System.out.println(server.type());

		// temp
		} else if (request.equals("temp")) {
		    System.out.println("Temperature: " +
				       server.temperature() +
				       " degrees celsius.");

		// wind
		} else if (request.equals("wind")) {
		    System.out.println("The wind blows with " +
				       server.wind() + " m/s.");

		// quit
		} else if (request.equals("quit")) {
		    keepGoing = false;

		// any other word...
		} else {
		    System.out.println("Command not found.");
		}
	    } catch (RemoteException re) {
		re.printStackTrace();
	    } catch (IOException ioe) {
		System.out.println("Could not read from standard in.");
	    }
	}
    }

    public static void main(String args[]) {
	String host;

	// Set host chosen by user if available
	if (args.length > 0) 
	    host = args[0];
	else
	    host = "localhost";

	// Create the socket and start the program.
	try {
	    WeatherClient wc = new WeatherClient(host, 4711);
	    wc.askServer();
	} catch(RemoteException re) {
	    System.out.println("Remote exception");
	} catch(MalformedURLException mue) {
	    System.out.println("The host " + host + " was malformed.");
	} catch(NotBoundException nbe) {
	    System.out.println("The name was not bound.");
	}
	
	// Just before the program ends...
	System.out.println("Thank you for using Weather Information !\n");
    }
    
}


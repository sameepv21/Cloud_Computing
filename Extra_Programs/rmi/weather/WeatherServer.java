/* WeatherServer
 * A server with current weather information.
 * The client can communicate with RMI.
 */

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.net.*;

public class WeatherServer extends UnicastRemoteObject
    implements WeatherInterface {

    Weather w;

    public WeatherServer() 
	throws RemoteException {
	// Weather(temp, wind, rain, stormy, sun, clouds, fog)
	w = new Weather(18, 10, true, false, false, true, true);
    }

    // Implements the methods in WeatherInterface

    public String type() 
	throws RemoteException {
	return w.getWeatherType();
    }

    public int temperature() 
	throws RemoteException {
	return w.getTemperature();
    }

    public int wind() 
	throws RemoteException {
	return w.getWind();
    }

    public Weather all() 
	throws RemoteException {
	return w;
    }

    // main-method to start the server.
    public static void main(String args[]) {
	try {
	
	    WeatherServer ws  = new WeatherServer();
	    LocateRegistry.createRegistry(4711);
	    Naming.rebind("//dslabsrv17.da-iict.ac.in:4711/WeatherServer", ws);
	
	} catch(RemoteException re) {
	    System.out.println("Couldn not start up server.");
	} catch(MalformedURLException mue) {
	    System.out.println("The URL was malformed.");
	}
    }
}


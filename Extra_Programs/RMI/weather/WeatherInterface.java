/*
 * WeatherInterface
 * A remote interface for the
 * WeatherServer.
 */

import java.rmi.*;

public interface WeatherInterface extends Remote {

    public String type() throws RemoteException;

    public int temperature() throws RemoteException;

    public int wind() throws RemoteException;

    public Weather all() throws RemoteException;
}

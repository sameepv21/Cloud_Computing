import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.RMISecurityManager;
import java.rmi.server.UnicastRemoteObject;
public class HelloServer 
      extends UnicastRemoteObject
      implements Hello 
   {
   public HelloServer() throws RemoteException {  }
   public String sayHello() 
      {
      return  "Hello World from " + getHostName();
      }
   protected static String getHostName() 
      {
      try 
         {
         return InetAddress.getLocalHost().getHostName();
         }
      catch (java.net.UnknownHostException who) 
         {
         return "Unknown";
         }
      }
public static void main(String args[])
      {
      // Create and install a security manager
      System.setSecurityManager(new RMISecurityManager());
      
      try 
         {
         // Create and register the server object
         HelloServer serverObject = new HelloServer();
         Naming.rebind("rmi://dslabsrv17.da-iict.ac.in/HelloServer", 
                           serverObject );
         // Signal successful registration
         System.out.println("HelloServer bound in registry");
         } 
      catch (Exception e)  
         {
         System.out.println("HelloServer err: ");
         e.printStackTrace();
         }
      }
   }

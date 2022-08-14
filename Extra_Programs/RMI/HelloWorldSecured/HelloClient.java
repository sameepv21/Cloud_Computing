import java.rmi.*;
import java.net.MalformedURLException;
public class HelloClient 
   {
   public static void main(String args[]) 
      {
      try {
         Hello remote = (Hello) Naming.lookup( 
                              "rmi://dslabsrv17.da-iict.ac.in/HelloServer");
         String message = remote.sayHello();
         System.out.println( message );
         } 
      catch ( NotBoundException error)
         {
         error.printStackTrace();
         }
      catch ( MalformedURLException error)
         {
         error.printStackTrace();
         }
      catch ( UnknownHostException error)
         {
         error.printStackTrace();
         }
      catch ( RemoteException error)
         {
         error.printStackTrace();
         }
      }
   }

import  java.net.ServerSocket;
import  sdsu.util.ProgramProperties;
import  java.io.IOException;
/**
 * This class starts the rmi registry on a random
 * open port. User can suggest a port using the -p flag
 */
public class StartRMIRegistry
   {
   public static void main( String[] args )
      {
      try
         {
         ProgramProperties flags = new ProgramProperties( args );
         int suggestedPort = flags.getInt( "p", 0);
         int port = getPort( suggestedPort );
         String randomPortRegistry = "rmiregistry " + port;
         Runtime.getRuntime().exec( randomPortRegistry );
         System.out.println( "rmiregistry running on port " + port );
         System.exit( 0 );
         }
      catch (IOException error )
         {
         System.out.println( "Had trouble trying to find a port\n " +
            error);
         }
      }

/*
    Return an open port on current machine. Try the suggested port first.
    If suggestedPort is zero, just select a random port    
*/

private static int getPort( int suggestedPort ) throws IOException
      {
      ServerSocket openSocket;
      try
         {
         openSocket = new ServerSocket( suggestedPort );
         }
      catch (java.net.BindException portbusy)
         {
         // find random open port
         openSocket = new ServerSocket( 0 );
         }
      
      int port = openSocket.getLocalPort();
      openSocket.close();
      
      return port;
      } 
   }

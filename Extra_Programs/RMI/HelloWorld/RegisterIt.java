import java.rmi.*;
import java.rmi.registry.*;

public class RegisterIt {

   public static void main(String args[]) {
      try {
         // Instantiate the object
         HelloServer obj = new HelloServer();
         System.out.println("Object instantiated"  +obj); 

//        LocateRegistry.createRegistry(4788);
        Naming.rebind("//dslabsrv17.da-iict.ac.in/HelloServer", obj);
//       Naming.rebind ("/HelloServer", obj); 
         System.out.println("HelloServer bound in registry");
      } catch (Exception e) {
         System.out.println(e);
      }
   }
}

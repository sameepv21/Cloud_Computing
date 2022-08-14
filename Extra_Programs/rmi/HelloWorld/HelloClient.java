import java.rmi.*;

public class HelloClient {

   public static void main(String args[]) {
      if (System.getSecurityManager() == null)
         System.setSecurityManager(new RMISecurityManager());
      try {
//         HelloInterface obj = (HelloInterface) Naming.lookup("//dslabsrv17.da-iict.ac.in/HelloServer");
         HelloInterface obj = (HelloInterface) Naming.lookup("//10.100.84.17/HelloServer");
         String message = obj.sayHello();
         System.out.println(message);
      } catch (Exception e) {
         System.out.println("HelloClient exception: " +e);
      }
   }
}

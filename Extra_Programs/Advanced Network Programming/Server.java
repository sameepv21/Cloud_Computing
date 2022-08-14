import java.io.*;
import java.net.*;
import java.security.*;
/**
* @(#)Server.java
*/
public class Server implements Cloneable, Runnable {
ServerSocket service = null;
Socket clientSocket = null;
ObjectInputStream ois = null;
Thread worker = null;
KeyPairGenerator kgen;
KeyPair kpair;
public static void main(String argv[]) throws IOException {
Server serv = new Server();
serv.startServer();
}
public synchronized void startServer() throws IOException {
if (worker == null) {
service = new ServerSocket(4000);
worker = new Thread(this);
worker.start();
}
}
public void run() {
Socket client = null;
if (service != null) { // Original or clone?
while(true) {
try {
client = service.accept();
Server newServer = (Server) clone();
newServer.service = null;
newServer.clientSocket = client;
newServer.worker = new Thread(newServer);
newServer.worker.start();
} catch(IOException e) {
e.printStackTrace();
} catch(CloneNotSupportedException e) {
e.printStackTrace();
}
}
} else {
perform(clientSocket);
}
}
private void perform(Socket client) {
try {
ois = new ObjectInputStream(clientSocket.getInputStream());
// Read object from client.
SignedObject obj = (SignedObject) ois.readObject();
// Generate object's signature.
Signature sig = Signature.getInstance("SHA/DSA");
sig.initVerify(obj.pub);
sig.update(obj.b);
// Verify the signature.
boolean valid = sig.verify(obj.sig);
if (valid) {
System.out.println("Signature is valid");
} else {
System.out.println("Signature is not valid...spy!");
}
} catch(Exception e) {
e.printStackTrace();
}
// Close streams and connection.
try {
ois.close();
clientSocket.close();
} catch(IOException ex) {
ex.printStackTrace();
}
}
}

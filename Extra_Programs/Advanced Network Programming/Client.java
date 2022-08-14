import java.io.*;
import java.net.*;
import java.security.*;
/**
* @(#)Client.java
*/
public class Client {
public static void main(String argv[]) {
Socket s = null;
ObjectOutputStream os = null;
try {
s = new Socket("10.100.84.26", 4000);
os = new ObjectOutputStream(s.getOutputStream());
System.out.println("Generating keys...this may take a few minutes");

// Generate public and private keys.
KeyPairGenerator kgen = KeyPairGenerator.getInstance("DSA");
kgen.initialize(512);
KeyPair kpair = kgen.generateKeyPair();

// Generate a signature.
System.out.println("Generating Signature....");
Signature sig = Signature.getInstance("SHA/DSA");
PublicKey pub = kpair.getPublic();
PrivateKey priv = kpair.getPrivate();
sig.initSign(priv);

// Read a file and compute a signature.
FileInputStream fis = new FileInputStream(argv[0]);
byte arr[] = new byte[fis.available()];
fis.read(arr);
sig.update(arr);

// Send the SignedObject on the wire.
SignedObject obj = new SignedObject(arr, sig.sign(), pub);
os.writeObject(obj);

// Close streams.
fis.close();
os.close();
s.close();
} catch (Exception e) {
e.printStackTrace();
}
}
}

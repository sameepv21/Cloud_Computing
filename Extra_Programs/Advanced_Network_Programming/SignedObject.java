import java.io.*;
import java.security.*;
/**
* @(#)SignedObject.java
*/
public class SignedObject implements Serializable {
byte b[];
byte sig[];
PublicKey pub;
// Constructor
public SignedObject(byte b[], byte sig[], PublicKey pub) {
this.b = b;
this.sig = sig;
this.pub = pub;
}
}
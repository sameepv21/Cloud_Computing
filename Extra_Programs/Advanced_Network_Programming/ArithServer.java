import java.io.*;
import java.net.*;
import java.util.*;
/**
* @(#)ArithServer.java
* This example shows how to use object serialization to send and receive
* objects over sockets.
*/
public class ArithServer {
/**
* Create the server socket and use its stream to receive serialized
* objects.
*/
public static void main(String args[]) {
ServerSocket ser = null;
Socket soc = null;
MathObj x = null;
MathObj y = null;
int z1[] = new int[5];
int z2[] = new int[5];
int result[] = new int[5];
try {
ser = new ServerSocket(4343);
/**
* This will wait for a connection to be made to this socket.
*/
soc = ser.accept();
InputStream o = soc.getInputStream();
ObjectInput s1 = new ObjectInputStream(o);
OutputStream o2 = soc.getOutputStream();
ObjectOutput s2 = new ObjectOutputStream(o2);
x = (MathObj) s1.readObject();
y = (MathObj) s1.readObject();
z1 = x.out();
z2 = y.out();
for(int i=0; i<z1.length; i++) {
System.out.println(z1[i]);
}
for(int i=0; i<z2.length; i++) {
System.out.println(z2[i]);
}
for(int p=0; p<z1.length; p++) {
result[p] = z1[p] + z2[p];
}
for(int p1=0; p1<result.length; p1++) {
System.out.println(result[p1]);
}
MathObj myM = new MathObj();
myM.set(result);
s2.writeObject(myM);
s2.flush();
s1.close();
s2.close();
} catch (Exception e) {
e.printStackTrace();
System.out.println(e.getMessage());
}
}
}

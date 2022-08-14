import java.io.*;
import java.util.*;
import java.net.*;
/**
* @(#)ArithClient.java
*/
public class ArithClient {
public static void main(String args[]) {
int a[] = {4, 4, 4, 4, 4};
int b[] = {7, 8, 9, 12, 14};
try {
// Create a socket.
Socket soc = new Socket(InetAddress.getLocalHost(), 4343);
OutputStream o = soc.getOutputStream();
ObjectOutput s = new ObjectOutputStream(o);
InputStream in = soc.getInputStream();
ObjectInput s2 = new ObjectInputStream(in);
MathObj a1 = new MathObj();
MathObj a2 = new MathObj();
MathObj res = null;
int arr[] = new int[5];
a1.set(a);
a2.set(b);
s.writeObject(a1);
s.writeObject(a2);
s.flush();
res = (MathObj) s2.readObject();
arr = res.out();
for(int i=0; i<arr.length; i++) {
System.out.println(arr[i]);
}
s.close();
s2.close();
} catch (Exception e) {
System.out.println(e.getMessage());
}
}
}

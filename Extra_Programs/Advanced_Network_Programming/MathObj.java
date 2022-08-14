import java.io.*;
import java.util.*;
/**
* @(#)MathObj.java
*/
public class MathObj implements Serializable {
private int x[] = null;
public MathObj(){
}
public int[] set(int msg[]){
x = msg;
return x;
}
public int[] out() {
return x;
}
}

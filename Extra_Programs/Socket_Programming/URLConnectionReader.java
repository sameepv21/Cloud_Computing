import java.net.*;
import java.io.*;

public class URLConnectionReader {
    public static void main(String[] args) throws Exception {
	URL IT636 = new URL("http://intranet.da-iict.org/~sanjay/distributed_computing/distributed_computing_toc.htm");
	URLConnection cc = IT636.openConnection();
	BufferedReader in = new BufferedReader(
				new InputStreamReader(
				cc.getInputStream()));

	String inputLine;

	while ((inputLine = in.readLine()) != null)
	    System.out.println(inputLine);

	in.close();
    }
}


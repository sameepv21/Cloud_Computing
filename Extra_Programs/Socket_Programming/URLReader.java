import java.net.*;
import java.io.*;

public class URLReader {
    public static void main(String[] args) throws Exception {
	URL IT636 = new URL("http://intranet.da-iict.org/~sanjay/distributed_computing/distributed_computing_toc.htm");
	BufferedReader in = new BufferedReader(
				new InputStreamReader(
				IT636.openStream()));

	String inputLine;

	while ((inputLine = in.readLine()) != null)
	    System.out.println(inputLine);

	in.close();
    }
}


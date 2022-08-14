import java.net.*;
import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

public class SoapClient {

   public static void main(String args[]) {        
      try { 
         //Create the connection
         SOAPConnectionFactory scf = SOAPConnectionFactory.newInstance();
         SOAPConnection connection = scf.createConnection();
         SOAPFactory sf = SOAPFactory.newInstance();
         
         //Create the message
         MessageFactory mf = MessageFactory.newInstance();
         SOAPMessage message = mf.createMessage();
         
         //Create objects for the message parts            
         SOAPPart soapPart = message.getSOAPPart();
         SOAPEnvelope envelope = soapPart.getEnvelope();
         SOAPBody body = envelope.getBody();

         //Populate the body of the message
         Name bodyName = sf.createName("getPrice", "ns1", "urn:xmethods-BNPriceCheck");
         SOAPBodyElement bodyElement = body.addBodyElement(bodyName);
         Name name = sf.createName("isbn");
         SOAPElement isbn = bodyElement.addChildElement(name);
         isbn.addTextNode("0596002432");

         //Display the request sent (Code Sample 1)
         System.out.println("SOAP Request Sent:");
         message.writeTo(System.out);
           
         //Set the destination
         URL endpoint = new URL("http://services.xmethods.net:80/soap/servlet/rpcrouter");
         //Send the message
         SOAPMessage response = connection.call(message, endpoint);

         //Close the connection
         connection.close();

         //Display the response received (Code Sample 3)
         System.out.println("SOAP Response Received:");

         //Create a transformer
         TransformerFactory tf = TransformerFactory.newInstance();
         Transformer transformer = tf.newTransformer();
         //Retrieve content of the response
         Source content = response.getSOAPPart().getContent();
         //Display it on the console
         StreamResult result = new StreamResult(System.out);
         transformer.transform(content, result);
         System.out.println();
            
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ppmc extends Frame{

	private TextArea ta = new TextArea("", 20, 80, TextArea.SCROLLBARS_VERTICAL_ONLY);
	private Label l = new Label("ENTER:");
	private Label spacer1 = new Label("NETWORKED ROOM OF ECHOES");
	private Label spacer2 = new Label("");
	private TextField tf = new TextField("", 60);
	private Button b = new Button("SUBMIT");

	private tfkListener tkfl;
	private bListener bl;

	private MulticastSocket socket;
    private DatagramPacket outgoing, incoming;
	private InetAddress group;
	private	int port;
	private String screenName;


	private ppmc(String title){

		super(title);
		setSize(640, 480);
		setBackground(Color.gray);
		addWindowListener(new WL());

		ta.addFocusListener(new tafListener());
		tf.addKeyListener(tkfl = new tfkListener());
		b.addActionListener(bl = new bListener());
		
		ta.setEditable(false);
		tf.setEditable(true);

		ta.setBackground(Color.darkGray);
		ta.setForeground(Color.white);
		spacer1.setForeground(Color.white);
		l.setForeground(Color.white);

		GridBagLayout gbl= new GridBagLayout();
		setLayout(gbl);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER; //end row
		gbl.setConstraints(ta, gbc);

		gbl.setConstraints(spacer1, gbc);
		add(spacer1);
		add(ta);
		gbl.setConstraints(spacer2, gbc);
		add(spacer2);
		add(l);
		add(tf);
		add(b);		
		
	} 

	public static void main(String[] args){
		
		ppmc app = new ppmc("Peer-to-Peer Multicast Chat");
		app.connect(args);
		app.setVisible(true);

	}
	
	public void connect(String[] args){

		if(args.length != 3){

			System.err.print("\nUsage: java ppmc <group> <port> ");
			System.err.print("<screen name>");

		}

		try {

			group = InetAddress.getByName(args[0]);
			port = Integer.parseInt(args[1]);
			screenName = args[2];

        } catch (UnknownHostException e) {

            System.err.println("Unknown host: " + args[0]);
            System.exit(1);

		} catch (NumberFormatException nfe){

			System.err.println("Invalid port: " + args[1]);
            System.exit(1);

		}
		
		try{

			socket = new MulticastSocket(port); 
			socket.setTimeToLive(1);
			socket.joinGroup(group);
			outgoing = new DatagramPacket(new byte[1], 1, group, port);
			incoming = new DatagramPacket(new byte[65508], 65508);

		}catch(IOException ioe1){

			System.err.println("Unable to join " + args[0]);
			System.exit(1);

		}

		

		ta.append("Joined ");
		ta.append(args[0]);
		ta.append(" on port " + args[1]);
		ta.append("\n\n");
		SocketReader reader = new SocketReader();

	}		

	class tfkListener extends KeyAdapter{

		public void keyPressed(KeyEvent e){

			if(e.getKeyCode() == KeyEvent.VK_ENTER){

				StringBuffer sb = new StringBuffer();
				sb.append(screenName);
				sb.append(": ");
				String str = tf.getText();
				if(str != null && !str.equals("")){

					sb.append(str);
					str = sb.toString();

					try{

						byte[] buf = str.getBytes("latin1");
						outgoing.setData(buf);
						outgoing.setLength(buf.length);
						socket.send(outgoing);
						tf.setText("");
					
					}catch(IOException ioe){
						ta.append("\nError sending " + str);
					}
				}
	
			}
		}
	}

	class bListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			StringBuffer sb = new StringBuffer();
			sb.append(screenName);
			sb.append(": ");
			String str = tf.getText();
			if(str != null && !str.equals("")){

				sb.append(str);
				str = sb.toString();

				try{

						byte[] buf = str.getBytes("latin1");
						outgoing.setData(buf);
						outgoing.setLength(buf.length);
						socket.send(outgoing);
						tf.setText("");
					
					}catch(IOException ioe){
						ta.append("\nERROR: sending " + str);
					}
			}

			tf.setText("");
			tf.requestFocus();

		}

	}

	class tafListener extends FocusAdapter{

		public void focusGained(FocusEvent e){

			tf.requestFocus();
			

		}

	}

	class WL extends WindowAdapter{
	
		public void windowClosing(WindowEvent e){
			try{
				socket.leaveGroup(group);
				socket.close();
			}catch(IOException ignore){}
			System.exit(0);
		}

		public void windowDeiconified(WindowEvent e){
			ta.requestFocus();	
			
		}
	}

	class SocketReader extends Thread{

		public SocketReader(){
		
			start();
			
		}
		
		public void run(){

			String msg;
		
			try{
			
				while(true){

					socket.receive(incoming);
					msg = new String(incoming.getData(), 0,
					                 incoming.getLength(),
									 "latin1");

					ta.append(msg + "\n");
					yield();

				}


			}catch(IOException lostGroup){

				ta.append("\nLost Group.");
				tf.setVisible(false);
				l.setVisible(false);
				b.setVisible(false);
				tf.removeKeyListener(tkfl);
				b.removeActionListener(bl);

			}finally{

				try{
					socket.leaveGroup(group);
					socket.close();
				}catch(IOException ignore){}

			}	

		}

	}

}

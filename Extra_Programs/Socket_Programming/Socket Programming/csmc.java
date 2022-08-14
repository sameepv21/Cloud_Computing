import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class csmc extends Frame{

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
	private InetAddress server;
	private	int serverPort;
	private InetAddress group;
	private	int groupPort;
	private String screenName;


	private csmc(String title){

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
		
		csmc app = new csmc("Client/Server Multicast Chat");
		app.connect(args);
		app.setVisible(true);

	}
	
	public void connect(String[] args){

		if(args.length != 5){

			System.err.print("\nUsage: java csmc <server> <port> ");
			System.err.print(" <group> <port> <screen name>");

		}

		try {

			server = InetAddress.getByName(args[0]);
			serverPort = Integer.parseInt(args[1]);
			group = InetAddress.getByName(args[2]);
			groupPort = Integer.parseInt(args[3]);
			screenName = args[4];

        } catch (UnknownHostException e) {

            System.err.println("Unknown host");
            System.exit(1);

		} catch (NumberFormatException nfe){

			System.err.println("Invalid port");
            System.exit(1);

		}
		
		try{

			socket = new MulticastSocket(groupPort); 
			socket.setTimeToLive(1);
			socket.joinGroup(group);
			outgoing = new DatagramPacket(new byte[1], 1, server, 
										  serverPort);
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

					incoming.setLength(incoming.getData().length);
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

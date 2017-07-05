//package juego;
//import java.awt.BorderLayout;
//import java.awt.EventQueue;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//
//import mensajeria.PaqueteUsuario;
//
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
//
//import java.awt.Font;
//
//import javax.swing.JOptionPane;
//import javax.swing.JTextField;
//import javax.swing.JButton;
//import javax.swing.ScrollPaneConstants;
//
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.io.IOException;
//import java.io.ObjectOutputStream;
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.Iterator;
//
//
//public class VentanaChat extends JFrame {
//
//	private JPanel contentPane;
//	private JTextField textField;
//	private JButton btnEnviar;
//	private JTextArea textArea;
//	private PaqueteUsuario paqueteUsuario;
//	private String nombreDestinatario;
//	//private DatosCliente paquete;
//    private String modoChat="aun no se";
//	private String ipDestino;
//	
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaChat frame = new VentanaChat("", "");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 * 
//	 * Modo:
//	 * 		"Sala":	Sala de Chat
//	 * 		"Chat": Sesión privada
//	 *  
//	 * Usuario Destino: Usuario en sesión privada ////////////////////////////////////////////////////////
//	 */
//	
//	public VentanaChat( Juego juego, String nombreDestinatario, String modoCh) {
//		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//		this.paqueteUsuario= juego.getCliente().getPaqueteUsuario();
//		modoChat = modoCh;
//		paqueteUsLocal = usuarioLocal;
//		paqueteUsLocal.setTipoChat(modoCh);
//		//obtengo el nombre usuario destino
//		this.nombreDestinatario = nombreDestinatario;
//		//this.solicitarChatDeLaSala();
//		// como cada intancia de ventana chat sera para una ipDestino distinta y el usuario local es uno siempre
//		//la ipDestino debe quedar fija al crear la instancia
//		if(modoChat.equals("Chat"))
//		ipDestino = paqueteUsLocal.getIpDestino();
//		
//		//obtengo los datos del usuario local
//		//this.usuarioLocal = usuarioLocal;
//		
//		if(modoChat.equals("Sala"))
//			setTitle("Sala de Chat");
//		else
//			setTitle(nombreDestinatario+" ip: "+paqueteUsLocal.getIpDestino() );
//				
//		setResizable(false);
//		
//		addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowClosing(WindowEvent arg0) {
//				mostrarVentanaConfirmacion();
//			}
//		});
//		
//		setBounds(100, 100, 450, 353);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
//		
//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		scrollPane.setBounds(0, 0, 444, 300);
//		contentPane.add(scrollPane);
//		
//		textArea = new JTextArea();
//		textArea.setEditable(false);
//		textArea.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
//		scrollPane.setViewportView(textArea);
//		
//		textField = new JTextField();
//		textField.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyReleased(KeyEvent arg0) {
//				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
//					escribeEnTextArea();
//					hacerFocoTextField(textField);
//				}
//			}
//		});
//		textField.setBounds(0, 300, 336, 27);
//		contentPane.add(textField);
//		textField.setColumns(10);
//		
//		btnEnviar = new JButton("Enviar");
//		btnEnviar.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//					
//					paqueteUsLocal.setTipoChat(modoCh);
//					System.out.println("modo al presionar enviar: "+ paqueteUsLocal.getTipoChat());
//					if(modoChat.equals("Chat"))
//					escribeEnTextArea();
//			    	
//			    	//env�a el mensaje via red (a traves del Servidor)
//			    	try{
//			    		System.out.println("IP-serverEnviar: " + paqueteUsLocal.getIpServer());
//			    		Socket  destinatarioServer = new Socket(paqueteUsLocal.getIpServer(),10000);
//			    		ObjectOutputStream flujoSalida = new ObjectOutputStream(destinatarioServer.getOutputStream());
//			    		//seteo el mensaje
//			    		paqueteUsLocal.setMensaje(textField.getText());
//			    		
//			    		if(modoChat.equals("Chat")){
//			    		paqueteUsLocal.setIpDestino(ipDestino);//Por que cada ventanaCaht tiene su ipDestino
//			    		System.out.println("Al presionar enviar esta es la ipDestino " +paqueteUsLocal.getIpDestino());
//			    	    }
//			    		textField.setText("");
//			    		flujoSalida.writeObject(paqueteUsLocal);
//			    		destinatarioServer.close();
//			    	}catch(IOException e){
//					
//			    		//System.out.println(e.printStackTrace());
//			    	}//Fin Catch
//			    
//			    	   
//			    	
//			    	
//			    
//			}
//		});
//		btnEnviar.setBounds(346, 302, 91, 23);
//		contentPane.add(btnEnviar);
//
//		setVisible(true);
//		textField.requestFocus();
//		if(modoChat.equals("Sala"))
//        this.solicitarChatDeLaSala();
//	}//fin constructor VentanaChat
//	
//	//lo hice solo para que no tire error el constructor de ejemplo
//	public VentanaChat(String a, String b){
//		
//		System.out.println("entro a este constructor!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//		
//	}
//	
//	public void escribeEnTextArea() {
//		textArea.setCaretPosition(textArea.getText().length());
//		textArea.append("Yo: "+ textField.getText() + "\n");
//		
//	}
//	
//	public void escribeEnTextArea(String mensaje){
//		
//		textArea.setCaretPosition(textArea.getText().length());
//		textArea.append(nombreDestinatario+": "+mensaje +"\n");
//		
//	}
//	
//	public void escribeEnTextAreaSala(ArrayList<String> conversasion){
//		//System.out.println("Entro a escribir en el text area de la sala...");
//		//escribeEnTextArea("hhhhhhhhhhooooooolllllllaaaaaaaaa");
//		System.out.println("tama�o:" +conversasion.size());
//		
//		Iterator<String> it = conversasion.iterator();
//		
//		boolean primeraLinea = true;
//		
//		while( it.hasNext()){
//			System.out.println("escribe..");
//			String mensaje = it.next();
//			textArea.setCaretPosition(textArea.getText().length());
//			if(primeraLinea){
//			textArea.setText(mensaje);
//			primeraLinea = false;
//			}
//			else
//				textArea.append(mensaje +"\n");
//		}
//		
//	}
//	
//	private void mostrarVentanaConfirmacion() {
//		int res = JOptionPane.showConfirmDialog(this, "Desea salir de la sesión de chat", "Confirmar cerrar", JOptionPane.YES_NO_OPTION);
//		if(res == JOptionPane.YES_OPTION)
//			dispose();
//	}
//	
//	private void hacerFocoTextField(JTextField textField) {
//		textField.requestFocus();
//		textField.selectAll();
//	}
//	//este metodo solicita las conversaciones que ya etaban en la sala antes que se conecte
//	public void solicitarChatDeLaSala(){
//	
//	    	
//	    	//env�a el mensaje via red (a traves del Servidor)
//	    	try{
//	    		
//	    		System.out.println("IP-serverAlSolicitar: " + paqueteUsLocal.getIpServer());
//	    		Socket  destinatarioServidor = new Socket(paqueteUsLocal.getIpServer(),10000);
//	    		ObjectOutputStream flujoSalida = new ObjectOutputStream(destinatarioServidor.getOutputStream());
//	    		//seteo el mensaje
//	    		paqueteUsLocal.setMensaje("solicitud de sala");
//	    		flujoSalida.writeObject(paqueteUsLocal);
//	    		destinatarioServidor.close();
//	    		
//	    	}catch(IOException e){
//	    		
//	    		System.out.println("No se pudo obtener la sala");
//	    		//System.out.println(e.printStackTrace());
//	    	}//Fin Catch
//		
//	}//fin metodo solicita
//	
//}//Fin de la clase

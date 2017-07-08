package juego;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.google.gson.Gson;

import mensajeria.Comando;
import mensajeria.PaqueteMensajeSala;
import mensajeria.Usuario;

import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.SwingUtilities;

public class VentanaSala extends JFrame {
	private Gson gson;
	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private int idPersonaje;
	private DefaultListModel<Usuario> listModel;
	private Juego juego;
/// CODIGO AGREGADO-----------------------------------------------------------------------------------------------------------------
	private JList<Usuario> countryList;
	private JScrollPane scrollPane;
/// CODIGO AGREGADO-----------------------------------------------------------------------------------------------------------------
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				//	VentanaSala frame = new VentanaSala(this);
			//		frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaSala(Juego juego) {

	  setResizable(false);
	  
	  setLocationRelativeTo(null); 
	  idPersonaje = juego.getPersonaje().getId();
	  this.setTitle("Chat");
	  this.juego = juego;
	  this.setVisible(false);
	  gson = new Gson();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String aux = juego.getPersonaje().getNombre()+": "+
						textField.getText()+System.lineSeparator();
				
				PaqueteMensajeSala pqs = new PaqueteMensajeSala(aux);
				pqs.setComando(Comando.SALAMSJ);
				try {
					juego.getCliente().getSalida().writeObject(gson.toJson(pqs));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				textField.setText("");
			}
		});
		btnEnviar.setBounds(335, 216, 89, 23);
		contentPane.add(btnEnviar);
		
		textField = new JTextField();
		textField.setBounds(10, 217, 315, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane sp = new JScrollPane(textArea);
		sp.setBounds(10, 11, 315, 187);
		contentPane.add(sp);
		
		
		// Etapa de creacion del panel lateral de usuarios
     listModel = new DefaultListModel<>();
    /// Aca adentro hay que meter la lista de los usuarios conectados para luego seleccionarlos y chatear con ellos
    //listModel.addElement("");
    /// Aca adentro hay que meter la lista de los usuarios conectados para luego seleccionarlos y chatear con ellos
    countryList = new JList<>(listModel);
    getContentPane().add(countryList);
    countryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
    countryList.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent evt) {
        JList list = (JList) evt.getSource();
        if (evt.getClickCount() == 2) {

          final List<Usuario> selectedValuesList = countryList.getSelectedValuesList();

          if (juego.getConversaciones().get(selectedValuesList.get(0).getId()) == null) {
            juego.getConversaciones().put(selectedValuesList.get(0).getId(),
                new VentanaChat(selectedValuesList.get(0), juego));

          } else {
            juego.getConversaciones().get(selectedValuesList.get(0).getId()).setVisible(true);
          }
        }
      }
    });
    
//    countryList.addListSelectionListener(new ListSelectionListener() {
//      @Override
//      public void valueChanged(ListSelectionEvent e) {
//        /// Aca adentro hay que meter un codigo que abra un chat privado con este usuario seleccionado
//        if (!e.getValueIsAdjusting()) {
//          final List<Usuario> selectedValuesList = countryList.getSelectedValuesList();
//          
//          	if(juego.getConversaciones().get(selectedValuesList.get(0).getId())==null){
//          		juego.getConversaciones().put(selectedValuesList.get(0).getId(), new VentanaChat(selectedValuesList.get(0),juego));
//
//          	}
//          	else{
//          		juego.getConversaciones().get(selectedValuesList.get(0).getId()).setVisible(true);
//          	}
//        }
//        /// Aca adentro hay que meter un codigo que abra un chat privado con este usuario seleccionado
//      }
//    });
    
    scrollPane = new JScrollPane(countryList);
    scrollPane.setBounds(335, 11, 89, 187);
    
    contentPane.add(scrollPane);
		
	}

	public void actualizar(PaqueteMensajeSala pqs) {
		this.textArea.setText(textArea.getText()+pqs.getMsj2());
		
	}

	public void actualizarUsuarios(List<Usuario> usuarios) {
		listModel.removeAllElements();
		for( Usuario sdas: usuarios){
			if(sdas.getId()!=idPersonaje)
			listModel.addElement(sdas);
		}	
	}
}

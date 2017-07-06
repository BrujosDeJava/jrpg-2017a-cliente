package juego;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dominio.Item;
import dominio.Mercado;
import mensajeria.PaqueteMercado;

import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.JScrollPane;

public class VentanaMercado extends JFrame {

	private JPanel contentPane;
	private JList<Item> listaDeItems;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMercado frame = new VentanaMercado();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaMercado() {
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
    DefaultListModel<Item> listModel = new DefaultListModel<>();
    /// Aca adentro hay que meter la lista de los usuarios conectados para luego seleccionarlos y chatear con ellos
    Item aux = new Item(1,1);
    aux.setNombre("Raul");
    Item aux1 = new Item(1,1);
    aux.setNombre("Pepe");
    Item aux2 = new Item(1,1);
    aux.setNombre("Tuti");
    listModel.addElement(aux);
    listModel.addElement(aux1);
    listModel.addElement(aux2);
    /// Aca adentro hay que meter la lista de los usuarios conectados para luego seleccionarlos y chatear con ellos
    listaDeItems = new JList<>(listModel);
    getContentPane().add(listaDeItems);
    listaDeItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
    listaDeItems.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
          /// Aca adentro hay que meter un codigo que abra un chat privado con este usuario seleccionado
          
          final List<Item> selectedValuesList = listaDeItems.getSelectedValuesList();
          System.out.println(selectedValuesList);
          /// Aca adentro hay que meter un codigo que abra un chat privado con este usuario seleccionado
        }
      }
    });
		
		JScrollPane scrollPane = new JScrollPane(listaDeItems);
		scrollPane.setBounds(10, 11, 323, 353);
		contentPane.add(scrollPane);
	}
	
	public void actualizarMercado(PaqueteMercado pm){
		
//		Mercado merca = pm.getMercado();
//		textPane.setText("");
//		for(ArrayList<Item> lista : merca.getMochilas().values()){
//			for(Item i: lista)
//			textPane.setText(textPane.getText()+i.getNombre()+System.lineSeparator());
//		}
//		
	}
}

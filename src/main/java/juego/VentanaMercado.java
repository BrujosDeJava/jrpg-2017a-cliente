package juego;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialException;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.google.gson.Gson;

import dominio.Item;
import dominio.Mercado;
import mensajeria.Comando;
import mensajeria.PaqueteIntercambio;
import mensajeria.PaqueteMercado;

import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class VentanaMercado extends JFrame {

	private JPanel contentPane;
	private JTextPane textPane;
	
	private JList<Item> listaDeItems;
	private JList<Item> mochila;
	DefaultListModel<Item> listModel;
	DefaultListModel<Item> mochilaModel;
	private Item ofrecido;
	private Item requerido;
	private Gson gson;
	private Juego juego;


	/**
	 * Create the frame.
	 */
	public VentanaMercado(Juego juego) {
		this.juego = juego;
		gson = new Gson();
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setBounds(100, 100, 265, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
    listModel = new DefaultListModel<>();
    mochilaModel = new DefaultListModel<>();
    /// Aca adentro hay que meter la lista de los usuarios conectados para luego seleccionarlos y chatear con ellos

    /// Aca adentro hay que meter la lista de los usuarios conectados para luego seleccionarlos y chatear con ellos
    listaDeItems = new JList<>(listModel);
    mochila = new JList<>(mochilaModel);
    
    listaDeItems.setBounds(1, 1, 134, 351);
    getContentPane().add(listaDeItems);
    mochila.setBounds(137,46,95,248);
    getContentPane().add(mochila);
    
    listaDeItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    mochila.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
    List<Item> lista = juego.getPersonaje().getInv().getMochila();
    
    for(Item i: lista){
    	i.setDuenio(juego.getPersonaje().getId());
    	mochilaModel.addElement(i);
    }
    
    listaDeItems.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
          
          final List<Item> selectedValuesList = listaDeItems.getSelectedValuesList();
          		requerido = selectedValuesList.get(0);
          		System.out.println("Requerido "+requerido);
        }
      }
    });
    
    mochila.addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
          if (!e.getValueIsAdjusting()) {
            
            final List<Item> selectedValuesList = mochila.getSelectedValuesList();
      			ofrecido = selectedValuesList.get(0);
      			System.out.println("Ofrecido "+ofrecido);
          }
        }
      });
		
		JScrollPane scrollPane = new JScrollPane(listaDeItems);
		scrollPane.setBounds(10, 44, 106, 320);
		contentPane.add(scrollPane);
		
		
		
		JButton btnIntercambiar = new JButton("Intercambiar");
		btnIntercambiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PaqueteIntercambio pi = new PaqueteIntercambio(ofrecido, requerido);
				pi.setComando(Comando.INTERCAMBIO);
				try {
					juego.getCliente().getSalida().writeObject(gson.toJson(pi));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		
		
		btnIntercambiar.setBounds(137, 322, 95, 23);
		contentPane.add(btnIntercambiar);
		
		JLabel lblEnVenta = new JLabel("Items disponibles");
		lblEnVenta.setBounds(10, 19, 106, 14);
		contentPane.add(lblEnVenta);
		
		JLabel lblTusItems = new JLabel("Mochila");
		lblTusItems.setBounds(155, 21, 63, 14);
		contentPane.add(lblTusItems);
		
		
	}
	
	public void actualizarMercado(PaqueteMercado pm){
		
		listModel.removeAllElements();
		Mercado merca = pm.getMercado();
		for(Integer codigo :merca.getMochilas().keySet()){			
			if(codigo!=juego.getPersonaje().getId()){
				for(Item i: merca.getMochilas().get(codigo)){
					i.setDuenio(codigo);
					listModel.addElement(i);
				}
		}
		}
		
	}
}

package juego;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dominio.Item;
import dominio.Mercado;
import mensajeria.PaqueteMercado;

import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;

public class VentanaMercado extends JFrame {

	private JPanel contentPane;
	private JTextPane textPane;
	
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
		
		textPane = new JTextPane();
		textPane.setBounds(23, 11, 177, 353);
		contentPane.add(textPane);
	}
	
	public void actualizarMercado(PaqueteMercado pm){
		
		Mercado merca = pm.getMercado();
		textPane.setText("");
		for(ArrayList<Item> lista : merca.getMochilas().values()){
			for(Item i: lista)
			textPane.setText(textPane.getText()+i.getNombre()+System.lineSeparator());
		}
		
	}
}

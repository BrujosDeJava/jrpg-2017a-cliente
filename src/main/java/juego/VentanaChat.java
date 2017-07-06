package juego;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import mensajeria.Comando;
import mensajeria.PaqueteChatPrivado;
import mensajeria.Usuario;

import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class VentanaChat extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private Usuario destinatario;
	private JTextPane textPane;
	Gson gson;
	/**
	 * Create the frame.
	 */
	public VentanaChat(Usuario destinatario, Juego juego) {
		gson = new Gson();
		this.destinatario = destinatario;
		this.setVisible(true);
		this.setTitle(destinatario.getNombre());
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textPane = new JTextPane();
		textPane.setBounds(10, 11, 414, 179);
		contentPane.add(textPane);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String msj = textPane.getText()+juego.getPersonaje().getNombre()+": "+textField.getText()+
						System.lineSeparator();
				PaqueteChatPrivado pcp = new PaqueteChatPrivado(new Usuario (juego.getPersonaje().getNombre()
						, juego.getPersonaje().getId()),msj);
				pcp.setComando(Comando.MENSAJEPRIVADO);
				pcp.setDireccion(destinatario.getId());
				textPane.setText(msj);
				try {
					juego.getCliente().getSalida().writeObject(gson.toJson(pcp));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
			}
		});
		btnNewButton.setBounds(335, 214, 89, 23);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(10, 201, 315, 49);
		contentPane.add(textField);
		textField.setColumns(10);
	}
	public void actualizar(String msj) {
		textPane.setText("");
		textPane.setText(textPane.getText()+msj);
	}
}

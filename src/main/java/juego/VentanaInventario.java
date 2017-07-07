package juego;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import dominio.Inventario;
import dominio.Item;
import frames.MenuJugar;
import mensajeria.PaquetePersonaje;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextField;

public class VentanaInventario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventario aux = new Inventario();
					for(int i=0;i<6;i++){
						Item auxi = new Item(i+1,i+1);
						auxi.setNombre("item"+(i+1));
						auxi.setAtaque(i);
						auxi.setDefensa(i);
						auxi.setEnergia(i);
						auxi.setTipo(i+1);
						aux.añadir(auxi);
						}
					for(int i=0;i<6;i++){
						Item auxi = new Item(i+1,i+1);
						auxi.setNombre("item"+(i+1));
						auxi.setAtaque(i);
						auxi.setDefensa(i);
						auxi.setEnergia(i);
						auxi.setTipo(i+1);
						aux.añadir(auxi);
						}
					PaquetePersonaje pp = new PaquetePersonaje();
					pp.setNombre("Raul");
					pp.setCasta("Asesino");
					pp.setRaza("Humano");
					pp.setInv(aux);
					VentanaInventario frame = new VentanaInventario(pp);
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
	public VentanaInventario(PaquetePersonaje pp) {
		//Para cambiar el cursor
		this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(),
				new Point(0,0),"custom cursor"));
		

		final Inventario inv = pp.getInv();
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Inventario");
		setBounds(100, 100, 270, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		
		/*BufferedImage fondo = null;
		try {
			fondo = ImageIO.read(new File("recursos\\Item\\inv.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel fondoLabel = new JLabel(new ImageIcon(fondo.getScaledInstance(270, 400, Image.SCALE_SMOOTH)));
		fondoLabel.setBounds(0, 0, 270, 400);
		contentPane.add(fondoLabel,1,0);*/
		
		JPanel cabeza = new JPanel();
		cabeza.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		cabeza.setBounds(90, 29, 70, 70);
		contentPane.add(cabeza);	
		
		JPanel cuerpo = new JPanel();
		cuerpo.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		cuerpo.setBounds(90, 110, 70, 70);
		contentPane.add(cuerpo);
		
		JPanel arma = new JPanel();
		arma.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		arma.setBounds(10, 59, 70, 70);
		contentPane.add(arma);
		
		JPanel guantes = new JPanel();
		guantes.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		guantes.setBounds(10, 140, 70, 70);
		contentPane.add(guantes);
		
		JPanel accesorio = new JPanel();
		accesorio.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		accesorio.setBounds(170, 94, 35, 35);
		contentPane.add(accesorio);
		
		JPanel pies = new JPanel();
		pies.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		pies.setBounds(170, 140, 70, 70);
		contentPane.add(pies);
		
		final JLabel stats = new JLabel("");
		stats.setBounds(170, 11, 90, 70);
		contentPane.add(stats);
		
		//A partir de aca estan los iconos de los items
		BufferedImage cabezaIcon = null;
		try {
			cabezaIcon = ImageIO.read(new File("recursos//Item//"+inv.getCabeza().getId()+".gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		cabeza.setLayout(new GridLayout(0, 1, 0, 0));
		JLabel cabezaLabel = new JLabel(new ImageIcon(cabezaIcon.getScaledInstance(cabezaIcon.getWidth()*2, cabezaIcon.getHeight()*2, Image.SCALE_SMOOTH)));
		cabeza.add(cabezaLabel);
		if(inv.getCabeza().getId()!=-1){
		cabezaLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				stats.setText("<html>"+inv.getCabeza().getNombre()+"<br>Defensa: "+inv.getCabeza().getDefensa()+"<br>Energia: "+inv.getCabeza().getEnergia()+"</html>");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				stats.setText("");
			}
		});
		}
		BufferedImage manosIcon = null;
		try {
			manosIcon = ImageIO.read(new File("recursos//Item//"+inv.getManos().getId()+".gif"));
		} catch (IOException e) {
			e.printStackTrace();

		}
		guantes.setLayout(new GridLayout(0, 1, 0, 0));
		JLabel manosLabel = new JLabel(new ImageIcon(manosIcon.getScaledInstance(manosIcon.getWidth()*2, manosIcon.getHeight()*2, Image.SCALE_SMOOTH)));
		guantes.add(manosLabel);
		if(inv.getManos().getId()!=-1){
		manosLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				stats.setText("<html>"+inv.getManos().getNombre()+"<br>Defensa: "+inv.getManos().getDefensa()+"</html>");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				stats.setText("");
			}
		});
		}
		
		BufferedImage botasIcon = null;
		try {
			botasIcon = ImageIO.read(new File("recursos//Item//"+inv.getPies().getId()+".gif"));
		} catch (IOException e) {
			e.printStackTrace();

		}
		pies.setLayout(new GridLayout(0, 1, 0, 0));
		JLabel botasLabel = new JLabel(new ImageIcon(botasIcon.getScaledInstance(botasIcon.getWidth()*2, botasIcon.getHeight()*2, Image.SCALE_SMOOTH)));
		pies.add(botasLabel);
		
		if(inv.getPies().getId()!=-1){
		botasLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				stats.setText("<html>"+inv.getPies().getNombre()+"<br>Defensa: "+inv.getPies().getDefensa()+"<br>Salud: "+inv.getPies().getSalud()+"</html>");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				stats.setText("");
			}
		});
		}
		
		BufferedImage cuerpoIcon = null;
		try {
			cuerpoIcon = ImageIO.read(new File("recursos//Item//"+inv.getCuerpo().getId()+".gif"));
		} catch (IOException e) {
			e.printStackTrace();

		}
		cuerpo.setLayout(new GridLayout(0, 1, 0, 0));
		JLabel cuerpoLabel = new JLabel(new ImageIcon(cuerpoIcon.getScaledInstance(cuerpoIcon.getWidth()*2, cuerpoIcon.getHeight()*2, Image.SCALE_SMOOTH)));
		cuerpo.add(cuerpoLabel);
		
		if(inv.getCuerpo().getId()!=-1){
		cuerpoLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				stats.setText("<html>"+inv.getCuerpo().getNombre()+"<br>Defensa: "+inv.getCuerpo().getDefensa()+"</html>");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				stats.setText("");
			}
		});
		}
		
		BufferedImage accesorioIcon = null;
		try {
			accesorioIcon = ImageIO.read(new File("recursos//Item//"+inv.getAccesorio().getId()+".gif"));
		} catch (IOException e) {
			e.printStackTrace();

		}
		accesorio.setLayout(new GridLayout(0, 1, 0, 0));
		JLabel accesorioLabel = new JLabel(new ImageIcon(accesorioIcon.getScaledInstance(accesorioIcon.getWidth(),accesorioIcon.getHeight(), Image.SCALE_SMOOTH)));
		accesorio.add(accesorioLabel);
		if(inv.getAccesorio().getId()!=-1){
		accesorioLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				stats.setText("<html>"+inv.getAccesorio().getNombre()+"<br>Magia: "+inv.getAccesorio().getMagia()+"</html>");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				stats.setText("");
			}
		});
		}
			
		BufferedImage armaIcon = null;
		try {
			armaIcon = ImageIO.read(new File("recursos//Item//"+inv.getArma().getId()+".gif"));
		} catch (IOException e) {
			e.printStackTrace();

		}
		arma.setLayout(new GridLayout(0, 1, 0, 0));
		JLabel armaLabel = new JLabel(new ImageIcon(armaIcon.getScaledInstance(armaIcon.getWidth()*2,armaIcon.getHeight()*2, Image.SCALE_SMOOTH)));
		arma.add(armaLabel);
		if(inv.getArma().getId()!=-1){
		armaLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				stats.setText("<html>"+inv.getArma().getNombre()+"<br>Ataque: "+inv.getArma().getAtaque()+"</html>");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				stats.setText("");
			}
		});
		}
			
		JLabel totalesLabel = new JLabel("<html>Salud: "+(pp.getSaludTope()+inv.getSalud())+" ("+pp.getSaludTope()+"+"+inv.getSalud()+")"
				+"<br>Energia: "+(pp.getEnergiaTope()+inv.getEnergia())+" ("+pp.getEnergiaTope()+"+"+inv.getEnergia()+")"
				+"<br>Ataque: "+(int)(pp.getFuerza()*1.5+inv.getAtaque())+" ("+(int)(pp.getFuerza()*1.5)+"+"+inv.getAtaque()+")"
				+"<br>Defensa: "+(pp.getDestreza()+inv.getDefensa())+" ("+pp.getDestreza()+"+"+inv.getDefensa()+")"
				+"<br>Magia: "+(int)(pp.getInteligencia()*1.5+inv.getMagia())+" ("+(int)(pp.getInteligencia()*1.5)+"+"+inv.getMagia()+")</html>"
	);
		totalesLabel.setBounds(10, 293, 134, 91);
		contentPane.add(totalesLabel);
		
		JLabel statsBaseLabel = new JLabel("<html>"+"Nivel: "+pp.getNivel()+"<br>Fuerza: "+pp.getFuerza()
		+"<br>Destreza: "+pp.getDestreza()
		+"<br>Inteligencia: "+pp.getInteligencia());
		statsBaseLabel.setBounds(10, 220, 92, 91);
		contentPane.add(statsBaseLabel);
		
		JLabel razacastaLabel = new JLabel("<html>"+pp.getCasta()+"<br>"+pp.getRaza()+"</html>",SwingConstants.CENTER);
		razacastaLabel.setBounds(10, 11, 70, 40);
		contentPane.add(razacastaLabel);
		
		JLabel nombreLabel = new JLabel(pp.getNombre(),SwingConstants.CENTER);
		nombreLabel.setBounds(90, 11, 70, 14);
		contentPane.add(nombreLabel);
		
		JLabel label = new JLabel("");
		label.setBounds(143, 222, 117, 162);
		contentPane.add(label);
		StringBuilder text = new StringBuilder("<html>");
		for( Item it:pp.getInv().getMochila()){
			text.append(it.getNombre()+"<br>");
		}
		text.append("</html>");
		label.setText(text.toString());
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {				
			}
			@Override
			public void keyReleased(KeyEvent arg0) {				
			}
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar()=='i'){
					dispose();
				}					
			}
        });
		
	}
}

package juego;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import com.google.gson.Gson;

import cliente.Cliente;
import frames.MenuJugar;
import mensajeria.Comando;
import mensajeria.Paquete;
import mundo.Mundo;

public class Pantalla {

	private JFrame pantalla;
	private Canvas canvas;
	private VentanaInventario v;
	private final Gson gson = new Gson();

	public Pantalla(final String NOMBRE, final int ANCHO, final int ALTO, final Cliente cliente, final Juego juego) {
		pantalla = new JFrame(NOMBRE);
		
		v = new VentanaInventario(juego.getPersonaje());
		v.setVisible(false);
		
		pantalla.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
			new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(),
			new Point(0,0),"custom cursor"));
		
		pantalla.setSize(ANCHO, ALTO);
		pantalla.setResizable(false);
		pantalla.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		pantalla.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				try {
					Paquete p = new Paquete();
					p.setComando(Comando.DESCONECTAR);
					p.setIp(cliente.getMiIp());
					cliente.getSalida().writeObject(gson.toJson(p));
					cliente.getEntrada().close();
					cliente.getSalida().close();
					cliente.getSocket().close();
					System.exit(0);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Fallo al intentar cerrar la aplicaci�n.");
					System.exit(1);
					e.printStackTrace();
				}
			}
		});
		
		pantalla.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {				
			}
			@Override
			public void keyReleased(KeyEvent arg0) {				
			}
			@Override
			public void keyTyped(KeyEvent e) {
				float[] pos = Mundo.isoA2D(juego.getUbicacionPersonaje().getPosX(), juego.getUbicacionPersonaje().getPosY());
				double x = pos[0];
				double y = pos[1];
				
				
				if(e.getKeyChar()=='i'){
					System.out.println("X: "+x
										+"\tY: "+y);
					
					if(!v.isVisible()){
					v = new VentanaInventario(juego.getPersonaje());
					v.setVisible(true);
					v.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					}
					else{
						v.setVisible(false);
					}
				}
				
				if(e.getKeyChar()=='m'){

					if((x<=16&&x>=8)&&(y<=28&&y>=11))
					
					System.out.println("Estas en el mercado");
				}
					
			}
        });
		pantalla.setLocationRelativeTo(null);
		pantalla.setVisible(false);

		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(ANCHO, ALTO));
		canvas.setMaximumSize(new Dimension(ANCHO, ALTO));
		canvas.setMinimumSize(new Dimension(ANCHO, ALTO));
		canvas.setFocusable(false);

		pantalla.add(canvas);
		pantalla.pack();
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public JFrame getFrame() {
		return pantalla;
	}
	
	public void mostrar() {
		pantalla.setVisible(true);
	}
	
	public static void centerString(Graphics g, Rectangle r, String s) {
	    FontRenderContext frc = new FontRenderContext(null, true, true);

	    Rectangle2D r2D = g.getFont().getStringBounds(s, frc);
	    int rWidth = (int) Math.round(r2D.getWidth());
	    int rHeight = (int) Math.round(r2D.getHeight());
	    int rX = (int) Math.round(r2D.getX());
	    int rY = (int) Math.round(r2D.getY());

	    int a = (r.width / 2) - (rWidth / 2) - rX;
	    int b = (r.height / 2) - (rHeight / 2) - rY;
	    
	    g.drawString(s, r.x + a, r.y + b);
	}
}
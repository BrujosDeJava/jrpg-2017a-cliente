package cliente;

import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import dominio.Item;
import dominio.Mercado;
import estados.Estado;
import estados.EstadoBatalla;
import juego.Juego;
import juego.VentanaChat;
import juego.VentanaMercado;
import mensajeria.Comando;
import mensajeria.Paquete;
import mensajeria.PaqueteAtacar;
import mensajeria.PaqueteBatalla;
import mensajeria.PaqueteChatPrivado;
import mensajeria.PaqueteDeMovimientos;
import mensajeria.PaqueteDePersonajes;
import mensajeria.PaqueteFinalizarBatalla;
import mensajeria.PaqueteInicioSesion;
import mensajeria.PaqueteIntercambio;
import mensajeria.PaqueteMensajeSala;
import mensajeria.PaqueteMercado;
import mensajeria.PaqueteMochila;
import mensajeria.PaqueteMovimiento;
import mensajeria.PaquetePersonaje;

public class EscuchaMensajes extends Thread {

	private Juego juego;
	private Cliente cliente;
	private ObjectInputStream entrada;
	private final Gson gson = new Gson();
	private PaqueteIntercambio pi;
	private Map<Integer, PaqueteMovimiento> ubicacionPersonajes;
	private Map<Integer, PaquetePersonaje> personajesConectados;

	public EscuchaMensajes(Juego juego) {
		this.juego = juego;
		cliente = juego.getCliente();
		entrada = cliente.getEntrada();
	}

	public void run() {

		try {

			Paquete paquete;
			PaquetePersonaje paquetePersonaje;
			PaqueteMovimiento personaje;
			PaqueteBatalla paqueteBatalla;
			PaqueteAtacar paqueteAtacar;
			PaqueteFinalizarBatalla paqueteFinalizarBatalla;
			personajesConectados = new HashMap<>();
			ubicacionPersonajes = new HashMap<>();

			while (true) {
				
				String objetoLeido = (String)entrada.readObject();

				paquete = gson.fromJson(objetoLeido , Paquete.class);
				
				switch (paquete.getComando()) {
	
				case Comando.CONEXION:
					personajesConectados = (Map<Integer, PaquetePersonaje>) gson.fromJson(objetoLeido, PaqueteDePersonajes.class).getPersonajes();
					break;

				case Comando.MOVIMIENTO:
					ubicacionPersonajes = (Map<Integer, PaqueteMovimiento>) gson.fromJson(objetoLeido, PaqueteDeMovimientos.class).getPersonajes();
					break;
					
				case Comando.BATALLA:
					paqueteBatalla = gson.fromJson(objetoLeido, PaqueteBatalla.class);
					juego.getPersonaje().setEstado(Estado.estadoBatalla);
					Estado.setEstado(null);
					juego.setEstadoBatalla(new EstadoBatalla(juego, paqueteBatalla));
					Estado.setEstado(juego.getEstadoBatalla());
					break;
					
				case Comando.ATACAR:
					
					paqueteAtacar = (PaqueteAtacar) gson.fromJson(objetoLeido, PaqueteAtacar.class);
					juego.getEstadoBatalla().getEnemigo().actualizarPorAtaque(paqueteAtacar);
					juego.getEstadoBatalla().getPersonaje().actualizarPorAtaque(paqueteAtacar);
					juego.getEstadoBatalla().setMiTurno(true);
					break;
					
				case Comando.FINALIZARBATALLA:
					paqueteFinalizarBatalla = (PaqueteFinalizarBatalla) gson.fromJson(objetoLeido, PaqueteFinalizarBatalla.class);
					juego.getPersonaje().setEstado(Estado.estadoJuego);
					Estado.setEstado(juego.getEstadoJuego());
					break;
					
				case Comando.ACTUALIZARPERSONAJE:
					paquetePersonaje = (PaquetePersonaje) gson.fromJson(objetoLeido, PaquetePersonaje.class);
					personajesConectados.remove(paquetePersonaje.getId());
					juego.getMercado().actualizarMochila();
					personajesConectados.put(paquetePersonaje.getId(), paquetePersonaje);
					
					if(juego.getPersonaje().getId() == paquetePersonaje.getId()) {
						juego.actualizarPersonaje();
						juego.getEstadoJuego().actualizarPersonaje();
					}
					break;
				case Comando.SALAMSJ:
					PaqueteMensajeSala pqs = (PaqueteMensajeSala)gson.fromJson(objetoLeido, PaqueteMensajeSala.class);

					juego.getSala().actualizar(pqs);
					break;
				case Comando.MERCADO:
					PaqueteMercado pmerca = (PaqueteMercado)gson.fromJson(objetoLeido, PaqueteMercado.class);
					if(juego.getMercado()==null)
						juego.setMercado(new VentanaMercado(juego));
					juego.getMercado().actualizarMercado(pmerca);
					break;
				case Comando.RECIBIRCONECTADOS:
					PaqueteInicioSesion pini = (PaqueteInicioSesion) gson.fromJson(objetoLeido, PaqueteInicioSesion.class);
					juego.getSala().actualizarUsuarios(pini.getUsuarios());
					break;
				case Comando.MENSAJEPRIVADO:
					
					
					PaqueteChatPrivado pcp = (PaqueteChatPrivado) gson.fromJson(objetoLeido, PaqueteChatPrivado.class);
					if(juego.getConversaciones().get(pcp.getUsuario().getId())==null){
						juego.getConversaciones().put(pcp.getUsuario().getId(), new VentanaChat(pcp.getUsuario(),juego));
					}
					else
						juego.getConversaciones().get(pcp.getUsuario().getId()).setVisible(true);
					
					
					juego.getConversaciones().get(pcp.getUsuario().getId()).actualizar(pcp.getMsj());
					break;
					
					
				case Comando.INTERCAMBIO:
					pi = (PaqueteIntercambio) gson.fromJson(objetoLeido, PaqueteIntercambio.class);
					int respuesta = JOptionPane.showConfirmDialog(null,
								"mi "+pi.getOfrecido().getNombre()+" por tu "+pi.getRequerido().getNombre(),"Intercambio",JOptionPane.YES_NO_OPTION);
		        	pi.setComando(Comando.RESPUESTAINTERCAMBIO);
					if (respuesta == JOptionPane.YES_OPTION) {
			        	pi.setRespuesta(true);
			        }
			        else
			        	pi.setRespuesta(false);
					juego.getCliente().getSalida().writeObject(gson.toJson(pi));
					break;
				case Comando.RESPUESTAINTERCAMBIO:
					pi = (PaqueteIntercambio) gson.fromJson(objetoLeido, PaqueteIntercambio.class);
					if(pi.getRespuesta()){
						Item aEquipar;
						Item aDesequipar;
						if(pi.getOfrecido().getDuenio()==juego.getPersonaje().getId()){
							aEquipar = pi.getRequerido();
							aDesequipar = pi.getOfrecido();
						}
						else{
							aDesequipar = pi.getRequerido();
							aEquipar = pi.getOfrecido();
						}
						
						juego.getPersonaje().getInv().desequipar(aDesequipar);
						juego.getPersonaje().getInv().añadir(aEquipar);
						juego.getMercado().actualizarMochila();
						PaquetePersonaje pp = juego.getPersonaje();
						pp.setComando(Comando.ACTUALIZARPERSONAJE);
						juego.getCliente().getSalida().writeObject(gson.toJson(pp));

						PaqueteMochila pm = new PaqueteMochila(juego.getPersonaje().getInv().getMochila(), juego.getPersonaje().getId());
						pm.setComando(Comando.MOCHILA);
						juego.getCliente().getSalida().writeObject(gson.toJson(pm));
						
					}else
						JOptionPane.showMessageDialog(null, "No se completo el intercambio");

				}	
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexion con el servidor.");
			e.printStackTrace();
		}
	}

	public Map<Integer, PaqueteMovimiento> getUbicacionPersonajes() {
		return ubicacionPersonajes;
	}
	
	public Map<Integer, PaquetePersonaje> getPersonajesConectados(){
		return personajesConectados; 
	}
}
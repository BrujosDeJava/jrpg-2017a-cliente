package mensajeria;

import java.io.Serializable;

import estados.Estado;

public class PaquetePersonaje extends PaquetePersonajeDom implements Serializable, Cloneable {

	private int estado;
	public PaquetePersonaje() {
		
		estado = Estado.estadoOffline;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
}

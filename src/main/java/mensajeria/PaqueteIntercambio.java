package mensajeria;

import java.io.Serializable;

import dominio.Item;

public class PaqueteIntercambio extends Paquete implements Serializable, Cloneable{
	
	private Item ofrecido;
	private Item requerido;
	private boolean respuesta;
	
	public PaqueteIntercambio(Item ofrecido, Item requerido) {
		super();
		this.ofrecido = ofrecido;
		this.requerido = requerido;
	}
	
	
	public Item getOfrecido() {
		return ofrecido;
	}
	public void setOfrecido(Item ofrecido) {
		this.ofrecido = ofrecido;
	}
	public Item getRequerido() {
		return requerido;
	}
	public void setRequerido(Item requerido) {
		this.requerido = requerido;
	}


	public void setRespuesta(boolean b) {
		respuesta = b;
	}
	
	public boolean getRespuesta(){
		return this.respuesta;
	}

}

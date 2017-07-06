package mensajeria;

import java.io.Serializable;

import dominio.Mercado;

public class PaqueteMercado extends Paquete implements Serializable, Cloneable{
	
	private Mercado mercado;
	private int id;
	
	
	public PaqueteMercado(Mercado mercado, int id){
		this.mercado = mercado;
		this.id = id;
	}


	public Mercado getMercado() {
		return mercado;
	}


	public void setMercado(Mercado mercado) {
		this.mercado = mercado;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

}

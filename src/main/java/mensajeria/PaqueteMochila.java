package mensajeria;

import java.io.Serializable;
import java.util.List;

import dominio.Item;
public class PaqueteMochila extends Paquete implements Serializable, Cloneable{
	private List<Item> mochila;
	private int id;
	private boolean conectar;
	
	public PaqueteMochila(List<Item> mochila, int id) {
		super();
		this.mochila = mochila;
		this.id = id;
	}
	public List<Item> getMochila() {
		return mochila;
	}
	public void setMochila(List<Item> mochila) {
		this.mochila = mochila;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}

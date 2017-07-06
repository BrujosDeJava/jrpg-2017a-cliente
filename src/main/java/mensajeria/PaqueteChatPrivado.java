package mensajeria;

import java.io.Serializable;

public class PaqueteChatPrivado extends Paquete implements Serializable, Cloneable {
	private Usuario usuario;
	private String msj;
	private int direccion;
	public PaqueteChatPrivado(Usuario usuario, String msj) {
		super();
		this.usuario = usuario;
		this.msj = msj;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getMsj() {
		return msj;
	}
	public void setMsj(String msj) {
		this.msj = msj;
	}
	public int getDireccion() {
		return direccion;
	}
	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}
	
	
	

}

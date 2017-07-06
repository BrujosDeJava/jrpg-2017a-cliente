package mensajeria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PaqueteInicioSesion extends Paquete implements Serializable, Cloneable{
	private List<Usuario> usuarios;
	
	public PaqueteInicioSesion() {
		super();
		this.usuarios = new ArrayList<Usuario>();
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public void add(Usuario u){
		usuarios.add(u);
	}
	
	
}

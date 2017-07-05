package mensajeria;

import java.io.Serializable;

public class PaqueteMensajeSala extends Paquete implements Serializable, Cloneable{
	private String msj2;
	
	
	public PaqueteMensajeSala(String msj2) {
		setComando(Comando.SALAMSJ);
		this.msj2 = msj2;
	}


	public String getMsj2() {
		return msj2;
	}


	public void setMsj2(String msj2) {
		this.msj2 = msj2;
	}
	
	
	
}

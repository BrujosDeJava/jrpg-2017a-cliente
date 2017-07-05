package mensajeria;

import java.io.Serializable;

public class PaqueteItem extends Paquete implements Serializable, Cloneable{
	private int id;
	private String nombre;
	private int bonusSalud;
	private int bonusEnergia;
	private int bonusAtaque;
	private int bonusDefensa;
	private int bonusMagia;
	private int tipo;
	

	public int getBonusSalud() {
		return bonusSalud;
	}

	public void setBonusSalud(int bonusSalud) {
		this.bonusSalud = bonusSalud;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public PaqueteItem(){
		setComando(Comando.ITEM);
		id = 0;
		nombre = null;
		bonusSalud = 0;
		bonusAtaque = 0;
		bonusEnergia = 0;
		bonusMagia = 0;
		bonusDefensa = 0;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getBounsSalud() {
		return bonusSalud;
	}

	public int getBonusEnergia() {
		return bonusEnergia;
	}
	public void setBonusEnergia(int bonusEnergia) {
		this.bonusEnergia = bonusEnergia;
	}
	public int getBonusAtaque() {
		return bonusAtaque;
	}
	public void setBonusAtaque(int bonusAtaque) {
		this.bonusAtaque = bonusAtaque;
	}
	public int getBonusDefensa() {
		return bonusDefensa;
	}
	public void setBonusDefensa(int bonusDefensa) {
		this.bonusDefensa = bonusDefensa;
	}
	public int getBonusMagia() {
		return bonusMagia;
	}
	public void setBonusMagia(int bonusMagia) {
		this.bonusMagia = bonusMagia;
	}
	
	
}

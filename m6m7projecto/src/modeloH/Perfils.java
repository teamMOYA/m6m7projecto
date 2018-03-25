package modeloH;
// Generated 21/02/2018 18:05:36 by Hibernate Tools 4.0.1.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Perfils generated by hbm2java
 */
public class Perfils implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int codi;
	private String descripcio;
	private Set<Usuaris> usuarises = new HashSet<Usuaris>(0);

	public Perfils() {
	}

	public Perfils(int codi, String descripcio) {
		this.codi = codi;
		this.descripcio = descripcio;
	}

	public Perfils(int codi, String descripcio, Set<Usuaris> usuarises) {
		this.codi = codi;
		this.descripcio = descripcio;
		this.usuarises = usuarises;
	}

	public int getCodi() {
		return this.codi;
	}

	public void setCodi(int codi) {
		this.codi = codi;
	}

	public String getDescripcio() {
		return this.descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	public Set<Usuaris> getUsuarises() {
		return this.usuarises;
	}

	public void setUsuarises(Set<Usuaris> usuarises) {
		this.usuarises = usuarises;
	}

}

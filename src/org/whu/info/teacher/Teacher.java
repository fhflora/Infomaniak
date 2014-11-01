package org.whu.info.teacher;

import java.io.Serializable;

/**
 * ≥ÈœÛ¿‡ Teacher¿‡
 * 
 * @author bobo
 *
 */
abstract public class Teacher implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int ID;
	public String prenom;
	public String nom;
	public String ville;
	public String region;
	
	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}

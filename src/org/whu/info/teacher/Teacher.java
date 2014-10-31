package org.whu.info.teacher;

/**
 * ≥ÈœÛ¿‡ Teacher¿‡
 * 
 * @author bobo
 *
 */
abstract public class Teacher {
	public int ID;
	public String prenom;
	public String nom;

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

	public abstract boolean addTeacher(Teacher t);

	public abstract boolean removeTeacher(Teacher t);

	public abstract Teacher getTeacher();
}

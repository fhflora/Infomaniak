package org.whu.info.student;

public class Student {
	private int ID;
	private String prenom;
	private String nom;
	private String ville;
	private String region;

	public Student(){}
	public Student(int ID, String prenom, String nom, String ville,
			String region) {
		this.ID = ID;
		this.prenom = prenom;
		this.nom = nom;
		this.ville = ville;
		this.region = region;
	}

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

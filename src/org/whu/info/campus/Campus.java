package org.whu.info.campus;

import org.whu.info.student.Student;

public class Campus {
	private String ville;
	private String region;
	private int capacite;

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

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
	
	public boolean addStudent(Student s) {
		boolean res = false;
		return res;
	}
}

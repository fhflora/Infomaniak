package org.whu.info.teacher;

import java.io.Serializable;

/**
 * ◊”¿‡ExternalTeacher¿‡
 * 
 * @author bobo
 *
 */
public class ExternalTeacher extends Teacher implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int salary;
	
	public ExternalTeacher(){}

	public ExternalTeacher(int ID, String prenom, String nom, int salary) {
		super.ID = ID;
		super.prenom = prenom;
		super.nom = nom;
		this.salary = salary;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

}

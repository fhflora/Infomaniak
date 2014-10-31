package org.whu.info.teacher;

import java.io.Serializable;

import org.whu.info.dbutil.DaoUtil;

/**
 * ����InternalTeacher
 * 
 * @author bobo
 *
 */
public class InternalTeacher extends Teacher implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int salary = 10000;

	public InternalTeacher() {
	}

	public InternalTeacher(int ID, String prenom, String nom) {
		super.ID = ID;
		super.prenom = prenom;
		super.nom = nom;
	}

	/**
	 * ͳһ�޸��ڲ���ʦ����
	 * 
	 * @param salary
	 * @return
	 */
	public static boolean modifySalary(int salary) {
		InternalTeacher.salary = salary;
		boolean res = DaoUtil.getTeacherDao().updateInternalTeacher(
				new InternalTeacher());
		return res;
	}

	public static void main(String[] args) {
		modifySalary(20000);
	}
}

package org.whu.info.campus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.whu.info.dbutil.DaoUtil;
import org.whu.info.exception.FullCampusException;
import org.whu.info.student.Student;
import org.whu.info.teacher.ExternalTeacher;
import org.whu.info.teacher.InternalTeacher;
import org.whu.info.teacher.Teacher;

public class Campus implements Serializable {

	private static final long serialVersionUID = 1L;
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

	public Campus(){
		
	}
	public Campus(String ville,String region, int capacite){
		this.ville=ville;
		this.region=region;
		this.capacite=capacite;
	}
	/**
	 * add a student to the campus
	 * 
	 * @param s
	 * @return
	 */
	public boolean addStudent(Student s) {
		boolean res = false;
		int capacite = DaoUtil.getCampusDao().getCampusCapacite(s.getVille(),
				s.getRegion());
		boolean checkId = false;
		if (capacite > 0) {
			if (s.getID() != 0) {
				checkId = DaoUtil.getStudentDao().checkStudentID(s);
			}
			boolean checkName = DaoUtil.getStudentDao().checkStudentName(s);
			if (!checkId && !checkName) {
				DaoUtil.getStudentDao().addStudent(s);
				capacite--;
				DaoUtil.getCampusDao().updateCampusCapacite(s.getVille(),
						s.getRegion(), capacite);
				res = true;
			}
		} else {
			throw new FullCampusException("本校区学生人数上限已满！");
		}
		return res;
	}

	/**
	 * remove a student from a campus
	 * 
	 * @param s
	 * @return
	 */
	public boolean removeStudent(Student s) {
		boolean res = false;
		boolean checkName = DaoUtil.getStudentDao().checkStudentName(s);
		if (checkName) {
			DaoUtil.getStudentDao().removeStudent(s);
			int capacite = DaoUtil.getCampusDao().getCampusCapacite(
					s.getVille(), s.getRegion());
			capacite++;
			DaoUtil.getCampusDao().updateCampusCapacite(s.getVille(),
					s.getRegion(), capacite);
			res = true;
		}
		return res;
	}

	/**
	 * Get a list of all students
	 * 
	 * @return
	 */
	public List<Student> getAllStudents() {
		List<Student> students = new ArrayList<Student>();
		students = DaoUtil.getStudentDao().getAllStudent();
		return students;
	}

	/**
	 * Get all students of a campus
	 * 
	 * @return
	 */
	public List<Student> getStudents(Campus c) {
		List<Student> students = new ArrayList<Student>();
		students = DaoUtil.getStudentDao().getCampusStudent(c);
		return students;
	}


	/**
	 * add a teacher
	 * 
	 * @param t the new teacher
	 * @return
	 */
	public boolean addTeacher(Teacher t) {
		if (DaoUtil.getTeacherDao().checkTeacherID(t)) {
			return false;
		}
		if (t instanceof InternalTeacher) {
			InternalTeacher internalT = (InternalTeacher) t;
			DaoUtil.getTeacherDao().addTeacher(internalT);
		} else {
			ExternalTeacher externalT = (ExternalTeacher) t;
			DaoUtil.getTeacherDao().addTeacher(externalT);
		}
		return true;

	}

	/**
	 * remove a teacher
	 * 
	 * @param t
	 * @return
	 */
	public boolean removeTeacher(Teacher t) {
		if (DaoUtil.getTeacherDao().checkTeacherID(t)) {
			DaoUtil.getTeacherDao().removeTeacher(t.getID());
			return true;
		}
		return false;
	}
	
	/**
	 * Get one student from his name and first name
	 * @param prenom
	 * @param nom
	 * @return
	 */
	   public Student getStudent(String prenom,String nom){
		   Student student=null;
		   student=DaoUtil.getStudentDao().getOneStudent(this, prenom, nom);
		   return student;
	   }

	/**
	 * get the list of teachers 
	 * 
	 * @return
	 */
	public List<Teacher> getTeachers(Campus c) {
		List<Teacher> teachers = new ArrayList<Teacher>();
		teachers = DaoUtil.getTeacherDao().getTeachers(c);
		return teachers;
	}
}

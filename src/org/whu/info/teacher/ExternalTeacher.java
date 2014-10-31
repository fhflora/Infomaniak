package org.whu.info.teacher;

/**
 * ×ÓÀàExternalTeacherÀà
 * 
 * @author bobo
 *
 */
public class ExternalTeacher extends Teacher {
	private int salary;

	@Override
	public boolean addTeacher(Teacher t) {
		return false;
	}

	@Override
	public boolean removeTeacher(Teacher t) {
		return false;
	}

	@Override
	public Teacher getTeacher() {
		return null;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
}

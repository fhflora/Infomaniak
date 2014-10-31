package org.whu.info.teacher;

/**
 * ×ÓÀàInternalTeacher
 * 
 * @author bobo
 *
 */
public class InternalTeacher extends Teacher {
	public static int salary = 10000;

	public static void modifySalary(int salary) {
		InternalTeacher.salary = salary;
	}

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
}

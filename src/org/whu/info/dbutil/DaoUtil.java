package org.whu.info.dbutil;

import org.whu.info.Dao.CampusDao;
import org.whu.info.Dao.StudentDao;
import org.whu.info.Dao.TeacherDao;

public class DaoUtil {
	public static CampusDao getCampusDao() {
		CampusDao campusDao = new CampusDao();
		return campusDao;
	}

	public static StudentDao getStudentDao() {
		StudentDao studentDao = new StudentDao();
		return studentDao;
	}

	public static TeacherDao getTeacherDao() {
		TeacherDao teacherDao = new TeacherDao();
		return teacherDao;
	}
}

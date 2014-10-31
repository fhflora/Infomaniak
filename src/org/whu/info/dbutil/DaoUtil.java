package org.whu.info.dbutil;

import org.whu.info.Dao.CampusDao;
import org.whu.info.Dao.StudentDao;
import org.whu.info.Dao.TeacherDao;
import org.whu.info.campus.Campus;
/**
 * 对象操作类
 * @author bobo
 *
 */
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

	public static Campus getCampus() {
		Campus campus=new Campus();
		return campus;
	}
}

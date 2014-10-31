package org.whu.info.backup;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.whu.info.campus.Campus;
import org.whu.info.dbutil.DaoUtil;
import org.whu.info.student.Student;
import org.whu.info.teacher.Teacher;

/**
 * 数据备份类
 * 
 * @author bobo
 *
 */
public class BackupData {
	/**
	 * 备份校区信息
	 * 
	 * @param filePath
	 */
	public void campusBackup(String filePath) {
		List<Campus> campus = new ArrayList<Campus>();
		campus = DaoUtil.getCampusDao().getAllCampus();
		try {
			for (int i = 0; i < campus.size(); i++) {
				FileOutputStream fs = new FileOutputStream(filePath);
				ObjectOutputStream os = new ObjectOutputStream(fs);
				os.writeObject(campus.get(i));
				os.flush();
				os.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 备份学生数据
	 */
	public void studentsBackup(String filePath) {
		List<Student> students = new ArrayList<Student>();
		students = DaoUtil.getCampus().getAllStudents();
		try {
			for (int i = 0; i < students.size(); i++) {
				FileOutputStream fs = new FileOutputStream(filePath);
				ObjectOutputStream os = new ObjectOutputStream(fs);
				os.writeObject(students.get(i));
				os.flush();
				os.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 备份老师数据
	 * 
	 * @param filePath
	 */
	public void teachersBackup(String filePath) {
		List<Teacher> teachers = new ArrayList<Teacher>();
		teachers = DaoUtil.getCampus().getTeachers();
		try {
			for (int i = 0; i < teachers.size(); i++) {
				FileOutputStream fs = new FileOutputStream(filePath);
				ObjectOutputStream os = new ObjectOutputStream(fs);
				os.writeObject(teachers.get(i));
				os.flush();
				os.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

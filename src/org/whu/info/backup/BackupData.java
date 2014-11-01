package org.whu.info.backup;
import java.util.ArrayList;
import java.util.List;

import org.whu.info.campus.Campus;
import org.whu.info.dbutil.DaoUtil;
import org.whu.info.student.Student;
import org.whu.info.teacher.ExternalTeacher;
import org.whu.info.teacher.InternalTeacher;
import org.whu.info.teacher.Teacher;

import edu.princeton.cs.introcs.Out;

/**
 * ���ݱ�����
 * 
 * @author bobo
 *
 */
public class BackupData {

	public static void main(String[] args) {
		BackupData bk = new BackupData();
		String filePath = "C:/teacher_bk.txt";
		bk.teachersBackup(filePath);
	}

	/**
	 * ����У����Ϣ
	 * 
	 * @param filePath
	 */
	public void campusBackup(String filePath) {
		List<Campus> campusList = new ArrayList<Campus>();
		campusList = DaoUtil.getCampusDao().getAllCampus();
		Out out = new Out(filePath);
		out.println("[");
		for (int i = 0; i < campusList.size(); i++) {
			Campus campus = campusList.get(i);
			String json = "{\"ville\":\"%s\",\"region\":\"%s\",\"capacite\":%d}";
			json = String.format(json, campus.getVille(), campus.getRegion(),
					campus.getCapacite());
			System.out.println(json);
			out.println(json);
			if (i != campusList.size() - 1) {
				out.print(",");
			}
		}
		out.println("]");
		out.close();
	}

	/**
	 * ����ѧ������
	 */
	public void studentsBackup(String filePath) {
		List<Student> studentsList = new ArrayList<Student>();
		studentsList = DaoUtil.getCampus().getAllStudents();
		Out out = new Out(filePath);
		out.println("[");
		for (int i = 0; i < studentsList.size(); i++) {
			Student student = studentsList.get(i);
			String json = "{\"stu_ID\":%d,\"prenom\":\"%s\",\"nom\":\"%s\",\"ville\":\"%s\",\"region\":\"%s\"}";
			json = String.format(json, student.getID(), student.getPrenom(),
					student.getNom(), student.getVille(), student.getRegion());
			System.out.println(json);
			out.println(json);
			if (i != studentsList.size() - 1) {
				out.print(",");
			}
		}
		out.println("]");
		out.close();
	}

	/**
	 * ������ʦ����
	 * 
	 * @param filePath
	 */
	public void teachersBackup(String filePath) {
		List<Teacher> teachersList = new ArrayList<Teacher>();
		teachersList = DaoUtil.getTeacherDao().getAllteachers();
		Out out = new Out(filePath);
		out.println("[");
		for (int i = 0; i < teachersList.size(); i++) {
			Teacher teacher = teachersList.get(i);
			String json = "{\"tea_ID\":%d,\"prenom\":\"%s\",\"nom\":\"%s\",\"salary\":%d}";
			if (teacher instanceof InternalTeacher) {
				InternalTeacher internalT = (InternalTeacher) teacher;
				json = String.format(json, internalT.getID(),
						internalT.getPrenom(), internalT.getNom(),
						InternalTeacher.salary);
			} else {
				ExternalTeacher externalT = (ExternalTeacher) teacher;
				json = String.format(json, externalT.getID(),
						externalT.getPrenom(), externalT.getNom(),
						externalT.getSalary());
			}
			System.out.println(json);
			out.println(json);
			if (i != teachersList.size() - 1) {
				out.print(",");
			}
		}
		out.println("]");
		out.close();
	}
}

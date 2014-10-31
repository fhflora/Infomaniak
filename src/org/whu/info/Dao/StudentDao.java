package org.whu.info.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.whu.info.dbutil.DBUtil;
import org.whu.info.student.Student;

public class StudentDao {
	/**
	 * ȡ��ѧ���б�
	 * 
	 * @return
	 */
	public List<Student> getAllStudent() {
		List<Student> allStudentList = new ArrayList<Student>();
		Connection conn = DBUtil.getConn();
		Statement stmt = DBUtil.createStmt(conn);
		String sql = "select * from student order by stu_ID ";
		System.out.println(sql);
		ResultSet rst = DBUtil.getRs(stmt, sql);
		try {
			while (rst.next()) {
				Student student = new Student();
				fillRsStudent(rst, student);
				allStudentList.add(student);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allStudentList;
	}
	public void fillRsStudent(ResultSet rst, Student s) {
		try {
			s.setID(rst.getInt("stu_ID"));
			s.setPrenom(rst.getString("prenom"));
			s.setNom(rst.getString("nom"));
			s.setVille(rst.getString("ville"));
			s.setRegion(rst.getString("region"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��ѯ����ID�ж��Ƿ����
	 * 
	 * @param s
	 * @return
	 */
	public boolean checkStudentID(Student s) {
		boolean res = false;
		Connection conn = DBUtil.getConn();
		Statement stmt = DBUtil.createStmt(conn);
		String sql = "select * from student where stu_ID=%d";
		sql = String.format(sql, s.getID());
		System.out.println(sql);
		ResultSet rst = DBUtil.getRs(stmt, sql);
		try {
			if (rst.next()) {
				res = true;
			}
		} catch (SQLException e) {
			res = false;
			e.printStackTrace();
		} finally {
			DBUtil.closeStmt(stmt);
			DBUtil.closeConn(conn);
			DBUtil.closeRs(rst);
		}
		return res;
	}
	/**
	 * ���������ж��Ƿ����
	 * @param s
	 */
	public boolean checkStudentName(Student s) {
		boolean res = false;
		Connection conn = DBUtil.getConn();
		Statement stmt = DBUtil.createStmt(conn);
		String sql = "select * from student where prenom='%s' and nom='%s'";
		sql = String.format(sql, s.getPrenom(), s.getNom());
		System.out.println(sql);
		ResultSet rst = DBUtil.getRs(stmt, sql);
		try {
			if (rst.next()) {
				res = true;
			}
		} catch (SQLException e) {
			res = false;
			e.printStackTrace();
		} finally {
			DBUtil.closeStmt(stmt);
			DBUtil.closeConn(conn);
			DBUtil.closeRs(rst);
		}
		return res;
	}
	/**
	 * ���һ��ѧ����¼
	 * @param s
	 */
	public int addStudent(Student s) {
		int res = -1;
		Connection conn = DBUtil.getConn();
		Statement stmt = DBUtil.createStmt(conn);
		String sql = "insert into student(stu_ID,prenom,nom,ville,region) values(%d,'%s','%s','%s','%s')";
		sql = String.format(sql, s.getID(), s.getPrenom(), s.getNom(),
				s.getVille(), s.getRegion());
		System.out.println(sql);
		try {
			stmt.execute(sql);
		} catch (SQLException e) {
			res = -1;
			e.printStackTrace();
		} finally {
			DBUtil.closeStmt(stmt);
			DBUtil.closeConn(conn);
		}
		return res;
	}
	/**
	 * ��������ɾ��ѧ��
	 * @param s
	 */
	public boolean removeStudent(Student s) {
		boolean res = false;
		Connection conn = DBUtil.getConn();
		Statement stmt = DBUtil.createStmt(conn);
		String sql = "delete from student where prenom = '%s' and  nom='%s' ";
		sql = String.format(sql, s.getPrenom(), s.getNom());
		System.out.println(sql);
		try {
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeStmt(stmt);
			DBUtil.closeConn(conn);
		}
		return res;
	}

	public static void main(String[] args) {
		Student s = new Student();
		s.setID(1);
		s.setNom("12");
		s.setPrenom("33");
		s.setRegion("33");
		s.setRegion("44");
		s.setVille("55");
		StudentDao studentDao = new StudentDao();
		// System.out.println(studentDao.checkStudentName(s));
		// studentDao.removeStudent(s);
		List<Student> allList = new ArrayList<Student>();
		allList = studentDao.getAllStudent();
		for (int i = 0; i < allList.size(); i++) {
			System.out.println(allList.get(i).getID() + " "
					+ allList.get(i).getPrenom());
		}
	}
}

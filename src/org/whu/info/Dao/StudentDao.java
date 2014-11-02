package org.whu.info.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.whu.info.campus.Campus;
import org.whu.info.dbutil.DBUtil;
import org.whu.info.student.Student;

/**
 * 学生表操作类
 * 
 * @author bobo
 *
 */
public class StudentDao {

	/**
	 * 获取每个校区学生列表
	 * 
	 * @return
	 */
	public List<Student> getCampusStudent(Campus c) {
		List<Student> allStudentList = new ArrayList<Student>();
		Connection conn = DBUtil.getConn();
		Statement stmt = DBUtil.createStmt(conn);
		String sql = "select * from student where ville='%s' and  region='%s' order by stu_ID ";
		sql = String.format(sql, c.getVille(), c.getRegion());
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

	/**
	 * 取表学生列表
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
	
	/**
	 * Get one student
	 * @param c
	 * @param prenom
	 * @param nom
	 * @return
	 */
	public Student getOneStudent(Campus c,String prenom,String nom){
		boolean res = false;
		Connection conn = DBUtil.getConn();
		Statement stmt = DBUtil.createStmt(conn);
		String sql = "select * from student where prenom= '%s' and  nom='%s' and ville='%s' and region='%s'";
		sql = String.format(sql, prenom, nom,c.getVille(),c.getRegion());
		System.out.println(sql);
		ResultSet rst = DBUtil.getRs(stmt, sql);
		Student student=new Student();
		try {
			while (rst.next()) {
				fillRsStudent(rst, student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

	/**
	 * 查询表中ID判断是否存在
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
	 * 根据姓名判断是否存在
	 * 
	 * @param s
	 * @return
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
	 * 添加一条学生记录
	 * 
	 * @param s
	 * @return
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
	 * 根据姓名删除学生
	 * 
	 * @param s
	 * @return
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
		StudentDao studentDao = new StudentDao();
		Campus c = new Campus();
		c.setVille("11");
		c.setRegion("22");
		List<Student> s = new ArrayList<Student>();
		s = studentDao.getCampusStudent(c);
		System.out.println(s.get(0).getPrenom() + " " + s.get(0).getNom());
	}
}

package org.whu.info.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.whu.info.dbutil.DBUtil;
import org.whu.info.teacher.ExternalTeacher;
import org.whu.info.teacher.InternalTeacher;

public class TeacherDao {
	/**
	 * 添加内部教师到数据表
	 * 
	 * @param s
	 * @return
	 */
	public int addTeacher(InternalTeacher s) {
		int res = -1;
		Connection conn = DBUtil.getConn();
		Statement stmt = DBUtil.createStmt(conn);
		String sql = "insert into teacher(tea_ID,prenom,nom,salary,flag) values(%d,'%s','%s','%d',0)";
		sql = String.format(sql, s.getID(), s.getPrenom(), s.getNom(),
				InternalTeacher.salary);
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
	 * 添加外部教师
	 * 
	 * @param s
	 * @return
	 */
	public int addTeacher(ExternalTeacher s) {
		int res = -1;
		Connection conn = DBUtil.getConn();
		Statement stmt = DBUtil.createStmt(conn);
		String sql = "insert into teacher(tea_ID,prenom,nom,salary,flag) values(%d,'%s','%s','%d',1)";
		sql = String.format(sql, s.getID(), s.getPrenom(), s.getNom(),
				s.getSalary());
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
	 * 通过教师ID 删除教师
	 */
	public boolean removeTeacher(int ID){
		boolean res=false;
		Connection conn = DBUtil.getConn();
		Statement stmt = DBUtil.createStmt(conn);
		String sql = "delete from teacher where tea_ID=ID ";
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
	/**
	 * 修改内部教师工资
	 * 
	 * @param s
	 * @return
	 */
	public boolean updateInternalTeacher(InternalTeacher s) {
		boolean res = false;
		Connection conn = DBUtil.getConn();
		Statement stmt = DBUtil.createStmt(conn);
		String sql = "update teacher set salary=%d where flag= 0";
		sql = String.format(sql, InternalTeacher.salary);
		System.out.println(sql);
		try {
			stmt.execute(sql);
			res = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeStmt(stmt);
			DBUtil.closeConn(conn);
		}
		return res;
	}
	/**
	 * 获得内部教师列表
	 * @param args
	 */
	public List<InternalTeacher> getInternalTeachers() {
		List<InternalTeacher> TeachersList = new ArrayList<InternalTeacher>();
		Connection conn = DBUtil.getConn();
		Statement stmt = DBUtil.createStmt(conn);
		String sql = "select * from teacher where flag=0 ";
		ResultSet rst = DBUtil.getRs(stmt, sql);
		try {
			while (rst.next()) {
				InternalTeacher t = new InternalTeacher();
				fillInternalTeacher(rst, t);
				TeachersList.add(t);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return TeachersList;
	}
	public void fillInternalTeacher(ResultSet rst, InternalTeacher t) {
		try {
			t.setID(rst.getInt("tea_ID"));
			t.setPrenom(rst.getString("prenom"));
			t.setNom(rst.getString("nom"));
			InternalTeacher.salary=rst.getInt("salary");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获得外部教师列表
	 * @param args
	 */
	public List<ExternalTeacher> getExternalTeachers() {
		List<ExternalTeacher> TeachersList = new ArrayList<ExternalTeacher>();
		Connection conn = DBUtil.getConn();
		Statement stmt = DBUtil.createStmt(conn);
		String sql = "select * from teacher where flag=1 ";
		ResultSet rst = DBUtil.getRs(stmt, sql);
		try {
			while (rst.next()) {
				ExternalTeacher t = new ExternalTeacher();
				fillExternalTeacher(rst, t);
				TeachersList.add(t);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return TeachersList;
	}
	public void fillExternalTeacher(ResultSet rst, ExternalTeacher t) {
		try {
			t.setID(rst.getInt("tea_ID"));
			t.setPrenom(rst.getString("prenom"));
			t.setNom(rst.getString("nom"));
			t.setSalary(rst.getInt("salary"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		InternalTeacher t = new InternalTeacher();
		t.setID(2);
		t.setPrenom("11");
		t.setNom("33");
		TeacherDao tt = new TeacherDao();
		ExternalTeacher e = new ExternalTeacher();
		e.setID(3);
		e.setNom("22");
		e.setPrenom("44");
		e.setSalary(50000);
		tt.addTeacher(e);
	}
}

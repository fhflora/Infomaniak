package org.whu.info.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.whu.info.campus.Campus;
import org.whu.info.dbutil.DBUtil;
import org.whu.info.teacher.ExternalTeacher;
import org.whu.info.teacher.InternalTeacher;
import org.whu.info.teacher.Teacher;

/**
 * 教师表操作类
 * 
 * @author bobo
 *
 */
public class TeacherDao {

	public List<Teacher> getAllteachers() {
		List<Teacher> allTeachersList = new ArrayList<Teacher>();
		Connection conn = DBUtil.getConn();
		Statement stmt = DBUtil.createStmt(conn);
		String sql = "select * from teacher order by tea_ID ";
		System.out.println(sql);
		ResultSet rst = DBUtil.getRs(stmt, sql);
		try {
			while (rst.next()) {
				Teacher teacher;
				if (rst.getInt("flag") == 0) {
					teacher = new InternalTeacher();
					fillInternalTeacher(rst, (InternalTeacher) teacher);
				} else {
					teacher = new ExternalTeacher();
					fillExternalTeacher(rst, (ExternalTeacher) teacher);
				}
				allTeachersList.add(teacher);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allTeachersList;
	}

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
		String sql = "insert into teacher(tea_ID,prenom,nom,salary,flag,ville,region) values(%d,'%s','%s','%d',0,'%s','%s')";
		sql = String.format(sql, s.getID(), s.getPrenom(), s.getNom(),
				InternalTeacher.salary, s.getVille(), s.getRegion());
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
		String sql = "insert into teacher(tea_ID,prenom,nom,salary,flag,ville,region) values(%d,'%s','%s','%d',1,'%s','%s')";
		sql = String.format(sql, s.getID(), s.getPrenom(), s.getNom(),
				s.getSalary(), s.getVille(), s.getRegion());
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
	public boolean removeTeacher(int ID) {
		boolean res = false;
		Connection conn = DBUtil.getConn();
		Statement stmt = DBUtil.createStmt(conn);
		String sql = "delete from teacher where tea_ID=" + ID;
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
	 * 
	 * @param args
	 */
	public List<InternalTeacher> getInternalTeachers(Campus c) {
		List<InternalTeacher> TeachersList = new ArrayList<InternalTeacher>();
		Connection conn = DBUtil.getConn();
		Statement stmt = DBUtil.createStmt(conn);
		String sql = "select * from teacher where flag=0 and ville='%s' and region='%s'";
		sql = String.format(sql, c.getVille(), c.getRegion());
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
			t.setVille(rst.getString("ville"));
			t.setRegion(rst.getString("region"));
			InternalTeacher.salary = rst.getInt("salary");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取校区教师列表
	 * 
	 * @return
	 */
	public List<Teacher> getTeachers(Campus c) {
		List<Teacher> TeachersList = new ArrayList<Teacher>();
		Connection conn = DBUtil.getConn();
		Statement stmt = DBUtil.createStmt(conn);
		String sql = "select * from teacher where ville='%s' and region='%s'";
		sql = String.format(sql, c.getVille(), c.getRegion());
		ResultSet rst = DBUtil.getRs(stmt, sql);
		try {
			while (rst.next()) {
				if (rst.getInt("flag") == 0) {
					InternalTeacher t = new InternalTeacher();
					fillInternalTeacher(rst, t);
					TeachersList.add(t);
				} else {
					ExternalTeacher t = new ExternalTeacher();
					fillExternalTeacher(rst, t);
					TeachersList.add(t);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return TeachersList;
	}

	/**
	 * 获得外部教师列表
	 * 
	 * @param args
	 */
	public List<ExternalTeacher> getExternalTeachers(Campus c) {
		List<ExternalTeacher> TeachersList = new ArrayList<ExternalTeacher>();
		Connection conn = DBUtil.getConn();
		Statement stmt = DBUtil.createStmt(conn);
		String sql = "select * from teacher where flag=1 and ville='%s' and region='%s'";
		sql = String.format(sql, c.getVille(), c.getRegion());
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
			t.setVille(rst.getString("ville"));
			t.setRegion(rst.getString("region"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询教师是否存在
	 * 
	 * @param t
	 * @return
	 */
	public boolean checkTeacherID(Teacher t) {
		boolean res = false;
		Connection conn = DBUtil.getConn();
		Statement stmt = DBUtil.createStmt(conn);
		String sql = "select * from teacher where tea_ID=%d";
		sql = String.format(sql, t.getID());
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
}

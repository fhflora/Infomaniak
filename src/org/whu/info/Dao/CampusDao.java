package org.whu.info.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.whu.info.campus.Campus;
import org.whu.info.dbutil.DBUtil;
import org.whu.info.student.Student;

public class CampusDao {
	/**
	 * ����һ��campus
	 */
	public int addCampus(Campus c) {
		int res = -1;
		Connection conn = DBUtil.getConn();
		Statement stmt = DBUtil.createStmt(conn);
		String sql = "insert into campus(ville,region,capacite) values('%s','%s',%d)";
		sql = String.format(sql,c.getVille(), c.getRegion(),c.getCapacite());
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
	 * ����ʡ���ж�campus�Ƿ����
	 * @param c
	 */
	public boolean checkCampus(Campus c) {
		boolean res = false;
		Connection conn = DBUtil.getConn();
		Statement stmt = DBUtil.createStmt(conn);
		String sql = "select * from campus where ville='%s' and region='%s'";
		sql = String.format(sql, c.getVille(),c.getRegion());
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
	 * ��ѯcampus��������
	 * @param ville
	 * @param region
	 */
	public int getCampusCapacite(String ville, String region) {
		int res = -1;
		Connection conn = DBUtil.getConn();
		Statement stmt = DBUtil.createStmt(conn);
		String sql = "select capacite from campus where ville='" + ville + "'"
				+ " and region=" + "'" + region + "'";
		System.out.println(sql);
		ResultSet rst = DBUtil.getRs(stmt, sql);
		try {
			if (rst.next()) {
				res = rst.getInt("capacite");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeStmt(stmt);
			DBUtil.closeConn(conn);
			DBUtil.closeRs(rst);
		}
		return res;
	}

	/**
	 * ����һ��У��ѧ������
	 * @param c
	 */
	public boolean updateCampusCapacite(Campus c) {
		boolean res = true;
		Connection conn = DBUtil.getConn();
		Statement stmt = DBUtil.createStmt(conn);
		String sql = "update campus set capacite=%d where ville='%s' and region='%s'";
		sql = String.format(sql, c.getCapacite(), c.getVille(), c.getRegion());
		try {
			stmt.execute(sql);
		} catch (SQLException e) {
			res = false;
			e.printStackTrace();
		} finally {
			DBUtil.closeStmt(stmt);
			DBUtil.closeConn(conn);
		}
		return res;
	}

	public static void main(String args[]) {
		Student s = new Student();
		Campus c = new Campus();
		c.setVille("11");
		c.setRegion("22");
		c.setCapacite(49);
		c.setCapacite(c.getCapacite() - 1);
		s.setID(1);
		s.setPrenom("��");
		s.setNom("��");
		s.setVille("11");
		s.setRegion("22");
		CampusDao campusDao = new CampusDao();
		campusDao.updateCampusCapacite(c);
		// campusDao.addStudent(s);
		System.out.println(campusDao.getCampusCapacite("11", "22"));
	}
}

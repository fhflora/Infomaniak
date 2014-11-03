package org.whu.info.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.whu.info.campus.Campus;
import org.whu.info.dbutil.DBUtil;

/**
 * manage campus
 * 
 * @author fuzhi
 *
 */
public class CampusDao {
	/**
	 * get a campus
	 * 
	 * @param ville
	 * @param region
	 * @return
	 */
	public Campus getCampus(String ville, String region) {
		Campus campus = null;
		Connection conn = DBUtil.getConn();
		Statement stmt = DBUtil.createStmt(conn);
		String sql = "select * from Campus where ville='" + ville + "'"
				+ " and region=" + "'" + region + "'";
		System.out.println(sql);
		ResultSet rst = DBUtil.getRs(stmt, sql);
		try {
			if (rst.next()) {
				campus = new Campus();
				campus.setCapacite(rst.getInt("capacite"));
				campus.setVille(rst.getString("ville"));
				campus.setRegion(rst.getString("region"));
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return campus;
	}
	/**
	 * get list of campus
	 * 
	 * @return
	 */
	public List<Campus> getAllCampus() {
		List<Campus> allCampusList = new ArrayList<Campus>();
		Connection conn = DBUtil.getConn();
		Statement stmt = DBUtil.createStmt(conn);
		String sql = "select * from Campus ";
		System.out.println(sql);
		ResultSet rst = DBUtil.getRs(stmt, sql);
		try {
			while (rst.next()) {
				Campus campus = new Campus();
				fillRsCampus(rst, campus);
				allCampusList.add(campus);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allCampusList;
	}

	public void fillRsCampus(ResultSet rst, Campus c) {
		try {
			c.setVille(rst.getString("ville"));
			c.setRegion(rst.getString("region"));
			c.setCapacite(rst.getInt("capacite"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * add a campus
	 */
	public int addCampus(Campus c) {
		int res = -1;
		Connection conn = DBUtil.getConn();
		Statement stmt = DBUtil.createStmt(conn);
		String sql = "insert into campus(ville,region,capacite) values('%s','%s',%d)";
		sql = String.format(sql, c.getVille(), c.getRegion(), c.getCapacite());
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
	 * check if the campus exist
	 * 
	 * @param c
	 */
	public boolean checkCampus(Campus c) {
		boolean res = false;
		Connection conn = DBUtil.getConn();
		Statement stmt = DBUtil.createStmt(conn);
		String sql = "select * from campus where ville='%s' and region='%s'";
		sql = String.format(sql, c.getVille(), c.getRegion());
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
	 * get the capacite of a campus
	 * 
	 * @param ville
	 * @param region
	 * @return
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
	 * update the capacite of a campus
	 * @param ville
	 * @param region
	 * @param capacite
	 * @return
	 */
	public boolean updateCampusCapacite(String ville, String region,
			int capacite) {
		boolean res = true;
		Connection conn = DBUtil.getConn();
		Statement stmt = DBUtil.createStmt(conn);
		String sql = "update campus set capacite= " + capacite
				+ " where ville='" + ville + "'" + " and region=" + "'"
				+ region + "'";
		System.out.println(sql);
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

}

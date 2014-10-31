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
 * 校区表操作类
 * 
 * @author bobo
 *
 */
public class CampusDao {

	/**
	 * 获取校区列表
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
	 * 增加一个campus
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
	 * 根据省市判断campus是否存在
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
	 * 查询campus容量操作
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
	 * 更新一个校区学生容量
	 * 
	 * @param c
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

	public static void main(String args[]) {
		CampusDao campusDao = new CampusDao();
		List<Campus> c = new ArrayList<Campus>();
		c = campusDao.getAllCampus();
		System.out.println(c.size());
	}
}

package org.whu.info.dbutil;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * manage database
 * @author fuzhi
 *
 */
public class DBUtil {
	private static DataSource ds;
	static{
		ds = new ComboPooledDataSource();
	}
	public static synchronized final Connection getConn() {    
        try {  
            return ds.getConnection();  
        } catch (SQLException e) {       
            e.printStackTrace();  
        }  
        return null;  
    }  

	public static Statement createStmt(Connection conn) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
	}

	public static ResultSet getRs(Statement stmt, String sql) {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static void executeSQL(Statement stmt, String sql)
			throws SQLException {
		try {
			stmt.execute(sql);
		} catch (SQLException e) {
			throw e;
		}
	}

	public static PreparedStatement preStmt(Connection conn, String sql) {
		PreparedStatement preStmt = null;
		try {
			preStmt = (PreparedStatement) conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preStmt;
	}
	public static PreparedStatement preStmt(Connection conn, String sql,
			int autokey) {
		PreparedStatement preStmt = null;
		try {
			preStmt = (PreparedStatement) conn.prepareStatement(sql, autokey);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preStmt;
	}
	public static void closeConn(Connection conn) {
		
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void closeStmt(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeRs(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
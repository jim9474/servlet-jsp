package och12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


// singleton + DBCP -> Memory 절감 + DOS공격
public class MemberDao {
	private static MemberDao instance;
	Connection conn = null;
	
	private MemberDao() {
		
	}
	
	public static MemberDao getInstance() {
		if(instance == null) {
			instance = new MemberDao();
		}
		return instance;
	}
	
	private Connection getConnection() {
		
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	// PreparedStatement
	public int insert(Member2 member) throws SQLException {
		getConnection();
		String sql = "insert into member2 values(?,?,?,?,?,sysdate)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, member.getId());
		pstmt.setString(2, member.getPasswd());
		pstmt.setString(3, member.getName());
		pstmt.setString(4, member.getAddress());
		pstmt.setString(5, member.getTel());
		int result = pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		return result;
	}
	
	public int check(String id, String passwd) throws SQLException {
		getConnection();
		String sql = "select id, passwd from member2 WHERE id = ? AND passwd = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, id);
		pstmt.setString(2, passwd);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			return 1;
		} else if(idCheck(id)) {
			return 0;
		} else return -1;
		
	}
	
	public boolean idCheck(String id) throws SQLException {
		getConnection();
		String idSql = "select id from member2 WHERE id = ?";
		PreparedStatement idmt = conn.prepareStatement(idSql);
		idmt.setString(1, id);
		ResultSet idchk = idmt.executeQuery();
		return idchk.next();
	}
	
}

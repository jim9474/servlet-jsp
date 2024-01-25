package och12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<Member2> list() throws SQLException {
		List<Member2> list = new ArrayList<Member2>();
		
		getConnection();
		String sql = "select id,name,address,tel,reg_date from member2";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			Member2 mb = new Member2();
			mb.setId(rs.getString(1));
			mb.setName(rs.getString(2));
			mb.setAddress(rs.getString(3));
			mb.setTel(rs.getString(4));
			mb.setReg_date(rs.getDate(5));
			list.add(mb);
			
		}
		stmt.close();
		conn.close();
		return list;
	}
	
	public Member2 select(String id) throws SQLException {
		getConnection();
		String sql = "select * from member2 where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			Member2 mb = new Member2();
			mb.setId(rs.getString(1));
			mb.setPasswd(rs.getString(2));
			mb.setName(rs.getString(3));
			mb.setAddress(rs.getString(4));
			mb.setTel(rs.getString(5));
			mb.setReg_date(rs.getDate(6));
			
			rs.close();
			pstmt.close();
			conn.close();
			return mb;
		}
		pstmt.close();
		conn.close();
		return null;
	}
	
	public int confirm(String id) throws SQLException {
		String sql = "select * from member2 where id=?";
		conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) return 1;
		else return 0;
	}
	
	public boolean idCheck(String id) throws SQLException {
		getConnection();
		String idSql = "select id from member2 WHERE id = ?";
		PreparedStatement idmt = conn.prepareStatement(idSql);
		idmt.setString(1, id);
		ResultSet idchk = idmt.executeQuery();
		return idchk.next();
	}
	
	public int update(Member2 member) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		conn = getConnection();
		String sql = "update member2 set passwd=?,name=?,address=?,tel=? where id=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, member.getPasswd());
		pstmt.setString(2, member.getName());
		pstmt.setString(3, member.getAddress());
		pstmt.setString(4, member.getTel());
		pstmt.setString(5, member.getId());
		result = pstmt.executeUpdate();
		if(result > 0) return 1;
		else return 0;
		
	}
	
	public int delete(String id, String passwd) throws SQLException {
		int result = 0;
		Member2 m = new Member2();
		String sql = "delete from member2 where id=? and passwd=?";
		conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, passwd);
		result = pstmt.executeUpdate();
		if(result > 0) {
			return 1;
		} else
			return 0;
		
		
	}
	
}

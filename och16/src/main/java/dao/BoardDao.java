package dao;

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

public class BoardDao {
	private static BoardDao instance;
	
	private BoardDao() {
		
	}
	
	public static BoardDao getInstance() {
		if(instance == null) {
			instance = new BoardDao();
		}
		return instance;
	}
	
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection(); 
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	public int getTotalCnt() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int tot = 0;
		String sql = "select count(*) from board";
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) tot = rs.getInt(1);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}
		return tot;
	}

	public List<Board> boardList(int startRow, int endRow) throws SQLException {
		List<Board> list = new ArrayList<Board>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select *FROM"
	               + "(select rownum rn ,a.* from "
	               + "(SELECT * FROM board Order by ref desc,re_step) a)"
	               + "where rn between ? And ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					Board board = new Board();
					board.setNum(rs.getInt("num"));
					board.setSubject(rs.getString("subject"));
					board.setWriter(rs.getString("writer"));
					board.setContent(rs.getString("content"));
					board.setEmail(rs.getString("email"));
					board.setIp(rs.getString("ip"));
					board.setReg_date(rs.getDate("reg_date"));
					board.setReadcount(rs.getInt("readcount"));
					board.setPasswd(rs.getString("passwd"));
					board.setRef(rs.getInt("ref"));
					board.setRe_step(rs.getInt("re_step"));
					board.setRe_level(rs.getInt("re_level"));
					list.add(board);
				} while(rs.next());
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return list;
	}

	public void readCount(int num) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = "update board set readcount=readcount+1 where num=?";
		try {
			Board board = new Board();
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
	}

	public Board select(int num) throws SQLException {
		Board board = new Board();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from board where num=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				
				board.setNum(num);
				board.setSubject(rs.getString("subject"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setEmail(rs.getString("email"));
				board.setIp(rs.getString("ip"));
				board.setReg_date(rs.getDate("reg_date"));
				board.setReadcount(rs.getInt("readcount"));
				board.setPasswd(rs.getString("passwd"));
				board.setRef(rs.getInt("ref"));
				board.setRe_step(rs.getInt("re_step"));
				board.setRe_level(rs.getInt("re_level"));
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return board;
	}
	
	
	
	
	
	
	
	
	
}

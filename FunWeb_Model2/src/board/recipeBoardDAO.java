package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class recipeBoardDAO {
	
	public Connection getConnection() {

		String dbUrl = "jdbc:mysql://localhost:3306/jspdb5";
		String dbUser = "root";
		String dbPass = "1234";
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return con;
	}
	
	public void insertBoard(BoardBean bb) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			con = getConnection();
			String sql = "select max(num) from recipeBoard";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if(rs.next()){
				bb.setNum(rs.getInt("max(num)") + 1);
			}

			sql = "insert into recipeBoard(num,name,subject,content,readcount,date) values(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, bb.getNum());
			pstmt.setString(2, bb.getName());
			pstmt.setString(3, bb.getSubject());
			pstmt.setString(4, bb.getContent());
			pstmt.setInt(5, bb.getReadcount());
			pstmt.setTimestamp(6, bb.getDate());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs != null) try {	rs.close();} catch (SQLException e) {}
			if(pstmt != null) try {	pstmt.close();} catch (SQLException e2) {}
			if(con != null) try{ con.close();} catch (SQLException e3) {}
		}
		
	}
	
	public List<BoardBean> getBoardList(int startRow, int pageSize) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardBean> list = new ArrayList<BoardBean>();
		
	
		try {
			con = getConnection();
			String sql = "select * from recipeBoard order by num desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardBean bb = new BoardBean();
				bb.setNum(rs.getInt("num"));
				bb.setName(rs.getString("name"));
				bb.setSubject(rs.getString("subject"));
				bb.setContent(rs.getString("content"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setDate(rs.getTimestamp("date"));
				list.add(bb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {	rs.close();} catch (SQLException e) {}
			if(pstmt != null) try {	pstmt.close();} catch (SQLException e2) {}
			if(con != null) try{ con.close();} catch (SQLException e3) {}
		}
		
		return list;	
	}
	
	public List<BoardBean> getBoardReadCountList(int startRow, int pageSize) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardBean> list = new ArrayList<BoardBean>();
		
	
		try {
			con = getConnection();
			String sql = "select * from recipeBoard order by readcount desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardBean bb = new BoardBean();
				bb.setNum(rs.getInt("num"));
				bb.setName(rs.getString("name"));
				bb.setSubject(rs.getString("subject"));
				bb.setContent(rs.getString("content"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setDate(rs.getTimestamp("date"));
				list.add(bb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {	rs.close();} catch (SQLException e) {}
			if(pstmt != null) try {	pstmt.close();} catch (SQLException e2) {}
			if(con != null) try{ con.close();} catch (SQLException e3) {}
		}
		
		return list;	
	}

	public List<BoardBean> getBoardList(int startRow, int pageSize, String search) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardBean> list = new ArrayList<BoardBean>();
		
	
		try {
			con = getConnection();
			String sql = "select * from recipeBoard where subject like ? order by num desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, startRow-1);
			pstmt.setInt(3, pageSize);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardBean bb = new BoardBean();
				bb.setNum(rs.getInt("num"));
				bb.setName(rs.getString("name"));
				bb.setSubject(rs.getString("subject"));
				bb.setContent(rs.getString("content"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setDate(rs.getTimestamp("date"));
				list.add(bb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {	rs.close();} catch (SQLException e) {}
			if(pstmt != null) try {	pstmt.close();} catch (SQLException e2) {}
			if(con != null) try{ con.close();} catch (SQLException e3) {}
		}
		
		return list;	
	}

	public BoardBean getBoard(int num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardBean bb = new BoardBean();
		
		try {
			con = getConnection();
			String sql = "select * from recipeBoard where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
			bb.setNum(rs.getInt("num"));
			bb.setName(rs.getString("name"));
			bb.setSubject(rs.getString("subject"));
			bb.setContent(rs.getString("content"));
			bb.setDate(rs.getTimestamp("date"));
			bb.setReadcount(rs.getInt("readcount"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(rs != null) try {	rs.close();} catch (SQLException e) {}
			if(pstmt != null) try {	pstmt.close();} catch (SQLException e2) {}
			if(con != null) try{ con.close();} catch (SQLException e3) {}
		}	
		return bb;		
	}
	
	public void updateBoard(BoardBean bb) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			con = getConnection();
			String sql = "update recipeBoard set name=?,subject=?,content=? where num=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, bb.getName());
			pstmt.setString(2, bb.getSubject());
			pstmt.setString(3, bb.getContent());
			pstmt.setInt(4, bb.getNum());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	finally {
			if(pstmt != null) try {	pstmt.close();} catch (SQLException e2) {}
			if(con != null) try{ con.close();} catch (SQLException e3) {}
		} 
	}
	
	public void updateReadcount(int num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			con = getConnection();
			String sql = "update recipeBoard set readcount=readcount+1 where num=?";		
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, num);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(pstmt != null) try {	pstmt.close();} catch (SQLException e2) {}
			if(con != null) try{ con.close();} catch (SQLException e3) {}
		}
	}
	
	public void deleteBoard(int num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			con = getConnection();
			String sql = "delete from recipeBoard where num=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pstmt != null) try {	pstmt.close();} catch (SQLException e2) {}
			if(con != null) try{ con.close();} catch (SQLException e3) {}
		}
	}
	
	public int numCheck(int num, String pass) {
		
		int check = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		
		try {
			String sql = "select * from recipeBoard where num=?";
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				check = 0; // 비밀번호 불일치 = 0
				if(pass.equals(rs.getString("pass"))){
					check = 1; // 비밀번호 일치 = 1
				}
			}
			else {
				check = -1; // num없을시 = -1
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs != null) try {	rs.close();} catch (SQLException e) {}
			if(pstmt != null) try {	pstmt.close();} catch (SQLException e) {}
			if(con != null) try{ con.close();} catch (SQLException e2) {}
		}					
		return check;
	}
	
	public int getBoardCount() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		
			try {
				con = getConnection();
				String sql = "select count(*) from recipeBoard";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					count = rs.getInt("count(*)");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	finally {
				if(rs != null) try {	rs.close();} catch (SQLException e) {}
				if(pstmt != null) try {	pstmt.close();} catch (SQLException e) {}
				if(con != null) try{ con.close();} catch (SQLException e2) {}
			}	 
			
			return count;
	}
	
		public int getBoardCount(String search) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		
			try {
				con = getConnection();
				String sql = "select count(*) from recipeBoard where subject like ? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
				rs = pstmt.executeQuery();
				if(rs.next()) {
					count = rs.getInt("count(*)");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	finally {
				if(rs != null) try {	rs.close();} catch (SQLException e) {}
				if(pstmt != null) try {	pstmt.close();} catch (SQLException e) {}
				if(con != null) try{ con.close();} catch (SQLException e2) {}
			}	 
			
			return count;
	}
	
}

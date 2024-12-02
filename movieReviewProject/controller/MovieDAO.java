package movieReviewProject.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import movieReviewProject.model.MovieVO;

public class MovieDAO {
	public static final String MOVIE_INSERT = "INSERT INTO MOVIES VALUES(MOVIE_SEQ.nextval, ?, ?, ?, ?)";
	public static final String MOVIE_SELECT = "SELECT * FROM MOVIES";
	public static final String MOVIE_GENRE_SELECT = "select title, release_date, duration, genre from Movies where genre = ?";
	public static final String callablGenreProcSQL = "{call movies_genre_list_proc(?, ?)}";
	public static final String MOVIE_UPDATE = "UPDATE MOVIES SET TITLE = ?, RELEASE_DATE = ?, DURATION = ?, GENRE = ? WHERE MOVIE_ID = ?";
	public static final String MOVIE_DELETE = "DELETE FROM MOVIES WHERE MOVIE_ID = ?";
	
	public static boolean MovieInsert(MovieVO mvo) throws SQLException {
		// Conection
		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		// 1 Load, 2. connect
		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(MOVIE_INSERT);
		pstmt.setString(1, mvo.getTitle());
		pstmt.setDate(2, mvo.getReleaseDate());
		pstmt.setInt(3, mvo.getDuration());
		pstmt.setString(4, mvo.getGenre());

		int result = pstmt.executeUpdate();
		System.out.println((result != 0) ? "입력성공" : "입력실패");

		DBUtility.dbClose(con, pstmt);
		successFlag = (result != 0) ? true : false;

		return successFlag;
	}

	public ArrayList<MovieVO> movieSelect(MovieVO mvo) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<MovieVO> movieList = new ArrayList<MovieVO>();

		con = DBUtility.dbCon();
		stmt = con.createStatement();
		rs = stmt.executeQuery(MOVIE_SELECT);

		while (rs.next()) {
			int movieId = rs.getInt("movie_id");
			String title = rs.getString("title");
			Date releaseDate = rs.getDate("release_date");
			int duration = rs.getInt("duration");
			String genre = rs.getString("genre");
			MovieVO movieVO = new MovieVO(movieId, title, releaseDate, duration, genre);
			movieList.add(movieVO);
		}

		DBUtility.dbClose(con, stmt, rs);
		return movieList;
	}

	public static void listMoviesByGenre(String genre) throws SQLException {
	    Connection con = null;
	    CallableStatement cstmt = null;
	    ResultSet rs = null;

	    try {
	        con = DBUtility.dbCon();
	        cstmt = con.prepareCall(callablGenreProcSQL);

	        // 프로시저 인수 설정
	        cstmt.setString(1, genre);
	        cstmt.registerOutParameter(2, java.sql.Types.REF_CURSOR);

	        // 프로시저 실행
	        cstmt.execute();

	        // 커서에서 결과 가져오기
	        rs = (ResultSet) cstmt.getObject(2);

	        // 결과 출력
	        while (rs.next()) {
	            String title = rs.getString("title");
	            Date releaseDate = rs.getDate("release_date");
	            int duration = rs.getInt("duration");
	            System.out.println("Title: " + title + ", Release Date: " + releaseDate + ", Duration: " + duration + " mins");
	        }
	    } finally {
	    	DBUtility.dbClose(con, cstmt, rs);
	    }
	}
	
	
	public static boolean movieUpdate(MovieVO mvo) throws SQLException {

		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(MOVIE_UPDATE);
		pstmt.setString(1, mvo.getTitle());
		pstmt.setDate(2, mvo.getReleaseDate());
		pstmt.setInt(3, mvo.getDuration());
		pstmt.setString(4, mvo.getGenre());
		pstmt.setInt(5, mvo.getMovieId());

		int count = pstmt.executeUpdate();
		successFlag = (count != 0) ? (true) : false;

		DBUtility.dbClose(con, pstmt);
		return successFlag;
	}

	public static boolean movieDelete(MovieVO mvo) {
		boolean successFlag = false;
		Connection con = null; // 오라클에 DB접속
		PreparedStatement pstmt = null; // 오라클에서 작업할 쿼리문을 사용할 수 있게하는 명령문

		// 커밋을 수동으로 바꾼다
		try {
			con = DBUtility.dbCon();
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(MOVIE_DELETE);
			pstmt.setInt(1, mvo.getMovieId());
			int count = pstmt.executeUpdate();
			successFlag = (count != 0) ? true : false;
			if (count != 0) {
				successFlag = true;
				// 삭제성공시 커밋기능을 부여한다
				con.commit();
			} else {
				successFlag = false;
				// 삭제실패시 롤백기능을 부여한다.
				con.rollback();
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag;
	}
}

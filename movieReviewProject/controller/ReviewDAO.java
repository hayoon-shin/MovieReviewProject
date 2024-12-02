package movieReviewProject.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import movieReviewProject.model.ReviewSelectVO;
import movieReviewProject.model.ReviewVO;

public class ReviewDAO {
	public static final String REVIEW_INSERT = "INSERT INTO REVIEWS (REVIEW_ID, WATCH_ID, RATING, DESCRIPTION) VALUES(REVIEWS_SEQ.NEXTVAL, ?, ?, ?)";
	public static final String REVIEW_SELECT_ALL = "SELECT R.REVIEW_ID, M.TITLE, M.GENRE, R.RATING, R.DESCRIPTION, W.WATCH_DATE, R.AVG "
			+ "FROM REVIEWS R " + "JOIN WATCH_HISTORY W ON R.WATCH_ID = W.WATCH_ID "
			+ "JOIN MOVIES M ON W.MOVIE_ID = M.MOVIE_ID " + "ORDER BY M.TITLE";
	public static final String REVIEW_SELECT = "SELECT R.REVIEW_ID, M.TITLE, M.GENRE, R.RATING, R.DESCRIPTION, W.WATCH_DATE, R.AVG "
			+ "FROM REVIEWS R " + "JOIN WATCH_HISTORY W ON R.WATCH_ID = W.WATCH_ID "
			+ "JOIN MOVIES M ON W.MOVIE_ID = M.MOVIE_ID " + "WHERE M.TITLE = ? " + "ORDER BY R.RATING";
	public static final String REVIEW_UPDATE = "UPDATE REVIEWS SET RATING = ?, DESCRIPTION = ? WHERE REVIEW_ID = ?";
	public static final String REVIEW_DELETE = "DELETE FROM REVIEWS WHERE REVIEW_ID = ?";

	public boolean reviewInsert(ReviewVO rvo) {
		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtility.dbCon();
		try {
			pstmt = con.prepareStatement(REVIEW_INSERT);
			pstmt.setInt(1, rvo.getWatchID());
			pstmt.setInt(2, rvo.getRating());
			pstmt.setString(3, rvo.getDescription());

			int result = pstmt.executeUpdate();
			successFlag = (result != 0) ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag;
	}

	public ArrayList<ReviewSelectVO> reviewSelectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<ReviewSelectVO> reviewList = new ArrayList<ReviewSelectVO>();
		con = DBUtility.dbCon();

		try {
			pstmt = con.prepareStatement(REVIEW_SELECT_ALL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					int review_id = rs.getInt("REVIEW_ID");
					String title = rs.getString("TITLE");
					String genre = rs.getString("GENRE");
					int rating = rs.getInt("RATING");
					String description = rs.getString("DESCRIPTION");
					Date watchDate = rs.getDate("WATCH_DATE");
					double avg = rs.getDouble("AVG");
					ReviewSelectVO rvo = new ReviewSelectVO(review_id, title, genre, rating, description, watchDate,
							avg);
					reviewList.add(rvo);
				} while (rs.next());
			} else {
				reviewList = null;
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt, rs);
		}
		return reviewList;
	}

	public ArrayList<ReviewSelectVO> reviewSelect(String titleSearch) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReviewSelectVO> reviewList = new ArrayList<ReviewSelectVO>();
		con = DBUtility.dbCon();
		try {
			pstmt = con.prepareStatement(REVIEW_SELECT);
			pstmt.setString(1, titleSearch);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					int review_id = rs.getInt("REVIEW_ID");
					String title = rs.getString("TITLE");
					String genre = rs.getString("GENRE");
					int rating = rs.getInt("RATING");
					String description = rs.getString("DESCRIPTION");
					Date watchDate = rs.getDate("WATCH_DATE");
					double avg = rs.getDouble("AVG");
					ReviewSelectVO rvo = new ReviewSelectVO(review_id, title, genre, rating, description, watchDate,
							avg);
					reviewList.add(rvo);
				} while (rs.next());
			} else {
				reviewList = null;
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt, rs);
		}
		return reviewList;
	}

	public boolean reviewUpdate(ReviewVO rvo, int reviewID) {
		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtility.dbCon();
		try {
			pstmt = con.prepareStatement(REVIEW_UPDATE);
			pstmt.setInt(1, rvo.getRating());
			pstmt.setString(2, rvo.getDescription());
			pstmt.setInt(3, reviewID);

			int result = pstmt.executeUpdate();
			successFlag = (result != 0) ? true : false;
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag;
	}

	public boolean reviewDelete(ReviewVO rvo) {
		boolean successFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtility.dbCon();
		try {
			pstmt = con.prepareStatement(REVIEW_DELETE);
			pstmt.setInt(1, rvo.getReviewID());
			int result = pstmt.executeUpdate();
			successFlag = (result != 0) ? true : false;
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag;
	}
}

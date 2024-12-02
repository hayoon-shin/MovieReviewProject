package movieReviewProject.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import movieReviewProject.model.WatchHistoryAllVO;
import movieReviewProject.model.WatchHistoryVO;


public class WatchHistoryDAO {	
	public final String WATCH_HISTORY_SELECT_ALL = "SELECT w.watch_id, w.user_id, w.movie_id, w.watch_date, (SELECT COUNT(*) FROM Watch_History w2 WHERE w2.movie_id = w.movie_id) AS watch_count FROM Watch_History w";
	public final String WATCH_HISTORY_SELECT = "select W.watch_id, W.user_id, W.movie_id, M.title, M.release_date, M.duration, M.genre, watch_date, watch_count  from watch_history W inner join Movies M on W.movie_id = M.movie_id WHERE W.user_id = ?";
    public final String WATCH_HISTORY_INSERT = "INSERT INTO WATCH_HISTORY VALUES(WATCH_HISTORY_SEQ.NEXTVAL, ?, ?, SYSDATE, ?)";
    public final String WATCH_HISTORY_UPDATE = "UPDATE WATCH_HISTORY SET user_id = ?, movie_id = ? WHERE watch_id = ?";
    public final String WATCH_HISTORY_DELETE = "DELETE FROM WATCH_HISTORY WHERE watch_id = ?";
    public final String WATCH_HISTORY_COUNT = "SELECT w.watch_id, w.user_id, w.movie_id, w.watch_date, (SELECT COUNT(*) FROM Watch_History w2 WHERE w2.movie_id = w.movie_id) AS watch_count FROM Watch_History w";
	
    public ArrayList<WatchHistoryAllVO> watchHistorySelect(String userId){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<WatchHistoryAllVO> watchList = new ArrayList<WatchHistoryAllVO>();

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(WATCH_HISTORY_SELECT);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do{
					
					int watchID = rs.getInt("WATCH_ID");
					String userID = rs.getString("USER_ID");
					int movieID = rs.getInt("MOVIE_ID");
					Date watchDate = rs.getDate("WATCH_DATE");
					int watchCount = rs.getInt("WATCH_COUNT");
					String title = rs.getString("TITLE");
					Date releaseDate = rs.getDate("RELEASE_DATE");
					int duration = rs.getInt("DURATION");
					String genre = rs.getString("GENRE");
					
					WatchHistoryAllVO wallvo = new WatchHistoryAllVO(watchID, userID, movieID, title, releaseDate, duration, genre, watchDate, watchCount);
					watchList.add(wallvo);
					
				}while (rs.next());
			}else {
				watchList = null; 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtility.dbClose(con, pstmt, rs);
		return watchList;
	}
	
	public ArrayList<WatchHistoryVO> watchHistorySelectAll(){
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<WatchHistoryVO> watchList = new ArrayList<WatchHistoryVO>();

		try {
			con = DBUtility.dbCon();
			stmt = con.createStatement();
			rs = stmt.executeQuery(WATCH_HISTORY_SELECT_ALL);
			if(rs.next()) {
				do{
					int watchID = rs.getInt("watch_id");
					String userID = rs.getString("user_id");
					int movieID = rs.getInt("movie_id");
					Date watchDate = rs.getDate("watch_date");
					int watchCount = rs.getInt("watch_count");
					
					WatchHistoryVO wvo = new WatchHistoryVO(watchID, userID, movieID, watchDate, watchCount);
					watchList.add(wvo);
				}while (rs.next());
			}else {
				watchList = null; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtility.dbClose(con, stmt, rs);
		return watchList;
	}
	
	public int watchHistoryInsert(WatchHistoryVO wvo) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    int watchCount = -1; // 삽입 후 조회된 watch_count를 저장할 변수

	    try {
	        con = DBUtility.dbCon();
	        con.setAutoCommit(false);

	        // INSERT 작업
	        pstmt = con.prepareStatement(WATCH_HISTORY_INSERT);
	        pstmt.setString(1, wvo.getUser_id());
	        pstmt.setInt(2, wvo.getMovie_id());
	        pstmt.setInt(3, wvo.getWatch_count());

	        int result1 = pstmt.executeUpdate();
	        if (result1 > 0) {
	            con.commit(); // 성공적으로 커밋

	            // INSERT 후 WATCH_HISTORY_COUNT 쿼리를 이용해 watch_count 조회
	            pstmt.close(); // 기존 pstmt 닫기
	            pstmt = con.prepareStatement(
	                "SELECT (SELECT COUNT(*) FROM WATCH_HISTORY w2 WHERE w2.movie_id = ?) AS watch_count FROM DUAL"
	            );
	            pstmt.setInt(1, wvo.getMovie_id());
	            rs = pstmt.executeQuery();

	            if (rs.next()) {
	                watchCount = rs.getInt("watch_count");
	            }
	        } else {
	            con.rollback(); // 실패 시 롤백
	            System.out.println("Transaction rolled back.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (con != null) con.rollback();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    } finally {
	        DBUtility.dbClose(con, pstmt, rs);
	    }

	    return watchCount; // 최종 watch_count 반환
	}

	public boolean watchHistoryUpdate(WatchHistoryVO svo)  {
		boolean successFlag = false; 
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtility.dbCon();
		try {
			pstmt = con.prepareStatement(WATCH_HISTORY_UPDATE);
			pstmt.setString(1, svo.getUser_id());
			pstmt.setInt(2, svo.getMovie_id());
			pstmt.setInt(3, svo.getWatch_id());
			int result1 = pstmt.executeUpdate();
			
			successFlag = (result1 != 0) ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		
		return successFlag; 
	}

	public boolean watchHistoryDelete(WatchHistoryVO svo) throws SQLException {
		boolean successFlag =false; 
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtility.dbCon();
		pstmt = con.prepareStatement(WATCH_HISTORY_DELETE);
		pstmt.setInt(1, svo.getWatch_id());
	
		int result = pstmt.executeUpdate();
		successFlag = (result != 0) ? true : false ;

		DBUtility.dbClose(con, pstmt);
		return successFlag; 
	}


}
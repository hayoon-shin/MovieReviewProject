package movieReviewProject.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import movieReviewProject.model.UsersVO;

public class UsersDAO {
	
	public final String USERS_INSERT = "INSERT INTO USERS VALUES( ?, ?, ?, ?)";
	public final String USERS_SELECT_ALL = "SELECT * FROM USERS"; 
	public final String USERS_SELECT = "SELECT * FROM USERS WHERE USER_ID = ?"; 
	public final String USERS_UPDATE = "UPDATE USERS SET USERNAME = ?, PASSWORD = ?, EMAIL = ? WHERE USER_ID = ?";
	public final String USERS_DELETE = "DELETE FROM USERS WHERE USER_ID = ?";
	public final String USERS_ID_TRIGGER = "INSERT INTO USERS VALUES( '', ?, ?, ?)";
	public final String USERS_FINDPW_PROC = "{call FIND_PASSWORD_PROC(?,?)}";
	public final String USERS_FINDPW_FUNC = "SELECT FIND_PASSWORD_FUNC(?, ?) AS password FROM DUAL";

	//테이블 전체 출력
	public ArrayList<UsersVO> userSelectAll(UsersVO lvo) {
		Connection con = null; 				
		PreparedStatement pstmt = null; 	
		ResultSet rs = null;
		ArrayList<UsersVO> usersList = new ArrayList<UsersVO>();
		
		try {
			con = DBUtility.dbCon(); 
			pstmt = con.prepareStatement(USERS_SELECT_ALL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String userid = rs.getString("USER_ID");
				String userName = rs.getString("USERNAME");
				String password = rs.getString("PASSWORD");
				String email = rs.getString("EMAIL");
				UsersVO usersVO = new UsersVO(userid, userName, password, email);
				usersList.add(usersVO);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt, rs);
		}
		
		return usersList;
	}

	//테이블에서 데이터삭제
	public boolean userDelete(UsersVO uvo) {
        Connection con = null; 
        PreparedStatement pstmt = null; 
        boolean successFlag = false;
        try {
            con = DBUtility.dbCon();
            pstmt = con.prepareStatement(USERS_DELETE);
            pstmt.setString(1, uvo.getUserID());
            int count = pstmt.executeUpdate();
            
            successFlag = (count != 0)? true : false;
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            DBUtility.dbClose(con, pstmt);
        }

        return successFlag;

    }

	//테이블에서 데이터입력
	public boolean userInsert(UsersVO uvo) {
		Connection con = null; 				
		PreparedStatement pstmt = null; 	
		boolean successFlag = false; 
		
		try {
			con = DBUtility.dbCon(); 
			pstmt = con.prepareStatement(USERS_INSERT);
			pstmt.setString(1, uvo.getUserID());
			pstmt.setString(2, uvo.getUserName()); 
			pstmt.setString(3, uvo.getPassword()); 
			pstmt.setString(4, uvo.getEmail()); 
	
			int count = pstmt.executeUpdate();
			successFlag = (count != 0)?(true):(false); 
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag ;
	}
	
	//테이블에서 데이터입력
	public boolean userAutoInsert(UsersVO uvo) {
		Connection con = null; 				
		PreparedStatement pstmt = null; 	
		boolean successFlag = false; 
		
		try {
			con = DBUtility.dbCon(); 
			pstmt = con.prepareStatement(USERS_ID_TRIGGER);
			pstmt.setString(1, uvo.getUserName()); 
			pstmt.setString(2, uvo.getPassword()); 
			pstmt.setString(3, uvo.getEmail()); 	
			int count = pstmt.executeUpdate();
			successFlag = (count != 0)?(true):(false); 
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag ;
	}

	//테이블에서 데이터수정
	public boolean userUpdate(UsersVO uvo) {
		Connection con = null; 				
		PreparedStatement pstmt = null; 	
		boolean successFlag = false; 
		try {
			con = DBUtility.dbCon(); 
			pstmt = con.prepareStatement(USERS_UPDATE);
			pstmt.setString(1, uvo.getUserName());
			pstmt.setString(2, uvo.getPassword()); 
			pstmt.setString(3, uvo.getEmail()); 
			pstmt.setString(4, uvo.getUserID()); 

			int count = pstmt.executeUpdate();
			successFlag = (count != 0)?(true):(false); 
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag ;
	}
	
	//테이블 선택 출력
	public ArrayList<UsersVO> userSelect(UsersVO uvo) {
		Connection con = null; 				
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<UsersVO> usersList = new ArrayList<UsersVO>();
		boolean successFlag = false; 
		
		try {
			con = DBUtility.dbCon(); 
			pstmt = con.prepareStatement(USERS_SELECT);
			pstmt.setString(1, uvo.getUserID());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String userid = rs.getString("USER_ID");
				String userName = rs.getString("USERNAME");
				String password = rs.getString("PASSWORD");
				String email = rs.getString("EMAIL");
				UsersVO usersVO = new UsersVO(userid, userName, password, email);
				usersList.add(usersVO);
			}

			int count = pstmt.executeUpdate();
			successFlag = (count != 0)?(true):(false); 
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt, rs);
		}
		return usersList ;
	}
	
	//비밀번호 찾기(PROC)
		public boolean userFindPasswordPROC(UsersVO uvo) {
			Connection con = null; 	
			CallableStatement cstmt = null;	
			boolean successFlag = false; 
			try {
				con = DBUtility.dbCon(); 
				cstmt = con.prepareCall(USERS_FINDPW_PROC);
				cstmt.setString(1, uvo.getUserID()); 
				cstmt.setString(2, uvo.getEmail()); 

				int count = cstmt.executeUpdate();
				successFlag = (count != 0)?(true):(false); 
			} catch (SQLException e) {
				System.out.println(e.toString());
			} finally {
				DBUtility.dbClose(con, cstmt);
			}
			return successFlag ;
		}
		
		//비밀번호 찾기(FUNC)
		public boolean userFindPasswordFUNC(UsersVO uvo) {
			Connection con = null; 	
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			boolean successFlag = false; 
			try {
				con = DBUtility.dbCon();
				pstmt = con.prepareStatement(USERS_FINDPW_FUNC);
				pstmt.setString(1, uvo.getUserID()); 
				pstmt.setString(2, uvo.getEmail()); 
				rs = pstmt.executeQuery();
				if(rs.next()) {
					String password = rs.getString("password");
					System.out.println("비밀번호는 :" +password+" 입니다.");
				}
				
				int count = pstmt.executeUpdate();
				successFlag = (count != 0)?(true):(false); 
			} catch (SQLException e) {
				System.out.println(e.toString());
			} finally {
				DBUtility.dbClose(con, pstmt, rs);
			}
			return successFlag ;
		}
		
	
}
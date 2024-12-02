package movieReviewProject.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import movieReviewProject.model.WatchHistoryAllVO;
import movieReviewProject.model.WatchHistoryVO;

public class WatchHistoryRegisterManager {
	public static Scanner sc = new Scanner(System.in);

	// 전체 학생리스트를 출력요청
	public void selectAllManager() throws SQLException {
		WatchHistoryDAO wd = new WatchHistoryDAO();
		ArrayList<WatchHistoryVO> watchList = new ArrayList<WatchHistoryVO>();
		watchList = wd.watchHistorySelectAll();
		if (watchList == null) {
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		printWatchHistoryList(watchList);
	}

	public void selectManager() throws SQLException {
		WatchHistoryDAO wd = new WatchHistoryDAO();
		ArrayList<WatchHistoryAllVO> watchList2 = new ArrayList<WatchHistoryAllVO>();
		System.out.print("검색할 사용자 ID(user_id)를 입력하세요: ");
        String userId = sc.nextLine().trim();
		watchList2 = wd.watchHistorySelect(userId);
		if (watchList2 == null) {
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		
		printWatchHistoryList2(watchList2);
	}

	public void insertManager() {
	    WatchHistoryDAO wd = new WatchHistoryDAO();

	    System.out.println("시청기록 입력");

	    System.out.print("userid >> ");
	    String userID = sc.nextLine().trim();
	    System.out.print("movieid >> ");
	    int movieID = Integer.parseInt(sc.nextLine().trim());

	    WatchHistoryVO wvo = new WatchHistoryVO(userID, movieID);

	    int updatedCount = wd.watchHistoryInsert(wvo);

	    if (updatedCount == -1) {
	        System.out.println("입력처리 실패");
	    } else {
	        System.out.println("입력처리 성공");
	        System.out.println("현재 영화의 총 시청 횟수: " + updatedCount);
	    }
	}

	public void updateManager() throws SQLException {
		WatchHistoryDAO wd = new WatchHistoryDAO();

		System.out.print("수정할 시청 번호(watch_id)를 입력하세요: ");
		int watchID = Integer.parseInt(sc.nextLine());
		System.out.print("새로운 아이디(user_id)를 입력하세요: ");
		String userID = sc.nextLine();
		System.out.print("새로운 영화 번호(movie_id)를 입력하세요: ");
		int movieID = Integer.parseInt(sc.nextLine());
		WatchHistoryVO wvo = new WatchHistoryVO(watchID, userID, movieID);
		boolean successFlag = wd.watchHistoryUpdate(wvo);

		if (successFlag == true) {
			System.out.println("입력처리 성공");
		} else {
			System.out.println("입력처리 실패");
		}
	}

	public void deleteManager() throws SQLException {
		WatchHistoryDAO wd = new WatchHistoryDAO();

		System.out.print("삭제할 시청 번호를 입력하세요: ");
		int watchId = Integer.parseInt(sc.nextLine());
		WatchHistoryVO wvo = new WatchHistoryVO();
		wvo.setWatch_id(watchId);
		boolean successFlag = wd.watchHistoryDelete(wvo);

		if (successFlag == true) {
			System.out.println("삭제처리 성공");
		} else {
			System.out.println("삭제처리 실패");
		}
	}

//	public  void sortManager() throws SQLException {
//		ArrayList<StudentVO> studentList = null;
//		studentList =StudentDAO.studentSort(); 
//		printStudentList(studentList);
//	}

	// 전체 학생리스트를 출력진행
	public void printWatchHistoryList(ArrayList<WatchHistoryVO> watchList) {
		System.out.println("============================================================================================");
		for (WatchHistoryVO wv : watchList) {
			System.out.println(wv.toString());
		}
		System.out.println("============================================================================================");
	}

	private void printWatchHistoryList2(ArrayList<WatchHistoryAllVO> watchList2) {
		System.out.println("============================================================================================");
		for (WatchHistoryAllVO wallv : watchList2) {
			System.out.println(wallv.toString());
		}
		System.out.println("============================================================================================");
	}

}
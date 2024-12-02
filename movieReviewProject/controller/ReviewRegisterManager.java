package movieReviewProject.controller;

import java.util.ArrayList;
import java.util.Scanner;

import movieReviewProject.model.ReviewSelectVO;
import movieReviewProject.model.ReviewVO;

public class ReviewRegisterManager {
	public static Scanner sc = new Scanner(System.in);

	public void insertManager() {
		ReviewDAO rdao = new ReviewDAO();
		int watchID;
		int rating;
		String description;
		int no;

		System.out.println("1. 리뷰 작성을 하기위해서는 시청기록번호가 필요합니다. (1.리뷰 작성 2.나가기)");
		System.out.print(">>");
		no = Integer.parseInt(sc.nextLine());
		if (no == 1) {
			System.out.print("시청기록 번호 >> ");
			watchID = Integer.parseInt(sc.nextLine());
			System.out.print("평점 (1-5) >> ");
			rating = Integer.parseInt(sc.nextLine());
			System.out.print("영화 리뷰(255자) >> ");
			description = sc.nextLine();
		} else if (no == 2) {
			return;
		} else {
			System.out.println("해당 메뉴에 번호로만 입력하세요");
			return;
		}

		ReviewVO rvo = new ReviewVO(watchID, rating, description);
		boolean successFlag = rdao.reviewInsert(rvo);

		if (successFlag == false) {
			System.out.println("리뷰등록 실패 시청기록번호, 평점(1-5), 리뷰 글자수 제한을 확인해 주세요");
			return;
		} else {
			System.out.println("리뷰가 성공적으로 등록되었습니다.");
		}
	}

	public void selectAllManager() {
		ReviewDAO rdao = new ReviewDAO();
		ArrayList<ReviewSelectVO> reviewList = new ArrayList<ReviewSelectVO>();

		reviewList = rdao.reviewSelectAll();
		if (reviewList == null) {
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		printReviewList(reviewList);
	}

	public void selectManager() {
		ReviewDAO rdao = new ReviewDAO();
		ArrayList<ReviewSelectVO> reviewList = new ArrayList<ReviewSelectVO>();

		System.out.print("리뷰목록을 원하는 영화의 제목을 입력 해주세요>>");
		String title = sc.nextLine();
		reviewList = rdao.reviewSelect(title);
		if (reviewList == null) {
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		printReviewList(reviewList);
	}

	public void updateManager() {
		ReviewDAO rdao = new ReviewDAO();
		ArrayList<ReviewSelectVO> reviewList = new ArrayList<ReviewSelectVO>();
		reviewList = rdao.reviewSelectAll();
		if (reviewList == null) {
			System.out.println("수정할 리뷰데이터가 존재하지 않습니다.");
			return;
		}
		System.out.print("수정할 리뷰 번호를 입력하세요: ");
		int reviewID = Integer.parseInt(sc.nextLine());
		System.out.print("수정된 평점을 입력하세요 (1-5): >> ");
		int rating = Integer.parseInt(sc.nextLine());
		System.out.print("수정된 영화에 대한 리뷰를 입력하세요 (255자) >> ");
		String description = sc.nextLine();

		ReviewVO rvo = new ReviewVO(rating, description);
		boolean successFlag = rdao.reviewUpdate(rvo, reviewID);

		if (successFlag == false) {
			System.out.println("리뷰수정 실패 리뷰번호, 평점(1-5), 리뷰 글자수 제한을 확인해 주세요");
			return;
		} else {
			System.out.println("리뷰가 성공적으로 수정되었습니다.");
		}
	}

	public void deleteManager() {
		ReviewDAO rdao = new ReviewDAO();

		ArrayList<ReviewSelectVO> reviewList = new ArrayList<ReviewSelectVO>();
		reviewList = rdao.reviewSelectAll();
		if (reviewList == null) {
			System.out.println("삭제할 리뷰데이터가 존재하지 않습니다.");
			return;
		}
		
		System.out.print("삭제할 리뷰 번호를 입력하세요: ");
		int reviewID = Integer.parseInt(sc.nextLine());
		ReviewVO rvo = new ReviewVO();
		rvo.setReviewID(reviewID);
		boolean successFlag = rdao.reviewDelete(rvo);

		if (successFlag == true) {
			System.out.println("리뷰가 성공적으로 삭제되었습니다.");
		} else {
			System.out.println("삭제처리 실패, 리뷰 번호를 확인해 주세요");
		}
	}

	private void printReviewList(ArrayList<ReviewSelectVO> reviewList) {
		System.out.println(
				"==============================================================================================");
		for (ReviewSelectVO rsvo : reviewList) {
			System.out.println(rsvo.toString());
		}
		System.out.println(
				"===============================================================================================");
	}
}

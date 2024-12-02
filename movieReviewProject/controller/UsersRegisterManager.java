package movieReviewProject.controller;

import java.util.ArrayList;
import java.util.Scanner;

import movieReviewProject.model.UsersVO;

public class UsersRegisterManager {
	public Scanner sc = new Scanner(System.in); 
	
	//회원가입
	public void insertManager() {
		UsersDAO udao = new UsersDAO(); 
		
		System.out.print("ID 입력>>");
		String userID= (sc.nextLine()).trim();

		System.out.print("이름 입력>>");
		String userName = (sc.nextLine()).trim();
		
		System.out.print("비밀번호 입력>>");
		String password = (sc.nextLine()).trim();
		
		System.out.print("이메일 입력>>");
		String email = (sc.nextLine()).trim();
		
		UsersVO uvo = new UsersVO(userID, userName, password, email);
		boolean successFlag = udao.userInsert(uvo);
		
		if(successFlag == true) {
			System.out.println("회원가입 성공");
		}else {
			System.out.println("회원가입 실패");
		}
	}
	
	//회원가입(ID 자동)
	public void insertAutoManager() {
		UsersDAO udao = new UsersDAO(); 
		
		System.out.print("이름 입력>>");
		String userName = (sc.nextLine()).trim();
		
		System.out.print("비밀번호 입력>>");
		String password = (sc.nextLine()).trim();
		
		System.out.print("이메일 입력>>");
		String email = (sc.nextLine()).trim();
		
		UsersVO uvo = new UsersVO(null, userName, password, email);
		boolean successFlag = udao.userInsert(uvo);
		
		if(successFlag == true) {
			System.out.println("회원가입 성공");
		}else {
			System.out.println("회원가입 실패");
		}
	}
	
	//전체 회원 출력
	public void selectAllManager() {
		UsersDAO udao = new UsersDAO(); 
		UsersVO uvo = new UsersVO();
		ArrayList<UsersVO> usersList = udao.userSelectAll(uvo);
		
		if(usersList.size() != 0) {
			printUserList(usersList);
		}else {
			System.out.println("출력할 데이터가 없습니다.");
		}
	} 

	//회원 탈퇴
	public void deleteManager() {
		UsersDAO udao = new UsersDAO();
		UsersVO uvo = new UsersVO();

		ArrayList<UsersVO> usersList = udao.userSelectAll(uvo); 
		if(usersList.size() != 0) {
			printUserList(usersList);
		}else {
			System.out.println("출력할 데이터가 없습니다.");
			return; 
		}
		
		System.out.print("삭제할 ID>>");
		String userID = (sc.nextLine()).trim();
		UsersVO uvo2 = new UsersVO();
		uvo2.setUserID(userID);
		boolean successFlag = udao.userDelete(uvo2);
		
		if(successFlag == true) {
			System.out.println("ID :"+userID+"를 삭제 하였습니다.");
		}else {
			System.out.println("삭제 실패하였습니다.");
		}
	} 
	
	//회원정보 수정
	public void updateManager() {
		UsersDAO udao = new UsersDAO();
		UsersVO uvo = new UsersVO();

		ArrayList<UsersVO> usersList = udao.userSelectAll(uvo); 
		if(usersList.size() != 0) {
			printUserList(usersList);
		}else {
			System.out.println("출력할 데이터가 없습니다.");
			return; 
		}
		
		System.out.print("수정할 ID선택>>");
		String userID = (sc.nextLine()).trim();
		
		System.out.print("수정할 이름 입력>>");
		String userName = (sc.nextLine()).trim();
		
		System.out.print("수정할 비밀번호 입력>>");
		String password = (sc.nextLine()).trim();
		
		System.out.print("수정할 이메일 입력>>");
		String email = (sc.nextLine()).trim();
		
		UsersVO uvo2 = new UsersVO(userID, userName, password, email);

		boolean successFlag = udao.userUpdate(uvo2); 
		
		if(successFlag == true) {
			System.out.println("ID :"+userID+"를 수정했습니다.");
		}else {
			System.out.println("수정 실패했습니다.");
		}
	}
	
	//회원 선택 출력
	public void selectManager() {
		UsersDAO udao = new UsersDAO();
		
		
		System.out.print("검색할 ID선택>>");
		String userID = (sc.nextLine()).trim();
		UsersVO uvo = new UsersVO();
		uvo.setUserID(userID);
		ArrayList<UsersVO> usersList = udao.userSelect(uvo);
		
		if(usersList != null) {
			System.out.println("ID :"+userID+"를 찾았습니다.");
			printUserList(usersList);
		}else {
			System.out.println("검색을 실패했습니다.");
		}
	}
	
	//회원 비번 검색
		public void findPWManager() {
			UsersDAO udao = new UsersDAO();
			
			System.out.print("검색할 ID선택>>");
			String userID = (sc.nextLine()).trim();
			System.out.print("검색할 email선택>>");
			String email = (sc.nextLine()).trim();
			
			UsersVO uvo = new UsersVO();
			uvo.setUserID(userID);
			uvo.setEmail(email);
			boolean successFlag = udao.userFindPasswordFUNC(uvo);
			
			if(successFlag == true) {
				System.out.println("검색 성공");
			}else {
				System.out.println("검색 실패");
			}
		}
	
	//화면출력
	private void printUserList(ArrayList<UsersVO> usersList) {
		System.out.println(
				"==============================================================================================");
		for( UsersVO data : usersList ) {
			System.out.println(data);
		}	
		System.out.println(
				"==============================================================================================");
	}


}





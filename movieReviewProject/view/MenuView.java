package movieReviewProject.view;

public class MenuView {
	// 메인 메뉴
	public static void mainMenuView() {
		System.out.println();
		System.out.println(" ==============");
		System.out.println("| 영화리뷰 프로그램 |");
		System.out.println("=================================================================================");
		System.out.println(String.format("%-12s | ","| 1. 회원 메뉴")+String.format("%-12s | ","2. 영화 메뉴")+String.format("%-12s | ","3. 시청 기록")+String.format("%-12s | ","4. 시청자 리뷰")+String.format("%-12s | ","5. 종료"));
		System.out.println("=================================================================================");
		System.out.print("번호 선택 >>");
	}

	// 회원 메뉴
	public static void usersMenuView() {
		System.out.println();
		System.out.println(" ========");
		System.out.println("| 회원 메뉴 |");
		System.out.println("=======================================================================================================================================================");
		System.out.println(String.format("%-12s | ","| 1. 회원가입")+String.format("%-12s | ","2. 회원가입(ID랜덤)")+String.format("%-12s | ","3. 전체회원 목록")+String.format("%-12s | ","3. 시청 기록")+String.format("%-12s | ","4. 회원정보 조회")+String.format("%-12s | ","5.  회원정보 수정")+String.format("%-12s | ","6.  회원 탈퇴")+String.format("%-12s | ","7.  비밀번호 찾기")+String.format("%-12s | ","8.  메인메뉴로 이동"));
		System.out.println("=======================================================================================================================================================");
		System.out.print("번호 선택 >>");
	}

	// 영화 메뉴
	public static void moviesMenuView() {
		System.out.println();
		System.out.println(" =========");
		System.out.println("| 영화 메뉴 |");
		System.out.println("=====================================================================================================");
		System.out.println(String.format("%-12s | ","| 1. 신규 영화 등록")+String.format("%-12s | ","2. 전체 영화 목록")+String.format("%-12s | ","3. 영화 장르 조회")+String.format("%-12s | ","4. 영화 정보 수정")+String.format("%-12s | ","5. 특정 영화 삭제")+String.format("%-12s | ","6. 메인메뉴로 이동"));
		System.out.println("=====================================================================================================");
		System.out.print("번호 선택 >>");
	}

	// 시청기록 메뉴
	public static void watchHistoryMenuView() {
		System.out.println();
		System.out.println("===========");
        System.out.println("| 시청 기록 |");
        System.out.println("==========================================================================================================");
        System.out.println(String.format("%-12s | ","| 1. 시청기록 추가")+String.format("%-12s | ","2. 전체 시청 목록 조회")+String.format("%-12s | ","3. 해당 시청 목록 조회")+String.format("%-12s | ","4. 시청 기록 수정")+String.format("%-12s | ","5. 시청 기록 삭제")+String.format("%-12s | ","6. 메인메뉴로 이동"));
        System.out.println("==========================================================================================================");
        System.out.print("번호 선택 >>");
	}

	// 리뷰 메뉴
	public static void reviewMenuView() {
		System.out.println();
		System.out.println("============");
        System.out.println("| 시청자 리뷰 |");
        System.out.println("======================================================================================================");
        System.out.println(String.format("%-12s | ","| 1. 리뷰 작성")+String.format("%-12s | ","2. 전체 리뷰 조회")+String.format("%-12s | ","3. 해당 영화 리뷰 조회")+String.format("%-12s | ","4. 리뷰 수정")+String.format("%-12s | ","5. 리뷰 삭제")+String.format("%-12s | ","6. 메인메뉴로 이동"));
        System.out.println("======================================================================================================");
        System.out.print("번호 선택 >>");
	}

}

package movieReviewProject.java;

import java.sql.SQLException;
import java.util.Scanner;

import movieReviewProject.controller.MovieRegisterManager;
import movieReviewProject.controller.ReviewRegisterManager;
import movieReviewProject.controller.UsersRegisterManager;
import movieReviewProject.controller.WatchHistoryRegisterManager;
import movieReviewProject.view.MenuChoice;
import movieReviewProject.view.MenuView;
import movieReviewProject.view.MoviesChoice;
import movieReviewProject.view.ReviewChoice;
import movieReviewProject.view.UserChoice;
import movieReviewProject.view.WatchHistoryChoice;

public class MovieReviewMain {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		int no;
		boolean exitFlag = false;
		while (!exitFlag) {
			MenuView.mainMenuView();
			no = Integer.parseInt(sc.nextLine());
			switch (no) {
			case MenuChoice.USER_CHOICE:
				userMenu();
				break;
			case MenuChoice.MOVIES_CHOICE:
				moviesMenu();
				break;
			case MenuChoice.WATCH_HISTORY_CHOICE:
				watchHistoryMenu();
				break;
			case MenuChoice.REVIEW_CHOICE:
				reviewMenu();
				break;
			case MenuChoice.EXIT:
				System.out.println("프로그램을 종료합니다.");
				exitFlag = true;
				break;
			default:
				System.out.println("해당 메뉴에 번호로만 입력하세요");
			}
		}
	}

	private static void userMenu() {
		int no;
		boolean exitFlag = false;
		UsersRegisterManager urm = new UsersRegisterManager();

		while (!exitFlag) {
			MenuView.usersMenuView();
			no = Integer.parseInt(sc.nextLine());

			switch (no) {
			case UserChoice.USER_INSERT:
				System.out.println("");
				urm.insertManager();
				break;
			case UserChoice.USER_AUTO_INSERT:
				System.out.println("");
				urm.insertAutoManager();
				break;
			case UserChoice.USER_SELECT_ALL:
				System.out.println("");
				urm.selectAllManager();
				break;
			case UserChoice.USER_SELECT:
				System.out.println("");
				urm.selectManager();
				break;
			case UserChoice.USER_UPDATE:
				System.out.println("");
				urm.updateManager();
				break;
			case UserChoice.USER_DELETE:
				System.out.println("");
				urm.deleteManager();
				break;
			case UserChoice.USER_FIND_PW:
				System.out.println("");
				urm.findPWManager();
				break;
			case UserChoice.MAIN_MENU:
				return;
			default:
				System.out.println("해당 메뉴에 번호로만 입력하세요");
			}
		}
	}

	private static void moviesMenu() throws SQLException {
		int no;
		boolean exitFlag = false;
		MovieRegisterManager mrm = new MovieRegisterManager();

		while (!exitFlag) {
			MenuView.moviesMenuView();
			no = Integer.parseInt(sc.nextLine());

			switch (no) {
			case MoviesChoice.MOVIE_INSERT:
				mrm.InsertManager();
				continue;
			case MoviesChoice.MOVIE_SELECT_ALL:
				mrm.selectManager();
				continue;
			case MoviesChoice.MOVIE_SELECT:
				mrm.genreSelectManager();
				continue;
			case MoviesChoice.MOVIE_UPDATE:
				mrm.updateManager();
				continue;
			case MoviesChoice.MOVIE_DELETE:
				mrm.deleteManager();
				continue;
			case MoviesChoice.MAIN_MENU:
				exitFlag = true;
				break;
			default:
				System.out.println("해당 메뉴에 번호로만 입력하세요");
			}
		}
	}

	private static void watchHistoryMenu() throws SQLException {
		int no;
		boolean exitFlag = false;
		WatchHistoryRegisterManager whrm = new WatchHistoryRegisterManager();

		while (!exitFlag) {
			MenuView.watchHistoryMenuView();
			no = Integer.parseInt(sc.nextLine());

			switch (no) {
			case WatchHistoryChoice.WATCH_HISTORY_INSERT:
				whrm.insertManager();
				continue;
			case WatchHistoryChoice.WATCH_HISTORY_SELECT_ALL:
				whrm.selectAllManager();
				continue;
			case WatchHistoryChoice.WATCH_HISTORY_SELECT:
				whrm.selectManager();
				continue;
			case WatchHistoryChoice.WATCH_HISTORY_UPDATE:
				whrm.updateManager();
				continue;
			case WatchHistoryChoice.WATCH_HISTORY_DELETE:
				whrm.deleteManager();
				continue;
			case WatchHistoryChoice.MAIN_MENU:
				exitFlag = true;
				break;
			default:
				System.out.println("해당 메뉴에 번호로만 입력하세요");
			}
		}
	}

	private static void reviewMenu() {
		int no;
		boolean exitFlag = false;
		ReviewRegisterManager rrm = new ReviewRegisterManager();

		while (!exitFlag) {
			MenuView.reviewMenuView();
			no = Integer.parseInt(sc.nextLine());

			switch (no) {
			case ReviewChoice.REVIEW_INSERT:
				rrm.insertManager();
				continue;
			case ReviewChoice.REVIEW_SELECT_ALL:
				rrm.selectAllManager();
				continue;
			case ReviewChoice.REVIEW_SELECT:
				rrm.selectManager();
				continue;
			case ReviewChoice.REVIEW_UPDATE:
				rrm.updateManager();
				continue;
			case ReviewChoice.REVIEW_DELETE:
				rrm.deleteManager();
				continue;
			case ReviewChoice.MAIN_MENU:
				exitFlag = true;
				break;
			default:
				System.out.println("해당 메뉴에 번호로만 입력하세요");
			}
		}
	}
}

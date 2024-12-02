package movieReviewProject.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import movieReviewProject.model.MovieVO;

public class MovieRegisterManager {
	public static Scanner sc = new Scanner(System.in);

	public void selectManager() throws SQLException {
		MovieDAO mdao = new MovieDAO();
		ArrayList<MovieVO> movieList = new ArrayList<MovieVO>();
		movieList = mdao.movieSelect(new MovieVO());
		if (movieList.size() <= 0) {
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		printMovieList(movieList);
	}

	public void InsertManager() throws SQLException {

		System.out.print("제목을 입력하세요: ");
		String title = sc.nextLine();
		System.out.print("개봉일을 입력하세요: ");
		Date releaseDate = Date.valueOf(sc.nextLine());
		System.out.print("상영시간을 입력하세요: ");
		int duration = Integer.parseInt(sc.nextLine());
		System.out.print("장르를 입력하세요: ");
		String genre = sc.nextLine();

		MovieVO movieVO = new MovieVO(title, releaseDate, duration, genre);
		boolean successFlag = MovieDAO.MovieInsert(movieVO);

		if (successFlag == true) {
			System.out.println("입력처리 성공");
		} else {
			System.out.println("입력처리 실패");
		}
	}
	
	public void genreSelectManager() throws SQLException {
	    System.out.print("원하시는 장르를 입력하세요: ");
	    String genre = sc.nextLine();

	    MovieDAO.listMoviesByGenre(genre); 
	}
	
	public void updateManager() throws SQLException {
		
		System.out.print("수정할 영화 아이디를 입력하세요: ");
		int movieId = Integer.parseInt(sc.nextLine());
		System.out.print("수정할 영화 제목을 입력하세요: ");
		String title = sc.nextLine();
		System.out.print("수정할 개봉일을 입력하세요: ");
		Date releaseDate = Date.valueOf(sc.nextLine());
		System.out.print("수정할 상영시간(분)을 입력하세요: ");
		int duration = Integer.parseInt(sc.nextLine());
		System.out.print("수정할 장르를 입력하세요: ");
		String genre = sc.nextLine();

		MovieVO mvo = new MovieVO(movieId, title, releaseDate, duration, genre);
		boolean successFlag = MovieDAO.movieUpdate(mvo);

		if (successFlag == true) {
			System.out.println("입력처리 성공");
		} else {
			System.out.println("입력처리 실패");
		}
	}
	
	public void deleteManager() throws SQLException {
		System.out.print("삭제할 영화 아이디를 입력하세요: ");
		int no = Integer.parseInt(sc.nextLine());
		MovieVO mvo = new MovieVO();
		mvo.setMovieId(no);
		boolean successFlag = MovieDAO.movieDelete(mvo);

		if (successFlag == true) {
			System.out.println("삭제처리 성공");
		} else {
			System.out.println("삭제처리 실패");
		}
	}

	public static void printMovieList(ArrayList<MovieVO> movieList) {
		System.out.println("================================================================================================================");
		for (MovieVO mvo : movieList) {
			System.out.println(mvo.toString());
		}
		System.out.println("================================================================================================================");
	}

}


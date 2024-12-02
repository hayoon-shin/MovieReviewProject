package movieReviewProject.model;

import java.sql.Date;

public class WatchHistoryAllVO {
	
	private int watch_id;
	private String user_id; 
	private int movie_id;
	private String title; 
	private Date release_date;
	private int duration;
	private String genre;
	private Date watch_date;
	private int watch_count;
	
	public WatchHistoryAllVO() {
		
	}

	public WatchHistoryAllVO(int watch_id, String user_id, int movie_id, String title, Date release_date, int duration,
			String genre, Date watch_date, int watch_count) {
		super();
		this.watch_id = watch_id;
		this.user_id = user_id;
		this.movie_id = movie_id;
		this.title = title;
		this.release_date = release_date;
		this.duration = duration;
		this.genre = genre;
		this.watch_date = watch_date;
		this.watch_count = watch_count;
	}

	@Override
	public String toString() {
		return String.format("%12s| ","[watchID=" + watch_id) + String.format("%18s| ","[userID=" + user_id)+String.format("%12s | "," movieID=" + movie_id)+String.format("%22s | "," watchDate=" + watch_date)+String.format("%17s| ","[title=" + title) + String.format("%18s| ","[releaseDate=" + release_date)+String.format("%12s | "," duration=" + duration)+String.format("%12s | "," genre=" + genre)+String.format("%-18s", " watchCount=" + watch_count) +"]";
	}
	
}

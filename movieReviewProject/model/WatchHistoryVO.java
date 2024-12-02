package movieReviewProject.model;

import java.sql.Date;

public class WatchHistoryVO {
	private int watch_id;
	private String user_id;
    private int movie_id;
    private Date watch_date;
    private int watch_count;
	
    public WatchHistoryVO() {

    }

	public WatchHistoryVO(int watch_id, String user_id, int movie_id, Date watch_date, int watch_count) {
		super();
		this.watch_id = watch_id;
		this.user_id = user_id;
		this.movie_id = movie_id;
		this.watch_date = watch_date;
		this.watch_count = watch_count;
	}

	public WatchHistoryVO(String user_id, int movie_id) {
		this.user_id = user_id;
		this.movie_id = movie_id;
	}

	public WatchHistoryVO(int watch_id, String user_id, int movie_id) {
		this.watch_id = watch_id;
		this.user_id = user_id;
		this.movie_id = movie_id;
	}

	public int getWatch_id() {
		return watch_id;
	}

	public void setWatch_id(int watch_id) {
		this.watch_id = watch_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}

	public Date getWatch_date() {
		return watch_date;
	}

	public void setWatch_date(Date watch_date) {
		this.watch_date = watch_date;
	}

	public int getWatch_count() {
		return watch_count;
	}

	public void setWatch_count(int watch_count) {
		this.watch_count = watch_count;
	}

	@Override
    public String toString() {
        return String.format("%12s| ","[watchID=" + watch_id) + String.format("%18s| ","[userID=" + user_id)+String.format("%12s | "," movieID=" + movie_id)+String.format("%22s | "," watchDate=" + watch_date)+String.format("%-18s", " watchCount=" + watch_count) +"]";

    }

    
	
}

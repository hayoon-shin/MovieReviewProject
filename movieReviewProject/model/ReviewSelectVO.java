package movieReviewProject.model;

import java.sql.Date;

public class ReviewSelectVO {
	private int reviewID;
	private String title;
	private String genre;
	private int rating;
	private String description;
	private Date watchDate;
	private double avg;
	
	public ReviewSelectVO() {}

	public ReviewSelectVO(int reviewID, String title, String genre, int rating, String description, Date watchDate, double avg) {
		super();
		this.reviewID = reviewID;
		this.title = title;
		this.genre = genre;
		this.rating = rating;
		this.description = description;
		this.watchDate = watchDate;
	}

	public int getReviewID() {
		return reviewID;
	}

	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getWatchDate() {
		return watchDate;
	}

	public void setWatchDate(Date watchDate) {
		this.watchDate = watchDate;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	@Override
    public String toString() {

        return String.format("%12s| ","[reviewID=" + reviewID) + String.format("%18s| ","[title=" + title)+String.format("%12s | "," genre=" + genre)+String.format("%12s | "," rating=" + rating)+String.format("%22s | "," description=" + description)+String.format("%-18s", " watchDate=" + watchDate) +"]";
    }

}

package movieReviewProject.model;

public class ReviewVO {
	private int reviewID;
	private int watchID;
	private int rating;
    private String description;
    
	public ReviewVO() {}
	
	public ReviewVO(int watchID, int rating, String description) {
		super();
		this.watchID = watchID;
		this.rating = rating;
		this.description = description;
	}

	public ReviewVO(int rating, String description) {
		super();
		this.rating = rating;
		this.description = description;
	}

	public ReviewVO(int reviewID, int watchID, int rating, String description) {
		super();
		this.reviewID = reviewID;
		this.watchID = watchID;
		this.rating = rating;
		this.description = description;
	}

	public int getReviewID() {
		return reviewID;
	}

	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}

	public int getWatchID() {
		return watchID;
	}

	public void setWatchID(int watchID) {
		this.watchID = watchID;
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

	@Override
	public String toString() {
		return String.format("%12s| ", "[reviewID=" + reviewID)+ String.format("%12s| "," watchID=" + watchID) + String.format("%12s| "," rating=" + rating) + String.format("%12s| "," description="
				+ description) + "]";
	}
	
	
}

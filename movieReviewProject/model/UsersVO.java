package movieReviewProject.model;

public class UsersVO {
	private String userID; 		//pk, seq
	private String userName; 	//과목 약어
	private String password; 
	private String email;
	public UsersVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UsersVO(String userID, String userName, String password, String email) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return String.format("%12s| ","[userID=" + userID)+String.format("%12s | "," userName=" + userName)+String.format("%18s | "," password=" + password)+String.format("%-18s", " email=" + email) +"]";
	} 
	
}


public class User {

	private final UserID userID;
	
	private final String name;

	public User(UserID userID, String name) {
		super();
		this.userID = userID;
		this.name = name;
	}

	/**
	 * @return the userID
	 */
	public UserID getUserID() {
		return userID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
}

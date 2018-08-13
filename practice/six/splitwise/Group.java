
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Group {

	private final GroupID groupID;
	
	private final Set<UserID> users = new HashSet<>();

	public Group(GroupID groupID, Collection<UserID> users) {
		super();
		this.groupID = groupID;
		if(users != null) {
			this.users.addAll(users);
		}
	}

	/**
	 * @return the groupID
	 */
	public GroupID getGroupID() {
		return groupID;
	}

	/**
	 * @return the users
	 */
	public Collection<UserID> getUsers() {
		return users;
	}
	
	public void removeUser(UserID userID) {
		users.remove(userID);
	}
	
}

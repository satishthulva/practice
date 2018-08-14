
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GroupService {

	private final Map<GroupID, Group> groupsMap = new HashMap<>();
	
	public Group findGroup(GroupID groupID) {
		return groupsMap.get(groupID);
	}
	
	public GroupID registerGroup(Collection<UserID> members)
	{
		GroupID groupID = generateGroupID();
		Group group = new Group(groupID, members);
		groupsMap.put(groupID, group);
		return groupID;
	}
	
	private GroupID generateGroupID() {
		return new GroupID(System.nanoTime() + "");
	}
	
}

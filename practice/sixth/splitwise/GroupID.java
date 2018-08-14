
public class GroupID extends Identity {

	public GroupID(String id) {
		super(id);
	}

	/* (non-Javadoc)
	 * @see practice.interview.flipkart.splitwise.Identity#getType()
	 */
	@Override
	public EntityType getType() {
		return EntityType.GROUP;
	}

}

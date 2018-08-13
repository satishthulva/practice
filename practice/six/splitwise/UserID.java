
public class UserID extends Identity {

	public UserID(String id) {
		super(id);
	}

	/* (non-Javadoc)
	 * @see practice.interview.flipkart.splitwise.Identity#getType()
	 */
	@Override
	public EntityType getType() {
		return EntityType.INDIVIDUAL;
	}
	
}

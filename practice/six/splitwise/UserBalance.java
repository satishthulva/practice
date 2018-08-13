
import java.util.HashMap;
import java.util.Map;

public class UserBalance {

	private final UserID userID;
	
	private final Map<Identity, Float> userBalances = new HashMap<>();
	
	private float totalBalance;

	public UserBalance(UserID userID) {
		super();
		this.userID = userID;
	}
	
	public void addToBalance(Identity identity, float amount) {
		Float currentBalance = userBalances.get(identity);
		userBalances.put(identity, (currentBalance != null ? currentBalance : 0.0f) + amount);
		
		totalBalance+=amount;
	}

	/**
	 * @return the totalBalance
	 */
	public float getTotalBalance() {
		return totalBalance;
	}

	/**
	 * @return the userID
	 */
	public UserID getUserID() {
		return userID;
	}

	/**
	 * @return the userBalances
	 */
	public Map<Identity, Float> getUserBalances() {
		return userBalances;
	}
	
	public float getBalance(Identity identity) {
		Float balanceEntry = userBalances.get(identity);
		return balanceEntry != null ? balanceEntry : 0.0f;
	}
	
}

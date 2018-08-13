
import java.util.HashMap;
import java.util.Map;

public class UserBalanceService {

	private final Map<UserID, UserBalance> userBalanceMap = new HashMap<>();

	public void addBill(Bill bill) {
		switch (bill.getBillType()) {
		case PERSON_TO_PERSON:
			processPersonToPersonBill(bill);
			break;

		case GROUP:
			proessGroupBill(bill);
			break;
			
		default:
			throw new RuntimeException("Unexpected bill type to process " + bill.getBillType());
		}
	}

	private void proessGroupBill(Bill bill) {
		GroupID groupID = bill.getGroupID();

		for (UserID userID : bill.getAllUsers()) {
			float userBillBalance = bill.getUserBalanceForBill(userID);
			addUserBalance(userID, groupID, userBillBalance);
		}
	}
	
	private void addUserBalance(UserID forUser, Identity against, float balance) {
		UserBalance userBalance = userBalanceMap.get(forUser);
		if (userBalance == null) {
			userBalance = new UserBalance(forUser);
			userBalanceMap.put(forUser, userBalance);
		}

		userBalance.addToBalance(against, balance);
	}
	
	private void processPersonToPersonBill(Bill bill) {
		UserID userOne = null, userTwo = null;
		for(UserID userID : bill.getAllUsers()) {
			if(userOne != null) {
				userTwo = userID;
			} else {
				userOne = userID;
			}
		}
		
		addUserBalance(userOne, userTwo, bill.getUserBalanceForBill(userOne));
		addUserBalance(userTwo, userOne, bill.getUserBalanceForBill(userTwo));
	}
	
	public UserBalance getUserBalance(UserID userID) {
		return userBalanceMap.get(userID);
	}
	
}

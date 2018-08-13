
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

public class Bill {

	private GroupID groupID;
	
	private Map<UserID, Float> billPaidAmounts;
	
	private Map<UserID, Float> billShareAmounts;
	
	private float totalBill;
	
	private BillType billType;
	
	private Collection<UserID> allUsers = new HashSet<>();

	Bill(GroupID groupID, Map<UserID, Float> billPaidAmounts, Map<UserID, Float> billShareAmounts) {
		super();
		this.groupID = groupID;
	
		if(groupID != null)
			this.billType = BillType.GROUP;
		else
			this.billType = BillType.PERSON_TO_PERSON;
		
		this.billPaidAmounts = billPaidAmounts;
		this.billShareAmounts = billShareAmounts;
		
		totalBill=0.0f;
		for(Float partialPayment : billPaidAmounts.values()) {
			if(partialPayment != null)
				totalBill+=partialPayment;
		}
		
		allUsers.addAll(billPaidAmounts.keySet());
		allUsers.addAll(billShareAmounts.keySet());
	}
	
	/**
	 * @return the billType
	 */
	public BillType getBillType() {
		return billType;
	}

	/**
	 * @return the allUsers
	 */
	public Collection<UserID> getAllUsers() {
		return allUsers;
	}

	/**
	 * @return the groupID
	 */
	public GroupID getGroupID() {
		return groupID;
	}

	/**
	 * @return the billPaidAmounts
	 */
	public Map<UserID, Float> getBillPaidAmounts() {
		return billPaidAmounts;
	}

	/**
	 * @return the billShareAmounts
	 */
	public Map<UserID, Float> getBillShareAmounts() {
		return billShareAmounts;
	}

	/**
	 * @return the totalBill
	 */
	public float getTotalBill() {
		return totalBill;
	}

	public float getUserBalanceForBill(UserID userID) {
		Float paid = billPaidAmounts.get(userID);
		Float owed = billShareAmounts.get(userID);

		if(owed == null || paid == null) {
			if(owed == null)
				return paid;
			else
				return -owed;
		}
		else 
		{
			return paid - owed;
		}
	}
	
	public static Bill createBillForGroupByAmountSharing(GroupID groupID, Map<UserID, Float> billPaidAmounts, Map<UserID, Float> billShareAmounts) {
		return new Bill(groupID, billPaidAmounts, billShareAmounts);
	}
	
	public static Bill createBillForGroupByPercentageSharing(GroupID groupID, Map<UserID, Float> billPaidAmounts, Map<UserID, Float> billSharingPercentages)
	{
		float totalBill=0.0f;
		for(Float partialPayment : billPaidAmounts.values()) {
			if(partialPayment != null)
				totalBill+=partialPayment;
		}
		
		Map<UserID, Float> billShareAmounts = new HashMap<>();
		for(Entry<UserID, Float> userEntry : billSharingPercentages.entrySet()) {
			UserID userID = userEntry.getKey();
			Float percentage = userEntry.getValue();
			
			float userShareAmount = 0.0f;
			if(percentage != null) {
				userShareAmount = (totalBill * percentage)/100.0f;
			}
			
			billShareAmounts.put(userID, userShareAmount);
		}
		
		return new Bill(groupID, billPaidAmounts, billShareAmounts);
	}
	
	
	public static Bill createBillForPersonToPerson(UserID userOne, UserID userTwo, Float userOnePaidAmount, Float userTwoPaidAmount, 
			Float userOneShare, Float userTwoShare) {
		
		Map<UserID, Float> paid = new HashMap<>();
		paid.put(userOne, userOnePaidAmount);
		paid.put(userTwo, userTwoPaidAmount);
		
		Map<UserID, Float> share = new HashMap<>();
		share.put(userOne, userOneShare);
		share.put(userTwo, userTwoShare);
		
		return new Bill(null, paid, share);
	}
	
}

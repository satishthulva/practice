
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class SplitwiseTest {

	public static void main(String[] args) {
		GroupService groupService = new GroupService();
		
		UserID satish = new UserID("satish");
		UserID ankit = new UserID("ankit");
		UserID vaibhav = new UserID("vaibhav");
		GroupID groupID = groupService.registerGroup(Arrays.asList(satish, ankit, vaibhav));
		
		Map<UserID, Float> payments = new HashMap<>();
		payments.put(ankit, 1000.0f);
		payments.put(satish, 200.0f);
		
		Map<UserID, Float> share = new HashMap<>();
		share.put(ankit, 400.0f);
		share.put(satish, 400.0f);
		share.put(vaibhav, 400.0f);
		
		Bill bill = Bill.createBillForGroupByAmountSharing(groupID, payments, share);
		
		UserBalanceService userBalanceService = new UserBalanceService();
		userBalanceService.addBill(bill);
		
		UserBalance satishBalance = userBalanceService.getUserBalance(vaibhav);
		System.out.println("Satish total balance " + satishBalance.getTotalBalance());
		for(Entry<Identity, Float> satishBalancesEntry : satishBalance.getUserBalances().entrySet()) {
			Identity identity = satishBalancesEntry.getKey();
			System.out.println(identity.getType() + "\t" + identity.getId() + "\t" + satishBalancesEntry.getValue());
		}
		
		System.out.println("\n\n");
		payments = new HashMap<>();
		payments.put(vaibhav, 1200.0f);
		
		Map<UserID, Float> sharePercentages = new HashMap<>();
		sharePercentages.put(vaibhav, 40.0f);
		sharePercentages.put(ankit, 30.0f);
		sharePercentages.put(satish, 30.0f);
		
		bill = Bill.createBillForGroupByPercentageSharing(groupID, payments, sharePercentages);
		
		userBalanceService.addBill(bill);
		
		satishBalance = userBalanceService.getUserBalance(ankit);
		System.out.println("Satish total balance " + satishBalance.getTotalBalance());
		for(Entry<Identity, Float> satishBalancesEntry : satishBalance.getUserBalances().entrySet()) {
			Identity identity = satishBalancesEntry.getKey();
			System.out.println(identity.getType() + "\t" + identity.getId() + "\t" + satishBalancesEntry.getValue());
		}
		
		UserID naveen = new UserID("naveen");
		GroupID secondGroup = groupService.registerGroup(Arrays.asList(satish, naveen));
		
		payments = new HashMap<>();
		payments.put(satish, 600.0f);
		
		share = new HashMap<>();
		share.put(satish, 400.0f);
		share.put(naveen, 200.0f);
		
		bill = Bill.createBillForGroupByAmountSharing(secondGroup, payments, share);
		userBalanceService.addBill(bill);
		
		satishBalance = userBalanceService.getUserBalance(satish);
		System.out.println("Satish total balance " + satishBalance.getTotalBalance());
		for(Entry<Identity, Float> satishBalancesEntry : satishBalance.getUserBalances().entrySet()) {
			Identity identity = satishBalancesEntry.getKey();
			System.out.println(identity.getType() + "\t" + identity.getId() + "\t" + satishBalancesEntry.getValue());
		}
		
		UserID manjari = new UserID("manjari");
		bill = Bill.createBillForPersonToPerson(satish, manjari, 500.0f, null, 200.0f, 300.0f);
		userBalanceService.addBill(bill);
		
		satishBalance = userBalanceService.getUserBalance(satish);
		System.out.println("Satish total balance " + satishBalance.getTotalBalance());
		for(Entry<Identity, Float> satishBalancesEntry : satishBalance.getUserBalances().entrySet()) {
			Identity identity = satishBalancesEntry.getKey();
			System.out.println(identity.getType() + "\t" + identity.getId() + "\t" + satishBalancesEntry.getValue());
		}
	}

}

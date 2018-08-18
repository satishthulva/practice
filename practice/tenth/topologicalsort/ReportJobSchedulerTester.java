
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReportJobSchedulerTester {

	private final ReportJobScheduler scheduler = new ReportJobScheduler();

	public void testIndependentJobs() {
		List<Report> reports = new ArrayList<>();

		Report one = new Report(new ReportID(1), null);
		reports.add(one);

		Report two = new Report(new ReportID(2), null);
		reports.add(two);

		Report three = new Report(new ReportID(3), null);
		reports.add(three);

		List<ReportID> order = scheduler.findExecutionbOrder(reports);
		
		if(!isOrderCorrect(order, reports)) {
			System.out.println("Failed for independent jobs");
		}
		printOrder(order);
	}

	public void testForAChain() {
		List<Report> reports = new ArrayList<>();

		Report one = new Report(new ReportID(1), null);
		reports.add(one);

		Report two = new Report(new ReportID(2), Arrays.asList(new ReportID(1)));
		reports.add(two);

		Report three = new Report(new ReportID(3), Arrays.asList(new ReportID(2)));
		reports.add(three);

		List<ReportID> order = scheduler.findExecutionbOrder(reports);
		
		if(!isOrderCorrect(order, reports)) {
			System.out.println("Failed for chain of jobs");
		}
		printOrder(order);
	}
	
	public void testForGivenInput() {
		List<Report> reports = new ArrayList<>();

		Report one = new Report(new ReportID(1), Arrays.asList(new ReportID(8), new ReportID(9), new ReportID(10)));
		reports.add(one);

		Report two = new Report(new ReportID(2), Arrays.asList(new ReportID(5), new ReportID(6)));
		reports.add(two);

		Report four = new Report(new ReportID(4), null);
		reports.add(four);
		
		Report five = new Report(new ReportID(5), null);
		reports.add(five);

		Report six = new Report(new ReportID(6), null);
		reports.add(six);

		Report seven = new Report(new ReportID(7), null);
		reports.add(seven);
		
		Report eight = new Report(new ReportID(8), Arrays.asList(new ReportID(7), new ReportID(4)));
		reports.add(eight);

		Report nine = new Report(new ReportID(9), null);
		reports.add(nine);

		Report ten = new Report(new ReportID(10), null);
		reports.add(ten);
		
		List<ReportID> order = scheduler.findExecutionbOrder(reports);
		if(!isOrderCorrect(order, reports)) {
			System.out.println("Failed for given input");
		}
		
		printOrder(order);
	}
	
	private void printOrder(List<ReportID> order) {
		for(int i = 0; i < order.size(); i+=1) {
			if(i > 0)
				System.out.print(", ");
			System.out.print(order.get(i).getId());
		}
		System.out.println();
	}
	
	private Map<ReportID, Set<ReportID>> getDependenciesMap(Collection<Report> reports) {
		Map<ReportID, Set<ReportID>> reportMap = new HashMap<>();
		for (Report report : reports) {
			reportMap.put(report.getReportID(), report.getDependencies());
		}
		return reportMap;
	}
	public boolean isOrderCorrect(List<ReportID> order, Collection<Report> reports) {
		Map<ReportID, Set<ReportID>> dependenciesMap = getDependenciesMap(reports);
		
		Set<ReportID> executedJobs = new HashSet<>();
		for(ReportID reportID : order) {
			Set<ReportID> dependenciesForJob = dependenciesMap.get(reportID);
			
			if(dependenciesForJob != null && dependenciesForJob.size() > 0) {
				dependenciesForJob.removeAll(executedJobs);
				if(dependenciesForJob.size() > 0) {
					return false;
				}
			}
				
			executedJobs.add(reportID);
		}
		return true;
	}
	
	public static void main(String[] args) {
		ReportJobSchedulerTester tester = new ReportJobSchedulerTester();
		
		tester.testIndependentJobs();
		
		tester.testForAChain();
		
		tester.testForGivenInput();
	}

}


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * Job scheduler which finds out one of the possible orders in which the jobs can be executed
 */
public class ReportJobScheduler {

	/**
	 * Find one execution order which does not violate the dependencies given for the reports
	 * @param reports	all reports
	 * @return	one execution order which does not violate the dependencies given for the reports
	 */
	public List<ReportID> findExecutionbOrder(Collection<Report> reports) {
		List<ReportID> orderOfExecution = new ArrayList<>();

		if (reports == null || reports.isEmpty())
			return new ArrayList<>();

		// report map
		Map<ReportID, Report> reportMap = getReportMap(reports);
		
		// because there is no cyclic dependency --> at least one report should be there in this category
		List<ReportID> reportsWithNoDependencies = findReportsWithNoDependencies(reports);
		
		// inverse dependency map --> for each report which reports are depending on it
		Map<ReportID, Set<ReportID>> inverseDependencies = findReportsDependingOn(reports);

		// iterating on all reports which are independent
		for (ReportID reportID : reportsWithNoDependencies) {
			orderOfExecution.add(reportID);
			orderOfExecution.addAll(findJobsEligibleAfterThisExecution(reportID, reportMap, inverseDependencies));
		}

		return orderOfExecution;
	}

	/**
	 * Find all jobs which are eligible for execution after executing given job
	 * 
	 * @param reportID				job that is executed just now
	 * @param reportMap				report map
	 * @param inverseDependencies	for each job which other jobs are depending on this job
	 * @return	all jobs which are eligible for execution after executing given job
	 */
	private List<ReportID> findJobsEligibleAfterThisExecution(ReportID reportID, Map<ReportID, Report> reportMap,
			Map<ReportID, Set<ReportID>> inverseDependencies) {
		Set<ReportID> dependents = inverseDependencies.get(reportID);
		// none depending on this job
		if (dependents == null || dependents.isEmpty())
			return new ArrayList<>();

		List<ReportID> others = new ArrayList<>();

		// for each dependent --> see if that can be executed now, if yes --> find dependents on that job which can be executed now
		for (ReportID dependent : dependents) {
			Report report = reportMap.get(dependent);

			report.getDependencies().remove(reportID);

			if (report.getDependencies().isEmpty()) {
				others.add(dependent);
				others.addAll(findJobsEligibleAfterThisExecution(dependent, reportMap, inverseDependencies));
			}
		}

		return others;
	}

	/**
	 * Return map of report ID to report object
	 * @param reports	All reports
	 * @return map of report ID to report object
	 */
	private Map<ReportID, Report> getReportMap(Collection<Report> reports) {
		Map<ReportID, Report> reportMap = new HashMap<>();
		for (Report report : reports) {
			reportMap.put(report.getReportID(), report);
		}
		return reportMap;
	}

	/**
	 * Find for each report, the reports that are depending on it
	 * @param reports	all reports
	 * @return	for each report, the reports that are depending on it. key is report id, value is collection of reports depending on it
	 */
	private Map<ReportID, Set<ReportID>> findReportsDependingOn(Collection<Report> reports) {
		Map<ReportID, Set<ReportID>> dependendingJobsMap = new HashMap<>();

		for (Report report : reports) {
			ReportID reportID = report.getReportID();

			for (ReportID dependingOn : report.getDependencies()) {
				Set<ReportID> allDependingReports = dependendingJobsMap.get(dependingOn);

				if (allDependingReports == null) {
					allDependingReports = new HashSet<>();
					dependendingJobsMap.put(dependingOn, allDependingReports);
				}

				allDependingReports.add(reportID);
			}
		}

		return dependendingJobsMap;
	}

	/**
	 * Find all reports which does not depend on any other report to be executed before them i.e. independent
	 * @param reports	all reports
	 * @return	all independent reports
	 */
	private List<ReportID> findReportsWithNoDependencies(Collection<Report> reports) {
		List<ReportID> reportsWithNoDependencies = new ArrayList<>();

		for (Report report : reports) {
			if (report.getDependencies().isEmpty()) {
				reportsWithNoDependencies.add(report.getReportID());
			}
		}

		return reportsWithNoDependencies;
	}

	// // report map
	// Map<ReportID, Report> reportMap = new HashMap<>();
	// // because there is no cyclic dependency --> at least one report should be
	// there in this category
	// List<ReportID> reportsWithNoDependencies = new ArrayList<>();
	// // inverse dependency map --> for each report which reports are depending on
	// it
	// Map<ReportID, Set<ReportID>> inverseDependencies = new HashMap<>();
	//
	// for (Report report : reports) {
	// ReportID reportID = report.getReportID();
	// reportMap.put(reportID, report);
	// if (report.getDependencies().isEmpty()) {
	// reportsWithNoDependencies.add(reportID);
	// } else {
	// for (ReportID dependentOn : report.getDependencies()) {
	// Set<ReportID> allDependentsForReport = inverseDependencies.get(dependentOn);
	//
	// if (allDependentsForReport == null) {
	// allDependentsForReport = new HashSet<>();
	// inverseDependencies.put(dependentOn, allDependentsForReport);
	// }
	//
	// allDependentsForReport.add(reportID);
	// }
	// }
	// }
	
}

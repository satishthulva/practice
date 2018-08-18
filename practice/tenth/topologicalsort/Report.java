
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
/**
 * Information known about the job
 * @author rebecca
 */
public class Report {
	
	private final ReportID reportID;

	private final Set<ReportID> dependencies = new HashSet<>();

	public Report(ReportID reportID, Collection<ReportID> dependencies) {
		super();
		this.reportID = reportID;
		if (dependencies != null) {
			this.dependencies.addAll(dependencies);
		}
	}

	/**
	 * @return the reportID
	 */
	public ReportID getReportID() {
		return reportID;
	}

	/**
	 * @return the dependencies
	 */
	public Set<ReportID> getDependencies() {
		return dependencies;
	}

	public boolean isDependentOn(ReportID reportID) {
		return dependencies.contains(reportID);
	}

}

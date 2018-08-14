
/**
 * <pre>
 * Assume that you own a car parking lot.
 * 
 * For every car that gets parked, there is an entry charge of 2 rupees.
 * 
 * You have a policy of charging for full hour even if the parking is done for 1 minute to 59 minutes i.e. there is no partial hour payment.
 * 
 * For the first hour parking, 3 rupees is the charge.
 * From the second hour onwards, the parking charge is 4 rupees per hour. 
 * 
 * Given the entry and exit time for a car, calculate the charges need to be paid for parking.
 * 
 * The entry time and exit time are given in the following format --> HH:MM.
 * HH is an integer in the range 00 to 23
 * MM is an integer in the range 00 to 59
 * </pre>
 * @author rebecca
 */
public class ParkingLotProblem {
    
    private static final String HOURS_MINUTES_SEPARATOR = ":";
    
    public static int findParkingPrice(String entryTime, String leavingTime) {
        HoursAndMinutes entryInstant = parseHoursAndMinutes(entryTime);
        HoursAndMinutes leavingInstant = parseHoursAndMinutes(leavingTime);
        
        HoursAndMinutes timeSpentInParkingLot = findDifference(entryInstant, leavingInstant);
        
        int cost = 2;
        int billedHours = timeSpentInParkingLot.getHours() + (timeSpentInParkingLot.getMinutes() > 0 ? 1 : 0);
        cost = cost + 3 + (billedHours - 1) * 4;
        return cost;
    }

    private static HoursAndMinutes findDifference(HoursAndMinutes from, HoursAndMinutes to) {
    	int hours = to.getHours() - from.getHours();
    	int minutes = to.getMinutes() - from.getMinutes();
    	return new HoursAndMinutes(hours, minutes);
    }
    
    private static HoursAndMinutes parseHoursAndMinutes(String time) {
        String[] fields = time.split(HOURS_MINUTES_SEPARATOR, -1);
        int hours = Integer.parseInt(fields[0]);
        int minutes = Integer.parseInt(fields[1]);
        
        return new HoursAndMinutes(hours, minutes);
    }

    private static class HoursAndMinutes
    {
        private final int hours;
        
        private final int minutes;

		public HoursAndMinutes(int hours, int minutes) {
			super();
			this.hours = hours;
			this.minutes = minutes;
		}

		/**
		 * @return the hours
		 */
		public int getHours() {
			return hours;
		}

		/**
		 * @return the minutes
		 */
		public int getMinutes() {
			return minutes;
		}
    }

}

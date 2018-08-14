/**
 * <pre>
 * Given an array of integers Arr, find the maximum value of |Arr[i]-Arr[j]|
 * where |x| = -x if x < 0 and |x| = x if x > 0
 * </pre>
 * @author rebecca
 */
public class FindMaxModDifference {

	public static int findMaxModDifference(int[] arr) {
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		
		for(int number : arr) {
			if(number < min) {
				min = number;
			}
			
			if(number > max) {
				max = number;
			}
		}
		
		return Math.abs(max - min);
	}
	
}

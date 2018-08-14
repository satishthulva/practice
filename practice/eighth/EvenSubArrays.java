
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Find the number of subarrays of given integer array containing at most given number of odd numbers(k)
 * 
 * Constraints : 
 * 
 * 1 <= array size <= 1000
 * 1 <= k <= n
 * 1 <= arr[i] <= 250
 * 
 * e.g. {3, 2, 3, 4} as array and k as 1 answer is 7 --> {3}, {2}, {4}, {3, 2}, {2, 3}, {3, 4}, {2, 3, 4}
 * 
 * @author rebecca
 */
public class EvenSubArrays {

	private static final char SEPARATOR = '_';
	
	public static int findNumberOfEvenSubArrays(int[] arr, int k) {
		Set<String> subArrays = new HashSet<>();
		int numSubArrs = 0;
		
		for(int i = 0; i < arr.length; i+=1) {
			StringBuilder subArrBuilder = new StringBuilder();
			int numOddElems = 0;
			
			for(int j = i; j >= 0; j-=1) {
				int num = arr[j];
			
				if(num % 2 == 1)
					numOddElems+=1;
				
				if(numOddElems <= k) {
					subArrBuilder = subArrBuilder.append(SEPARATOR).append(num);
					String subArrStr = subArrBuilder.toString();
					if(!subArrays.contains(subArrStr)) {
						numSubArrs+=1;
						subArrays.add(subArrStr);
					}
				} else {
					break;
				}
			}
		}
		
		return numSubArrs;
	}
	
	public static void main(String[] args) throws IOException {
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			int numElems = Integer.parseInt(reader.readLine());
			int[] arr = new int[numElems];
			for(int i = 0; i < numElems; i+=1) {
				arr[i] = Integer.parseInt(reader.readLine());
			}
			int k = Integer.parseInt(reader.readLine());
			System.out.println(findNumberOfEvenSubArrays(arr, k));
		}
	}
	
}

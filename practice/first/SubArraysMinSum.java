
/**
 * <pre>
 * Given an array of integers, find the sum of minimum element
 * for each subarray
 * 
 * e.g. [4, 6, 8, 9, 1]
 * 
 * Subarrays : [4], [6], [8], [9], [1], [4, 6], [6, 8], [8, 9], [9, 1], [4, 6, 8], [6, 8, 9], [8, 9, 1], [4, 6, 8, 9], [6, 8, 9, 1]
 * Minimum	 :  4    6    8    9    1      4       6       8       1        4          6          1            4            1
 * Sum 		 :  63
 * 
 * Brute force is O(n ^ 3) and divide and conquer is O(n ^ 2) solution
 * </pre>
 * @author rebecca
 */
public class SubArraysMinSum {

	/**
	 * Find the minimum element of subarray of arr starting at index startIndex and ending at index endIndex
	 * @param arr			Array of interest
	 * @param startIndex	Starting index of subarray
	 * @param endIndex		Ending array of subarray
	 * @return	the minimum element of subarray of arr starting at index startIndex and ending at index endIndex
	 */
	private static int findMin(int[] arr, int startIndex, int endIndex)
	{
		int min = arr[startIndex];
		
		for(int i = startIndex + 1; i <= endIndex; i+=1)
		{
			if(arr[i] < min)
				min = arr[i];
		}
		
		return min;
	}
	
	/**
	 * Find sum of minimum elements from all subarrays of given array
	 * 
	 * @param arr	Array of interest
	 * @return	sum of minimum elements from all subarrays of given array
	 */
	public static long findSubArrayMinSum(int[] arr)
	{
		long subArrayMinSum = 0l;
		
		for(int length = 1; length < arr.length; length+=1)
		{
			for(int index = 0; index <= arr.length - length; index+=1)
			{
				subArrayMinSum+=findMin(arr, index, index + length - 1);
			}
		}
		
		return subArrayMinSum;
	}

	private static long findSubArrayMinSumDivideAndConquer(int[] arr, int startIndex, int endIndex) {
		if(startIndex > endIndex || endIndex > arr.length - 1)
			return 0;
		
		if(startIndex == endIndex)
			return arr[startIndex];

		long minSum = 0;
		// total three subarrays --> min elem will come twice
		if(startIndex + 1 == endIndex) {
			minSum+=arr[startIndex];
			if(minSum < arr[endIndex])
				minSum+=arr[startIndex] + arr[endIndex];
			else
				minSum+= 2 * arr[endIndex];
			
			return minSum;
		}
		
		int minElem = arr[startIndex], minElemIndex = startIndex;
		for(int i = startIndex; i <= endIndex; i+=1) {
			if(minElem > arr[i]) {
				minElem = arr[i];
				minElemIndex = i;
			}
		}
		
		return minElem * (minElemIndex - startIndex + 1) * (endIndex - minElemIndex + 1) + 
			findSubArrayMinSumDivideAndConquer(arr, startIndex, minElemIndex - 1) + 
			findSubArrayMinSumDivideAndConquer(arr, minElemIndex + 1, endIndex);
	}
	
	public static long findSubArrayMinSumDivideAndConquerHelper(int[] arr) {
		long includingMainArray = findSubArrayMinSumDivideAndConquer(arr, 0, arr.length - 1);
		int min = arr[0];
		for(int num : arr) {
			min = min > num ? num : min;
		}
		return includingMainArray - min;
	}
	
	public static void main(String[] args)
	{
		int[] arr = {4, 6, 8, 9, 1};
		System.out.println(findSubArrayMinSum(arr));
		System.out.println(findSubArrayMinSumDivideAndConquerHelper(arr));
	}
	
}

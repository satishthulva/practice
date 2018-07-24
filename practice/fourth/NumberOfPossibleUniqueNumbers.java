
/**
 * <pre>
 * Given two numbers X and Y, find out whether they consists of same digits( i.e. order does not matter ). 
 * 2345 and 4523 are similar according to this definition. If the numbers are made of same digits,
 * find the number unique numbers that can be made using X. Otherwise, find the number of unique numbers that can be made
 * using Y
 * 
 * When counting unique numbers that can be formed by a number, consider any number that starts with 0 
 * as invalid. i.e. 0200 is not a valid number where as 2000 is a valid number
 * </pre>
 * @author rebecca
 */
public class NumberOfPossibleUniqueNumbers {
	/**
	 * Factorial values
	 */
	private static long[] precomputedFactorials = new long[17];
	
	static
	{
		precomputedFactorials[0] = 1;
		for(int i = 1; i < 17; i+=1) {
			precomputedFactorials[i] = precomputedFactorials[i - 1] * i;
		}
	}
	
	/**
	 * Find number of times each digit occurs in the number
	 * @param number string representation of number
	 * @return array containing number of times each digit occurs in the number
	 */
	private static int[] findDigitCounts(String number) {
		int[] digitCounts = new int[10];
		
		for(char ch : number.toCharArray()) {
			digitCounts[ch - '0']++;
		}
		
		return digitCounts;
	}
	
	/**
	 * Find if given equal length arrays contain same elements in same order or not
	 * 
	 * @param arrOne	Array one
	 * @param arrTwo	Array two
	 * @return	<code>true</code>, if both are of same length. <code>false</code>, otherwise
	 */
	private static boolean areEqual(int[] arrOne, int[] arrTwo) {
		for(int i = 0; i < arrOne.length; i+=1) {
			if(arrOne[i] != arrTwo[i])
				return false;
		}
		
		return true;
	}
	
	/**
	 * Find number of permutations of number such that none of them start with 0
	 * 
	 * @param digitCounts	digit counts of given number
	 * @return	number of permutations of given number such that none of them start with 0
	 */
	static long findNumberOfPermutations(int[] digitCounts) {
		int numberOfDigits = 0, numberOfZeros = digitCounts[0];
		long denominatorForTotalPermutations = 1;
		
		for(int digitCount : digitCounts) {
			denominatorForTotalPermutations*=precomputedFactorials[digitCount];
			numberOfDigits+=digitCount;
		}
		
		long totalPermutations = precomputedFactorials[numberOfDigits] / denominatorForTotalPermutations;
		if(numberOfZeros == 0) {
			return totalPermutations;
		} else {
			long denominatorForStartingWithZeroPermutations = precomputedFactorials[numberOfZeros - 1];
			for(int i = 1; i < 10; i+=1) {
				denominatorForStartingWithZeroPermutations*=precomputedFactorials[digitCounts[i]];
			}
			long permutationsStartingWithZero = precomputedFactorials[numberOfDigits - 1] / denominatorForStartingWithZeroPermutations;
			return totalPermutations - permutationsStartingWithZero;
		}
	}
	
	static long findSimilar(String a, String b) {
		int[] aDigitCounts = findDigitCounts(a);
		int[] bDigitCounts = findDigitCounts(b);

		// are strings anagrams
		boolean areAnagrams = areEqual(aDigitCounts, bDigitCounts);
		
		if(areAnagrams) {
			return findNumberOfPermutations(aDigitCounts);
		} else {
			return findNumberOfPermutations(bDigitCounts);
		}
    }
	
	
	public static void main(String[] args) {
		String a = "1234", b = "2341";
		System.out.println(findSimilar(a, b));
		
		a = "1100"; b = "1001";
		System.out.println(findSimilar(a, b));
		
		a = "1234"; b = "1213";
		System.out.println(findSimilar(a, b));
	}
	
}

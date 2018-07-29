
/**
 * <pre>
 * Given an array of integers A with n elements, construct a new array B which has the following property
 * 
 * B[i] = (A[0] * A[1] * A[2] * ....... A[n - 1]) / A[i]
 * 
 * Do this in O(n) time without using division operation.
 * </pre>
 * 
 * @author rebecca
 */
public class ArrayMultiplyAllButOne {

	public static int[] arrayMultiplyAllButOne(int[] A) {
		int[] B = new int[A.length];

		int[] prefixMultiply = new int[A.length];
		int[] suffixMultiply = new int[A.length];

		prefixMultiply[0] = 1;
		suffixMultiply[A.length - 1] = 1;

		for (int i = 1, j = A.length - 2; i < A.length; i += 1, j -= 1) {
			prefixMultiply[i] = prefixMultiply[i - 1] * A[i - 1];
			suffixMultiply[j] = suffixMultiply[j + 1] * A[j + 1];
		}

		for (int i = 0; i < A.length; i += 1) {
			B[i] = prefixMultiply[i] * suffixMultiply[i];
		}

		return B;
	}

	public static void main(String[] args) {
		int[] a = { 2, 3, 4, 5 };
		for (int i : arrayMultiplyAllButOne(a)) {
			System.out.println(i);
		}
	}

}

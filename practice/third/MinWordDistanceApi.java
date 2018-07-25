
/**
 * <pre>
 * Let us say you have a document which contains words made of lower case letters from English alphabet. Each word in the document 
 * is separated by space. Assume words are numbered starting from 1.
 * 
 * There will be queries on given document. Input for the query are two words w1 and w2. 
 * The output has to be the minimum distance between the words in the document.
 * 
 * Assume that the document is very large and the number of queries also can be a lot.
 * 
 * Design an API to answer the queries.
 * 
 * 		 1	 2		3		4  5	6	 7	 8		9
 * e.g. the king summoned all his people to his darbar
 * 
 * minDistance(his, people)  -->	0 because there are two instances of his in the document - 5 and 8
 * and there is one instance of people - 6. The minimum distance is 0 because 5 and 6 are next to each other 
 * 
 * Solve this first.
 * 
 * Given that you have infinite memory, what are the optimizations you can do to improve the performance
 * </pre>
 * @author rebecca
 */
public class MinWordDistanceApi {

	/**
	 * You are given two sorted arrays.
	 * Find minimum distance between any pair of elements such that one element is from array one and another is from array two
	 * @param sortedArrOne
	 * @param sortedArrTwo
	 * @return
	 */
	private static int getMinDistance(int[] sortedArrOne, int[] sortedArrTwo) {
		int indexOfArrOne = 0, indexOfArrTwo = 0;
		
		boolean isElementFromOne = sortedArrOne[0] < sortedArrTwo[0] ? true : false;
		int currentElem = isElementFromOne ? sortedArrOne[0] : sortedArrTwo[0];
		
		if(isElementFromOne)
			indexOfArrOne = 1;
		else
			indexOfArrTwo = 1;
		
		int curMinDistance = Integer.MAX_VALUE;
		
		while(indexOfArrOne < sortedArrOne.length && indexOfArrTwo < sortedArrTwo.length) {
			int nextElem = sortedArrOne[indexOfArrOne] < sortedArrTwo[indexOfArrTwo] ? sortedArrOne[indexOfArrOne] : sortedArrTwo[indexOfArrTwo];
			boolean isNextElemFromOne = sortedArrOne[indexOfArrOne] < sortedArrTwo[indexOfArrTwo] ? true : false;
			
			if(isElementFromOne != isNextElemFromOne) {
				int distance = currentElem > nextElem ? currentElem - nextElem : nextElem - currentElem;
				if(curMinDistance > distance)
					curMinDistance = distance;
			}
			
			currentElem = nextElem;
			isElementFromOne = isNextElemFromOne;
			
			if(isNextElemFromOne)
				indexOfArrOne+=1;
			else
				indexOfArrTwo+=1;
		}
		
		if(indexOfArrOne < sortedArrOne.length && !isElementFromOne) {
			int distance = sortedArrOne[indexOfArrOne] - currentElem;
			if(curMinDistance > distance)
				curMinDistance = distance;
		} else if(indexOfArrTwo < sortedArrTwo.length && isElementFromOne) {
			int distance = sortedArrTwo[indexOfArrTwo] - currentElem;
			if(curMinDistance > distance)
				curMinDistance = distance;
		}
		
		return curMinDistance;
	}
	
	public static void main(String[] args) {
		int[] one = {1, 2, 3};
		int[] two = {5, 6, 7};
		System.out.println(getMinDistance(one, two));
		
		int[] oneA = {5, 6, 7};
		int[] twoA = {1, 2, 3};
		System.out.println(getMinDistance(oneA, twoA));
		
		int[] oneB = {10, 31, 51};
		int[] twoB = {1, 4, 11};
		System.out.println(getMinDistance(oneB, twoB));
	}
		
	
	
}

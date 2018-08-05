
/**
 * <pre>
 * K lists are given which have integers inside them. They need not be of the same size.
 * Given this, return a minimum range [min, max] such that from each list, at least one element 
 * is part of the range [min, max]
 * 
 * e.g.	3 lists
 * 
 * {3, 9, 6}
 * {15, 10, 11}
 * {19, 24}
 * 
 * The answer will be [9, 19]
 * </pre>
 * @author rebecca
 */
public class MinRangeToCoverOneFromEachList {
	// Could come up woth a k ^ 2 * n solution when on an average the list size is n
	// which I am sure is optimal or not 
	
	// Prepare a hash map with key as number of any of the lists. Value is a collection of list identifiers( number lists from 1 to k )
	// Till all the lists are covered, pick the next element which is covering the highest number of lists which are not covered till now
	// and update the range to accommodate that element. Note the lists that are covered.
	
	// In each iteration, we need to go through all the unique elements which can be k * n in the worst case
	// In the worst case, in each iteration, we reduce the number of lists which still need to be covered by only one i.e. k iterations are needed
	// So O(k * k * n) i.e. O(k ^ 2 * n)
}

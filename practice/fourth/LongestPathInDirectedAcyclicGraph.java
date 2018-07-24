
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <pre>
 * You are given a collection of words. 
 * 
 * Assume that each word is a node in a graph and there is a directed edge starting from word X to word Y
 * iff Y can be obtained by deleting exactly one letter from X.
 * 
 * In this graph, find the length of the longest path.
 * </pre>
 * @author rebecca
 */
public class LongestPathInDirectedAcyclicGraph {

	/**
	 * String comparator that primarily works on the string length. In case of tie,
	 * it defers to original string comparator
	 * 
	 * @author satish
	 */
	private static class StringComparatorByLength implements Comparator<String>
	{
		@Override
		public int compare(String arg0, String arg1) {
			int arg0Length = arg0.length();
			int arg1Length = arg1.length();
			
			int lengthDiff = arg0Length - arg1Length;
			if(lengthDiff != 0)
				return lengthDiff;
			
			return arg0.compareTo(arg1);
		}
	}
	
    // Complete the longestChain function below.
    public static int longestChain(List<String> words) {
    	// set for member check
    	Set<String> wordsSet = new HashSet<>(words);
    	
    	// sorting strings first
    	Comparator<String> stringComparatorByLength = new StringComparatorByLength();
    	Collections.sort(words, stringComparatorByLength);

    	// for each string, maintaining the length of longest chain
    	Map<String, Integer> longestChainLengthMap = new HashMap<>();
    	
    	// maximum chain length in dictionary
    	int maxChainLength = 1;
    	for(String word : words) {
    		int chainLengthForWord = 1;
    		
    		StringBuilder wordBuilder = new StringBuilder(word);
    		// deleting one character at all possible positions and reusing the results computed in previous iterations
    		for(int i = word.length(); i-- > 0; ) {
    			// delete char
    			char charToDelete = wordBuilder.charAt(i);
    			wordBuilder.deleteCharAt(i);
    			String nextInChain = wordBuilder.toString();
    			
    			if(wordsSet.contains(nextInChain)) {
    				int possibleLongestLengthForWord = longestChainLengthMap.get(nextInChain) + 1;
    				if(possibleLongestLengthForWord > chainLengthForWord)
    					chainLengthForWord = possibleLongestLengthForWord;
    			}
    			// insert char again
    			wordBuilder.insert(i, charToDelete);
    		}
    		
    		longestChainLengthMap.put(word, chainLengthForWord);
    		if(maxChainLength < chainLengthForWord) 
    			maxChainLength = chainLengthForWord;
    	}
    	
    	return maxChainLength;
    }
	
    public static void main(String[] args) {
    	String[] arr = {"bdca", "a", "b", "bda", "ba", "bca"};
    	List<String> dictionary = Arrays.asList(arr);
    	
    	System.out.println(longestChain(dictionary));
    }
    
    
}

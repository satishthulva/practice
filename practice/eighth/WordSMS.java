
/**
 * <pre>
 * Let us say tweet length is limited to 30 characters. Design an API to find out the number of tweets a 
 * given text has to be broken into such that each tweet carries a suffix of the form (k/n) where k is the number
 * given to a tweet(assume numbering starts from 1), if the given text has to be broken into n tweets. 
 * 
 * While splitting, do not break words. Assume words are separated by space. 
 * Assume any character other than space is part of the word.
 * 
 * If the text length is less than or equal to 30 characters, no need to give any numbering, so one tweet is enough.
 * 
 * Constraints :
 * 
 * Text length is less than 1000 characters
 * No word is greater than the tweet length
 * It is guaranteed that you do not need more than 99 tweets for any test input given
 * 
 * </pre>
 * @author rebecca
 */
public class WordSMS {

	private static final int EFFECTIVE_CHUNK_LENGTH = 24;
	
	public static int getNumberOfChunks(String line) {
		if(line.length() <= 30)
			return 1;
		
		String[] words = line.trim().split(" +", -1);
		int numChunks = 0;
		int currentChunkSize = 0;
		boolean isFirstWord = true;
		
		for(int i = 0; i < words.length; i+=1) {
			currentChunkSize+= words[i].length();
			
			if(!isFirstWord)
				currentChunkSize+=1;// for space

			isFirstWord = false;
			
			if(currentChunkSize > EFFECTIVE_CHUNK_LENGTH) {
				numChunks+=1;
				currentChunkSize = words[i].length();
			} 
		}
		
		if(currentChunkSize > 0)
			numChunks+=1;
		
		return numChunks;
	}
	
	public static void main(String[] args) {
		System.out.println(getNumberOfChunks("the best lie"));
	}
	
}

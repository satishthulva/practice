
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import practice.interview.rivigo.RearrangeABC;

/**
 * Tests for {@link RearrangeABC}
 * @author rebecca
 */
public class RearrangeABCTester {
	/**
	 * Generate a random string which is made of only a,b, and c 
	 * of length <code>length</code> and is not present in the collection of <code>generatedStrings</code>
	 * 
	 * @param length			Length of the string to be generated
	 * @param generatedStrings	Collection of exclusion strings
	 * @return	random string of given length which is not present in given collection of strings
	 */
	private static String generateRandomString(int length, Set<String> generatedStrings)
	{
		Random random = new Random(System.currentTimeMillis());
		boolean isNew = false;
		String candidate = null;
		
		while(!isNew)
		{
			StringBuilder stringBuilder = new StringBuilder();
			
			for(int i = 0; i < length; i+=1)
			{
				switch (random.nextInt(3))
				{
					case 0:
						stringBuilder.append("a");
						break;
		
					case 1:
						stringBuilder.append("b");
						break;
						
					case 2:
						stringBuilder.append("c");
						break;
				}
			}
			
			candidate = stringBuilder.toString();
			isNew = !generatedStrings.contains(candidate);
		}
		
		return candidate;
	}
	
	/**
	 * Find whether line segments on X-axis - [startOne, endOne] and [startTwo, endTwo] are overlapping, if both segments are valid
	 * if at least one segment boundaries are [-1, -1] i.e. invalid segment --> non-overlapping
	 * 
	 * @param startOne	starting of first line segment
	 * @param endOne	ending of first line segment
	 * @param startTwo	starting of second line segment
	 * @param endTwo	ending of second line segment
	 * @return	<code>true</code>, if both segments are valid and they overlap
	 */
	private static boolean isOverlapping(int startOne, int endOne, int startTwo, int endTwo)
	{
		if(startOne == -1 || startTwo == -1)
			return false;
		
		return startOne <= endTwo && endOne >= startTwo;
	}
	
	/**
	 * Find whether given string made of characters a, b, and c only matches the pattern a*b*c*
	 * 
	 * @param string	string made of just a, b, and c
	 * @return	<code>true</code>, if string matches pattern a*b*c*
	 * 
	 * NOTE : I could have used regular expression. But, did not - just for fun :)
	 */
	private static boolean isValidString(String string)
	{
		int startA = -1, endA = -1;
		int startB = -1, endB = -1;
		int startC = -1, endC = -1;
		
		char[] arr = string.toCharArray();
		for(int i = 0; i < arr.length; i+=1)
		{
			if(arr[i] == 'a')
			{
				if(startA == -1)
					startA = i;
				endA = i;
			}
			else if(arr[i] == 'b')
			{
				if(startB == -1)
					startB = i;
				endB = i;
			}
			else
			{
				if(startC == -1)
					startC = i;
				endC = i;
			}
		}
		
		return !(
				isOverlapping(startA, endA, startB, endB) || 
				isOverlapping(startB, endB, startC, endC) || 
				isOverlapping(startA, endA, startC, endC)
				);
	}
	
	private static void test()
	{
		for(int i = 1; i <= 100; i+=1)
		{
			Set<String> usedStrings = new HashSet<>();
			for(int j = 1; j <=3; j+=1)
			{
				String string = generateRandomString(i, usedStrings);
				usedStrings.add(string);
				
				String rearranged = RearrangeABC.rearrageABC(string);
				if(!isValidString(rearranged))
					System.out.println(string + "\t" + rearranged);
			}
		}
	}
	
	public static void main(String[] args)
	{
		test();
	}
}

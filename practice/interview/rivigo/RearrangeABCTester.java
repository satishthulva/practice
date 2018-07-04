package practice.interview.rivigo;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RearrangeABCTester {
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
	
	private static boolean isOverlapping(int startOne, int endOne, int startTwo, int endTwo)
	{
		if(startOne == -1 || startTwo == -1)
			return false;
		
		return startOne <= endTwo && endOne >= startTwo;
	}
	
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

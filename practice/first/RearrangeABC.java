package practice.interview.rivigo;

/**
 * <pre>
 * Given a string made of only characters a, b, and c -> modify the string in such a way that
 * all a's come first(if any), then comes all b's(if any), and then comes all c's(if any)
 * 
 * Restrictions :
 * --------------
 * 		1. Do not use counter variables for a, b, and c in any manner - either individual or using a map
 * 		2. Do not use extra space to copy the full string
 * 		3. Do all this in one pass of the string. Not multiple passes.
 * </pre>
 * @author rebecca
 */
public class RearrangeABC {

	public static String rearrageABC(String str)
	{
		char[] arr = str.toCharArray();
		
		int aIndex = -1, bIndex = -1, cIndex = -1;
		
		// initialization of aIndex
		if(arr[0] == 'a')
		{
			aIndex = 0;
			while(aIndex + 1 < arr.length && arr[aIndex + 1] == 'a')
				aIndex+=1;
		}
		
		// initialization of cIndex
		if(arr[arr.length - 1] == 'c')
		{
			cIndex = arr.length - 1;
			while(cIndex - 1 >= 0 && arr[cIndex - 1] == 'c')
				cIndex-=1;
		}
		
		for(int i = aIndex + 1; i < arr.length && (cIndex == -1 || i < cIndex); i+=1)
		{
			char currentChar = arr[i];
			
			if(currentChar == 'a')
			{
				arr[i] = arr[aIndex+1];
				arr[aIndex+1]='a';
				aIndex+=1;
				
				// extending a's till they can
				while(aIndex < arr.length && arr[aIndex] == 'a')
					aIndex+=1;
				aIndex-=1;
				
				// to make sure we do only pass through array --> extending a's till we can
				if(i < aIndex)
					i = aIndex;
				
				if(arr[i] == 'c') // if the exchanged character is c --> need to process it again
					i-=1;
			}
			else if(currentChar == 'b')
			{
				if(bIndex == -1)
					bIndex = i;
			}
			else
			{
				if(cIndex == -1)
					cIndex = arr.length;
				arr[i] = arr[cIndex - 1];
				arr[cIndex - 1] = 'c';
				cIndex-=1;
				
				// extending c's till they can
				while(cIndex >= 0 && arr[cIndex] == 'c')
					cIndex-=1;
				cIndex+=1;
				
				if(arr[i] == 'a')// if the exchanged character is a --> need to process it again
					i-=1;
			}
			
//			System.out.println("ai = " + aIndex + "\tbi = " + bIndex + "\tci = " + cIndex + "\ti = " + i + "\t" + new String(arr));
		}
		
		return new String(arr);
	}
	
	public static void main(String[] args)
	{
		System.out.println("abcab" + "\t" + rearrageABC("abcab"));
		System.out.println("aba" + "\t" + rearrageABC("aba"));
		System.out.println("bba" + "\t" + rearrageABC("bba"));
		System.out.println("bab" + "\t" + rearrageABC("bab"));
		System.out.println("acbababab" + "\t" + rearrageABC("acbababab"));
		System.out.println("bcaabcbabacca" + "\t" + rearrageABC("bcaabcbabacca"));
		System.out.println("abbac" + "\t" + rearrageABC("abbac"));
		System.out.println("cbc" + "\t" + rearrageABC("cbc"));
	}
	
}

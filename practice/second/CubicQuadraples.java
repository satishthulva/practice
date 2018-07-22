
import java.util.HashMap;
import java.util.Map;

/**
 * Given a number N, find number of quadraples a, b, c, and such that a ^ 3 + b ^ 3 = c ^ 3 + d ^ 3
 * and 1 <= a, b, c, d <= N
 * 
 * @author rebecca
 */
public class CubicQuadraples {

	public static int findNumberOfCubicQuadraples(int number) {
		// for each possible pair, find the sum of cubes and maintain counter --> key is sum of cubes. value is number of pairs
		Map<Integer, Integer> allPossibleSumsOfaCubeAndbCube = new HashMap<>();
		
		for(int i = 1; i <= number; i+=1) {
			for(int j = i + 1; j <= number; j+=1) {
				int sumOfCubes = i * i * i + j * j * j;
				Integer numPairs = allPossibleSumsOfaCubeAndbCube.get(sumOfCubes);

				if(numPairs == null)
					numPairs = 0;
				
				allPossibleSumsOfaCubeAndbCube.put(sumOfCubes, numPairs + 1);
			}
		}
		
		// for each possible sum of cubes --> find the quadraples
		int numQuadraples = 0;
		for(int numPairs : allPossibleSumsOfaCubeAndbCube.values()) {
			if(numPairs > 1) {
				numQuadraples+= (numPairs * (numPairs - 1)) / 2;
			}
		}
		
		return numQuadraples;
	}
	
	public static void main(String[] args) {
		System.out.println(findNumberOfCubicQuadraples(12));
	}
	
}

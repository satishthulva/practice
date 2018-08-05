
/**
 * <pre>
 * Given an elavation map of bars of one unit width each with different height, try to find out
 * how much rain water can be trapped in this elevation map
 * </pre>
 * @author rebecca
 */
public class WaterTrappedArea {

	public static int findTrappedWaterArea(int[] barHeights) {
		int waterArea = 0;
		int numBars = barHeights.length;
		
		int[] maxHeightTill = new int[numBars];
		int[] maxHeightFrom = new int[numBars];
		
		maxHeightTill[0] = barHeights[0];
		maxHeightFrom[numBars - 1] = barHeights[numBars - 1];
		
		for(int i = 1; i < numBars; i+=1) {
			maxHeightTill[i] = barHeights[i - 1] > maxHeightTill[i - 1] ? barHeights[i - 1] : maxHeightTill[i - 1];
		}
		
		for(int i = numBars - 2; i >= 0; i-=1) {
			maxHeightFrom[i] = barHeights[i + 1] > maxHeightFrom[i + 1] ? barHeights[i + 1] : maxHeightFrom[i + 1];
		}
		
		for(int i = 1; i < numBars - 1; i+=1) {
			int trappableHeight = maxHeightTill[i] < maxHeightFrom[i] ? maxHeightTill[i] : maxHeightFrom[i];
			if(barHeights[i] < trappableHeight)
				waterArea+= trappableHeight - barHeights[i];
		}
		return waterArea;
	}
	
	public static void main(String[] args) {
		int[] heights = {2, 0, 3, 2};
		System.out.println(findTrappedWaterArea(heights));
		
		int[] heightsOne = {3, 0, 1, 2, 4};
		System.out.println(findTrappedWaterArea(heightsOne));
	}
	
}

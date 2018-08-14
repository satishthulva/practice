
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * <pre>
 * Assume that you are given an array of N unique integers with every value in the range [1, N].
 * Find the minimum number of swap operations needed to arrange the array elements in descending order.
 * </pre>
 * @author rebecca
 */
public class NumberOfSwaps {

	public static int minimumSwaps(List<Integer> popularity) {
		int numElems = popularity.size(), swaps = 0;

		List<NumberAndPosition> numbersAndPositions = new ArrayList<>();
		for (int i = 0; i < numElems; i += 1)
			numbersAndPositions.add(new NumberAndPosition(popularity.get(i), i));

		Collections.sort(numbersAndPositions, new NumberAndPositionComparator());

		boolean[] visitedNumbers = new boolean[numElems];
		for (int i = 0; i < numElems; i += 1) {
			if (visitedNumbers[i] || numbersAndPositions.get(i).getPosition() == i)
				continue;

			int cycleLength = 0;
			int j = i;
			while (!visitedNumbers[j]) {
				visitedNumbers[j] = true;
				j = numbersAndPositions.get(j).getPosition();
				cycleLength += 1;
			}

			swaps += (cycleLength - 1);
		}

		return swaps;
	}

	private static class NumberAndPositionComparator implements Comparator<NumberAndPosition> {
		@Override
		public int compare(NumberAndPosition o1, NumberAndPosition o2) {
			if (o1.getNumber() > o2.getNumber())
				return -1;

			else if (o1.getNumber() == o2.getNumber())
				return 0;

			else
				return 1;
		}
	}

	private static class NumberAndPosition {
		private final int number;

		private final int position;

		public NumberAndPosition(int number, int position) {
			super();
			this.number = number;
			this.position = position;
		}

		/**
		 * @return the number
		 */
		public int getNumber() {
			return number;
		}

		/**
		 * @return the position
		 */
		public int getPosition() {
			return position;
		}

	}

	public static void main(String[] args) {
		System.out.println(minimumSwaps(Arrays.asList(3, 1, 2)));
	}

}

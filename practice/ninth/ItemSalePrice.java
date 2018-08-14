
import java.util.Stack;
/**
 * <pre>
 * Assume that an array of integers represent the maximum retail price of items to be sold.
 * For some reason, the seller decided that every item will be sold at a price which is equal 
 * to the price of the item less the price of the first item in the array whose price is less 
 * than or equal to that item's price, if there is any item like that. If there is no item like
 * that, the item will be sold for its' original price.
 * 
 * Find out how much money the seller makes by selling all the items.
 * In addition to that, find out the indices of the items which are actually sold for MRP.
 * </pre>
 * @author rebecca
 */
public class ItemSalePrice {

	private static class PriceAndIndex
	{
		private final int price;
		
		private final int index;

		public PriceAndIndex(int price, int index) {
			super();
			this.price = price;
			this.index = index;
		}

		/**
		 * @return the price
		 */
		public int getPrice() {
			return price;
		}

		/**
		 * @return the index
		 */
		public int getIndex() {
			return index;
		}
	}
	
	static void finalPrice(int[] prices) {
		Stack<PriceAndIndex> itemsToBeSold = new Stack<>();
		PriceAndIndex currentItem = new PriceAndIndex(prices[0], 0);
		itemsToBeSold.push(currentItem);
		long totalPrice = 0l;
		
		for(int i = 1; i < prices.length; i+=1) {
			currentItem = new PriceAndIndex(prices[i], i);
			
			PriceAndIndex topItem = itemsToBeSold.isEmpty() ? null : itemsToBeSold.peek();
			if(topItem == null || topItem.getPrice() < currentItem.getPrice()) {
				itemsToBeSold.push(currentItem);
			} else {
				while(topItem != null && topItem.getPrice() >= currentItem.getPrice()) {
					itemsToBeSold.pop();
					totalPrice+=topItem.getPrice() - currentItem.getPrice();
					
					topItem = itemsToBeSold.isEmpty() ? null : itemsToBeSold.peek();
				}
				itemsToBeSold.push(currentItem);
			}
		}
		
		int[] indicesOfItemsSoldForOriginalPrice = new int[itemsToBeSold.size()];
		int index = indicesOfItemsSoldForOriginalPrice.length - 1;
		while(itemsToBeSold.size() > 0) {
			PriceAndIndex item = itemsToBeSold.pop();
			totalPrice+=item.getPrice();
			
			indicesOfItemsSoldForOriginalPrice[index] = item.getIndex();
			index-=1;
		}
		System.out.println(totalPrice);
		for(int i : indicesOfItemsSoldForOriginalPrice) {
			System.out.print(i + " ");
		}
	}

}

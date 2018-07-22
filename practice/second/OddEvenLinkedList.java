
/**
 * <pre>
 * You are given a linked list. Assume that the nodes are numbered from 1.
 * 
 * Modify it to contain all the odd numbered nodes first followed by even numbered nodes next.
 * 
 * e.g. Given the following linked list
 * 
 * Node number		 1				2				 3				 4 		
 * Node value		10		-->		8		--> 	30		-->		23
 * 
 * Output should be
 * 					10		-->		30		-->		8		-->		23
 * 
 * </pre>
 * @author rebecca
 */
public class OddEvenLinkedList {

	public static Node modifyToOddEvenList(Node node) {
		// at most one node
		if(node == null || node.getNext() == null)
			return node;
		
		// head to return
		Node linkedListHead = node;
		Node oddListLastNode = node;
		
		Node evenListFirstNode = node.getNext();
		Node evenListLastNode = node.getNext();
		
		int nodeCount = 2;
		
		Node temp = node.getNext().getNext();
		while(temp != null) {
			if(nodeCount % 2 == 0) {
				oddListLastNode.setNext(temp);
				oddListLastNode = temp;
			} else {
				evenListLastNode.setNext(temp);
				evenListLastNode = temp;
			}
			
			nodeCount+=1;
			temp = temp.getNext();
		}
		
		if(evenListFirstNode != null)
			oddListLastNode.setNext(evenListFirstNode);
		
		evenListLastNode.setNext(null);
		
		return linkedListHead;
	}
	
	
	private static void printList(Node head) {
		while(head != null)
		{
			System.out.println(head.getValue());
			head = head.getNext();
		}
	}
	
	public static void main(String[] args) {
		Node head = new Node(1);
		
		Node second = new Node(2);
		head.setNext(second);
		
		Node third = new Node(3);
		second.setNext(third);
		
		Node fourth = new Node(4);
		third.setNext(fourth);
		
		Node oddEven = modifyToOddEvenList(head);
		printList(oddEven);
	}
	
}

class Node
{
	private int value;
	
	private Node next;

	public Node(int value) {
		super();
		this.value = value;
	}

	/**
	 * @return the next
	 */
	public Node getNext() {
		return next;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(Node next) {
		this.next = next;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}
	
}

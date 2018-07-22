package practice.trees;

/**
 * Represents a node in binary tree
 * 
 * @author satish
 */
public class BinaryNode {
	/**
	 * Value stored at node
	 */
	private int value;
	/**
	 * Left child of the node
	 */
	private BinaryNode leftChild;
	/**
	 * Right child of the node
	 */
	private BinaryNode rightChild;
	
	public BinaryNode(int value, BinaryNode leftChild, BinaryNode rightChild) {
		super();
		this.value = value;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	
	public BinaryNode(int value) {
		this(value, null, null);
	}
	
	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}
	/**
	 * @return the leftChild
	 */
	public BinaryNode getLeftChild() {
		return leftChild;
	}
	/**
	 * @return the rightChild
	 */
	public BinaryNode getRightChild() {
		return rightChild;
	}

	/**
	 * @param leftChild the leftChild to set
	 */
	public void setLeftChild(BinaryNode leftChild) {
		this.leftChild = leftChild;
	}

	/**
	 * @param rightChild the rightChild to set
	 */
	public void setRightChild(BinaryNode rightChild) {
		this.rightChild = rightChild;
	}

}

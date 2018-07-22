package practice.trees;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Serialization utilities for Binary tree
 * 
 * @author satish
 */
public class BinaryTreeSerializer {

	/**
	 * Given the serialized version of a binary tree, construct and return the tree
	 * @param serializedTree	Serialized data for the binary tree
	 * @return	Binary tree corresponding to the serialized version of the tree
	 */
	public static BinaryNode deserializeTree(List<Integer> serializedTree) {
		if(serializedTree == null || serializedTree.isEmpty()) {
			return null;
		}
		
		Map<Integer, BinaryNode> indexToNodeMap = new HashMap<>();
		
		int treeSize = serializedTree.size() - 1;
		for(int i = treeSize; i > 0; i-=1) {
			Integer serializedNodeValue = serializedTree.get(i);
			BinaryNode currentNode = serializedNodeValue != null ? new BinaryNode(serializedNodeValue) : null;			
			indexToNodeMap.put(i, currentNode);
			
			if(currentNode == null)
				continue;
			
			int leftChildIndex = 2 * i;
			int rightChildIndex = 2 * i + 1;
			
			if(leftChildIndex <= treeSize) {
				currentNode.setLeftChild(indexToNodeMap.get(leftChildIndex));
			}
			
			if(rightChildIndex <= treeSize) {
				currentNode.setRightChild(indexToNodeMap.get(rightChildIndex));
			}
		}
		
		return indexToNodeMap.get(1);
	}

	/**
	 * Serialize the given binary tree
	 * 
	 * @param root	Root node of binary tree
	 * @return	Serialized data for given binary tree
	 */
	public static List<Integer> serializeTree(BinaryNode root) {
		List<Integer> serialized = new ArrayList<>();
		if(root == null)
			return serialized;
		
		int nodesWritten = 0, numNodes = (int)Math.pow(2, getTreeHeight(root)) - 1;
		
		// dummy value to use 1-based index for binary tree
		serialized.add(null);
		Queue<BinaryNode> nodesToProcess = new LinkedList<>();
		nodesToProcess.add(root);
		
		while(nodesWritten < numNodes) {
			BinaryNode currentNode = nodesToProcess.poll();
			serialized.add(currentNode == null ? null : currentNode.getValue());
			nodesWritten+=1;
			
			nodesToProcess.add(currentNode != null ? currentNode.getLeftChild() : null);
			nodesToProcess.add(currentNode != null ? currentNode.getRightChild() : null);
		}
		
		return serialized;
	}
	
	/**
	 * Get height of the tree with given node as root
	 * @param node	root of the tree
	 * @return	height of the tree with given node as root
	 */
	public static int getTreeHeight(BinaryNode node) {
		if(node == null)
			return 0;
		
		int leftSubTreeHeight = getTreeHeight(node.getLeftChild());
		int rightSubTreeHeight = getTreeHeight(node.getRightChild());
		
		return (leftSubTreeHeight < rightSubTreeHeight ? rightSubTreeHeight : leftSubTreeHeight) + 1;
	}
	
	/**
	 * Find number of nodes in the tree with given node as root
	 * @param node	root of the tree
	 * @return number of nodes in the tree with given node as root
	 */
	public static int getNumberOfNodes(BinaryNode node) {
		int numNodes = 0;
		if(node == null)
			return numNodes;
		
		numNodes+=1;
		numNodes+=getNumberOfNodes(node.getLeftChild());
		numNodes+=getNumberOfNodes(node.getRightChild());
		
		return numNodes;
	}
	

	/**
	 * Print in-order traversal of given binary tree
	 * @param root		root node of binary tree
	 * @param writer	writer to print in-order data
	 */
	public static void printInOrder(BinaryNode root, PrintWriter writer) {
		if(root == null)
			return;
		
		printInOrder(root.getLeftChild(), writer);// left
		writer.print(root.getValue() + " ");// node
		printInOrder(root.getRightChild(), writer);// right
	}
	
	public static void main(String[] args) {
		BinaryNode root = new BinaryNode(10);
		
		BinaryNode rootLeft = new BinaryNode(6);
		root.setLeftChild(rootLeft);
		
		BinaryNode rightOfRootLeft = new BinaryNode(8);
		rootLeft.setRightChild(rightOfRootLeft);
		
		BinaryNode leftOfRootLeftRight = new BinaryNode(7);
		rightOfRootLeft.setLeftChild(leftOfRootLeftRight);
		
		try(PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(System.out)))
		{
			printWriter.println("Before serialization");
			printInOrder(root, printWriter);
			printWriter.println();
			
			printWriter.println("Serialized");
			List<Integer> serialized = serializeTree(root);
			for(Integer nodeValue : serialized)
			{
				printWriter.print(nodeValue + " ");
			}
			printWriter.println();
			
			BinaryNode deserialized = deserializeTree(serialized);
			printWriter.println("After serialization");
			printInOrder(deserialized, printWriter);
			printWriter.println();
		}
	}
	
}

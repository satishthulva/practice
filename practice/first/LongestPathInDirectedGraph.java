package practice.interview.rivigo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * A matrix of unique positive integers is given - M[R][C]
 * From an element at index (i,j) --> the allowed movement are either to 
 * 						left (i, j-1), 
 * 						right (i, j+1), 
 * 						top (i-1, j), or 
 * 						bottom (i+1, j)
 * provided the element we're trying to move into is greater than the element we're moving from.
 * 
 * e.g. we can move from M[i][j] to M[i-1][j] only if M[i-1][j] > M[i][j]
 * 
 * Assume we can start any element in the matrix and continue till we can move. 
 * Find the longest possible path that we can take given the matrix.
 * 
 * e.g. 1	2	3
 * 		0	5	6
 * 		7	8	9
 * 
 * then the longest possible path is 0 -> 1 -> 2 -> 3 -> 6 -> 9
 * </pre>
 * @author rebecca
 */
public class LongestPathInDirectedGraph {

	/**
	 * Given the directed acyclic graph, find the longest path possible between any two nodes
	 * 
	 * @param nodesMap	graph
	 * @return	The longest path possible between any two nodes
	 */
	private List<Integer> findLongestPath(Map<Integer, Node> nodesMap)
	{
		Map<Integer, List<Integer>> longestPathsForNodeMap = new HashMap<>();
		List<Integer> longestPath = new ArrayList<>();
		
		for(Node node : nodesMap.values())
		{
			if(longestPathsForNodeMap.containsKey(node.getValue()))
				continue;
			
			List<Integer> currentNodeLongestPath = findLongestPathForNode(node, nodesMap, longestPathsForNodeMap);
			if(longestPath.size() < currentNodeLongestPath.size())
				longestPath = currentNodeLongestPath;
		}
		
		return longestPath;
	}
	
	/**
	 * Find longest path that is possible starting from given node
	 * 
	 * @param node				Node to start the path from
	 * @param graph				Graph
	 * @param longestPathMap	Cache containing the longest path possible for each node element
	 * @return	longest path that is possible starting from given node
	 */
	private List<Integer> findLongestPathForNode(Node node, Map<Integer, Node> graph, Map<Integer, List<Integer>> longestPathMap)
	{
		List<Integer> longestPath = new ArrayList<>();
		longestPath.add(node.getValue());// node itself
		
		List<Integer> currentLongestPath = new ArrayList<Integer>();
		// all connected nodes
		for(Node connectedNode : node.getConnectedNodes())
		{
			int connectedNodeValue = connectedNode.getValue();
			// if longest path for connected node is not cached --> cache it
			if(!longestPathMap.containsKey(connectedNodeValue))
			{
				findLongestPathForNode(connectedNode, graph, longestPathMap);
			}
			
			List<Integer> currentNodeLongestPath = longestPathMap.get(connectedNodeValue);
			// if longest path can be bettered --> improve it
			if(currentLongestPath.size() < currentNodeLongestPath.size())
			{
				currentLongestPath = new ArrayList<>(currentNodeLongestPath);
			}
		}
		
		// append longest of all connected nodes path to the current node
		longestPath.addAll(currentLongestPath);
		// cache it
		longestPathMap.put(node.getValue(), longestPath);
		
		// return the longest path
		return longestPath;
	}
	
	/**
	 * Prepare directed acyclic graph nodes as elements of given matrix, and directed edge between
	 * node X to node Y if we can move from node X to node Y
	 * 
	 * @param matrix	input matrix with unique positive elements
	 * @param rows		number of rows in the matrix
	 * @param columns	number of columns in the matrix
	 * @return	Graph as a map of node value to node
	 */
	private Map<Integer, Node> prepareNodes(int[][] matrix, int rows, int columns)
	{
		Map<Integer, Node> nodesMap = new HashMap<>();
		
		for(int i = 0; i < rows; i+=1)
		{
			for(int j = 0; j < columns; j+=1)
			{
				Node node = new Node(matrix[i][j]);
				nodesMap.put(matrix[i][j], node);
			}
		}
		
		for(int i = 0; i < rows; i+=1)
		{
			for(int j = 0; j < columns; j+=1)
			{
				Node node = nodesMap.get(matrix[i][j]);
				
				// left
				int x = i;
				int y = j - 1;
				if(isValidCell(x, y, rows, columns))
				{
					Node temp = nodesMap.get(matrix[x][y]);
					checkAndAddEdge(node, temp);
				}
				
				// right
				x = i;
				y = j + 1;
				if(isValidCell(x, y, rows, columns))
				{
					Node temp = nodesMap.get(matrix[x][y]);
					checkAndAddEdge(node, temp);
				}
				
				// top
				x = i - 1;
				y = j;
				if(isValidCell(x, y, rows, columns))
				{
					Node temp = nodesMap.get(matrix[x][y]);
					checkAndAddEdge(node, temp);
				}
				
				// bottom
				x = i + 1;
				y = j;
				if(isValidCell(x, y, rows, columns))
				{
					Node temp = nodesMap.get(matrix[x][y]);
					checkAndAddEdge(node, temp);
				}
			}
		}
		
		return nodesMap;
	}
	
	/**
	 * Check if an edge can be added from <code>from</code> to <code>to</code> node
	 * and add the edge if it can be added
	 * 
	 * @param from	Edge from
	 * @param to	Edge to
	 */
	private void checkAndAddEdge(Node from, Node to)
	{
		if(to.getValue() > from.getValue())
			from.addEdge(to);		
	}
	
	/**
	 * Is the given cell a valid element in matrix M
	 * @param i			row index of cell
	 * @param j			column index of cell
	 * @param rows		number of rows in the matrix
	 * @param columns	number of columns in the matrix
	 * @return	<code>true</code>, if cell is valid. <code>false</code>, otherwise
	 */
	private boolean isValidCell(int i, int j, int rows, int columns)
	{
		return j >= 0 && j < columns && i >=0 && i < rows;
	}
	
	
	public List<Integer> findLongestPath(int[][] matrix, int numRows, int numCols)
	{
		Map<Integer, Node> graph = prepareNodes(matrix, numRows, numCols);
		return findLongestPath(graph);
	}
	
	public static void main(String[] args)
	{
		LongestPathInDirectedGraph processor = new LongestPathInDirectedGraph();
		int[][] matrix = {{1, 2, 3}, {0, 5, 6}, {7, 8, 9}};
		List<Integer> longestPath = processor.findLongestPath(matrix, 3, 3);
		System.out.println(longestPath);
	}
}

/**
 * Node in the graph. There will be a directed edge from node X to another node Y 
 * if the movement from X to Y is allowed
 * 
 * @author rebecca
 */
class Node
{
	private int value;
	/**
	 * Nodes connected to
	 */
	private List<Node> edges = new ArrayList<>();
	
	public Node(int value)
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return value;
	}
	/**
	 * Nodes given node is connected to
	 * @return
	 */
	public List<Node> getConnectedNodes()
	{
		return edges;
	}
	/**
	 * Add edge to from node to the highValue node
	 * @param highValueNode
	 */
	public void addEdge(Node highValueNode)
	{
		edges.add(highValueNode);
	}	
}

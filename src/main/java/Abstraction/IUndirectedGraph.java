package Abstraction;

import Nodes.AbstractNode;
import Nodes.UndirectedNode;

public interface IUndirectedGraph extends IGraph {

	/**
	 * @return the number of edges in the graph
 	 */
	int getNbEdges();

	/**
	 * @return true if there is an edge between x and y
	 */
	boolean isEdge(UndirectedNode x, UndirectedNode y);

	/**
	 * Removes edge (x,y) if there exists one
     */
	void removeEdge(UndirectedNode x, UndirectedNode y);

	/**
	 * Adds edge (x,y), requires that nodes x and y already exist
     */
	void addEdge(UndirectedNode x, UndirectedNode y);

}

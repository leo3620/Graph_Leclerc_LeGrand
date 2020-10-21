package Abstraction;

import Nodes.AbstractNode;
import Nodes.DirectedNode;

public interface IDirectedGraph extends IGraph {

	/**
	 * @return the number of arcs in the graph
 	 */
	int getNbArcs();

	/**
	 * @return true if arc (from,to) exists in the graph
 	 */
	boolean isArc(DirectedNode from, DirectedNode to);

	/**
	 * Removes the arc (from,to), if it exists
 	 */
	void removeArc(DirectedNode from, DirectedNode to);

	/**
	 * Adds the arc (from,to) if it is not already present in the graph, requires the existing of nodes from and to 
 	 */
	void addArc(DirectedNode from, DirectedNode to);

	/**
	 * @return a new graph implementing IDirectedGraph interface which is the inverse graph of this
 	 */
	IDirectedGraph computeInverse();
}

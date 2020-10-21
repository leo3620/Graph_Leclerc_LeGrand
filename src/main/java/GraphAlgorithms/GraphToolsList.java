package GraphAlgorithms;

import Abstraction.AbstractListGraph;
import AdjacencyList.DirectedGraph;
import Nodes.AbstractNode;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphToolsList  extends GraphTools {

	private static int _DEBBUG =0;

	private static int[] visite;
	private static int[] debut;
	private static int[] fin;
	private static List<Integer> order_CC;
	private static int cpt=0;

	//--------------------------------------------------
	// 				Constructors
	//--------------------------------------------------

	public GraphToolsList(){
		super();
	}

	// ------------------------------------------
	// 				Accessors
	// ------------------------------------------

	// ------------------------------------------
	// 				Methods
	// ------------------------------------------

	public static void breadthFirstSearch(AbstractListGraph<AbstractNode> graph) {
		Boolean[] mark = new Boolean[graph.getNbNodes()];

		for (int i = 0; i < graph.getNbNodes(); i++) {
			mark[i] = false;
		}

		AbstractNode s = graph.getNodes().get(0);
		mark[s.getLabel()] = true;

		Queue<AbstractNode> toVisit = new LinkedList<>();
		toVisit.add(s);
		while (!toVisit.isEmpty()) {
			AbstractNode v = toVisit.remove();
			for (AbstractNode node : v.getSuccOrNeighbour()) {
				if (!mark[node.getLabel()]) {
					mark[node.getLabel()] = true;
					toVisit.add(node);
				}
			}
			System.out.println(toVisit.toString());
		}
	}

	/*
	public void breadthFirstSearch(AbstractListGraph<UndirectedNode> graph) {
		Boolean[] mark = new Boolean[graph.getNbNodes()];

		UndirectedNode s = graph.getNodes().get(0);
		mark[s.getLabel()] = true ;

		Queue<UndirectedNode> toVisit = new PriorityQueue<>();
		toVisit.add(s) ;
		while (!toVisit.isEmpty()) {
			UndirectedNode v = toVisit.remove() ;
			for (Entry<UndirectedNode, Integer> node : v.getNeighbours().entrySet()) {
				UndirectedNode w = node.getKey();
				if(!mark[w.getLabel()]) {
					mark[w.getLabel()] = true;
					toVisit.add(w) ;
				}
			}
		}
	}
	/
*/


	public static void main(String[] args) {
		int[][] Matrix = GraphTools.generateGraphData(10, 20, false, false, true, 100001);
		GraphTools.afficherMatrix(Matrix);
		AbstractListGraph al = new DirectedGraph(Matrix);
		System.out.println(al);
		breadthFirstSearch(al);

		// A completer
	}
}

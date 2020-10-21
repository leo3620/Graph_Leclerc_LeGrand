package GraphAlgorithms;

import Abstraction.AbstractListGraph;
import AdjacencyList.DirectedGraph;
import Nodes.AbstractNode;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphToolsList  extends GraphTools {

	private static final int _DEBBUG =0;

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
		boolean[] mark = new boolean[graph.getNbNodes()];

		AbstractNode s = graph.getNodes().get(0);
		mark[s.getLabel()] = true;

		Queue<AbstractNode> toVisit = new LinkedList<>();
		toVisit.add(s);
		while (!toVisit.isEmpty()) {
			AbstractNode v = toVisit.remove();
			System.out.print(v + " ");
			for (AbstractNode node : v.getSuccOrNeighbour()) {
				if (!mark[node.getLabel()]) {
					mark[node.getLabel()] = true;
					toVisit.add(node);
				}
			}
		}
	}

	public static void depthFirstSearch(AbstractListGraph<AbstractNode> graph) {
		Set<AbstractNode> marked = new HashSet<>();

		AbstractNode firstNode = graph.getNodes().get(0);
		marked.add(firstNode);

		for (AbstractNode node : graph.getNodes()) {
			if (firstNode.getSuccOrNeighbour().contains(node)) {
				if (!marked.contains(node)) {
					exploreNode(firstNode, marked);
				}
			}
		}
	}

	public static void exploreNode(AbstractNode node, Set<AbstractNode> marked) {
		marked.add(node);
		System.out.print(node + " ");

		for (AbstractNode neighbour : node.getSuccOrNeighbour()) {
			if (!marked.contains(neighbour)) {
				exploreNode(neighbour, marked);
			}
		}
	}

	public static void main(String[] args) {
		int[][] Matrix = GraphTools.generateGraphData(10, 20, false, false, true, 100001);
		GraphTools.afficherMatrix(Matrix);
		AbstractListGraph al = new DirectedGraph(Matrix);
		//System.out.println(al);
		System.out.print("Breadth First Search : ");
		breadthFirstSearch(al);
		System.out.println("\n=======================================\n");
		System.out.print("Depth First Search : ");
		depthFirstSearch(al);

		// A completer
	}
}

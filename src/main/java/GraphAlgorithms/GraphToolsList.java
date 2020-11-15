package GraphAlgorithms;

import static java.util.stream.Collectors.toList;

import Abstraction.AbstractListGraph;
import AdjacencyList.DirectedGraph;
import AdjacencyList.DirectedValuedGraph;
import AdjacencyList.UndirectedGraph;
import AdjacencyList.UndirectedValuedGraph;
import Collection.Triple;
import Nodes.AbstractNode;
import Nodes.DirectedNode;
import Nodes.UndirectedNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

public class GraphToolsList extends GraphTools {

	private static final int _DEBBUG = 0;

	private static int[] visite;
	private static int[] debut;
	private static Map<DirectedNode, Integer> fin;
	private static List<Integer> order_CC;
	private static int cpt = 0;

	//--------------------------------------------------
	// 				Constructors
	//--------------------------------------------------

	public GraphToolsList() {
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

	static void explorerSommet(AbstractNode s, Set<AbstractNode> a,
			List<AbstractNode> uneComposante) {
		a.add(s);
		uneComposante.add(s);
		visite[s.getLabel()] = 1;
		debut[s.getLabel()] = cpt;
		cpt++;
		for (AbstractNode t : s.getSuccOrNeighbour()) {
			if (!a.contains(t)) {
				explorerSommet(t, a, uneComposante);
			}
		}
		visite[s.getLabel()] = 2;
		fin.put((DirectedNode) s, cpt);
		cpt++;
	}

	static List<List<AbstractNode>> explorerGraphe(DirectedGraph graph, List<DirectedNode> sommets) {
		Set<AbstractNode> atteint = new HashSet<>();

		debut = new int[graph.getNbNodes()];
		visite = new int[graph.getNbNodes()];
		fin = new HashMap<>();
		cpt = 0;
		List<List<AbstractNode>> composante = new ArrayList<>();

		for (AbstractNode s : sommets) {
			if (!atteint.contains(s)) {
				ArrayList<AbstractNode> uneComposante = new ArrayList<>();
				composante.add(uneComposante);
				explorerSommet(s, atteint, uneComposante);
			}
		}

		return composante;
	}

	static void getConnex(DirectedGraph graph) {
		explorerGraphe(graph, graph.getNodes());
		DirectedGraph graphInverse = (DirectedGraph) graph.computeInverse();

		// Inverse the map
		List<DirectedNode> sorted = fin
				.entrySet()
				.stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				.map(Entry::getKey)
				.collect(toList());

		System.out.println(explorerGraphe(graphInverse, sorted));
	}

	static void prim(UndirectedValuedGraph graph, UndirectedNode node) {
		BinaryHeapEdge binaryHeapEdge = new BinaryHeapEdge();
		List<UndirectedNode> visited = new ArrayList<>();
		visited.add(node);

		UndirectedNode actualNode = node;
		while (visited.size() != graph.getNodes().size()) {
			for(UndirectedNode neighbour : actualNode.getNeighbours().keySet()) {
				binaryHeapEdge.insert(actualNode, neighbour, actualNode.getNeighbours().get(neighbour));
			}
			Triple<UndirectedNode, UndirectedNode, Integer> edge = binaryHeapEdge.remove();
			while (visited.contains(edge.getSecond())) {
				edge = binaryHeapEdge.remove();
			}
			System.out.println(edge);
			visited.add(edge.getSecond());
			actualNode = edge.getSecond();
		}

	}

	static void bellman(DirectedValuedGraph graph) {
		int n = graph.getNbNodes();
		List<Integer> predecActuel = new ArrayList<>();
		List<Integer> predecAvant = new ArrayList<>();
		List<Integer> valeursActuel = new ArrayList<>();
		List<Integer> valeursAvant = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			predecActuel.add(-1);
			predecAvant.add(-1);
			valeursActuel.add(Integer.MAX_VALUE);
			valeursAvant.add(Integer.MAX_VALUE);
		}

		predecAvant.add(0, 0);
		valeursAvant.add(0, 0);

		for (int k = 0; k <= n; k++) { // parcours
			for (int v = 0; v < n; v++) { // noeuds
				int d = valeursAvant.get(v);
				predecActuel.add(v, predecAvant.get(v));
				for (DirectedNode u : graph.getNodeOfList(new DirectedNode(v)).getPreds().keySet()) {
					if (valeursAvant.get(u.getLabel()) + u.getSuccs().get(graph.getNodeOfList(new DirectedNode(v))) < d) {
						predecActuel.add(v, u.getLabel());
						d = predecAvant.get(u.getLabel()) + u.getSuccs().get(graph.getNodeOfList(new DirectedNode(v)));
					}
				}
				valeursActuel.add(v, d);
			}

			if (k == n) {
				System.out.println(valeursAvant);
				System.out.println(valeursActuel);
				System.out.println(valeursAvant.equals(valeursActuel));
			} else {
				predecAvant = predecActuel;
				predecActuel = new ArrayList<>();
				valeursAvant = valeursActuel;
				valeursActuel = new ArrayList<>();
			}
		}
	}

	/**
	 * Djikstra method to get path between 2 nodes
	 *
	 * @param graph Matrix graph
	 * @param n     Number of vertices
	 * @param src   source node
	 * @param dst   destination node
	 */
	static void djikstra(int[][] graph, int n, int src, int dst) {
		int[] v = new int[n];
		int[] p = new int[n];
		Boolean[] b = new Boolean[n];
		Arrays.fill(b, Boolean.FALSE);
		Arrays.fill(v, Integer.MAX_VALUE);
		v[src] = 0;
		int x = 0;

		while (!Arrays.stream(b).allMatch(s -> s.equals(true))) {
			int min = Integer.MAX_VALUE;
			for (int y = 0; y < n; y++) {
				if (!b[y] && v[y] < min) {
					x = y;
					min = v[y];
				}
			}
			if (min < Integer.MAX_VALUE) {
				b[x] = true;
				for (int y = 0; y < n; y++) {
					// Add !=0 because in our graph representation 0 means no path
					if (!b[y] && v[x] + graph[x][y] < v[y] && graph[x][y] != 0) {
						v[y] = v[x] + graph[x][y];
						p[y] = x;
					}
				}
				// If all accessible nodes from origin is done
			} else {
				break;
			}
		}
		int h = dst;
		Deque<Integer> path = new LinkedList<>();
		path.add(h);
		while (h != src) {
			if (v[h] == Integer.MAX_VALUE) {
				throw new IndexOutOfBoundsException("Impossible d'attendre ce sommet");
			}
			path.addFirst(p[h]);
			h = p[h];
		}
		System.out.println(Arrays.toString(v));
		System.out.println("\n=======================================\n");
		System.out.print("Chemin :  ");
		System.out.println(path.toString());
	}


	public static void main(String[] args) {
		int[][] Matrix = GraphTools.generateGraphData(10, 20, false, false, true, 100001);
		int[][] MatrixUndirected = GraphTools.generateValuedGraphData(10, false, true, true, false, 100001);
		int[][] MatrixValued = GraphTools.generateValuedGraphData(10, false, false, true, false, 100000);
		GraphTools.afficherMatrix(Matrix);
		AbstractListGraph al = new DirectedGraph(Matrix);
		UndirectedValuedGraph alUndirected = new UndirectedValuedGraph(MatrixUndirected);
		DirectedValuedGraph dvg = new DirectedValuedGraph(MatrixValued);
		//System.out.println(al);
		System.out.print("Breadth First Search : ");
		breadthFirstSearch(al);
		System.out.println("\n=======================================\n");
		System.out.print("Depth First Search : ");
		depthFirstSearch(al);
		System.out.println("\n=======================================\n");
		System.out.print("Get connexity : ");
		getConnex((DirectedGraph) al);
		System.out.println("\n=======================================\n");
		System.out.print("Djikstra ");
		djikstra(Matrix, 10, 0, 8);
		System.out.println("\n=======================================\n");
		System.out.print("Bellman ");
		bellman(dvg);
		System.out.println("\n=======================================\n");
		System.out.print("Prim ");
		System.out.print(alUndirected);
		prim(alUndirected, alUndirected.getNodeOfList(new UndirectedNode(0)));
	}
}

package GraphAlgorithms;


public class BinaryHeap {

    private int[] nodes;
    private int pos;

    public BinaryHeap() {
        this.nodes = new int[32];
        for (int i = 0; i < nodes.length; i++) {
            this.nodes[i] = Integer.MAX_VALUE;
        }
        this.pos = 0;
    }

    public void resize() {
        int[] tab = new int[this.nodes.length + 32];
        for (int i = 0; i < nodes.length; i++) {
            tab[i] = Integer.MAX_VALUE;
        }
        System.arraycopy(this.nodes, 0, tab, 0, this.nodes.length);
        this.nodes = tab;
    }

    public boolean isEmpty() {
        return pos == 0;
    }

    public void insert(int element) {
    	this.nodes[this.pos] = element;

        int tmpPos = this.pos;

    	while (Math.round((tmpPos-1)/2) >= 0 && element < this.nodes[Math.round((tmpPos-1)/2)]) {
    	    int parentIndex = Math.round((tmpPos-1)/2);
            this.swap(parentIndex, tmpPos);
            tmpPos = parentIndex;
        }
        this.pos++;
    }

    public int remove() {

        this.swap(0, this.pos-1);
        int value = this.nodes[this.pos-1];
        this.nodes[this.pos-1] = Integer.MAX_VALUE;

        int tmpPos = 0;
        int bestChild = this.getBestChildPos(tmpPos);
        while (bestChild != Integer.MAX_VALUE  && this.nodes[bestChild] < this.nodes[tmpPos]) {
            this.swap(tmpPos, bestChild);
            tmpPos = bestChild;
            bestChild = this.getBestChildPos(tmpPos);
        }

        this.pos--;
    	return value;
    }

    private int getBestChildPos(int src) {
        if (isLeaf(src)) { // the leaf is a stopping case, then we return a default value
            return Integer.MAX_VALUE;
        } else if ((src*2)+2 < this.pos-1) {
                // deux
                int valueLeft = this.nodes[(src*2)+1];
                int valueRight = this.nodes[(src*2)+2];

                return (valueLeft <= valueRight) ? (src*2)+1 : (src*2)+2;
        } else {
            // un
            return (src*2)+1;
        }
    }

    
    /**
	 * Test if the node is a leaf in the binary heap
	 * 
	 * @returns true if it's a leaf or false else
	 * 
	 */	
    private boolean isLeaf(int src) {
    	return (2*src)+1 > this.pos-1;
    }

    private void swap(int father, int child) {
        int temp = nodes[father];
        nodes[father] = nodes[child];
        nodes[child] = temp;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < pos; i++) {
            s.append(nodes[i]).append(", ");
        }
        return s.toString();
    }

    /**
	 * Recursive test to check the validity of the binary heap
	 * 
	 * @returns a boolean equal to True if the binary tree is compact from left to right
	 * 
	 */
    public boolean test() {
        return this.isEmpty() || testRec(0);
    }

    private boolean testRec(int root) {
        if (isLeaf(root)) {
            return true;
        } else {
            int left = 2 * root + 1;
            int right = 2 * root + 2;
            if (right >= pos) {
                return nodes[left] >= nodes[root] && testRec(left);
            } else {
                return nodes[left] >= nodes[root] && testRec(left) && nodes[right] >= nodes[root] && testRec(right);
            }
        }
    }

    public static void main(String[] args) {
        BinaryHeap jarjarBin = new BinaryHeap();
        System.out.println(jarjarBin.isEmpty()+"\n");
        int k = 20;
        int m = k;
        int min = 2;
        int max = 20;
        while (k > 0) {
            int rand = min + (int) (Math.random() * ((max - min) + 1));
            System.out.print("insert " + rand);
            jarjarBin.insert(rand);            
            k--;
        }
     // A completer
        System.out.println("\n" + jarjarBin);
        System.out.println(jarjarBin.test());
        System.out.println("\n====================  Remove ======================");
        System.out.println("removed : " + jarjarBin.remove());
        System.out.println("\n" + jarjarBin);
        System.out.println(jarjarBin.test());
    }

}

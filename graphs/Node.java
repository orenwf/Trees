package graphs;

import java.util.ArrayList;

public class Node {
	
	private ArrayList<Node> neighbors;
	
	public Node(int m, Node[] n) {
		neighbors = new ArrayList<>();
		for (int i=0; i<m; i++) {
			try { neighbors.add(n[i]); } 
			catch (NullPointerException npe) { neighbors.add(null); }
		}
	}
	
	@SuppressWarnings("unchecked")
	protected <T extends Node> 
	T neighbor(int i) { return (T) neighbors.get(i); }
	
	protected void set(int i, Node n) { neighbors.set(i, n); }
}

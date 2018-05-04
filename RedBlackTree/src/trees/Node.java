package trees;

import java.util.ArrayList;

public class Node<K extends Comparable<K>, V> {
	
	private ArrayList<Node<K,V>> neighbors;
	private K key;
	private V value;
	
	public Node(K k, V v, int m, Node<K,V>[] n) {
		key = k;
		value = v;
		neighbors = new ArrayList<>();
		for (int i=0; i<m; i++) {
			try { neighbors.add(n[i]); } 
			catch (NullPointerException npe) { neighbors.add(null); }
		}
	}
	
	@SuppressWarnings("unchecked")
	protected <T extends Node<K,V>> 
	T neighbor(int i) { return (T) neighbors.get(i); }
	
	protected void set(int i, Node<K,V> n) { neighbors.set(i, n); }
	
	protected K key() { return key; }
	
	protected V val() { return value; }
	
	public final K viewKey() { return key; }
	
	public final V viewVal() { return value; }
}

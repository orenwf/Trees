package trees;

public class RBTree <K extends Comparable<K>,V> 
extends Node <K,V> 
implements RedBlack, BinaryTree <Node<K,V>>, DynamicSet <K,V> {
	
	private nc color;
	@SuppressWarnings("rawtypes")
	private static Node nil = new Nil();

	@SafeVarargs
	public RBTree (K k, V v, Node<K,V> ... n) {
		super(k, v, 3, n);
		color = nc.RED;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <K extends Comparable<K>,V> 
	RBTree<K,V> init(K k, V v) {
		RBTree<K,V> rbt = new RBTree(k, v, new Node[] {nil, nil, nil});
		rbt.swapColor();
		return rbt;
	}
	
	private RBTree<K,V> find(K k) throws KeyNotFoundException {
		RBTree<K,V> t;
		if (this.viewKey().compareTo(k) < 0) {
			t = left();
			if (t == nil) throw new KeyNotFoundException(this, this.viewKey().toString());
			return t.find(k);
		} else if (this.viewKey().compareTo(k) > 0) {
			t = right();
			if (t == nil) throw new KeyNotFoundException(this, this.viewKey().toString());
			return t.find(k);
		} else return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void add(K k, V v) throws DuplicateKeyException {
		RBTree<K,V> t;
		try { 
			t = find(k); 
			throw new DuplicateKeyException(t.viewKey().toString(), t.viewVal().toString()); 
		} catch (KeyNotFoundException knf) { t = knf.prev; }
		if (t.viewKey().compareTo(k) < 0)
			t.setLeft(new RBTree<K, V>(k, v, new Node[] { t, nil, nil }));
		else t.setRight(new RBTree<K, V>(k, v, new Node[] { t, nil, nil }));
		t.balance();
	}

	@Override
	public V remove(K k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() { return 1 + left().size() + right().size(); }

	@Override
	public RBTree<K, V> parent() { return this.<RBTree<K,V>>neighbor(0); }
	
	private void setParent(RBTree<K,V> n) { set(0, n); }

	@Override
	public RBTree<K, V> left() { return this.<RBTree<K,V>>neighbor(1); }
	
	private boolean isLeft() { 
		RBTree<K,V> t = parent();
		if (t == nil) return false;
		return this == t.left(); 
	}

	private void setLeft(RBTree<K,V> n) { set(1, n); }
	
	@Override
	public RBTree<K, V> right() { return this.<RBTree<K,V>>neighbor(2); }
	
	private boolean isRight() { 
		RBTree<K,V> t = parent();
		if (t == nil) return false;
		return this == t.right(); 
	}
	
	private void setRight(RBTree<K,V> n) { set(2, n); }
	
	private RBTree<K,V> uncle() throws KeyNotFoundException {
		RBTree<K,V> p = parent();
		if (p == nil) throw new KeyNotFoundException(this, this.viewKey().toString());
		RBTree<K,V> g = p.parent();
		if (g == nil) throw new KeyNotFoundException(p, p.viewKey().toString());
		if (p.isLeft()) return g.right();
		else return g.left();
	}
	
	private boolean isInside() { 
		return isRight() && parent().isLeft() || isLeft() && parent().isRight();						
	}

	@Override
	public nc getColor() { return color; }

	@Override
	public void swapColor() {
		if (color == nc.RED) color = nc.BLACK;
		else color = nc.RED;
	}
	
	private void balance() { 
		// red uncle
		// recolor
		// black uncle 1
		//  rotate()
		// black uncle 2
		// rotate()
		// recolor
	}
	
	private void rotate() { }

	@Override
	public void modify(K k, V v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public K generate() {
		// TODO Auto-generated method stub
		return null;
	}

}

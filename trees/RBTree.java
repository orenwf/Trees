package trees;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import graphs.Node;

public class RBTree <K extends Comparable<K>,V extends Comparable<V>> 
extends Node 
implements BinaryTree, Contents<K,V>, DynamicSet <K,V>, RedBlack {
	
	private static Node nil = new Nil();
	private K key;
	private V val;
	private nc color;

	@SafeVarargs
	public RBTree (K k, V v, Node ... n) {
		super(3, n);
		color = nc.RED;
	}
	
	public static <K extends Comparable<K>,V extends Comparable<V>> 
	RBTree<K,V> init(K k, V v) {
		RBTree<K,V> rbt = new RBTree<K,V>(k, v, new Node[] {nil, nil, nil});
		rbt.swapColor();
		return rbt;
	}

	@Override
	public RBTree<K, V> root() {
		if (isLeft() || isRight()) return parent();
		return this;
	}

	@Override
	public int count() { return 1 + left().count() + right().count(); }

	@Override
	public RBTree<K,V> order(int x) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 0;
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

	@Override
	public void add(K k, V v) throws DuplicateKeyException {
		try { 
			RBTree<K,V> t = find(k); 
			throw new DuplicateKeyException(t.viewKey().toString(), t.viewVal().toString()); 
		} catch (KeyNotFoundException knf) {
			@SuppressWarnings("unchecked")
			RBTree<K,V> t = (RBTree<K, V>) knf.prev;
			RBTree<K,V> c = new RBTree<>(k, v, new Node[] { knf.prev, nil, nil });
			if (t.viewKey().compareTo(k) < 0)
				t.setLeft(c);
			else t.setRight(c);
			c.balance();
		}
	}
	
	private void adopt(BiConsumer<RBTree<K,V>, Node> f, Node t) {
		f.accept(this, t);
	}
	
	private BiConsumer<RBTree<K,V>, RBTree<K,V>> left = (p, c) -> {
		p.setLeft(c);
		c.setParent(p);
	};
	
	private BiConsumer<RBTree<K,V>, RBTree<K,V>> right = (p, c) -> {
		p.setRight(c);
		c.setParent(p);
	};
	
	@Override
	public V remove(K k) {
		// TODO Auto-generated method stub
		return null;
	}

	private RBTree<K, V> parent() { return this.<RBTree<K,V>>neighbor(0); }
	
	private void setParent(RBTree<K,V> n) { set(0, n); }

	private RBTree<K, V> left() { return this.<RBTree<K,V>>neighbor(1); }
	
	private boolean isLeft() { 
		RBTree<K,V> t = parent();
		if (t == nil) return false;
		return this == t.left(); 
	}

	private void setLeft(RBTree<K,V> n) { set(1, n); }
	
	private RBTree<K, V> right() { return this.<RBTree<K,V>>neighbor(2); }
	
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
	
	private RBTree<K,V> lmrd() { /*TODO*/ return null; }
	
	private RBTree<K,V> fral() { /*TODO*/ return null; }
	
	private RBTree<K,V> rmld() { /*TODO*/ return null; }
	
	private RBTree<K,V> flar() { /*TODO*/ return null; }
	
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
	
	private void balance() {	// start by assuming tree was balanced prior this node checked
		RBTree<K,V> g, u;
		// look for an uncle
		try { u = uncle(); } catch (KeyNotFoundException knf) {
			// if no uncle, find out whether b/c this is root or child of root
			// if it's root, color it black if it's red and return
			if (knf.prev == this)
				if (getColor() == nc.RED) { 
					swapColor();
					return;
				} else return;
			else if (parent().getColor() == nc.RED) {
			// if it's child of root, color root black if it's red and return
				parent().swapColor();
				return;
			} else return;	
		} // if we made it here we found the uncle, so there must be a grandparent g
		g = parent().parent();
		if (u.getColor() == nc.RED) {			// if it's a red uncle
			parent().swapColor();				// parent must be red, turn it black
			u.swapColor();						// turn uncle black
			g.swapColor();						// g must have been black, turn it red
			g.balance();						// recursive call to balance the tree above
		} else {								// black uncle
			if (isInside()) {
				rotate();						// if this node is an inside-facer, rotate it up
				rotate();						// and again rotate it to the top
			} else parent().rotate();			// otherwise just rotate it's parent up
		}
		g.swapColor();							// re-color g to red, new subtree root to black
		g.parent().swapColor();
		g.parent().balance();					// recursively balance the tree above
	}
	
	private void rotate() {
		RBTree<K,V> p, g, child;
		// Using functional interfaces for less left() / right() logic testing
		Function<RBTree<K,V>,RBTree<K,V>> direction; 					// apply(this) -> RBTree
		BiConsumer<RBTree<K,V>,RBTree<K,V>> setDirection, setOpposite; 	// accept(this, other) -> void
		// Initializing functional interfaces with correct lambda objects
		if (isLeft()) {
			direction = x -> x.right();
			setDirection = (x,y) -> x.setRight(y);
			setOpposite = (x,y) -> x.setLeft(y);
		} else if (isRight()) {
			direction = x -> x.left();
			setDirection = (x,y) -> x.setLeft(y);
			setOpposite = (x,y) -> x.setRight(y);
		} else return;
		p = parent();
		g = p.parent();
		if (direction.apply(this) != nil) {			// if there is an inside child
			child = direction.apply(this);			
			setOpposite.accept(p,child);			// move it to be the new inside child of p
			child.setParent(p);
		}
		setDirection.accept(this,p);				// set p as the new child of this
		if (p.isRight()) g.setRight(this); 			// if p is a right child of g, make this the new one
		else if (p.isLeft()) g.setLeft(this);		// same check if left child
		else ;										// else g is nil, no op
		p.setParent(this);							// make this the parent of p
		setParent(g);								// make g the new parent of this
	}

	@Override
	public void modify(K k, V v) throws KeyNotFoundException {
		RBTree<K,V> t = find(k);
		t.setVal(v);
	}

	@Override
	public K generate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RBTree<K,V> predecessor(BinaryTree x) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RBTree<K,V> successor(BinaryTree x) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BinaryTree> inOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BinaryTree> levelOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BinaryTree> preOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BinaryTree> postOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V lookUp(K k) throws KeyNotFoundException { return find(k).viewVal(); }

	@Override
	public K viewKey() { return key; }

	@Override
	public V viewVal() { return val; }

	@Override
	public void setKey(K k) { key = k; }

	@Override
	public void setVal(V v) { val = v; }

}

package rbtree;

public class INode <K extends Comparable<K>, W extends Comparable<W>>
extends RBNode<K,W>
{
	private RBNode<K,W> parent;
	private RBNode<K,W> left;
	private RBNode<K,W> right;
	private K key;
	private W weight;
	
	public INode(K k, W w) { 
		key = k;
		weight = w;
		color = nodecolor.RED;
	}
	
	public String toString() { return "Key: "+key+"; Weight: "+weight+"."; }
	
	K getKey() { return key; }
	W getWeight() { return weight; }
	
	boolean hasLeft() { return left instanceof INode; }

	boolean isLeft() {
		try {
			INode<K,W> t = parent();
			return (this == t.left());
		} catch (NilNodeException nne) { return false; }
	}
	
	INode<K, W> left() throws NilNodeException { 
		if (this.hasLeft()) {
			return (INode<K,W>)left;
		} else throw new NilNodeException(this, this.left, "Left child is a nil node");
	}
	
	void setLeft(RBNode<K,W> l) { left = l; }

	boolean hasRight() { return right instanceof INode; }
	
	INode<K,W> right() throws NilNodeException {
		if (this.hasRight()) {
			return (INode<K,W>)right;
		} else throw new NilNodeException(this, this.right, "Right child is a nil node");
	}
	
	boolean isRight() {
		try {
			INode<K,W> t = parent();
			return (this == t.right());
		} catch (NilNodeException nne) { return false; }
	}
	
	void setRight(RBNode<K,W> r) { right = r; }
	
	boolean hasParent() { return parent instanceof INode; }
	
	INode<K,W> parent() throws NilNodeException {
		if (this.hasParent()) {
			return (INode<K,W>)parent; 
		} else throw new NilNodeException(this, this.parent, "Parent is a nil node");
	}
	
	void setParent(RBNode<K,W> p) { parent = p; }
	
	RBNode<K,W> uncle() throws NilNodeException {
		INode<K,W> p, g;
		p = parent();
		g = p.parent();
		try {
			if (p.isLeft()) return g.right();
			else return g.left();
		} catch (NilNodeException nne) { return nne.nil; }
	}
	
	boolean isInside() {
		INode<K,W> p;
		try { p = parent(); }
		catch (NilNodeException nne) { return false; }
		return
				(	isRight() && p.isLeft()	)
		||		(	isLeft() && p.isRight()	)	;
	}

	@Override
	INode<K,W> traverse(K other) throws NilNodeException {
		int x = this.key.compareTo(other);
		if (x < 0) return left(); 
		else if (x > 0) return right(); 
		else return this;
	}
	
	INode<K,W> rmld() throws NilNodeException {	// rightmost left descendant
		INode<K,W> t = this.left();
		while (t.hasRight()) t = t.right();
		return t;
	}
	
	INode<K,W> flar() throws NilNodeException {	// first left ancestor on the right
		INode<K,W> t = this;
		while (t.isLeft()) t = t.parent();
		return t.parent();
	}
	
	INode<K,W> lmrd() throws NilNodeException {	// leftmost right descendant
		INode<K,W> t = this.right();
		while (t.hasLeft()) t = t.left();
		return t;
	}
	
	INode<K,W> fral() throws NilNodeException {	// first right ancestor on the left
		INode<K,W> t = this;
		while (t.isRight()) t = t.parent();
		return t.parent();
	}
	
	void addChild(K k, W w) throws DuplicateKeyException {
		INode<K,W> c = new INode<>(k,w);
		int x = this.key.compareTo(k);
		if (x < 0) { 
			setLeft(c);
			c.setParent(this);
		}
		else if (x == 0) throw new DuplicateKeyException(k.toString(), this.weight.toString());
		else {
			setRight(c);
			c.setParent(this);
		}
	}
}

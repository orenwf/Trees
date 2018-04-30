package rbtree;

import java.util.LinkedList;

public class RBTree< K extends Comparable<K>, W extends Comparable<W> > 
implements DynamicSet<K,W>
{	
	
	private int count;
	private INode<K,W> root;
	private final RBNode<K,W> nil;
	
	public RBTree(K rootKey, W rootWeight) {
		nil = new NilNode<K,W>();
		root = new INode<K,W>(rootKey, rootWeight);
		root.swapColor();
		root.setParent(nil);
		root.setLeft(nil);
		root.setRight(nil);
		count = 1;
	}
	
	public String toString() { 
		LinkedList<INode<K,W>> q = new LinkedList<>();
		StringBuilder s = new StringBuilder();
		q.add(root);
		while (!q.isEmpty()) {
			INode<K,W> x = q.pop();
			s.append(x.toString()+" | ");
			try { q.push(x.left()); } catch (NilNodeException nne) { nne.getMessage(); }
			try { q.push(x.right()); } catch (NilNodeException nne) { nne.getMessage(); }
		}
		return s.toString();
	}
	
	void balance(INode<K,W> node) {
		INode<K,W> p;
		RBNode<K,W> u;
		try { p = node.parent(); } catch (NilNodeException nne) { return; };
		try { u = node.uncle(); } catch (NilNodeException nne) { u = nne.nil; }
		if (u.getColor().text().equalsIgnoreCase("red")) {
			// RED UNCLE
			u.swapColor();
			p.swapColor();
		} else { 
		// BLACK UNCLE STAGE #1
			if (node.isInside()) { rotate(node); }
		// BLACK UNCLE stage #2
			rotate(p);
		}
	}
	
	void rotate(INode<K,W> node) {
		INode<K,W> p;
		RBNode<K,W> c, g;
		String direction = "";
		// Hook parent to p
		try { p = node.parent(); } catch (NilNodeException nne) { return; }
		try { // Hook inside child to c, or nil otherwise
			if (node.isLeft()) {
				direction = "right";
				c = node.right();
			} else {
				direction = "left";
				c = node.left();
			}
		} catch (NilNodeException nne) { c = nne.nil; }
		// hook p's parent to g, or if it is nil, p is the root, so set root to node
		try { g = p.parent(); } catch (NilNodeException nne) { root = node; }
		if (direction.equalsIgnoreCase("right")) {
			p.setLeft(c);
			node.setRight(p);
			p.setParent(node);
			if (c != nil) ((INode) c).setParent(p);
		} else {
			p.setRight(c);
			node.setLeft(p);
			p.setParent(node);
			if (c != nil) ((INode) c).setParent(p);
		}
	}
	
	INode<K,W> find(K key) throws KeyNotFoundException {
		INode<K,W> t = root;
		while (t.getKey() != key) try { t = t.traverse(key); }
		catch (NilNodeException nne) { 
			System.out.println("Reached a leaf: "+nne.getMessage());
			throw new KeyNotFoundException(nne.prev, "Key not found.");
		}
		return t;
	}
	
	INode<K,W> predecessor(K key) throws KeyNotFoundException {
		INode<K,W> t = find(key);
		try { return t.rmld(); } 
		catch (NilNodeException nne) {  }
		try { return t.flar(); }
		catch (NilNodeException nne) { throw new KeyNotFoundException(t, "No predecessor"); }
	}
	
	INode<K,W> successor(K key) throws KeyNotFoundException {
		INode<K,W> t = find(key);
		try { return t.lmrd(); }
		catch (NilNodeException nne) {	}
		try { return t.fral(); }
		catch (NilNodeException nne) { throw new KeyNotFoundException(t, "No predecessor"); }
	}

	@Override
	public void add(K k, W w) {
		INode<K,W> t;
		try { t = find(k); } 
		catch (KeyNotFoundException knf) { t = knf.prev; }
		try { 
			t.addChild(k, w);
			count += 1;
		} catch (DuplicateKeyException dke) { dke.getMessage(); }
	}

	@Override
	public K remove(K k) {
		return null;
	}

	@Override
	public void modify(K k, W w) {	}

	@Override
	public K generate() {
		return null;
	}

}
package rbtree;

public abstract class RBNode<K extends Comparable<K>, W extends Comparable<W>>
{	
	static enum nodecolor { 
		RED("red"), BLACK("black");
		private nodecolor(String c) { this.s = c; }
		private String s;
		public String text() { return s; }
	}
	
	nodecolor color;
	
	nodecolor getColor() { return color; }
	
	void swapColor() { 
		switch (color) {
			case RED: 	{ color = nodecolor.BLACK; 	break; }
			case BLACK: { color = nodecolor.RED; 	break; }
		}
	}
	
	abstract K getKey();
	abstract W getWeight();
	abstract RBNode<K,W> traverse(K other) throws NilNodeException;
	abstract void addChild(K key, W weight) throws DuplicateKeyException;
}
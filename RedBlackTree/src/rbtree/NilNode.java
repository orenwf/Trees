package rbtree;

public class NilNode<K extends Comparable<K>, W extends Comparable<W>> extends RBNode<K,W>
{		
		NilNode() { color = nodecolor.BLACK; }

		@Override
		RBNode<K,W> traverse(K other) { return this; }
		
		@Override
		void addChild(K key, W weight) { }
		
		@Override
		K getKey() { return null; }

		@Override
		W getWeight() { return null; }
}

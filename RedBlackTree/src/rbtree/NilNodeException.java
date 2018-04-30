package rbtree;

public class NilNodeException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	INode prev;
	RBNode nil;

	NilNodeException(INode p, RBNode n, String arg) { 
		super(arg);
		prev = p;
		nil = n;
	}
}

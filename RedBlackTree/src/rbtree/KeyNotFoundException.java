package rbtree;

public class KeyNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public INode prev;

	public KeyNotFoundException(INode p, String arg) { 
		super(arg);
		prev = p;
	}

}

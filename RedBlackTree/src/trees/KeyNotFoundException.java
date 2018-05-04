package trees;

@SuppressWarnings("rawtypes")
public class KeyNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RBTree prev;

	public KeyNotFoundException(RBTree p, String arg) { 
		super(arg);
		prev = p;
	}

}

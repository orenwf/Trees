package trees;
import graphs.Node;

public class KeyNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Node prev;

	public KeyNotFoundException(Node p, String arg) { 
		super(arg);
		prev = p;
	}

}

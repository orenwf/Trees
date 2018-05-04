package trees;

public class DuplicateKeyException 
extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DuplicateKeyException(String key, String weight) { 
		super("Key: "+key+" already exists in tree with weight "+weight+".");
	}
}

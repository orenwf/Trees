package rbtree;

public class DuplicateKeyException 
extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String s;
	
	DuplicateKeyException(String key, String weight) { 
		s  = "Key: "+key+" already exists in tree with weight "+weight+".";
	}
}

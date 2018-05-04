package trees;

@SuppressWarnings("rawtypes")
public class Nil
extends Node
implements RedBlack, BinaryTree {

	@SuppressWarnings("unchecked")
	public Nil() { super(null, null, 0, null); }

	@Override
	public nc getColor() { return nc.BLACK; }

	@Override
	public void swapColor() { }

	@Override
	public int size() {	return 0; }

	@Override
	public Node parent() { return null; }

	@Override
	public Node left() { return null; }

	@Override
	public Node right() { return null; }
}

package trees;
import graphs.Node;

public class Nil
extends Node
implements RedBlack{

	public Nil() { super(0, null); }

	@Override
	public nc getColor() { return nc.BLACK; }

	@Override
	public void swapColor() { }

	public int size() {	return 0; }
}


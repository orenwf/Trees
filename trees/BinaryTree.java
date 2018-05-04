package trees;

import java.util.List;

public interface BinaryTree {
	
	int count();
	int height();
	BinaryTree root();
	BinaryTree order(int x);
	default BinaryTree min() { return order(0); }
	default BinaryTree max() { return order(count()-1); }
	BinaryTree predecessor(BinaryTree x);
	BinaryTree successor(BinaryTree x);
	List<BinaryTree> inOrder();
	List<BinaryTree> levelOrder();
	List<BinaryTree> preOrder();
	List<BinaryTree> postOrder();
}

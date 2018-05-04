package trees;

public interface RedBlack
{	
	enum nc { RED, BLACK; }
	
	nc getColor();
	void swapColor();
}
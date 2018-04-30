package tests;
import java.util.Scanner;

import rbtree.INode;

public class RBNodeTests {
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter a key: ");
		String key = in.next();
		System.out.println("Enter a value: ");
		String value = in.next();
		INode<String, String> x = new INode<>(key, value);
	}

}

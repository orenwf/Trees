package tests;

import java.util.Scanner;

import rbtree.RBTree;

public class RBTreeTests {
	
	public static void main(String args[]) {
		
		RBTree<String, String> x = new RBTree<>("Hello", "World");
		System.out.println(x);
		
		x.add("You", "Know");
		x.add("We'll", "see");
		
		System.out.println(x);
		
		Scanner s = new Scanner(System.in);
		
		do { 
			x.add(s.next(), s.next());
			System.out.println(x.toString());
		} while (s.hasNext());
	}
}

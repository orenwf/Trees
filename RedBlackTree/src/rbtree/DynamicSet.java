package rbtree;

public interface DynamicSet<T, S> {
	void add(T k, S w);
	T remove(T k);
	void modify(T k, S w);
	T generate();
}

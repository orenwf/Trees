package trees;

public interface DynamicSet<K, V> {
	void add(K k, V v) throws DuplicateKeyException;
	V remove(K k);
	void modify(K k, V v);
	K generate();
}

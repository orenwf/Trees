package trees;

public interface DynamicSet<K, V> {
	void add(K k, V v) throws DuplicateKeyException;
	V lookUp(K k) throws KeyNotFoundException;
	V remove(K k) throws KeyNotFoundException;
	void modify(K k, V v) throws KeyNotFoundException;
	K generate() throws KeyNotFoundException;
}

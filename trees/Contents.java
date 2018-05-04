package trees;

interface Contents<K,V> {
	
	K viewKey();
	V viewVal();
	void setKey(K k);
	void setVal(V v);

}

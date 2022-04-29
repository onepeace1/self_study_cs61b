package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    @Override
    public void clear() {
        Size=0;
        buckets=null;
    }

    @Override
    public boolean containsKey(K key) {
        int index=calc_hashcode(key);
        if(containsKey(buckets[index],key)) return true;
        return false;
    }

    public int calc_hashcode(K key) {
        int transform=key.hashCode();
        transform=(transform%buckets.length);
        transform=(transform+ buckets.length)%buckets.length;

        return transform;

    }

    public boolean containsKey(Collection<Node> t, K key) {
        for(Node data:t) {
            if(data.key==key) return true;
        }

       // if(t.contains(key)) return true;

        return false;
    }

    @Override
    public V get(K key) {
        V data=null;
        int index=calc_hashcode(key);

        data=get(buckets[index],key);
        if(data!=null) return data;

        return null;
    }

    public V get(Collection<Node> t, K key) {
        V data;
        for(Node N:t) {
            if(N.key==key) return N.value;
        }

        return null;
    }
    @Override
    public int size() {
        return Size;
    }

    @Override
    public void put(K key, V value) {
        if(containsKey(key)) return;

        if(buckets.length /Size>=maxLoad) resize();

        int code=key.hashCode();
        boolean success=buckets[code].add(createNode(key,value));

        if(success) {
            MySet.add(key);
            Size++;
        }

    }

    public void resize() {
        Collection<Node>[] newbuckets=createTable(multi* buckets.length);

        for(Collection<Node> t:buckets) {
            for(Node data:t) {
                int index=calc_hashcode(data.key);  //length change

                if(newbuckets[index].contains(data.key)) continue;
                newbuckets[index].add(data); // don't need to add key to set
            }
        }

        buckets=newbuckets;
    }

    @Override
    public Set<K> keySet() {
        return MySet;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        Set<K> t= (Set<K>) keySet();
        return t.iterator();
    }

    public class MyHashMapIterator<K>  implements Iterator<K> {
        Set<K> t= (Set<K>) keySet(); //?
        Iterator<K> iter=t.iterator();

        @Override
        public boolean hasNext() {
            return iter.hasNext();
        }

        @Override
        public K next() {
            return iter.next();
        }
    }
    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int Size;
    private double maxLoad;
    private HashSet<K> MySet;
    private final int multi=2;
    /** Constructors */
    public MyHashMap() {
        buckets=null;
        MySet=null;
        maxLoad=Size=0;

    }

    public MyHashMap(int initialSize) {
        buckets=createTable(initialSize);
        Size=initialSize;
        MySet=null;
        maxLoad=0;
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        buckets=createTable(initialSize);
        Size=initialSize;
        this.maxLoad=maxLoad;
        MySet=null;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key,value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     * @return
     */
    protected Collection<Node> createBucket() {
        Collection<Node> t=null;
        return t;
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] temp=new Collection[tableSize];
        for(Collection<Node> ele: temp) {
            ele=createBucket();
        }

        return temp;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

}

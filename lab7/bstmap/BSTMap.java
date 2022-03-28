package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V> {

    @Override
    public Iterator<K> iterator() {
        return null;
    }



    public class BSTNode  {
        // no sentinel here since this is just defining one node
        private BSTNode left;
        private BSTNode right;
        private K Key;
        private V data;

        public BSTNode() {
            left=right=null;
            Key=null;
            data=null;

        }
    }

    //private static BSTMap sentinel; //not bstnode?
    private int Size;
    private final BSTNode root;

    public BSTMap() {
        Size=0;
        root=null;


    }

    @Override
    public boolean containsKey(K key) {
        BSTNode  cur=root;
        return containsKey(cur,key);


    }

    public boolean containsKey(BSTNode cur, K key) {
        if(cur==null) return false;
        if(cur.Key.compareTo(key)<0) return containsKey(cur.left,key);
        else if(cur.Key.compareTo(key)>0) return containsKey(cur.right,key);

        return true;

    }
    @Override
    //use contains?
    //Must not+useless if returns BSTnode! if return node, since it is private can't
    public V get(K key) {
        BSTNode cur=root;
        while(cur!=null) {
            if(cur.Key.compareTo(key)<0) cur=cur.left;
            else if(cur.Key.compareTo(key)>0) cur=cur.right;
            else break;
        }

        if(cur==null) return null;

        return cur.data;
    }

    @Override
    public int size() {
        return Size;
    }

    @Override
    public void put(K key, V value) {
        BSTNode  cur=root;
        while(cur!=null) {
            if(cur.Key.compareTo(key)<0) cur=cur.left;
            else if(cur.Key.compareTo(key)>0) cur=cur.right;

        }

        cur.Key=(K) key;
        cur.data=(V) value;
        Size++;
    }

    @Override
    public Set<K> keySet() {
        return null;
    }


    @Override
    public V remove(K key) {
        //BSTNode  cur=(BSTNode )get(key); // impossible
        BSTNode cur=find(key);
        if(cur==null) return null;

        Size--;
        if(cur.left==null && cur.right==null) { cur=null; return cur.data; }
        else if(cur.left==null) {cur=null; return cur.right.data; }
        else if(cur.right==null) { cur=null; return cur.left.data; }

        BSTNode  replace=find_next(cur.left); // find largest node which is smaller than cur
        cur.Key=replace.Key;
        cur.data=replace.data;

        replace=null;

        return cur.data;
    }

    //must be private since return node which is private
    private BSTNode find(K key) {
        BSTNode cur=root;
        while(cur!=null) {
            if(cur.Key.compareTo(key)<0) cur=cur.left;
            else if(cur.Key.compareTo(key)>0) cur=cur.right;
            else break;
        }

        if(cur==null) return null;

        return cur;
    }

    private BSTNode find_next(BSTNode  cur) {
        BSTNode  replace =cur;

        while(replace.right!=null) replace=replace.right;

        return replace;
    }
    @Override
    public V remove(K key, V value) {
        return null;
    }

    ;
    @Override
    public void clear() {
        root.left=null; //Why not root=null? root gone-> child gone by garbage collector->)
        root.right=null;
        root.Key=null;
        root.data=null;
        Size=0;
    }


}

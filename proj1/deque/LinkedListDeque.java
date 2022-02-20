package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>,Iterable<T> {

    private static class TNode<T> {
        public T item;
        public TNode left;
        public TNode right;
        public TNode(T item,TNode left, TNode right) {
            item = item;
            left = left;
            right=right;
        }
    }

    private TNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel=new TNode(null,null,null);
        sentinel.left=sentinel.right=sentinel;

    }

    @Override
    /** Adds an item to the front of the deque. */
    public void addFirst(T x) {
        sentinel.right=new TNode(x,sentinel,sentinel.right); //what if sentinel=new? left argument first then error occurs?
        if(size()==0) sentinel.left=sentinel.right;
        size++;
        return;

    }

    @Override
    /** Adds an item to the end of the deque. */
    public void addLast(T x) {
        sentinel.left=new TNode(x,sentinel.left,sentinel);
        if(size()==0) sentinel.right=sentinel.left;
        size++;
    }

    /** Returns the number of items in the deque using recursion. */
    public int size() {
        return size;
    }

    /**Returns true if deque is empty, false otherwise.**/
    public boolean isEmpty() {
        if(size==0) return true;
        else return false;
    }

    /**Prints the items in the deque from first to last, separated by a space. Once all the items have been printed, print out a new line.**/
    public void printDeque() {
        TNode index=sentinel.right;
        for(int i=0;i<size;i++) {
            System.out.println(sentinel.item.toString()+" ");
        }

        System.out.println("\n");

    }

    public T removeFirst() {
        if(size==0) return null;

        T data=(T) sentinel.right.item;
        sentinel.right=sentinel.right.right;
        size--;

        if(size==0) sentinel.left=sentinel.right=sentinel;
        return data;
    }

    public T removeLast() {
        if(size==0) return null;

        T data=(T) sentinel.left.item;
        sentinel.left=sentinel.left.left;
        size--;

        if(size==0) sentinel.right=sentinel.left=sentinel;
        return data;
    }

    public T get(int index) {

        if(index>size) return null;

        TNode cur=sentinel.right;
        for(int i=0;i<index;i++) {
            cur=cur.right;
        }

        return (T) cur.item;
    }

    public T getRecursive(int index) {
        if(index>size) return null;

        return getRecursive(index,sentinel.right);
    }

    public T getRecursive(int index, TNode cur) {
        if(index==0) return (T) cur.item;

        return getRecursive(index-1,cur.right);
    }

    /**public Iterator<T> iterator() {
        if(iterator().hasNext()) return iterator().next();
    }**/

    @Override
    public Iterator<T> iterator() {
        return new LLDeque<T>(this);
    }

    public class LLDeque<T> implements Iterator<T> {
        private TNode cur;

        public LLDeque(LinkedListDeque<T> pointer) {
            cur = pointer.sentinel;
        }

        @Override
        public boolean hasNext() {
            if (cur.right != sentinel) return true;
            return false;
        }

        @Override
        public T next() {
            cur = cur.right;
            return (T) cur.item;
        }

        @Override
        public void remove() {
            return;
        }
    }

    @Override
    public boolean equals(Object o) {

        if(o==null) return false;
        if(this==o) return true;
        //if(o.getClass()!=this.getClass()) return false;<-we should check as if they are parent/child not exact class

        boolean same=true;


        if(!(o instanceof Deque<?>) || ((Deque<T>) o).size()!=size) return false; //not <T>?
        Deque<T> compared=(Deque<T>) o;
        //LinkedListDeque<T> op=(LinkedListDeque<T>) o;
        //o=(LinkedListDeque) o;
        //if(op.size()!=size) return false;
        //TNode compared=((LinkedListDeque<?>) o).sentinel.right;

        for(int i=0;i<this.size;i++) {
            if(!contains(compared.get(i))) {
                same = false;
                break;
            }
        }
        return same;
    }

    public boolean contains(T item) {
        for(int i=0;i<size;i++) {
            if(get(i)!=item) return false; //get.class=item.class
        }

        return true;
    }




}


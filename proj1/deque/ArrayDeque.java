package deque;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

//nextFirst is the index to put item when Addifrst called but think of it as moving anti-clockwise(similar to left in LL)
// nextLast is same but when addlast called but think of it as moving clockwise(similar to right in LL)
//size means how many items are stored, not the capacity
//Iffactor is increasing factor and Dfactor is decreasing factor for reSize
public class ArrayDeque<T> implements Deque<T>,Iterable<T> {
    private ArrayList<T> array;
    private int size=0;
    private int nextFirst=0;
    private int nextLast=0;
    private static int Ifactor=2;
    private static double Dfactor=0.25;

    //since this works similar to LLD, just initilaize first,last as 0 not 0/7
    public ArrayDeque() {
        array=new ArrayList<T>(8);
        nextFirst=0;
        nextLast=0;
        //nextLast=array.size()-1;
    }
    @Override
    /** Adds an item to the front of the list. */
    //if there is no item, must change both first and last
    public void addFirst(T x) {
        if(isFull()) array=reSize(Ifactor*array.size());

        array.add(nextFirst,x);
        size++;
        change_nextFirst();
        if(size==1) change_nextLast();

        return;
    }

    @Override
    //if there is no item, must change both first and last
    public void addLast(T x) {
        if(isFull()) array=reSize(Ifactor*array.size());

        array.add(nextLast,x);
        size++;
        change_nextLast();
        if(size==1) change_nextFirst();

        return;
    }

    public boolean isFull() {
        return size==array.size();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public void printDeque() {
        int start=index_of_first();

        for(int i=start;i!=nextLast;i=(i+1)%ArraySize()) {
            System.out.println(array.get(i));
        }
    }

    @Override
    public T removeFirst() {
        if(size==0) return null;

        if(size-1<Dfactor*array.size()) array=reSize((int)(Dfactor*array.size())+1);

        int index=index_of_first();
        T d=array.get(index);
        size--;
        nextFirst=index;

        return d;
    }

    @Override
    public T removeLast() {
        if(size==0) return null;

        if(size-1<Dfactor*array.size()) array=reSize((int)(Dfactor*array.size())+1);

        int index=index_of_last();
        T d=array.get(index);
        size--;
        nextLast=index;

        return d;
    }

    @Override
    //if not change to real_index, there might be error in equal or other method that uses this method
    public T get(int index) {

        if((index-nextFirst)<=Math.abs(nextLast-nextFirst)) return null;

        int real_index=(index+nextFirst+1)%array.size();
        return array.get(real_index);
    }

    @Override
    public Iterator<T> iterator() {
        return new ADeque<T>();
    }

    public class ADeque<T> implements Iterator<T> {
        //private ArrayList<T> ref;
        private int index=nextFirst;
        public ADeque() {
            index=nextFirst;
        }

        @Override
        public boolean hasNext() {
            if ((index+1)%array.size()!=nextLast) return true;
            return false;
        }

        @Override
        public T next() {
            index=(index+1)%array.size();
            return (T) get(index);
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

    //copy items on same index as before, taking to change nextFirst and nextLast off our hands
    //this approach is not possible of both decreasing and increasing
    //so just copy from 0
    public ArrayList<T> reSize(int length) {
        ArrayList<T> temp=array;
        array=new ArrayList<T>(length);
        System.arraycopy(temp,0,array,0,temp.size());
        //size=length;
        //0-1=size-1(mod size)
        nextFirst=array.size()-1;
        nextLast=size;

        return array;
    }

    public boolean contains(T item) {
        for(int i=0;i<size;i++) {
            if(get(i)!=item) return false; //get.class=item.class
        }

        return true;
    }

    //index of first item
    public int index_of_first() {
        return (nextFirst+1)%ArraySize();
    }

    //index of last item
    public int index_of_last() {
        return (nextLast-1+ArraySize())%ArraySize();
    }

    public void change_nextFirst() {
        nextFirst=(nextFirst-1+array.size())% ArraySize();
    }

    public void change_nextLast() {
        nextLast=(nextLast+1)% ArraySize();
    }

    public int ArraySize() {
        return array.size();
    }


    public static void main(String[] args) {
        ArrayDeque<Integer> myiterator=new ArrayDeque<Integer>();

        for(Integer item : myiterator) {
            System.out.println(item);
        }
    }
}

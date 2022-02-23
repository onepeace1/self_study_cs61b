package deque;

import org.junit.Test;
import static org.junit.Assert.*;

//any methods to make compiler decide T as class to make new T possible? T extend?
// why no generic method
public class DequeTest {
    private int start=0;
    private int last;
    @Test
    /**check whether add methods work porperly**/
    public void addTest() {
        ArrayDeque<Integer> test=new ArrayDeque<>();
        assertTrue("A newly initialized ADeque should be empty", test.isEmpty());

        test.addFirst(2);
        test.addFirst(1);
        test.addFirst(0);

        assertEquals(3,test.size());
        assertEquals(0,(int) test.get(0));
        assertEquals(2,(int) test.get(2));

        test=null;
        ArrayDeque<Integer> test2=new ArrayDeque<>();

        test2.addLast(0);
        test2.addLast(1);
        test2.addLast(2);
        assertEquals(3,test2.size());
        assertEquals(0,(int) test2.get(0));
        assertEquals(2,(int) test2.get(2));

        test2=null;

        ArrayDeque<Integer> test3=new ArrayDeque<>();
        for(int i=0;i<test3.ArraySize();i++) test3.addLast(i);
        assertEquals(0,(int) test3.get(0));
        assertEquals(7,(int) test3.get(7));
    }
    @Test
    /** check whether each variables(nextFirst,nextLast,size) store values correctly **/
    public void removeTest() {
        ArrayDeque<Integer> T=new ArrayDeque<>();

        for(int i=7;i>=0;i--) T.addFirst(i);

        assertEquals(0,(int) T.removeFirst());
       // assertEquals(7,(int) T.removeLast());
    }

    @Test
    public void resizeTest() {
        ArrayDeque<Integer> T=new ArrayDeque<>();
        for(int i=7;i>=0;i--) T.addFirst(i);

        T.reSize(14);
        assertEquals(8,(int) T.size());

        for(int i=0;i<8;i++) assertEquals(i,(int) T.get(i));

        T=null;

        ArrayDeque<Integer> T2=new ArrayDeque<>();
        T2.addFirst(0);
        T2.addLast(1);

        T2.reSize(3);
        assertEquals(0,(int) T2.get(0));
        assertEquals(1,(int) T2.get(1));

    }

    @Test
    public void add_reSizeTest() {
        ArrayDeque<Integer> T=new ArrayDeque<>();
        for(int i=8;i>=1;i--) T.addFirst(i);

        T.addFirst(0);
        T.addLast(9);
        assertEquals(10,(int) T.size());
        for(int i=0;i<10;i++) assertEquals(i,(int) T.get(i));
    }

    @Test
    public void remove_reSizeTest() {
        ArrayDeque<Integer> T=new ArrayDeque<>();
        for(int i=3;i>=0;i--) T.addFirst(i);

        T.removeLast();
        T.removeFirst();
        T.removeFirst();
        assertEquals(1,(int) T.size());
        assertEquals(5,(int) T.ArraySize());
        for(int i=0;i<1;i++) assertEquals(i,(int) T.get(i));
    }
}

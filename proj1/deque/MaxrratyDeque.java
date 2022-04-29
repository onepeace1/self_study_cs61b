package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    Comparator<T> c;
    public MaxArrayDeque(Comparator<T> C) {
        this.c=C;
    }

    public T max() {
        if(isEmpty()) {
            return null;
        }

        //Iterator<T> I=iterator();
        T max_item=get(0);

        for(T item:this) {
            if(c.compare(max_item,item)<0) max_item=item;
        }

        return max_item;
    }

    public T max(Comparator<T> C) {
        this.c = C;

        return max();
    }

}

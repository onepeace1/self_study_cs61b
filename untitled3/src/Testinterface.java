public class Maximizer {
    public static Comparable max(comparable[] A) {
        comparable max_index=A[0];
        for(int i=0;i<A.length;i++) {
            if(max_index.compare(A[i])>0) max_index=A[i];
        }

        return max_index;
    }

    public static void main(String[] args) {

    }
}

public int compare()
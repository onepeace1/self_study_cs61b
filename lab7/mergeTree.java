public class IntTree {
        private final int data;//for sentinel? no!
        private IntTree left=null, right=null;
        private static int MAX=100;
        public IntTree(int data, IntTree left, IntTree right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public static IntTree merge(IntTree basic,IntTree add) {
            IntTree insert=add;

            while (insert!= null) {
                if()
                mergeData(basic.left, insert.left,insert);

                else
                    mergeData(basic.left, insert.right,insert);
                insert = insert.right;
            }

            return basic;
        }

        //since right-leaning, if data
        //What about first element in add?
        private static void mergeData(IntTree basic, IntTree current,IntTree before) {

            if(basic==null) {
                basic = current;
                return;
            }
            //else if(basic.data==current.data) return;->don;t have to consider duplicate, since below codes address it
            else if(basic.data<=before.data) return;

            if(basic.data<current.data) mergeData(basic.right,current,before);
            else if(basic.data>current.data) mergeData(basic.left,current,before);

            return;


    }



    }
}

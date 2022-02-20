public class mySLList {
    public IntNode first;
    public int size;

    public mySLList() {
        first=new IntNode(0,null);
        size=0;
    }

    public mySLList(int data) {
        size=1;
        first=new IntNode(0,null);
        first=new IntNode(data,first);
    }

    public void AddFirst(int data) {
        size++;
        first.next=new IntNode(data,null);
    }

    public void AddLast(int data) {
        size++;

        if(first==null) first=new IntNode(data,first);
        //recursion x?
        IntNode last=first;
        while(last.next!=null) last=last.next;

        last.next=new IntNode(data,null);
    }


}

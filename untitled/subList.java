public class subList {
    int ele;

    public subList(int size,subList s,int ele) {
        super(size,s);
        ele=ele;
    }

    public int size_ele_ratio() {
        return super.size()/ele;
    }

}

public class MacBook implements Product{
    private int memory;
    private int color;

    public MacBook() {}

    @Override
    public Product getUserSelection() {
        int product_number;
        Product product;

        switch (product_number) {
            case 1:
                product = MacBook() M;
            case 2:
                product = iPhone I;
            case 3:
                product = AirPods A;
        }

        if(product_number)
        return product;
    }

    public void intialize() {

    }
    @Override
    public int calculateCost() {

    }
}

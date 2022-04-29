public interface Product {
    int basePrice=0;
    int totalCost=0;
    int model=0;
    int quantity=0;
    Product getUserSelection();
    int calculateCost();
}

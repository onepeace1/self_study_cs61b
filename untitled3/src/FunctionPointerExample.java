class FunctionPointerExample {
    // Define interface with the desired method signature
    private interface FunctionPointer {
        void methodSignature(int a);
    }

    // Method that has the target method signature
    public void example1(int b) {
        System.out.println("Called example1 with integer " + b);
    }

    // Method that has the target method signature
    public void example2(int v) {
        System.out.println("Called example2 with integer " + v);
    }

    // Method that has the target method signature
    public void example3(int a) {
        System.out.println("Called example3 with integer " + a);
    }

    public void testFunctionPointers() {
        // Create a "pointer" from the interface and assign
        // the method references from the examples methods
        FunctionPointer pointer1 = this::example1;
        FunctionPointer pointer2 = this::example2;

        // Call both methods using their "pointer"
        pointer1.methodSignature(3);
        pointer2.methodSignature(2);

        // Reassign "pointer" with another method reference
        pointer2 = this::example3;

        // Call methods
        pointer2.methodSignature(5);
    }

    public static void main(String[] args) {
        // Create example object
        FunctionPointerExample example = new FunctionPointerExample();

        // Call test method
        example.testFunctionPointers();
    }
}

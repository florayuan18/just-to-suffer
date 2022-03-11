public class IntByReference {
    private int value;

    // Hack: create IntByReference, swapToLowHighOrder and toString methods

    // constructor
    public IntByReference(int v) {
        value = v;
    }

    public void swapToLowHighOrder(IntByReference object2) {
        if (this.value > object2.value) {
            int temp = this.value; // creating a temporary variable to hold this.value
            this.value = object2.value; // assigning the object.value to this.value
            object2.value = temp;
        }
    }

    public String toString() {
        return "" + value; // change to a string because just value is an integer
    }

    // static method that enables me to see numbers swapped by reference (before, after)
    public static void swapper(int n0, int n1) {
        IntByReference a = new IntByReference(n0);
        IntByReference b = new IntByReference(n1);
        System.out.println("Before: " + a + " " + b);
        a.swapToLowHighOrder(b);  // conditionally build swap method to change values of a, b
        System.out.println("After: " + a + " " + b);
        System.out.println();
    }

    // static main method that provides some simple test cases
    public static void main(String[] ags) {
        IntByReference.swapper(21, 16);
        IntByReference.swapper(16, 21);
        IntByReference.swapper(16, -1);
    }
}
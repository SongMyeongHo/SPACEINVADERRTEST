package shop;

public class TESTFILE {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }

    public void testMethod() {
        System.out.println("This is a test method.");
        if(true) {
            System.out.println("Condition is true.");
            if(false) {
                System.out.println("Nested condition is true.");
            } else {
                System.out.println("Nested condition is false.");
            }
        } else {
            System.out.println("Condition is false.");
        }
    }
}
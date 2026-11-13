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
    
    public void anotherTestMethod() {
        System.out.println("Another test method.");
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 3; j++) {
                System.out.println("Nested loop iteration: " + i + ", " + j);
            }
            System.out.println("Loop iteration: " + i);
        }
    }
}
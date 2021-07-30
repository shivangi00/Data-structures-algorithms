package StacksnQueues;

public class client {

    public static int divideSolve(int a, int b) throws Exception {
        if (b == 0) {
            throw new Exception("CanNotDivideByZero");
        }
        return a / b;
    }

    public static int divide(int a, int b) {
        int ans = -1;

        try {
            ans = divideSolve(a, b);
        } catch (Exception e) {
            System.out.println("INFI");
        }

        return ans;
    }

    public static void main(String[] args) throws Exception {
        // stack st = new stack();  For static stack implementation
        
        dynamicStack st = new dynamicStack();   //for dynamic stack implementation
        for (int i = 0; i < 30; i++) {
            st.push(i + 10);
        }

        while (st.size() != 0) {
            System.out.print(st.pop() + " ");
        }

        //System.out.println(divide(100, 0));   for try catch example implementation
    }
}
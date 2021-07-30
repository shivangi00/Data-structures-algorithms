package StacksnQueues;
import java.util.Stack;
public class validStackSeq {

    public static boolean isValidStackSequence(ArrayList pushed, ArrayList popped){
        
        Stack<Integer> st = new Stack<>();
        int i = 0, n = popped.size();
        for(int ele : pushed){
            st.push(ele);

            while(st.size() != 0 && st.peek() == popped[i]){
                st.pop();
                i++;
            }
        }

        return st.size() == 0;  // i != n

    }
}

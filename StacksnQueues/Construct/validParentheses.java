package StacksnQueues;
import java.util.Stack;

public class validParentheses {
    
    public static boolean isValid(String str){
    
        if(str.length() == 0) 
            return true;
        int n = str.length();
        if(str.charAt(0) == ')' || str.charAt(0) == ']' || str.charAt(0) == '}') 
            return false;
        if(str.charAt(n - 1) == '(' || str.charAt(n - 1) == '[' || str.charAt(n - 1) == '{') 
            return false;

        Stack<Character> st = new Stack<>();

        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            if(ch == '(' || ch == '[' || ch == '{'){
                st.push(ch);
            } else{
                if(st.size() == 0){
                    return false;
                } else if(ch == ')' && st.peek() != '('){
                    return false;
                } else if(ch == ']' && st.peek() != '['){
                    return false;
                } else if(ch == '}' && st.peek() != '{'){
                    return false;
                } else{
                    st.pop();
                }
            }

        }

        return st.size() == 0;  //st.size() != 0; //means more number of opening brackets

    }
}

package StacksnQueues;
import java.util.Stack;

public class LC1249 {
//c++ code
    public static String minRemoveToMakeValid(String s)    {
        Stack<Integer> st = new Stack<>();

        int n = s.length();
        for(int i = 0; i < n; i++){
            char ch = s.charAt(i);
            if(ch == '('){
                st.push_back(i);
            } else if( ch == ')'){

                if(st.size() != 0 && s[st.back()] == '('){
                    st.pop_back(i);
                } else{
                    st.push_back();
                }
            }
        }

        String ans = "";
        int idx = 0;
        for(int i = 0; i < n; i++){
            if(idx < st.size() && st.charAt(idx) == i){
                idx++;
                continue;
            }

            ans += s.charAt(i);
        }

        return ans;
    }
}



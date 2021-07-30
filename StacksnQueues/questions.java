import java.util.Arrays;
import java.util.Stack;
import java.util.PriorityQueue;
import java.util.ArrayDeque;

public class questions{
    //N: Next, G = greater, S: smaller, L= left, R = right
    //503
    public static void NGOR(int[] arr, int[] ans){
        int n = arr.length;
        Arrays.fill(ans, n); //NSOR = (ans, -1)

        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < n; i++){
            while(st.size() != 0 && arr[st.peek()] < arr[i]){
                ans[st.peek()] = i;
                st.pop();
            }
            st.push(i);
        }
    }
    
    public static void NGOL(int[] arr, int[] ans){
        int n = arr.length;
        Arrays.fill(ans, -1); //NSOR = (ans, -1)

        Stack<Integer> st = new Stack<>();
        for(int i = n - 1; i >= 0; i--){
            while(st.size() != 0 && arr[st.peek()] < arr[i]){
                ans[st.peek()] = i;
                st.pop();
            }
            st.push(i);
        }
    }
    
    public static void NSOR(int[] arr, int[] ans){
        int n = arr.length;
        Arrays.fill(ans, n); 

        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < n; i++){
            while(st.size() != 0 && arr[st.peek()] > arr[i]){
                ans[st.pop()] = i;
            }
            st.push(i);
        }
    }
    
    public static void NSOL(int[] arr, int[] ans){
        int n = arr.length;
        Arrays.fill(ans, -1); 

        Stack<Integer> st = new Stack<>();
        for(int i = n - 1; i >= 0; i--){
            while(st.size() != 0 && arr[st.peek()] > arr[i]){
                ans[st.pop()] = i;
            }
            st.push(i);
        }
    }

    //20 same as Balanced Brackets
    public boolean isValid(String str){
    
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
    
    //42
    public static int trap(int[] height){
        int n = height.length;
        if(n == 0) return 0;
        
        int[] lHeight = new int[n];
        int[] rHeight = new int[n];
        
        lHeight[0] = height[0];
        rHeight[n - 1] = height[n - 1];
        
        for(int i = 1; i < n; i++){
            lHeight[i] = Math.max(lHeight[i - 1], height[i]);
        }
        
        for(int i = n - 2; i >= 0; --i){
            rHeight[i] = Math.max(rHeight[i + 1], height[i]);
        }
        
        int totalWater = 0;
        for(int i = 0; i < n; i++){
            totalWater += (Math.min(lHeight[i], rHeight[i])) - height[i];
        }
        
        return totalWater;
    }
    
    public static int trap_01(int[] height){

        int n = height.length;
        Stack<Integer> st = new Stack<>();
        int totalWater = 0;

        for(int i = 0; i < n; i++){
            while(st.size() != 0 && height[st.peek()] <= height[i]){
                int idx = st.peek();
                st.pop();
                if(st.size() == 0) break;

                int w = i - st.peek() - 1;
                int h = height[idx];

                totalWater += w * (Math.min(height[st.peek()], height[i]) - h);
            }

            st.push(i);
        }

        return totalWater;
    }

    public static int trap_02(int[] height){
        int n = height.length;
        int lmax = 0, rmax = 0;
        int l = 0, r = n - 1;
        int totalWater = 0;
        
        while(l < r){
            lmax = Math.max(lmax, height[l]);
            rmax = Math.max(rmax, height[r]);

            totalWater += lmax < rmax ? lmax - height[l++] : rmax - height[r--];
        }
        return totalWater;
    }

    //946

    //Approach - take two pointer on the pushed and popped arr respectively and a stack. 
    //push the values in pushed arr into the stack. Check if st.peek() = popped[idx] ? pop() and increment the popped pointer : push(pushed[idx])

    bool validStackSequences(int[] pushed, int[] popped){
        int idx = 0, n = popped.length;

        Stack<Integer> st = new Stack<>();
        
        for(int ele: pushed){
            st.push(ele);
            while(st.size() != 0 && st.peek() == popped[idx]){
                st.pop();
                idx++;
            }
        }

        return idx != n ? false : true;
    }

    //1249
    /*In this question we have to remove the parentheses which are useless and return an edited string. By using the stack DS we can push 
    only the braces and not characters. We will push the index of braces (say '(''), then check whether the top of stack brace and the 
    upcoming char in the string (say ')') make a perfect pair? If yes we will pop else we will push the brace and move ahead. In the end 
    we will be left with only those indices that are erroneous. (Eg. the stack we have is [11 12 27 29] (Remember stack cannot be traversed
    from bottom to top i.e. from 11 to 29, as it is FILO type))
    Now when we will traverse the original string, we will have to iterate from the end of string and keep adding to new string in reverse
    order but that gives poor time compexity. We can either traverse from end, keep adding in the normal order then reverse the string.
    Or we can implement stack by using another DS like StringBuilder or ArrayList in Java or <vector> in C++. 
    vector in C++ is a good option as it is easy to write and gives better time complexity. 
    In java ArrayList are not a good option because we cannot access the last element, there is no command, the alt way is 
    str.charAt(st.get(len - 1)) where len = st.size(). This is not a mindful option to go for. 
    Implementing a stack whose contents need to be accessed in bottom to top manner, the best DS to be used is Linked Lists(made from nodes) 
    or ArrayDequeue(made of arrays, thus faster but can only grow upto a limit, this is the only difference)*/
    
    public static String minRemoveToMakeValid(String s){
        ArrayDeque<Integer> st = new ArrayDeque<>();    //substitue of stack
        int n = s.length();
        for(int i = 0; i < n; i++){
            char ch = s.charAt(i);
            if(ch == '('){
                st.addFirst(i);
            } else if(ch == ')'){
                if(st.size() != 0 && s.charAt(st.getFirst()) == '(')
                    st.removeFirst();
                else
                    st.addFirst(i);
            }
        }

        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < n; i++){
            if(st.size() != 0 && st.getLast() == i){
                st.removeLast();
                continue;
            } 

            ans.append(s.charAt(i));
        }

        return ans.toString();
    }

    //735
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();
        
        for(int ele : asteroids){
            if(ele > 0){
                st.push(ele);
                continue;
            }
            
            while(st.size() != 0 && st.peek() > 0 && st.peek() < -ele){
                st.pop();
            }
            
            if( st.size() != 0 && st.peek() == -ele){
                st.pop();
            } else if(st.size() == 0 || st.peek() < 0) {
                st.push(ele);
            } else{
                
            }
        }
        
        int[] ans = new int[st.size()];
        for (int t = st.size() - 1; t >= 0; --t) {
            ans[t] = st.pop();
        }
        return ans;
    }

    //84
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] nsol = new int[n];
        int[] nsor = new int[n];
        
        NSOL(heights, nsol);
        NSOR(heights, nsor);
        
        int maxArea = 0;
        for(int i = 0; i < n; i++){
            int h = heights[i];
            int w = nsor[i] - nsol[i] - 1;
            
            maxArea = Math.max(maxArea, h * w); //maxArea = Math.ax(maxArea, (h < w) ? h * h : w * w) for maximalSquare question 221 Leetcode
        }
        
        return maxArea;
    }

    //85
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        int n = matrix.length;
        int m = matrix[0].length;
        int maxRect = 0;
        int[] height = new int[m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                char ch = matrix[i][j];
                height[j] = (ch == '1' ? height[j] + 1 : 0);
            }
            
            maxRect = Math.max(maxRect, largestRectangleArea(height));  //84 Leetcode largestRectangleArea
        }
        return maxRect;
    }

    //221 MaximalSquare Leetcode problem
    //wherever h and w are equal waha sq banega hi 
    //but if height = 2 and width = 3
    //or h = 3 and w = 4
    //then we would never be able to make a sq as per above assumption
    //thus what we do is min(h, w) and whoever is less, then its square is made i.e. min(2,3) = sq of size 2
    //min(3,4) => sq of size 3.

    //901
    Stack<int[]> st;
    int day;
    //2 size ka array instead of pair 
    public StockSpanner() {
        this.st = new Stack<>();
        this.day = 0;
        
        st.push(new int[]{-1, -1}); //{Day, Data}
    }
    
    public int next(int price) {
        while(st.peek()[0] != -1 && st.peek()[1] <= price){
            st.pop();
        }
        
        int span = day - st.peek()[0];
        st.push(new int[]{day++, price});
        
        return span;
    }

    //https://practice.geeksforgeeks.org/problems/stock-span-problem-1587115621/1
    public static int[] NGOL(int price[], int n){   
        // Your code here
        int[] ans = new int[n];
        Arrays.fill(ans, -1); //NSOR = (ans, -1)

        Stack<Integer> st = new Stack<>();
        st.push(0);
        
        for(int i = n - 1; i >= 0; i--){
            while(st.size() != 0 && price[st.peek()] < price[i]){
                
                ans[st.peek()] = i;
                st.pop();
                
            }
            
            st.push(i);
            
        }
        
        return ans;
    }

    public static int[] calculateSpan(int price[], int n){
        int[] ans = NGOL(price, n);
        for(int i = 0; i < n; i++){
            ans[i] = i - ans[i];
        }
        return ans;
    }

    //946
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int idx = 0, n = popped.length;
        Stack<Integer> st = new Stack<>();
        
        for(int ele: pushed){
            st.push(ele);
            while(st.size() != 0 && st.peek() == popped[idx]){
                st.pop();
                idx++;
            }
        }
        return (idx != n ? false : true);   //return i == n; or return st.size() == 0;
    }

    //402
    public String removeKdigits(String num, int k) {
        ArrayList<Character> st = new ArrayList<>();
        
        for(int i = 0; i < num.length(); i++){
            char ch = num.charAt(i);
            while(st.size() != 0 && st.get(st.size() - 1) > ch && k > 0){   //st.get(st.size() - 1) < ch for largest possible integer after removing k digits from num
                st.remove(st.size() - 1);
                k--;
            }
            st.add(ch);
        }
        //if after the for loop the value of k is not zero, we will remove chars till it is zero
        
        while(k-- > 0){
            st.remove(st.size() - 1);
        }
        
        StringBuilder ans = new StringBuilder();
        boolean flag = false;
        for(Character ch : st){
            if(ch == '0' && !flag){
                continue;
            } 
                flag = true;
                ans.append(ch);    
        }
        
        String res = ans.toString();
        return res.length() == 0 ? "0" : res;
    }

    //316
    public String removeDuplicateLetters(String s) {    
        int n = s.length();
        StringBuilder st = new StringBuilder();
        boolean[] visited = new boolean[26];
        int[] freq = new int[26];
        
        //get the frequency of every char 
        for(int i = 0; i < n; i++){
            freq[s.charAt(i) - 'a']++;
        }
        
        for(int i = 0; i < n; i++){
            char ch = s.charAt(i);
            freq[ch - 'a']--;
            
            if(visited[ch - 'a']) continue;
            else{
                
                while(st.length() != 0 && (st.length() - 1) < ch && freq[st.charAt(st.length() - 1) - 'a'] > 0){
                    char rch = st.charAt(st.length() - 1);
                    visited[rch - 'a'] = false;
                    st.deleteCharAt(st.length() - 1);
                }

                visited[ch - 'a'] = true;
                st.append(ch);
            }
        }
        return st.toString();   
    }

    //32


    class MinStack {

        /** initialize your data structure here. */
        Stack<Long> st = new Stack<>();
        Long min = 0;

        public MinStack() {
            
        }
        
        public void push(int val) {
            if(st.size() == 0)            {
                st.push((long) val);
                min = val;
            } else{
                if(val < min){
                    st.push(val + (val - min));
                    min = val;
                }
                else   
                    st.push((long)val);
            }
        }
        
        public void pop() {
            if(st.peek() < min){
                min = min + (min - st.peek());
                st.pop();
            }
        }
        
        public int top() {
            if(st.peek() < min)     
                return min;
            return st.peek();
        }
        
        public int getMin() {
            return min;
        }
    }

    
}







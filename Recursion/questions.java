import java.util.ArrayList;

public class questions {

    //print increasing
    public static void printIncreasing(int a, int b){
        if(a > b)
            return;
        System.out.println(a);      //pre order of recursion tree
        printIncreasing(a + 1, b);  //recursion tree building up
    }

    //print decreasing
    public static void printDecreasing(int a, int b){
        if(a > b) return;
        printDecreasing(a + 1, b); //recursion tree building up
        System.out.println(a);     //post order of recursion tree
    }

    //print increasing decreasing
    public static void IncreasingDecreasing(int a, int b){
        if(a > b) return;               //if(a == b) Sop(a); to avoid duplicacy

        System.out.println(a);          //pre order of recursion tree prints increasing
        IncreasingDecreasing(a + 1, b); //recursion tree building up
        System.out.println(a);          //post order of recursion tree prints decreasing
    }

    //print odd even
    public static void oddEven(int a, int b){
        if(a > b) return;

        if(a % 2 != 0)
            System.out.println(a);
        oddEven(a + 1, b);
        if(a % 2 == 0)
            System.out.println(b);
    }

    //print factorial
    public static int fact(int n){
        int f = fact(n - 1);
        int factorial = n * f;
        if(n == 1) {
            System.out.println(1);
        } else{
            System.out.println(factorial);
        }
        return 0;
    }

    //print power linear
    public static void power(int a, int b){

    }

    public static void powerBtr(int a, int b){

    }

    //print maximum of array
    public static int maximum(int[] arr, int idx){
        if(idx == arr.length) return (int)-1e9;
        int maxSoFar = maximum(arr, idx + 1);
        return Math.max(maxSoFar, arr[idx]);
        // if(maximum > arr[idx]){
        //     return maximum;
        // } else {
        //     return arr[idx];
        // }
    }

    //print minimum of array
    public static int minimum(int[] arr, int idx){
        if(idx == arr.length) return (int)1e9;
        int minSoFar = minimum(arr, idx + 1);
        return Math.min(minSoFar, arr[idx]);
    }

    //find data in array
    public static boolean find(int[] arr, int idx, int data){
        if(idx == arr.length) return false;
        return arr[idx] == data || find(arr, idx + 1, data);    //condition 1 OR condition 2 = true if one or both of the conditions are true;
    }

    //find occurrence of first index of data in array
    public static int firstIdx(int[] arr, int idx, int data){
        if(idx == arr.length) return -1;
        return arr[idx] == data ? idx : firstIdx(arr, idx + 1, data);
    }

    //find occurrence of last index of data in array
    public static int lastIdx(int[] arr, int idx, int data){
        if(idx == arr.length) return -1;
        int smallRes = lastIdx(arr, idx + 1, data);
        if(smallRes != -1) 
            return idx;
        return arr[idx] == data ? idx : -1;
    }

    //Understanding functionality of recursion tree
    public static int printTreePath(int n){
        if(n == 1 || n == 0){
            System.out.println("Base" + n);
            return n;
        }

        int ans = 0;

        System.out.println("Pre" + n);
        ans += printTreePath(n - 1);

        System.out.println("In" + n);
        ans += printTreePath(n - 2);

        System.out.println("Post" + n);

        return ans + 3;
    }
    
    //upar jaate waqt jis order mei data mila ussi order mei data neeche aate waqt milega
    public static int[] AllIndices(int[] arr, int idx, int data, int count){
        if(idx == arr.length) return new int[count];

        if(arr[idx] == data) count++;
        int[] a = AllIndices(arr, idx + 1, data, count); 
        
        if(arr[idx] == data)
            a[count - 1] = idx;
        return a;
    }

    //subsequence
    public static ArrayList<String> subSequence(String str, int idx){
        if(idx == str.length()){
            ArrayList<String> base = new ArrayList<>();
            return base;
        }

        ArrayList<String> recAns = subSequence(str, idx + 1);
        ArrayList<String> myAns = new ArrayList<>(recAns);
        for(String s : recAns){
            myAns.add(str.charAt(idx) + s);
        }

        return myAns;
    }

    //kpc
    public static ArrayList<String> kpc(String str){
        ArrayList<String> ans = new ArrayList<>();

        return ans;
    }

    //stair path
    
    //Maze path HDV one jump allowed
    //TOP TO BOTTOM APPROACH 
    public static ArrayList<String> mazePath_HVD(int sr, int sc, int er, int ec){
        
        if(sr == er && sc == ec){
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> myAns = new ArrayList<>();

        if(sr + 1 <= er){
            ArrayList<String> Vertical = mazePath_HVD(sr + 1, sc, er, ec);
            for(String s : Vertical){
                myAns.add("V" + s);
            }
        }

        if(sr + 1 <= er && sc + 1 <= ec){
            ArrayList<String> Diagonal = mazePath_HVD(sr + 1, sc + 1, er, ec);
            for(String s : Diagonal){
                myAns.add("D" + s);
            }
        }

        if(sc + 1 <= ec){
            ArrayList<String> Horizontal = mazePath_HVD(sr, sc + 1, er, ec);
            for(String s : Horizontal){
                myAns.add("H" + s);
            }
        }

        return myAns;
    }

    //BOTTOM TO TOP APPROACH
    //Arraylist is passed in argument so that as we make recursive calls we keep on adding the answer in the ans string list 
    //instead of creating another for loop and then adding the values to the ans string list, as in prev ques.
    public static int mazePath_HDV(int sr, int sc, int er, int ec, ArrayList<String> ans, String psf){

        //base case
        if(sr == er && sc == ec){
            ans.add(psf);
            return 1;
        }

        int count = 0;
        if(sr + 1 <= er){
            count += mazePath_HDV(sr + 1, sc, er, ec, ans, psf + "V");
        } if(sc + 1 <= ec){
            count += mazePath_HDV(sr, sc + 1, er, ec, ans, psf + "H");
        } if(sr + 1 <= er && sc + 1 <= ec){
            count += mazePath_HDV(sr + 1, sc + 1, er, ec, ans, psf + "D");
        }

        return count;
    }

    //TOP TO BOTTOM FOR MULTIPLE JUMPS
    public static ArrayList<String> mazePath_HVD_multi(int sr, int sc, int er, int ec){
        
        if(sr == er && sc == ec){
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> myAns = new ArrayList<>();

        for(int jump = 1; sr + jump <= er; jump++){
            ArrayList<String> Vertical = mazePath_HVD_multi(sr + jump, sc, er, ec);
            for(String s : Vertical){
                myAns.add("V" + jump + s);
            }
        }

        for(int jump = 1; sr + jump <= er && sc + jump <= ec; jump++){
            ArrayList<String> Diagonal = mazePath_HVD_multi(sr + jump, sc + jump, er, ec);
            for(String s : Diagonal){
                myAns.add("D" + jump + s);
            }
        }

        for(int jump = 1; sc + jump <= ec; jump++){
            ArrayList<String> Horizontal = mazePath_HVD_multi(sr, sc + jump, er, ec);
            for(String s : Horizontal){
                myAns.add("H" + jump + s);
            }
        }

        return myAns;
    }

    //BOTTOM TO TOP FOR MULTIPLE JUMPS ALLOWED
    public static int mazePath_HDV_multi(int sr, int sc, int er, int ec, ArrayList<String> ans, String psf){

        //base case
        if(sr == er && sc == ec){
            ans.add(psf);
            return 1;
        }

        int count = 0;
        for(int jump = 1; sr + jump <= er; jump++){
            count += mazePath_HDV_multi(sr + jump, sc, er, ec, ans, psf + "V" + jump);
        } 
        for(int jump = 1; sr + jump <= er && sc + jump <= ec; jump++){
            count += mazePath_HDV_multi(sr + jump, sc + jump, er, ec, ans, psf + "D" + jump);
        }
        for(int jump = 1; sc + jump <= ec; jump++){
            count += mazePath_HDV_multi(sr, sc + jump, er, ec, ans, psf + "H" + jump);
        } 

        return count;
    }

    public static int mazePath_HDV2(int sr, int sc, int er, int ec, int[][] dir, String[] dirS, ArrayList<String> ans, String psf){
        //base case
        if(sr == er && sc == ec){
            ans.add(psf);
            return 1;
        }

        int count = 0;
        for(int d = 0; d < dir.length; d++){
            int row = sr + dir[d][0];
            int col = sc + dir[d][1];

            if(row >= 0 && col >= 0 && row <= er && col <= ec){
                count += mazePath_HDV2(row, col, er, ec, dir, dirS, ans, psf + dirS[d]);
            }
        }
        return count;
    }

    //FLOOD FILL ALGO GENERIC FOR MAZE PATH TRAVERSAL PROBLEM (change dir and dirS as per constraints)
    public static int floodFill(int sr, int sc, boolean[][] vis, int[][] dir, String[] dirS, ArrayList<String> ans,
    String psf){

        int n = vis.length, m = vis[0].length;
        if(sr == n - 1 && sc == m - 1){
            ans.add(psf);
            return 1;       //signifies that one answer is found
        }
        int count = 0;
        vis[sr][sc] = true;
        for(int d = 0; d < dir.length; d++){
            int row = sr + dir[d][0];
            int col = sc + dir[d][1];

            if(row >= 0 && col >= 0 && row < n && col < m && !vis[row][col]){
                count += floodFill(row, col, vis, dir, dirS, ans, psf + dirS[d]);
            }
        }

        vis[sr][sc] = false;
        return count;
    }

    // public static void mazePath(){
    //     int sr = 0, sc = 0, er = 2, ec = 2;
    //     int[][] dir = { {1,0}, {0,1}, {1,1} };
    //     String[] dirS = {"V", "H", "D"};
    //     ArrayList<String> ans = new ArrayList<>();
    //     // System.out.println(mazePath_HVD_multi(sr, sc, er, ec));
    //     // System.out.println(mazePath_HDV2(sr, sc, er, ec, dir, dirS, ans, " "));
    //     // System.out.println(ans);
    // }

    public static void floodfill(){
        int sr = 0, sc = 0, n = 3, m = 3;
        boolean[][] vis = new boolean[n][m];
        ArrayList<String> ans = new ArrayList<>();
        int[][] dir = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
        String[] dirS = {"U", "E", "L", "S", "D", "N", "R", "W"};
        System.out.println(floodFill(sr, sc, vis, dir, dirS, ans, ""));
        System.out.println(ans);
    }   

    public static void main(String[] args){
        // mazePath();
        floodfill();
    }
}

package DP;

public class dp {
    public static void print1D(int[] arr) {
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void print2D(int[][] arr) {
        for (int[] ar : arr) {
            print1D(ar);
        }
    }

    public static int mazePath_memo(int sr, int sc, int er, int ec, int[][] dp){
        if(sr == er && sc == ec){
            return dp[sr][sc] = 1;
        }
        //Memoization loop
        if(dp[sr][sc] != 0){
            return dp[sr][sc];
        }
        int count = 0;
        //if loop prevents from going out of bounds
        if(sc + 1 <= ec){
            count += mazePath_memo(sr, sc + 1, er, ec, dp);
        }

        if(sr + 1 <= er){
            count += mazePath_memo(sr + 1, sc, er, ec, dp);
        }

        if(sr + 1 <= er && sc + 1 <= ec){
            count += mazePath_memo(sr + 1, sc + 1, er, ec, dp);
        }

        return dp[sr][sc] = count;
    }

    public static int mazePath_Dp(int SR, int SC, int er, int ec, int[][] dp){
        for(int sr = er; sr >=0; sr--){
            for(int sc = ec; sc >= 0; sc--){

                if(sr == er && sc == ec){
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                //if loop prevents from going out of bounds
                if(sc + 1 <= ec){
                    count += dp[sr][sc + 1]; //mazePath_memo(sr, sc + 1, er, ec, dp);
                }
        
                if(sr + 1 <= er){
                    count += dp[sr + 1][sc]; //mazePath_memo(sr + 1, sc, er, ec, dp);
                }
        
                if(sr + 1 <= er && sc + 1 <= ec){
                    count += dp[sr + 1][sc + 1]; //mazePath_memo(sr + 1, sc + 1, er, ec, dp);
                }
                //store the answer in dp
                dp[sr][sc] = count;
            }
        }

        return dp[SR][SC];
    }
    
    
    public static void mazePath() {
        int n = 5;
        int m = 5;
        int[][] dp = new int[n][m];
        System.out.println(mazePath_memo(0, 0, n - 1, m - 1, dp));
        // System.out.println(mazePathInfi_memo(0, 0, n - 1, m - 1, dp));
        // System.out.println(mazePathInfi_DP(0, 0, n - 1, m - 1, dp));

        print2D(dp);
    }

    public static void main(String[] args) {
        // fibo();
        mazePath();
        // boardPath();
        // climbStairs(10);
        // countFriendsPairings(10);
        // goldMine();
    }
}

import java.util.Arrays;

public class nqueen {
    //tnb = total no of boxes, tnq = total no of queens
    //bno = box no., qno = queen no.

    //to do - place the tnq in boxes. Once a box is occuppied, the boxes before that cannot be used by the left queens
    //E.g. if Q0 is placed in B2, then B0 and B1 are not available options for the next queen.
    //Similar to CoinCombination Single Coin
    public static int queenCombination(int tnb, int tnq, int bno, int qno, String ans){
        //tar == to place all queens i.e. tar = tnq
        if(qno == tnq){
            System.out.println(ans);
            return 1;
        }
        
        int count = 0;
        for(int i = bno; i < tnb; i++){
            count += queenCombination(tnb, tnq, i + 1, qno + 1, ans + "b" + i + "q" + qno);
        }
        
        return count;
    }
    //In permutation, we have to mark the boxes which are visited. We'll do that using boolean arr
    public static int queenPermutation(boolean[] tnb, int tnq, int bno, int qno, String ans){
        if(qno == tnq){
            System.out.println(ans);
            return 1;
        }

        int count = 0;

        for(int i = bno; i < tnb.length; i++){
            if(!tnb[i]){
                tnb[i] = true;
                count += queenPermutation(tnb, tnq, 0, qno + 1, ans + "b" + i + "q" + qno);
                tnb[i] = false;
            }
        }

        return count;
    }

    public static int queenCombination2D(int[][] board, int tnq, int bno, String ans){
        if(tnq == 0){
            System.out.println(ans);
            return 1;
        }
    
        int count = 0, n = board.length, m = board[0].length;

        for(int i = bno; i < n * m; i++){
            int r = i / m;  //convert a 2d matrix to a 1d array and then solving as prev questions
            int c = i % m;  
            count += queenCombination2D(board, tnq - 1, i + 1, ans + "(" + r + "," + c + ")");
        }

        return count;
    }

    public static int queenPermutation2D(boolean[][] board, int tnq, int bno, String ans){
        if(tnq == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0, n = board.length, m = board[0].length;
         for(int i = bno; i < n * m; i++){
             int r = i / m; //convert a 2d matrix to a 1d array and then solving as prev questions
             int c = i % m;
             if(!board[r][c]){
                board[r][c] = true;
                count += queenPermutation2D(board, tnq - 1, 0, ans + "(" + r + "," + c + ")");
                board[r][c] = false;
             }
         }

        return count;
    }

    //==============================N QUEENS===============================================================

    public static boolean isSafeToPlace(boolean[][] board, int row, int col){
        // int[][] dir = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}}; //for nqueen combination
        int[][] dir = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};   //for nqueens permutation
        //direction loop
        for(int d = 0; d < dir.length; d++){
            //radius loop
            for(int rad = 1; rad < board.length; rad++){   
                int r = row + rad * dir[d][0];
                int c = col + rad * dir[d][1];
                //check boundaries of board, if within boundaries execute next if statement
                if(r >= 0 && c >= 0 && r < board.length && c < board[0].length){
                    if(board[r][c]) 
                        return false;   //if board[r][c] is occuppied return false
                } else{
                    //if not within boundaries break;
                    break;
                }
            }
        }
        return true;
    }

    public static int nQueen_combination(boolean[][] board, int tnq, int idx, String ans){
        if(tnq == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0, n = board.length, m = board[0].length;

        for(int i = idx; i < n * m; i++){
            int r = i / m;
            int c = i % m;
            if(isSafeToPlace(board, r, c)){
                board[r][c] = true;
                count += nQueen_combination(board, tnq - 1, i + 1, ans + "(" + r + "," + c + ")");
                board[r][c] = false;
            }
        }
        return count;
    }

    public static int nQueen_permutation(boolean[][] board, int tnq, int idx, String ans){
        if(tnq == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0, n = board.length, m = board[0].length;

        for(int i = idx; i < n * m; i++){
            int r = i / m;
            int c = i % m;
            if(isSafeToPlace(board, r, c) && !board[r][c]){
                board[r][c] = true;
                count += nQueen_permutation(board, tnq - 1, 0, ans + "(" + r + "," + c + ")");
                board[r][c] = false;
            }
        }
        return count;
    }

    public static int nQueen_combination_Sub(boolean[][] board, int tnq, int idx, String ans){
        int count = 0, n = board.length, m = board[0].length;

        if(tnq == 0 || idx == n * m){
            if(tnq == 0){
                System.out.println(ans);
            }
            return tnq == 0 ? 1 : 0;
        }

        int r = idx / m;
        int c = idx % m;
        if(isSafeToPlace(board, r, c)){
            board[r][c] = true;
            count += nQueen_combination_Sub(board, tnq - 1, idx + 1, ans + "(" + r + "," + c + ")");
            board[r][c] = false;
        }
        count += nQueen_combination_Sub(board, tnq, idx + 1, ans);
        
        return count;
    }


    //Method 1 - creating 1D shadows of 2D board
    public static int nQueen_2Dto1D(int n, int m, int tnq, int idx, String ans){
        if(tnq == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        //Mind here: traversal is from idx to n * m thus complexity is O(n * m)
        for(int i = idx; i < n * m; i++){
            int r = i / m; 
            int c = i % m;
            if(!row[r] && !col[c] && !diag[r + c] && !aDiag[r - c + m - 1]){
                row[r] = col[c] = diag[r + c] = aDiag[r - c + m - 1] = true;
                count += nQueen_2Dto1D(n, m, tnq - 1, i + 1, ans + "(" + r + "," + c + ")");
                row[r] = col[c] = diag[r + c] = aDiag[r - c + m - 1] = false;
            }
        }
        return count;
    }

    public static int nQueens_floorRoom(int n, int m, int floor, String ans){
        if(floor == n){
            System.out.println(ans);
            return 1;
        }

        int count = 0;

        for(int rooms = 0; rooms < m; rooms++){
            int r = floor, c = rooms;
            if(!row[r] && !col[c] && !diag[r + c] && !aDiag[r - c + m - 1]){
                row[r] = col[c] = diag[r + c] = aDiag[r - c + m - 1] = true;
                count += nQueens_floorRoom(n, m, floor + 1, ans + "(" + r + "," + c + ")");
                row[r] = col[c] = diag[r + c] = aDiag[r - c + m - 1] = false;
            }
        }
        return count;
    }

    //================================================================================================

    public static void Queens(){
        int tnb = 5;
        boolean[] boxes = new boolean[tnb];
        // int[][] box = new int[tnb][tnb];
        // boolean[][] board = new boolean[tnb][tnb];
        Arrays.fill(boxes, false);
        int tnq = 3;
        String ans = "";
        // System.out.println(queenCombination(tnb, tnq, 0, 0, ans));
        System.out.println(queenPermutation(boxes, tnq, 0, 0, ans));
        // System.out.println(queenCombination2D(box, tnq, 0, ans));
        // System.out.println(queenPermutation2D(board, tnq, 0, ans));
    }

    public static void nQueens(){
        int tnq = 4;
        boolean[][] board = new boolean[tnq][tnq];
        String ans = "";

        System.out.println(nQueen_combination_Sub(board, tnq, 0, ans));
    }

    static boolean[] row;
    static boolean[] col;
    static boolean[] diag;
    static boolean[] aDiag;

    public static void nQueens2DTo1D(){
        int tnq = 4, n = 4, m = 4;
        row = new boolean[n];
        col = new boolean[m];
        diag = new boolean[n + m - 1];
        aDiag = new boolean[n + m - 1];
        nQueen_2Dto1D(n, m, tnq, 0, "");
        nQueens_floorRoom(n, m, 0, "");
    }

    public static void main(String[] args){
        nQueens2DTo1D();
    }

    //OUTPUTS
    /*  queenCombination()

        b0q0b1q1b2q2
        b0q0b1q1b3q2
        b0q0b1q1b4q2
        b0q0b2q1b3q2
        b0q0b2q1b4q2
        b0q0b3q1b4q2
        b1q0b2q1b3q2
        b1q0b2q1b4q2
        b1q0b3q1b4q2
        b2q0b3q1b4q2
        10
    */
    
}

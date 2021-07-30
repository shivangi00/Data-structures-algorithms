public class RecursionTree {

    public static int permutationInfCoins(int[] arr, int tar, String ans){
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for(int ele : arr){
            if(tar - ele >= 0){
                count += permutationInfCoins(arr, tar - ele, ans + ele);
            }
        }
        return count;
    }

    public static int combinationInfCoins(int[] arr, int tar, int idx, String ans){
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for(int i = idx; i < arr.length; i++){
            if(tar - arr[i] >= 0){
                count += combinationInfCoins(arr, tar - arr[i], i, ans + arr[i]);
            }
        }
        return count;
    }

    public static int combinationSingleCoins(int[] arr, int tar, int idx, String ans){
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = idx; i < arr.length; i++){
            if(tar - arr[i] >= 0){
                count += combinationSingleCoins(arr, tar - arr[i], i + 1, ans + arr[i]);
            }
        }
        return count;
    }
    
    public static int permutationSingleCoins(int[] arr, int tar, String ans){
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }
        int  count = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > 0 && tar - arr[i] >= 0){
                int val = arr[i];
                arr[i] = -val;      //this value should be one that is not there in the arr
                count += permutationSingleCoins(arr, tar - val, ans + val);
                arr[i] = val;
            }
        }
        return count;
    }

    //================================USING SUBSEQUENCE METHOD====================================================

    public static int combinationSingleCoins_Sub(int[] arr, int tar, int idx, String ans){
        if(tar == idx || idx == arr.length){
            if(tar == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;

        // for(int i = 0; i < arr.length; i++){
        if(tar - arr[idx] >= 0){    //coin aaya and fir next coin onwards participate karenge
                count += combinationSingleCoins_Sub(arr, tar - arr[idx], idx + 1, ans + arr[idx]);
        }
        //coin nahi aaya but single coin hai isliye next ko chance milega
        count += combinationSingleCoins_Sub(arr, tar, idx + 1, ans);
        return count;
    }

    public static int combinationInfCoins_Sub(int[] arr, int tar, int idx, String ans){
        if(tar == 0 || idx == arr.length){
            if(tar == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        
        if(tar - arr[idx] >= 0){
            count += combinationInfCoins_Sub(arr, tar - arr[idx], idx, ans + arr[idx]);
        }
        count += combinationInfCoins_Sub(arr, tar, idx + 1, ans);

        return count;
    }

    public static int permutationInfCoins_Sub(int[] arr, int tar, int idx, String ans){
        if(tar == 0 || idx == arr.length){
            if(tar == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;

        if(tar - arr[idx] >= 0){
            count += permutationInfCoins_Sub(arr, tar - arr[idx], 0, ans + arr[idx]);
        }
        count += permutationInfCoins_Sub(arr, tar, idx + 1, ans);

        return count;
    }

    public static int permutationSingleCoin_Sub(int[] arr, int tar, int idx, String ans){
        if(tar == 0 || idx == arr.length){
            if(tar == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
//2357
        if(arr[idx] > 0 && tar - arr[idx] >= 0){
            int val = arr[idx];
            arr[idx] = -val;
            count += permutationSingleCoin_Sub(arr, tar - val, 0, ans + val);
            arr[idx] = val;
        }
        count += permutationSingleCoin_Sub(arr, tar, idx + 1, ans);

        return count;
    }

    //==========================================================================================

    public static void permutationCombinations(){
        int[] arr = {2, 3, 5, 7};
        int tar = 10;
        String ans = "";
        //System.out.println(combinationInfCoins(arr, tar, 0, ans));
        //System.out.println(permutationInfCoins(arr, tar, ans));
        System.out.println(combinationSingleCoins(arr, tar, 0, ans));
    }

    public static void permutationCombinations_Sub(){
        int[] arr = {2,3,5,7};
        int tar = 10;
        String ans = "";

        System.out.println(permutationSingleCoin_Sub(arr, tar, 0, ans));
    }

    public static void main(String[] args){
        // permutationCombinations();
        permutationCombinations_Sub();
    }

    //OUTPUTS

    /*
        permutationSingleCoin_Sub()
        
        235
        253
        325
        352
        37
        523
        532
        73
        8
    */
}

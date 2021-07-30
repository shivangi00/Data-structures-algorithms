import java.util.*;

public class ArraysQuestions {

    //Segregate positive and negative numbers
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static int[] SegPosNeg(int[] arr){
        int i = 0, j = 0, pivot = 0;
        while(i < arr.length){
            if(arr[i] > pivot){
                i++;
            } else{
                swap(arr, i++, j++);    //equivalent to swap(arr, i, j); i++; j++;
            }
        }
        return arr;
    }

    //159
    public class Solution {
        /**
         * @param s: a string
         * @return: the length of the longest substring T that contains at most 2 distinct characters
         */
        public int lengthOfLongestSubstringTwoDistinct(String s) {
            // Write your code here
            if(s.length() <= 2) return s.length();
    
            int end = 0, start = 0, len = 0, dist_count = 0, n = s.length();
            int[] freq = new int[128];
            while(end < n){
                if(freq[s.charAt(end++)]++ == 0){
                    dist_count++;
                }
    
                while(dist_count > 2){
                    if(freq[s.charAt(start++)]-- == 1){
                        dist_count--;
                    }
                }
    
                len = Math.max(len, end - start);
            }
    
            return len;
        }
    }

    //Count all subarrays with atMost K diff integers
    public static int AtmostKDistinct(int[] arr, int k){
        //HashMap<Integer, Integer> freq = new HashMap<>();
        
//         while(end < n){
//             freq.put(arr[end], freq.getOrDefault(nums[end], 0) + 1);
//             end++;
            
//             while(freq.size() > k){
//                 freq.put(nums[start], freq.get(nums[start]) - 1);
//                 if(freq.get(nums[start]) == 0){
//                     freq.remove(nums[start]);
//                 }
                
//                 start++;
//             }
            
//             count += end - start;
//         }
        
//         return count;
        
        return 0;
    }

    public static int subarrayWithKDistinct(int[] arr, int k){

        return atMostDistinct(arr, k) - atMostDistinct(arr, k - 1);
    }

    //KadensAlgorithm

    //this will give smallest value as 0 but won't work for all negative arr
    public static int KadensAlgo(int[] arr){
        int gsum = 0, currsum = 0;
        for(int ele : arr){
            currsum += ele;
            if(currsum > gsum) 
                gsum = currsum;
            if(currsum <= 0)
                currsum = 0;
        }

        return gsum;
    }

    public static int KadensAlgo_Subaaray(int[] arr){
        int gSum = 0, currSum = 0, gsi = 0, gei = 0, csi = 0;
        for(int i = 0, i < arr.length; i++){
            int ele = arr[i];
            currSum += ele;
            if(currSum > gSum){
                gSum = currSum;
                gsi = csi;
                gei = i;
            }
            if(currSum <= 0){
                currSum = 0;
                csi = i + 1;
            }
        }
        return gSum;
    }

    //this will give smallest negative value if arr has all negative terms
    //but it will not give 0 as answer
    public static int KadensAlgoGeneric(int[] arr){
        int gsum = -(int)1e9, currsum = arr[0];
        for(int ele : arr){
            currsum = Math.max(ele, currsum + ele);
            gsum = Math.max(gsum, currsum);
        }
    }

    public static int kadanesAlgoGenericSubarray(int[] arr) {
        int gSum = -(int) 1e9, cSum = 0, gsi = 0, gei = 0, csi = 0;
        for (int i = 0; i < arr.length; i++) {
            int ele = arr[i];
            cSum += ele;
            if (ele >= cSum) {
                cSum = ele;
                csi = i;
            }

            if (cSum > gSum) {
                gSum = cSum;
                gsi = csi;
                gei = i;
            }
        }

        return gSum;
    }

    int mod = (int) 1e9 + 7;

    public int kadanesAlgo(int[] arr, int k) {
        int n = arr.length;
        long gsum = 0, csum = 0;

        for (int i = 0; i < k * n; i++) {
            int ele = arr[i % n];
            csum += ele;

            if (csum > gsum)
                gsum = csum;
            if (csum <= 0)
                csum = 0;
        }

        return (int) gsum % mod;
    }

    //https://practice.geeksforgeeks.org/problems/maximum-sum-rectangle2948/1
    public static int KadensAlgoForNegative(int[] arr) {
        int gSum = -(int) 1e9, cSum = 0;
        for (int ele : arr) {
            cSum = Math.max(ele, cSum + ele);
            gSum = Math.max(gSum, cSum);
        }
        return gSum;
    }
    
    int maximumSumRectangle(int R, int C, int arr[][]) {
        // code here
        int n = R, m = C, maxSum = -(int)1e9;
        int[] colPrefixSum = new int[m];        //SpaceComplexity = C
        for(int fixrow = 0; fixrow < n; fixrow++){  //runs N times
            Arrays.fill(colPrefixSum, 0);
            for(int row = fixrow; row < n; row++){  //runs N times
                for(int col = 0; col < m; col++){   //runs M times
                    colPrefixSum[col] += arr[row][col];
                }
                int sum = KadensAlgoForNegative(colPrefixSum);
                maxSum = Math.max(maxSum, sum);
            }
        }   //Time Complexity = N*N*M
        return maxSum;
    }

    //if we want to print matrix
    int maximumSumRectangle_02(int R, int C, int arr[][]) {
        // code here
        int n = R, m = C, maxSum = -(int)1e9;
        int r1 = 0, c1 = 0, r2 = 0, c2 = 0;
        int[] colPrefixSum = new int[m];        //SpaceComplexity = C
        for(int fixrow = 0; fixrow < n; fixrow++){  //runs N times
            Arrays.fill(colPrefixSum, 0);
            for(int row = fixrow; row < n; row++){  //runs N times
                for(int col = 0; col < m; col++){   //runs M times
                    colPrefixSum[col] += arr[row][col];
                }
                int res[] = KadensAlgoGenericSubarray(colPrefixSum);
                if(res[0] >= maxSum){
                    r1 = fixRow;
                    c1 = res[1];
                    r2 = row;
                    c2 = res[2];
                }
            }
        }   //Time Complexity = N*N*M

        for(int i = r1; i <= r2; i++){
            for(int j = c1; j <= c2; j++){
                System.out.println(arr[i][j] + " ");
            }
            System.out.pritnln("");
        }
    }
}

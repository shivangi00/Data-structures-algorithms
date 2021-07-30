import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

public class questions {

    //215
    public int findKthLargest(int[] nums, int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>(); //by default priority queue in java is min type
        for(int ele : nums){
            pq.add(ele);
            if(pq.size() > k)
                pq.remove();
        }
        return pq.peek();
    }
    
    public int findKthSmallest(int[] nums, int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) ->{
            return b - a;
        }); //by default min
        for(int ele : nums){
            pq.add(ele);
            if(pq.size() > k)
                pq.remove();
        }
        return pq.peek();
    }

    //703

    //349
    public static int[] intersection(int[] nums1, int[] nums2){
        HashSet<Integer> set = new HashSet<>();
        for(int ele : nums1) set.add(ele);

        ArrayList<Integer> ans = new ArrayList<>();
        for(int ele : nums2){
            if(set.contains(ele)){
                ans.add(ele);
                set.remove(ele);
            }
        }

        int[] res = new int[ans.size()];
        int i = 0;
        for(int ele : ans) res[i++] = ele;

        return res;
    }

    //347
    public int[] topKFrequent(int[] nums, int k){
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int ele : nums) map.put(ele, map.getOrDefault(ele, 0) + 1);

        //{val, freg}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->{
            return a[1] - b[1];
        });

        for(Integer key : map.keySet()){
            pq.add(new int[] {key, map.get(key)});
            if(pq.size() > k){
                pq.remove();
            }
        }

        int[] ans = new int[pq.size()];
        int i = 0;
        while(pq.size() != 0){
            int[] p = pq.remove();
            int val = p[0];
            int freq = p[1];

            ans[i++] = val;
        }

        return ans;
    }

    //128
    public static int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int ele : nums) set.add(ele);
        
        int len = 0;
        for(int ele : nums){
            if(!set.contains(ele)) continue;
            
            int ple = ele - 1, pre = ele + 1;
            set.remove(ele);
            
            while(set.contains(ple)) set.remove(ple--);
            while(set.contains(pre)) set.remove(pre++);
            
            len = Math.max(len, pre - ple - 1);
        }
        
        return len;
    }

    //973
    public int[][] kClosest(int[][] points, int k){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) ->{
            int d1 = a[0] * a[0] + a[1] * a[1]; //x1^2 + y1^2
            int d2 = b[0] * b[0] + b[1] * b[1]; //x1^2 + y1^2

            return d2 - d1;
        });

        for(int[] p : points){
            pq.add(new int[]{p[0], p[1]});
            if(pq.size() > k) pq.remove();
        }

        int[][] ans = new int[k][];
        int i = 0;
        while(pq.size() != 0){
            int[] p = pq.remove();
            ans[i++] = p;
        }

        return ans;
    }

    //380
    class RandomizedSet {
        HashMap<Integer, Integer> map;
        ArrayList<Integer> list;
        Random rand;

        /** Initialize your data structure here. */
        public RandomizedSet() {
            map = new HashMap<>(1000);
            list = new ArrayList<>();
            rand = new Random();
        }
        
        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if(map.containsKey(val)){
                return false;
            } else{
                list.add(val);
                map.put(val, list.size() - 1);
                return true;
            }
        }
        
        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if(!map.containsKey(val)){
                return false;
            } else{
                int idx = map.get(val);
                int lidx = list.size() - 1;
                int lval = list.get(lidx);

                list.set(idx, lval);
                map.put(lval, idx);
                
                map.remove(val);
                list.remove(lidx);
                
                return true;
            }
        }
        
        /** Get a random element from the set. */
        public int getRandom() {
            int n = rand.nextInt(list.size());
            return list.get(n);
        }
    }

    class FreqStack {

        HashMap<Integer, Integer> freq;
        ArrayList<Stack<Integer>> freqMap;
        int maxFreq = 0;

        public FreqStack() {
            maxFreq = 0;
            freq = new HashMap<>();
            freqMap = new ArrayList<>();
            
            freqMap.add(new Stack<>());
        }
        
        public void push(int val) {
            freq.put(val, freq.getOrDefault(val, 0) + 1);
            maxFreq = Math.max(maxFreq, freq.get(val));
            
            if(freqMap.size() == maxFreq) freqMap.add(new Stack<>());
            freqMap.get(freq.get(val)).add(val);
        }
        
        public int pop() {
            int rv = freqMap.get(maxFreq).pop();
            if(freqMap.get(maxFreq).size() == 0){
                freqMap.remove(maxFreq --);
            }

            freq.put(rv, freq.get(rv) - 1);
            if(freq.get(rv) == 0){
                freq.remove(rv);
            }

            return rv;
        }
    }
    
    //Method 2 using Priority Queue
    class FreqStack {

        class pair implements Comparable<pair>{
            int val = 0;
            int freq = 0;
            int idx = 0;

            pair(int val, int freq, int idx){
                this.val = val;
                this.freq = freq;
                this.idx = idx;
            }

            public int compareTo(pair o){
                if(this.freq == o.freq){
                    return o.idx - this.idx;    //other - this, for max PQ
                } 
                return o.freq - this.freq;
            }
        }

        private HashMap<Integer, Integer> freqMap;
        private PriorityQueue<pair> pq;
        private int idx = 0;

        public FreqStack() {
            freqMap = new HashMap<>();
            pq = new PriorityQueue<>();
            this.idx = 0;
        }
        
        public void push(int val) {
            map.put(val, freqMap.getOrDefault(val, 0) + 1);
            pq.add(new pair(val, freqMap.get(val), idx++));
        }
        
        public int pop() {
            pair p = pq.remove();
            map.put(p.val, freqMap.get(p.val) - 1);
            if(map.get(p.val)) freqMap.get(p.val){
                freqMap.remove(p.val);
            }
            return p.val;
        }
    }
    

    /**
     * Your FreqStack object will be instantiated and called as such:
     * FreqStack obj = new FreqStack();
     * obj.push(val);
     * int param_2 = obj.pop();
     */


     //295
     class MedianFinder {

        /** initialize your data structure here. */
        private PriorityQueue<Integer> maxPQ = new PriorityQueue<>(->{
            return b - a;
        });
        private PriorityQueue<Integer> minPQ = new PriorityQueue<>();

        public MedianFinder() {
               
        }
        
        public void addNum(int num) {
            if(maxPQ.size() == 0 || num <= maxPQ.peek()){
                maxPQ.add(num);
            } else{
                minPQ.add(num);
            }

            if(maxPQ.size() - minPQ.size() == -1) maxPQ.add(minPQ.remove());
            if(maxPQ.size() - minPQ.size() == 2) minPQ.add(maxPQ.remove());
        }
        
        public double findMedian() {
            if(maxPQ.size() == minPQ.size()){
                return ((maxPQ.peek() + minPQ.peek()) / 2.0);
            } else{
                return maxPQ.peek() * 1.0;
            }
        }
    }

    //632
    class Solution {
        public int[] smallestRange(List<List<Integer>> nums) {
            int n = nums.size();

            //{r,c}
            PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
                int r1 = a[0], c1 = a[1];
                int r2 = b[0], c2 = b[1];

                return nums.get(r1).get(c1) - nums.get(r2).get(c2);
            });

            int maxVal = -(int)1e9;
            for(int i = 0; i < n; i++){
                pq.add(new int[]{i, 0});
                maxVal = Math.max(maxVal, nums.get(i).get(0));
            }

            int range = (int)1e9;
            int sp = -1;
            int ep = -1;

            while(pq.size() == n){
                int[] re = pq.remove();
                int r = re[0], c = re[1], val = nums.get(r).get(c);
                if(maxVal - val < range){
                    range = maxVal - val;
                    sp =val;
                    ep = maxVal;
                }

                c++;
                if(c < nums.get(r).size()){
                    pq.add(new int[] {r,c});
                    maxVal = Math.max(maxVal, nums.get(r).get(c));
                }
            }

            return new int[]{sp,ep};
        }
    }
}

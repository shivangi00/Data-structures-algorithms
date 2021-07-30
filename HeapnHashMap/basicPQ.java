import java.util.PriorityQueue;
public class basicPQ{
    public static void test1_minPQ(int[] arr){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int ele : arr){
            pq.add(ele);
        }

        while(pq.size() != 0){
            System.out.println(pq.poll());
        }
    }
    
    public static void test1_maxPQ(int[] arr){
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b ->{   //a = this, b = other, think about this wrt other
            return a - b;   //this - other, default behaviour
            //return b - a; //other - this, reverse of default behaviour
        }); //Max PQ
        for(int ele : arr){
            pq.add(ele);
        }

        while(pq.size() != 0){
            System.out.println(pq.poll());
        }
    }

    public class pair{
        int a = 0, b = 0;

        pair(int a, int b){
            this.a = a;
            this.b = b;
        }
    }

    public static void test3(int[][] arr){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b ->{   //a = this, b = other, think about this wrt other
            //return a - b;   //this - other, default behaviour
            return b[0] - a[0]; //other - this, reverse of default behaviour
        }); //Max PQ
        for(int[] a : arr){
            pq.add(a);
        }

        while(pq.size() != 0){
            int[] a = pq.poll();
            int i = a[0];
            int j = a[1];
            System.out.println("(" + i + "," + j + ")");
        }
    }

    public static class pair implements Comparable{
        int i = 0, j = 0;

        pair(int i, int j){
            this.i = i;
            this.j = j;
        }

        public int compareTo(pair o){
            return o.i - this.i;
        }
    }

    public static void test4(int[][] arr){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b ->{   //a = this, b = other, think about this wrt other
            //return a - b;   //this - other, default behaviour
            return a.i - b.i; //other - this, reverse of default behaviour
        }); //Max PQ

        for(int[] a : arr){
            pq.add(new pair(a[0], a[1]));
        }

        while(pq.size() != 0){
            pair p = pq.poll();
            int i = p.i;
            int j = p.j;
            System.out.println("(" + i + "," + j + ")");
        }
    }

    public static void main(String[] args){
        //int[] arr = {2, 5, 1, -1, 0, -4, -6, 3, 6, 9, 9, 40};
        //test1_minPQ(arr);
        
        int[][] arr = {{2, 5}, {1, -1}, {0, -4}, {-6, 3}, {6, 9}. {9, 40}};
        test3(arr);
    }
}
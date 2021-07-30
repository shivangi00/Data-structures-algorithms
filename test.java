public class test {
    public static int[] base2To6Hire2020(boolean[] base2) {
        //base2 to decimal
        int val, p = 1, n = base2.length;
        int digit = 0;
        for(int i = 0; i < n; i++){
            if(base2[i] = false)
                val = 0;
            else
                val = 1;
                
            digit += val * p;
            p = p * 2;
        }
        
        //decimal to base6
        
        int value, power = 1;
        int[]arr = new int[n + 1];
        for(int i = 0; i < arr.length; i++){
            if(digit == 0)
                break;
            value = digit % 6;
            arr[i] = value * power;
            power = power * 10;
            digit = digit / 6;
        }
        return arr;
    }

    public static void base2To6Hire2020(){
        boolean base2[] = {false, true, true, true, false, false};
        System.out.println(base2To6Hire2020(base2));
    }

    public static void main(String[] args){
        base2To6Hire2020();
    }    
}

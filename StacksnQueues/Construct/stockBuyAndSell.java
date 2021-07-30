public class stockBuyAndSell {
    //121

    public static int maxProfit(int[] prices) {
        int dpi10 = 0, dpi11 = -(int)1e9;
        
        for(int price : prices){
            dpi10 = Math.max(dpi10, dpi11 + price);
            dpi11 = Math.max(dpi11, 0 - price);
        }
        
        return dpi10;
    }

    //123
    public static int maxProfit(int[] prices) {
        int dpi10 = 0, dpi11 = -(int)1e9;
        
        for(int price : prices){
            dpi10 = Math.max(dpi10, dpi11 + price);
            dpi11 = Math.max(dpi11, 0 - price);

            dpi20 = Math.max(dpi20, dpi21 + price);
            dpi21 = Math.max(dpi21, dpi10 - price);
        }
        
        return dpi20;
    }

    //122

    public static int maxProfit(int[] prices) {
        int dpik0 = 0, dpik1 = -(int)1e9;
        
        for(int price : prices){
            int dpik_10 = dpik0;    //max profit generated by k - 1 times transaction.
            dpik0 = Math.max(dpik0, dpik1 + price);
            dpik1 = Math.max(dpik1, dpik_10 - price);
        }
        
        return dpik0;
    }

    
}
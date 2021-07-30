import java.util.HashMap;

public class basicHM {
    public static void test1()    {
        HashMap<String, Integer> map = new HashMap<>();
        //Order doesn't matter
        map.put("USA", 1000);
        map.put("IND", 10000);
        map.put("NEP", 90);
        map.put("usa", 9000);
        map.put("USA", 9990);   //will update the prev value not put again
        System.out.println(map.get("Usa")); //returns null since Usa doesn't exist


        System.out.println(map);
        //keySet -> get arrays of keys
        for(String s : map.keySet()){
            //map.get(s) -> gives values at a particular key
            System.out.println(s + "->" + map.get(s));
        }
    }

    //Frequency map of given string
    public static void test2(String str){
        HashMap<Character, Integer> map = new HashMap<>();
        /*for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            if(!map.containsKey(ch)){
                map.put(ch, 1);
            } else{
                map.put(ch, map.get(ch) + 1);
            }
        }*/

        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
    }

    

    public static void main(String[] args){
        test1();
    }
}
